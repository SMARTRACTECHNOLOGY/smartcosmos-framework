package net.smartcosmos.objects.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.objects.model.context.IDevice;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

public class Device extends AccountTypedNamedObject<IDevice> implements IDevice
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String identification;

    @Override
    public String getIdentification()
    {
        return identification;
    }

    @Override
    public void setIdentification(String identification)
    {
        this.identification = identification;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Device device = (Device) o;

        if (!type.equals(device.type)) return false;
        if (!identification.equals(device.identification)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + identification.hashCode();
        return result;
    }
}
