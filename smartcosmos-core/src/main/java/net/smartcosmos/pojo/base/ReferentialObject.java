package net.smartcosmos.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;

public abstract class ReferentialObject<T> extends DomainResource<T> implements IReferentialObject
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    protected EntityReferenceType entityReferenceType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String referenceUrn;

    @Override
    public String getReferenceUrn()
    {
        return referenceUrn;
    }

    @Override
    public void setReferenceUrn(String urn)
    {
        this.referenceUrn = urn;
    }

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return entityReferenceType;
    }

    @Override
    public void setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        this.entityReferenceType = entityReferenceType;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ReferentialObject that = (ReferentialObject) o;

        if (!account.equals(that.account)) return false;
        if (entityReferenceType != that.entityReferenceType) return false;
        if (!referenceUrn.equals(that.referenceUrn)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + entityReferenceType.hashCode();
        result = 31 * result + referenceUrn.hashCode();
        return result;
    }
}
