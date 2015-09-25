package ${package}.smartcosmos.extension.server.resource.secure;

import com.fasterxml.jackson.core.JsonProcessingException;
import ${package}.smartcosmos.extension.server.dao.IMoreInterestingExampleExtensionDAO;
import ${package}.smartcosmos.extension.server.jpa.IMoreInterestingExampleEntity;
import ${package}.smartcosmos.extension.server.jpa.impl.MoreInterestingExampleEntity;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.IObjectDAO;
import net.smartcosmos.platform.api.service.IEventService;
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

class MoreInterestingExampleExtensionPutRequestHandler extends AbstractRequestHandler<JSONObject>
{

    private static final Logger LOG = LoggerFactory.getLogger(MoreInterestingExampleExtensionPutRequestHandler.class);

    protected IUser exchangeForActual(IAuthenticatedUser authenticatedUser)
    {
        return context.getDAOFactory().getUserDAO().lookupByUrn(authenticatedUser.getUrn());
    }

    MoreInterestingExampleExtensionPutRequestHandler(IContext context)
    {
        super(context);
    }

    @Override
    public Response handle(JSONObject inputValue, ViewType view, IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        IEventService eventSink = context.getServiceFactory().getEventService(authenticatedUser.getAccount());

        if (!inputValue.has("firstString") || !inputValue.has("secondString")
            || !inputValue.has("referenceUrn") || !inputValue.has("entityReferenceType"))
        {
            // log it
            eventSink.recordEvent(EventType.ExtensionElementInsertOrUpdateFailure, authenticatedUser.getAccount(),
                                  authenticatedUser, inputValue);

            // bad input response generated here
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(ResponseEntity.toJson(Result.ERR_MISSING_FIELD,
                                                  "firstString, secondString, referenceUrn or entityReferenceType"))
                    .build();
        }

        IObjectDAO objectDAO = context.getDAOFactory().getObjectDAO();
        IMoreInterestingExampleExtensionDAO exampleExtensionDAO =
                context.getDAOFactory().lookup(IMoreInterestingExampleExtensionDAO.class.getName(),
                                               IMoreInterestingExampleExtensionDAO.class);

        EntityReferenceType entityReferenceType =
                EntityReferenceType.valueOf(inputValue.getString("entityReferenceType"));
        String referenceUrn = objectDAO.getSystemUrnFromObjectUrn(inputValue.getString("referenceUrn"),
                                                                  authenticatedUser.getAccount());

        if (exampleExtensionDAO.findByBothStrings(inputValue.getString("firstString"),
                                                  inputValue.getString("secondString"),
                                                  referenceUrn,
                                                  entityReferenceType,
                                                  authenticatedUser.getAccount())
                               .isEmpty())
        {
            IMoreInterestingExampleEntity entity = new MoreInterestingExampleEntity();
            entity.setFirstString(inputValue.getString("firstString"));
            entity.setSecondString(inputValue.getString("secondString"));
            entity.setReferenceUrn(referenceUrn);
            entity.setEntityReferenceType(entityReferenceType);
            entity.setAccount(authenticatedUser.getAccount());
            exampleExtensionDAO.insert(entity);

            // log it
            eventSink.recordEvent(EventType.ExtensionElementCreated, authenticatedUser.getAccount(), authenticatedUser,
                                  inputValue);

            return Response
                    .status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .header(HTTP_HEADER_EVENT, "Two More Interesting Strings Inserted")
                    .entity(inputValue.toString(3))
                    .build();
        } else
        {
            // log it
            eventSink.recordEvent(EventType.ExtensionElementUpdated, authenticatedUser.getAccount(), authenticatedUser,
                                  inputValue);

            return Response
                    .ok()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .header(HTTP_HEADER_EVENT, "Two More Interesting Strings Inserted")
                    .entity(inputValue.toString(3))
                    .build();
        }
    }
}
