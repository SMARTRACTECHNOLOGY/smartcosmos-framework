package net.smartcosmos.client.objects.relationship;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IDeleteableBaseClient;
import net.smartcosmos.client.impl.IUpsertableBaseClient;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines, deletes, or queries for {@link net.smartcosmos.objects.model.context.IRelationship} instances.
 * <p/>
 * A relationship is a <b>binary concept</b> that either exists, or doesn't. For example, a specific Driver object cannot
 * "LIKE" a specific Car object multiple times. The driver "LIKE"s the Car, or does not "LIKE" the car. As documented
 * by {@link net.smartcosmos.client.impl.IUpsertableBaseClient}, relationship creation is idempotent; no matter how
 * many times the relationship is defined, it is guaranteed to only exist once in the database.
 */
public interface IRelationshipClient extends IUpsertableBaseClient<IRelationship>, IDeleteableBaseClient<IRelationship>
{
    /**
     * Retrieves <b>all</b> of the documented relationships between two specific entities.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn) throws ServiceException;

    /**
     * Retrieves <b>all</b> of the documented relationships between two specific entities.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using the specified field verbosity.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @param viewType                   Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, ViewType viewType) throws ServiceException;

    /**
     * Retrieves <b>a very specific</b> relationship between two specific entities, if it exists.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType) throws ServiceException;

    /**
     * Retrieves <b>a very specific</b> relationship between two specific entities, if it exists.
     * <p/>
     * The {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using the specified field verbosity.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @param relationshipType           case-sensitive name of the relationship
     * @param viewType                   Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    /**
     * Retrieves <b>all</b> child entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     * <p/>
     * One can use this query to answer the question "Tell me all of the relationships my Vehicle has, which might
     * be two: an {@link net.smartcosmos.objects.model.context.IObject} that is the "garage" where the vehicle is worked on and
     * another {@link net.smartcosmos.objects.model.context.IObject} that is the "owner" of the vehicle. Compare this result
     * with the collection returned from
     * {@link #findReverseRelationships(net.smartcosmos.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType owner/parent entity reference type
     * @param referenceUrn        owner/parent reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException;

    /**
     * Retrieves <b>all</b> child entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using the specified field verbosity.
     * <p/>
     * One can use this query to answer the question "Tell me all of the relationships my Vehicle has, which might
     * be two: an {@link net.smartcosmos.objects.model.context.IObject} that is the "garage" where the vehicle is worked on and
     * another {@link net.smartcosmos.objects.model.context.IObject} that is the "owner" of the vehicle. Compare this result
     * with the collection returned from
     * {@link #findReverseRelationships(net.smartcosmos.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType owner/parent entity reference type
     * @param referenceUrn        owner/parent reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @param viewType            Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    /**
     * Retrieves <b>all</b> owner/parent entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     * <p/>
     * One can use this query to answer the question "Tell me all of the parent relationships to my "garage", which might
     * be four: an {@link net.smartcosmos.objects.model.context.IObject} for each "Vehicle" that has been to the garage. Compare
     * this result with the collection returned from
     * {@link #findRelationships(net.smartcosmos.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType child entity reference type
     * @param referenceUrn        child reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException;

    /**
     * Retrieves <b>all</b> owner/parent entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link net.smartcosmos.objects.model.context.IRelationship} will be serialized using the specified field verbosity.
     * <p/>
     * One can use this query to answer the question "Tell me all of the parent relationships to my "garage", which might
     * be four: an {@link net.smartcosmos.objects.model.context.IObject} for each "Vehicle" that has been to the garage. Compare
     * this result with the collection returned from
     * {@link #findRelationships(net.smartcosmos.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType child entity reference type
     * @param referenceUrn        child reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @param viewType            Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException;
}
