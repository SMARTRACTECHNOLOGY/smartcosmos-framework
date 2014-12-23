package net.smartcosmos.client.impl.base;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.ICreateableBaseClient;
import net.smartcosmos.client.impl.command.PutCommand;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractCreateableBaseClient<T> extends AbstractFindableBaseClient<T> implements ICreateableBaseClient<T>
{
    protected AbstractCreateableBaseClient(ServerContext context)
    {
        super(context);
    }

    public ResponseEntity create(T instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            return create(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    protected ResponseEntity create(JSONObject instance, String path) throws ServiceException
    {
        PutCommand<ResponseEntity> command = new PutCommand<>(context);
        return command.call(ResponseEntity.class, path, instance);
    }
}
