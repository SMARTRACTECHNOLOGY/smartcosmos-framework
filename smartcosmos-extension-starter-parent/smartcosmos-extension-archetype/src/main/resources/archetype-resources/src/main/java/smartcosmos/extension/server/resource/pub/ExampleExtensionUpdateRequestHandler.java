package ${package}.smartcosmos.extension.server.resource.pub;

import com.fasterxml.jackson.core.JsonProcessingException;
import ${package}.smartcosmos.extension.server.dao.IExampleExtensionDAO;
import ${package}.smartcosmos.extension.server.jpa.IExampleEntity;
import ${package}.smartcosmos.extension.server.jpa.impl.ExampleEntity;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.resource.AbstractRequestHandler;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static net.smartcosmos.Field.HTTP_HEADER_EVENT;

class ExampleExtensionUpdateRequestHandler extends AbstractRequestHandler<JSONObject>
{

    private static final Logger LOG = LoggerFactory.getLogger(ExampleExtensionUpdateRequestHandler.class);

    protected IUser exchangeForActual(IAuthenticatedUser authenticatedUser)
    {
        return context.getDAOFactory().getUserDAO().lookupByUrn(authenticatedUser.getUrn());
    }


    private String verificationType = null;

    ExampleExtensionUpdateRequestHandler(IContext context)
    {
        super(context);
    }

    @Override
    public Response handle(JSONObject inputValue, ViewType view, IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        Response response = null;

        if (!inputValue.has("firstString") || !inputValue.has("secondString"))
        {
            // bad input response generated here
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();

        }

        IExampleExtensionDAO exampleExtensionDAO =
                context.getDAOFactory().lookup(IExampleExtensionDAO.class.getName(), IExampleExtensionDAO.class);

        IExampleEntity entity = new ExampleEntity();
        entity.setFirstString(inputValue.getString("firstString"));
        entity.setSecondString(inputValue.getString("secondString"));

        if (exampleExtensionDAO.findByBothStrings(entity.getFirstString(), entity.getSecondString()).isEmpty())
        {
            exampleExtensionDAO.upsert(entity);

        } else
        {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(ResponseEntity.toJson(Result.ERR_MISSING_FIELD))
                    .build();
        }
        return Response
                .ok().type(MediaType.APPLICATION_JSON_TYPE)
                .header(HTTP_HEADER_EVENT, "Two Strings Inserted")
                .entity(inputValue.toString(3))
                .build();
    }
}
