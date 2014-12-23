package net.smartcosmos.client.objects.device;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.endpoint.DeviceEndpoints;
import net.smartcosmos.objects.model.context.IDevice;
import net.smartcosmos.objects.pojo.context.Device;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class DeviceClient extends AbstractUpdateableBaseClient<IDevice> implements IDeviceClient
{
    DeviceClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, DeviceEndpoints.create());
    }

    @Override
    public Collection<IDevice> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IDevice> command = new GetCollectionCommand<>(context);
        return command.call(Device.class, DeviceEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public IDevice findByDeviceIdentification(String identification, ViewType viewType) throws ServiceException
    {
        GetCommand<IDevice> command = new GetCommand<>(context);
        return command.call(Device.class, DeviceEndpoints.findByDeviceIdentification(identification, viewType));
    }

    @Override
    public IDevice findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, DeviceEndpoints.findByUrn(urn, viewType), Device.class);
    }

    @Override
    public Collection<IDevice> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }

    @Override
    public IDevice findByDeviceIdentification(String identification) throws ServiceException
    {
        return findByDeviceIdentification(identification, ViewType.Standard);
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, DeviceEndpoints.update());
    }
}
