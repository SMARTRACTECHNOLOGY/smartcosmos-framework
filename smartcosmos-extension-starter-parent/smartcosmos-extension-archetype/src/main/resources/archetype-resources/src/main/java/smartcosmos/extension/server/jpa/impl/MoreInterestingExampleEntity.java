package ${package}.smartcosmos.extension.server.jpa.impl;

import ${package}.smartcosmos.extension.server.jpa.IMoreInterestingExampleEntity;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.jpa.base.DomainResourceReferentialObjectEntity;

import javax.persistence.Entity;

@Entity(name = "moreInterestingExample")
public class MoreInterestingExampleEntity
        extends DomainResourceReferentialObjectEntity<IMoreInterestingExampleEntity>
        implements IMoreInterestingExampleEntity
{
    private static final long serialVersionUID = 1L;

    private String firstString;

    private String secondString;

    public String getFirstString()
    {
        return firstString;
    }

    public void setFirstString(String firstString)
    {
        this.firstString = firstString;
    }

    public String getSecondString()
    {
        return secondString;
    }

    public void setSecondString(String secondString)
    {
        this.secondString = secondString;
    }

    @Override
    public void copy(IMoreInterestingExampleEntity target)
    {
        super.copy(target);
        this.firstString = target.getFirstString();
        this.secondString = target.getSecondString();
        this.setReferenceUrn(target.getReferenceUrn());
        this.setEntityReferenceType(target.getEntityReferenceType());
    }

    @Override
    protected void copyDomainResourceReferentialObjectEntity(EntityReferenceType entityReferenceType,
                                                             String referenceUrn)
    {
        // NOTE TO EXTENSION DEVELOPER:
        // this method exists int the abstract parent class (DomainResourceReferentialObjectEntity)
        // to remind you to copy the entityReferenceType and the referenceUrn in the local copy() method
        // in any class that extends it.
    }
}
