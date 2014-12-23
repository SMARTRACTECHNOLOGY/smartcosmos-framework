package net.smartcosmos.model.extension;

/**
 * Collection of fine-grained read and write permissions that an extension may choose to
 * ask a user for when performing an OAuth 2.0 authorization handshake.
 * <p/>
 * <b>NOTE:</b> The {@link #EventStream} implies *Read permissions across the board. It is
 * not presently possible to constrain the event stream to a subset of entities.
 */
public enum PermissionType
{
    AccountRead,
    AccountWrite,

    DeviceRead,
    DeviceWrite,

    // Implies *Read permissions since JSON is included in all events
    EventStream,

    FileRead,
    FileWrite,

    GeospatialEntryRead,
    GeospatialEntryWrite,

    MetadataRead,
    MetadataWrite,

    ObjectRead,
    ObjectWrite,

    ObjectAddressRead,
    ObjectAddressWrite,

    ObjectInteractionRead,
    ObjectInteractionWrite,

    ObjectInteractionSessionRead,
    ObjectInteractionSessionWrite,

    RelationshipRead,
    RelationshipWrite,

    TagAssignmentRead,
    TagAssignmentWrite,

    TagRead,
    TagWrite,

    TimelineRead,
    TimelineWrite,

    UserRead,
    UserWrite
}
