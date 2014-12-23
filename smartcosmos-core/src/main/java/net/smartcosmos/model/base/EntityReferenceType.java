
package net.smartcosmos.model.base;

/**
 * Represents a specific type of platform entity in a generic operation. Instead of defining many different
 * entity specific operations, a singular operation is typically defined by the platform where one of the
 * mandatory parameters is an {@link EntityReferenceType} coupled with a <b>referenceUrn</b>. This reduces
 * the number of API methods to learn, and provides an easy extension mechanism when new types of entities
 * are defined in future releases.
 * <p/>
 * As a concrete example, consider a {@link net.smartcosmos.objects.model.context.IRelationship} used to
 * define some arbitrary type of connection between any two existing entities defined in the system.
 * As new entity types are added to the system, no additional work is required to support those entities
 * so that they too can participate in relationships.
 */
public enum EntityReferenceType
{
    Account,

    BatchTransmission,

    Extension,

    NotificationEndpoint,

    Object,
    Relationship,
    ObjectInteraction,
    ObjectInteractionSession,

    Device,
    ObjectAddress,
    File,
    Metadata,
    Tag,
    Timeline,
    Georectification,

    Library,
    Shelf,
    Book,
    PageEntry
}
