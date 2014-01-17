package com.snapbundle.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import com.snapbundle.model.base.ITypedObject;
import com.snapbundle.util.json.JsonGenerationView;

public abstract class TypedNamedObject<T> extends NamedObject<T> implements ITypedObject
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String type;

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        Preconditions.checkNotNull(type, "type must not be null");
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TypedNamedObject that = (TypedNamedObject) o;

        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
