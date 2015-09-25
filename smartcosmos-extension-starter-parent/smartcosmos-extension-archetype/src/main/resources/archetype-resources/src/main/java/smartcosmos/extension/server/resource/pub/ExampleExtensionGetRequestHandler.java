package ${package}.smartcosmos.extension.server.resource.pub;

import com.fasterxml.jackson.core.JsonProcessingException;
import ${package}.smartcosmos.extension.server.dao.IExampleExtensionDAO;
import ${package}.smartcosmos.extension.server.jpa.IExampleEntity;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.resource.AbstractRequestHandler;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

class ExampleExtensionGetRequestHandler extends AbstractRequestHandler<JSONObject>
{

    private static final Logger LOG = LoggerFactory.getLogger(ExampleExtensionGetRequestHandler.class);

    IExampleExtensionDAO exampleExtensionDAO;

    ExampleExtensionGetRequestHandler(IContext context)
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
            IExampleExtensionDAO exampleExtensionDAO =
                    context.getDAOFactory().lookup(IExampleExtensionDAO.class.getName(), IExampleExtensionDAO.class);

            if (inputValue.has("firstString") && inputValue.has("secondString"))
            {
                Collection<IExampleEntity> results =
                        exampleExtensionDAO.findByBothStrings(inputValue.getString("firstString"),
                                                              inputValue.getString("secondString"));
                responseJson = JsonUtil.toJson(results);

            } else if (inputValue.has("firstString"))
            {
                Collection<IExampleEntity> results =
                        exampleExtensionDAO.findByFirstString(inputValue.getString("firstString"));
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
