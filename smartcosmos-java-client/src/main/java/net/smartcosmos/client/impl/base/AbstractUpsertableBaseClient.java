package net.smartcosmos.client.impl.base;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpsertableBaseClient;
import net.smartcosmos.client.impl.command.UpsertCommand;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

public abstract class AbstractUpsertableBaseClient<T> extends AbstractFindableBaseClient<T> implements IUpsertableBaseClient<T>
{
    protected AbstractUpsertableBaseClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ResponseEntity upsert(T instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            return upsert(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    protected ResponseEntity upsert(JSONObject instance, String path) throws ServiceException
    {
        UpsertCommand<ResponseEntity> command = new UpsertCommand<>(context);
        return command.call(ResponseEntity.class, path, instance);
    }

    protected Collection<ResponseEntity> upsert(JSONArray jsonArray, String path) throws ServiceException
    {
        UpsertCommand<ResponseEntity> command = new UpsertCommand<>(context);
        return command.call(path, jsonArray);
    }

    @Override
    public Collection<ResponseEntity> upsert(JSONArray jsonArray) throws ServiceException
    {
        throw new UnsupportedOperationException();
    }
}

