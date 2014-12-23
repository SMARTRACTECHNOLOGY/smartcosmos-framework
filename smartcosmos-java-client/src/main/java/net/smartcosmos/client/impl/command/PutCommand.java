package net.smartcosmos.client.impl.command;

import com.google.common.base.Preconditions;
import net.smartcosmos.Field;
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
import java.util.ArrayList;
import java.util.Collection;

import static net.smartcosmos.Field.CODE_FIELD;
import static net.smartcosmos.Field.MESSAGE_FIELD;

public class PutCommand<T> extends AbstractBaseClient implements ICommand<T, T>
{
    private final static Logger LOGGER = LoggerFactory.getLogger(PutCommand.class);

    public PutCommand(ServerContext context)
    {
        super(context);
    }

    @Override
    public T call(Class<? extends T> clazz, String path) throws ServiceException
    {
        throw new UnsupportedOperationException("PUT command must have inputJson");
    }

    @Override
    public T call(Class<? extends T> clazz, String path, JSONObject inputJson) throws ServiceException
    {
        T response;

        Preconditions.checkNotNull(inputJson);

        ClientResource service = createClient(path);

        try
        {
            JSONObject jsonResult;
            try
            {
                Representation result = service.put(new JsonRepresentation(inputJson));
                JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
                jsonResult = jsonRepresentation.getJsonObject();

                response = JsonUtil.fromJson(jsonResult, clazz);

            } catch (ResourceException e)
            {
                if (e.getStatus().equals(Status.CLIENT_ERROR_CONFLICT))
                {
                    ResponseEntity entity = new ResponseEntity.Builder(
                            Result.ERR_ALREADY_EXISTS.getCode(),
                            String.format(Result.ERR_ALREADY_EXISTS.getFormattedMessage(), "user", inputJson.getString(Field.EMAIL_ADDRESS_FIELD)))
                            .build();

                    throw new ServiceException(entity);
                } else if (e.getStatus().equals(Status.CLIENT_ERROR_BAD_REQUEST))
                {
                    ResponseEntity entity = new ResponseEntity.Builder(
                            Result.ERR_FAILURE.getCode(),
                            String.format(Result.ERR_FAILURE.getFormattedMessage(), "Bad Request - if this was an interaction, was your session already closed?"))
                            .build();

                    throw new ServiceException(entity);
                } else
                {
                    LOGGER.error("Unexpected Resource Exception", e);
                    throw new ServiceException(e);
                }
            }

            if (!service.getStatus().equals(Status.SUCCESS_CREATED))
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());

                try
                {
                    if (jsonResult.has(CODE_FIELD) && jsonResult.has(MESSAGE_FIELD))
                    {
                        ResponseEntity responseEntity = new ResponseEntity();
                        responseEntity.setCode(jsonResult.getInt(CODE_FIELD));
                        responseEntity.setMessage(jsonResult.getString(MESSAGE_FIELD));

                        throw new ServiceException(responseEntity);

                    } else if (jsonResult.has(CODE_FIELD))
                    {
                        throw new ServiceException(jsonResult.getInt(CODE_FIELD));
                    }
                } catch (JSONException e)
                {
                    throw new ServiceException(Result.ERR_FAILURE.getCode());
                }
            } else
            {
                if (clazz.isAssignableFrom(ResponseEntity.class))
                {
                    LOGGER.debug(((ResponseEntity) response).getMessage());
                }
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }

    @Override
    public Collection<ResponseEntity> call(String path, JSONArray inputJson) throws ServiceException
    {
        Collection<ResponseEntity> response = new ArrayList<>();

        Preconditions.checkNotNull(inputJson);

        ClientResource service = createClient(path);

        try
        {
            Representation result = service.put(new JsonRepresentation(inputJson));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);

            if (!service.getStatus().equals(Status.SUCCESS_OK))
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());

                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                try
                {
                    if (jsonResult.has(CODE_FIELD) && jsonResult.has(MESSAGE_FIELD))
                    {
                        ResponseEntity responseEntity = new ResponseEntity();
                        responseEntity.setCode(jsonResult.getInt(CODE_FIELD));
                        responseEntity.setMessage(jsonResult.getString(MESSAGE_FIELD));

                        throw new ServiceException(responseEntity);

                    } else if (jsonResult.has(CODE_FIELD))
                    {
                        throw new ServiceException(jsonResult.getInt(CODE_FIELD));
                    }
                } catch (JSONException e)
                {
                    throw new ServiceException(Result.ERR_FAILURE.getCode());
                }
            } else
            {
                JSONArray jsonResult = jsonRepresentation.getJsonArray();

                for (int i = 0; i < jsonResult.length(); i++)
                {
                    JSONObject jsonObject = jsonResult.getJSONObject(i);

                    ResponseEntity responseEntity = new ResponseEntity();
                    responseEntity.setCode(jsonObject.getInt(CODE_FIELD));
                    responseEntity.setMessage(jsonObject.getString(MESSAGE_FIELD));

                    response.add(responseEntity);

                }
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }
}

