package ${package}.smartcosmos.extension.server.resource.pub;

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


@Path("/extension/example")
public class ExampleExtensionResource extends AbstractPlatformResource
{


    public ExampleExtensionResource(IContext context)
    {
        super(context);
        this.getRequestHandler = new ExampleExtensionGetRequestHandler(context);
        this.putRequestHandler = new ExampleExtensionPutRequestHandler(context);
        this.updateRequestHandler = new ExampleExtensionUpdateRequestHandler(context);
    }

    ExampleExtensionPutRequestHandler putRequestHandler;
    ExampleExtensionGetRequestHandler getRequestHandler;
    ExampleExtensionUpdateRequestHandler updateRequestHandler;

    @PUT
    @Path("/putTwoStrings")
    @Timed
    @EndpointMethodControl(key = "extension.example.putTwoStrings")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response putTwoStrings(@Auth (required = false) AuthenticatedUser authenticatedUser,
                                  @QueryParam("view") ViewType view,
                                  String json)
            throws JSONException
    {
        JSONObject body = new JSONObject(json);
        return dispatchRequest(body, view, putRequestHandler, authenticatedUser);
    }

    @GET
    @Path("/findByFirstString")
    @Timed
    @EndpointMethodControl(key = "extension.example.findByFirstString")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response findByFirstString(@Auth (required = false) AuthenticatedUser authenticatedUser,
                                      @QueryParam("view") ViewType view,
                                      @QueryParam("firstString") String firstString)
            throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstString", firstString);
        return dispatchRequest(jsonObject, view, getRequestHandler, authenticatedUser);
    }

    @GET
    @Path("/findByBothStrings")
    @Timed
    @EndpointMethodControl(key = "extension.example.findByBothStrings")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response findBySecondString(@Auth (required = false) AuthenticatedUser authenticatedUser,
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
    @Path("/updateAll")
    @Timed
    @EndpointMethodControl(key = "extension.example.updateAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response updateAll(@Auth (required = false) AuthenticatedUser authenticatedUser,
                              @QueryParam("view") ViewType view,
                              String json)
            throws JSONException
    {
        JSONObject body = new JSONObject(json);
        return dispatchRequest(body, view, updateRequestHandler, authenticatedUser);
    }
}
