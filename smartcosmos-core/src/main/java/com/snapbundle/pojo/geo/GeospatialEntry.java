package com.snapbundle.pojo.geo;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.geo.GeometricShape;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.geo.IGeospatialEntry;
import com.snapbundle.model.geo.IGeospatialType;
import com.snapbundle.pojo.base.DomainResource;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.util.json.JsonGenerationView;

public class GeospatialEntry extends DomainResource<IGeospatialEntry> implements IGeospatialEntry
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = GeospatialType.class)
    protected IGeospatialType geospatialType;

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
    public IGeospatialType getGeospatialType()
    {
        return geospatialType;
    }

    @Override
    public void setGeospatialType(IGeospatialType geospatialType)
    {
        this.geospatialType = geospatialType;
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
    public void copy(IGeospatialEntry object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public IAccount getAccount()
    {
        return account;
    }

    @Override
    public void setAccount(IAccount account)
    {
        this.account = account;
    }
}

