

package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractMonikerBuilder;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.objects.pojo.context.Relationship;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IRelationship} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#ENTITY_REFERENCE_TYPE}</li>
 * <li>{@link net.smartcosmos.Field#REFERENCE_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#RELATED_ENTITY_REFERENCE_TYPE}</li>
 * <li>{@link net.smartcosmos.Field#RELATED_REFERENCE_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * </ul>
 */
public final class RelationshipBuilder extends AbstractMonikerBuilder<IRelationship, RelationshipBuilder>
{
    public RelationshipBuilder(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        super(new Relationship());
        instance.setEntityReferenceType(entityReferenceType);
        instance.setReferenceUrn(referenceUrn);
    }

    public RelationshipBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public RelationshipBuilder setRelatedReferenceUrn(String urn)
    {
        Preconditions.checkNotNull(urn);
        instance.setRelatedReferenceUrn(urn);
        return this;
    }

    public RelationshipBuilder setRelatedEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        Preconditions.checkNotNull(entityReferenceType);
        instance.setRelatedEntityReferenceType(entityReferenceType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
        Preconditions.checkNotNull(instance.getReferenceUrn(), "reference urn must not be null");
        Preconditions.checkNotNull(instance.getRelatedReferenceUrn(), "related reference urn must not be null");
        Preconditions.checkNotNull(instance.getEntityReferenceType(), "entity reference type must not be null");
        Preconditions.checkNotNull(instance.getRelatedEntityReferenceType(), "related entity reference type must not be null");
    }
}
