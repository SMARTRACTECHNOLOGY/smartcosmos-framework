package com.snapbundle.pojo.geo;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.geo.GeometricShape;
import com.snapbundle.model.geo.GeorectificationType;
import com.snapbundle.model.geo.IGeorectification;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.JsonGenerationView;

public class Georectification extends ReferentialObject<IGeorectification> implements IGeorectification
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected GeorectificationType georectificationType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected GeometricShape geometricShape;

    @JsonView(JsonGenerationView.Minimum.class)
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
    public GeorectificationType getGeorectificationType()
    {
        return georectificationType;
    }

    @Override
    public void setGeorectificationType(GeorectificationType georectificationType)
    {
        this.georectificationType = georectificationType;
    }

    @Override
    public GeometricShape getGeometricShape()
    {
        return geometricShape;
    }

    @Override
    public void setGeometricShape(GeometricShape geometricShape)
    {
        this.geometricShape = geometricShape;
    }

    @Override
    public void copy(IGeorectification object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}

