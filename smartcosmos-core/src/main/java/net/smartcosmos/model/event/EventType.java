package net.smartcosmos.model.event;

/**
 * Collection of SMART COSMOS events that capture virtually all interactions between a user and the platform. In
 * many ways, the event mechanism that powers the integration features also indirectly provides a complete
 * audit of user activities.
 */
public enum EventType
{
    RegistrationRequest,

    RealmDefined,

    AccountDefined,
    AccountAccessed,
    AccountConfirmed,
    AccountUpdated,

    EventQuery,

    UserDefined,
    UserUpdated,
    UserAccessed,
    UserLoginSuccess,
    UserLoginFailure,

    DeviceDefined,
    DeviceUpdated,
    DeviceAccessed,

    ObjectDefined,
    ObjectUpdated,

    ObjectInteraction,

    FileDefined,
    FileUploaded,
    FileAccessed,
    FileRetrieval,
    FileDeleted,

    UserPasswordReset,
    UserPasswordChanged,

    ObjectBatchStart,
    ObjectBatchStop,

    UserBatchStart,
    UserBatchStop,

    DeviceBatchStart,
    DeviceBatchStop,

    ObjectDeactivated,

    ObjectAddressDefined,
    ObjectAddressUpdated,
    ObjectAddressDeleted,

    InteractionSessionStart,
    InteractionSessionStop,

    NotificationEnroll,
    NotificationSubscribe,
    NotificationDefined,

    NotificationSubscriptionConfirmed,

    NotificationUnsubscribe,
    NotificationWithdrawn,
    NotificationDeleted,

    NotificationAccessed,

    NotificationBroadcast,

    SetDeveloperLicense,
    UnsetDeveloperLicense,

    MetadataUpserted,
    MetadataDeleted,
    MetadataAccessed,

    TagUpserted,
    TagDeleted,

    TagAssigned,
    TagRevoked,
    TagAccessed,

    RelationshipDefined,
    RelationshipDeleted,

    ExtensionDefined,
    ExtensionUpdated,
    ExtensionActivated,
    ExtensionDeactivated,
    ExtensionAccessed,
    ExtensionDeleted,

    OAuthRequest,
    OAuthAuthorizationCodeIssued,
    OAuthRequestDenied,
    OAuthTokenIssued,
    OAuthTokenScopeRequest,
    OAuthLoginSuccess,
    OAuthLoginFailure,
    OAuthLoginFailureDisabledUser,
    OAuthLoginFailureStaleBearerAccessToken,
    OAuthTokenRefreshed,
    TokenRevocation,

    TimelineEntryDefined,
    TimelineEntryUpdated,
    TimelineEntryDeleted,
    TimelineEntryHidden,
    TimelineEntryShown,

    ObjectAccessed,
    ObjectAddressAccessed,
    ObjectInteractionAccessed,
    ObjectInteractionSessionAccessed,
    RelationshipAccessed,
    TimelineEntryAccessed,

    GeospatialEntryAccessed,
    GeospatialEntryDefined,
    GeospatialEntryUpdated,
    GeospatialEntryDeleted,

    BatchTransmissionRequest,
    BatchTransmissionReceipt,
    BatchTransmissionQueueSuccess,
    BatchTransmissionQueueError
}
