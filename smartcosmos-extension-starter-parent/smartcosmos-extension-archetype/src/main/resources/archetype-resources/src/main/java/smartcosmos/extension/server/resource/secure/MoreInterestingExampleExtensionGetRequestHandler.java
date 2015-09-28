package ${package}.smartcosmos.extension.server.resource.secure;

import com.fasterxml.jackson.core.JsonProcessingException;
import ${package}.smartcosmos.extension.server.dao.IMoreInterestingExampleExtensionDAO;
import ${package}.smartcosmos.extension.server.jpa.IMoreInterestingExampleEntity;
import net.smartcosmos.Field;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.IObjectDAO;
import net.smartcosmos.platform.resource.AbstractRequestHandler;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

class MoreInterestingExampleExtensionGetRequestHandler extends AbstractRequestHandler<JSONObject>
{

    private static final Logger LOG = LoggerFactory.getLogger(MoreInterestingExampleExtensionGetRequestHandler.class);

    IMoreInterestingExampleExtensionDAO exampleExtensionDAO;

    MoreInterestingExampleExtensionGetRequestHandler(IContext context)
    {
        super(context);
    }

    protected IUser exchangeForActual(IAuthenticatedUser authenticatedUser)
    {
        return context.getDAOFactory().getUserDAO().lookupByUrn(authenticatedUser.getUrn());
    }

    @Override
    public Response handle(JSONObject inputValue, ViewType view, IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        Response response = null;
        String responseJson = null;
        try
        {
            IObjectDAO objectDAO = context.getDAOFactory().getObjectDAO();
            IMoreInterestingExampleExtensionDAO exampleExtensionDAO =
                    context.getDAOFactory().lookup(IMoreInterestingExampleExtensionDAO.class.getName(),
                                                   IMoreInterestingExampleExtensionDAO.class);

            if (!inputValue.has("referenceUrn") || !inputValue.has("entityReferenceType"))
            {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .entity(ResponseEntity.toJson(Result.ERR_MISSING_FIELD, Field.REFERENCE_URN_FIELD))
                        .build();
            }
            EntityReferenceType entityReferenceType =
                    EntityReferenceType.valueOf(inputValue.getString("entityRerferenceType"));
            String referenceUrn = objectDAO.getSystemUrnFromObjectUrn(inputValue.getString("referenceUrn"),
                                                                      entityReferenceType,
                                                                      authenticatedUser.getAccount());

            if (inputValue.has("firstString") && inputValue.has("secondString"))
            {
                Collection<IMoreInterestingExampleEntity> results =
                        exampleExtensionDAO.findByBothStrings(inputValue.getString("firstString"),
                                                              inputValue.getString("secondString"),
                                                              referenceUrn,
                                                              entityReferenceType,
                                                              authenticatedUser.getAccount());
                responseJson = JsonUtil.toJson(results);

            } else if (inputValue.has("firstString"))
            {
                Collection<IMoreInterestingExampleEntity> results =
                        exampleExtensionDAO.findByFirstString(inputValue.getString("firstString"),
                                                              referenceUrn,
                                                              entityReferenceType,
                                                              authenticatedUser.getAccount());
                responseJson = JsonUtil.toJson(results);

            } else
            {
                throw new JSONException("required input firstString not found in body");
            }
            response = Response.ok().type(MediaType.APPLICATION_JSON_TYPE).entity(responseJson).build();

        } catch (JSONException je)
        {
            // bad input response generated here
            response = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).build();

        }
        return response;
    }
}
