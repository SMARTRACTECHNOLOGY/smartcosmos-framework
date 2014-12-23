package net.smartcosmos.client.impl.base;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.client.impl.command.PostCommand;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractUpdateableBaseClient<T> extends AbstractCreateableBaseClient<T> implements IUpdateableBaseClient<T>
{
    protected AbstractUpdateableBaseClient(ServerContext context)
    {
        super(context);
    }

    public void update(T instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            update(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    protected void update(JSONObject instance, String path) throws ServiceException
    {
        PostCommand command = new PostCommand(context);
        command.call(Object.class, path, instance);
    }
}
