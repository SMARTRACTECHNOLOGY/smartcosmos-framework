package net.smartcosmos.client.impl.command;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractBaseClient;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

import static net.smartcosmos.Field.URN_FIELD;

public class PostCommand extends AbstractBaseClient implements ICommand<Object, Object>
{
    private final static Logger LOGGER = LoggerFactory.getLogger(PostCommand.class);

    public PostCommand(ServerContext context)
    {
        super(context);
    }

    @Override
    public Object call(Class<?> clazz, String path) throws ServiceException
    {
        throw new UnsupportedOperationException("POST command must have inputJson");
    }

    @Override
    public Object call(Class<?> clazz, String path, JSONObject inputJson) throws ServiceException
    {
        ClientResource service = createClient(path);

        try
        {
            try
            {
                Representation result = service.post(new JsonRepresentation(inputJson));

                if (service.getStatus().equals(Status.SUCCESS_NO_CONTENT))
                {
                    if (inputJson.has(URN_FIELD))
                    {
                        LOGGER.info("Successfully updated URN {} at path {}", inputJson.getString(URN_FIELD), path);
                    } else
                    {
                        LOGGER.info("Successfully updated entity at path {}", path);
                    }
                } else
                {
                    JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
                    JSONObject jsonResult = jsonRepresentation.getJsonObject();

                    LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                    ResponseEntity response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                    throw new ServiceException(response);
                }

            } catch (ResourceException e)
            {
                if (e.getStatus().equals(Status.CLIENT_ERROR_BAD_REQUEST))
                {
                    ResponseEntity entity = new ResponseEntity.Builder(
                            Result.ERR_FAILURE.getCode(),
                            String.format(Result.ERR_FAILURE.getFormattedMessage(), e.getMessage()))
                            .build();

                    throw new ServiceException(entity);
                } else
                {
                    LOGGER.error("Unexpected Resource Exception", e);
                    throw new ServiceException(e);
                }
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return null;
    }

    @Override
    public Collection<ResponseEntity> call(String path, JSONArray inputJson) throws ServiceException
    {
        throw new UnsupportedOperationException("POST command doesn't accept input as a JSONArray");
    }
}
