package ${package}.smartcosmos.extension.server.resource.secure;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.annotation.EndpointMethodControl;
import net.smartcosmos.platform.pojo.authentication.AuthenticatedUser;
import net.smartcosmos.platform.resource.AbstractPlatformResource;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/extension/moreInterestingExample")
public class MoreInterestingExampleExtensionResource extends AbstractPlatformResource
{


    public MoreInterestingExampleExtensionResource(IContext context)
    {
        super(context);
        this.putRequestHandler = new MoreInterestingExampleExtensionPutRequestHandler(context);
        this.getRequestHandler = new MoreInterestingExampleExtensionGetRequestHandler(context);
        this.updateRequestHandler = new MoreInterestingExampleExtensionUpdateRequestHandler(context);
    }

    MoreInterestingExampleExtensionPutRequestHandler putRequestHandler;
    MoreInterestingExampleExtensionUpdateRequestHandler updateRequestHandler;
    MoreInterestingExampleExtensionGetRequestHandler getRequestHandler;

    @PUT
    @Path("/moreInterestingPutTwoStrings")
    @Timed
    @EndpointMethodControl(key = "extension.moreInterestingExample.moreInterestingPutTwoStrings")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response putTwoStrings(@Auth AuthenticatedUser authenticatedUser,
                                  @QueryParam("view") ViewType view,
                                  String json)
            throws JSONException
    {
        JSONObject body = new JSONObject(json);
        return dispatchRequest(body, view, putRequestHandler, authenticatedUser);
    }

    @GET
    @Path("/moreInterestingFindByFirstString")
    @Timed
    @EndpointMethodControl(key = "extension.moreInterestingExample.moreInterestingFindByFirstString")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response findByFirstString(@Auth AuthenticatedUser authenticatedUser,
                                      @QueryParam("view") ViewType view,
                                      @QueryParam("firstString") String firstString)
            throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstString", firstString);
        return dispatchRequest(jsonObject, view, getRequestHandler, authenticatedUser);
    }

    @GET
    @Path("/moreInterestingFindByBothStrings")
    @Timed
    @EndpointMethodControl(key = "extension.moreInterestingExample.moreInterestingFindByBothStrings")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response findBySecondString(@Auth AuthenticatedUser authenticatedUser,
                                       @QueryParam("view") ViewType view,
                                       @QueryParam("firstString") String firstString,
                                       @QueryParam("secondString") String secondString)
            throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstString", firstString);
        jsonObject.put("secondString", secondString);
        return dispatchRequest(jsonObject, view, getRequestHandler, authenticatedUser);
    }

    @PUT
    @Path("/moreInterestingUpdateAll")
    @Timed
    @EndpointMethodControl(key = "extension.moreInterestingExample.moreInterestingUpdateAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response updateAll(@Auth AuthenticatedUser authenticatedUser,
                              @QueryParam("view") ViewType view,
                              String json)
            throws JSONException
    {
        JSONObject body = new JSONObject(json);
        return dispatchRequest(body, view, updateRequestHandler, authenticatedUser);
    }

}
