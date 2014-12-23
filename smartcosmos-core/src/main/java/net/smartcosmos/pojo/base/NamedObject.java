

package net.smartcosmos.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

public abstract class NamedObject<T> extends DomainResource<T> implements INamedObject<T>
{
    @JsonView(JsonGenerationView.Published.class)
    protected String name;

    @JsonView(JsonGenerationView.Standard.class)
    protected String description;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean activeFlag;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean isActive()
    {
        return activeFlag;
    }

    @Override
    public void setActive(boolean flag)
    {
        this.activeFlag = flag;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NamedObject that = (NamedObject) o;

        if (activeFlag != that.activeFlag) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (activeFlag ? 1 : 0);
        return result;
    }
}
