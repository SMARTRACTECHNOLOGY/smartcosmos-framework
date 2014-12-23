

package net.smartcosmos.objects.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

public class ObjectImpl extends AccountTypedNamedObject<IObject> implements IObject
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String objectUrn;

    @Override
    public String getObjectUrn()
    {
        return objectUrn;
    }

    @Override
    public void setObjectUrn(String objectUrn)
    {
        this.objectUrn = objectUrn;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObjectImpl object = (ObjectImpl) o;

        if (!objectUrn.equals(object.objectUrn)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + objectUrn.hashCode();
        return result;
    }
}
