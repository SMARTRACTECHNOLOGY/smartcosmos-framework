package net.smartcosmos.events;

/**
 * Collection of SMART COSMOS events that capture virtually all interactions between a
 * user and the platform. In many ways, the event mechanism that powers the integration
 * features also indirectly provides a complete audit of user activities.
 */
public class DefaultEventTypes {

    /*
     * User Management Events
     */
    final public static String TenantRead = "tenant:read";
    final public static String TenantConfirmed = "tenant:confirmed";
    final public static String TenantCreated = "tenant:created";
    final public static String TenantUpdated = "tenant:updated";
    final public static String TenantCreateFailedAlreadyExists = "tenant:createFailedAlreadyExists";
    final public static String TenantNotFound = "tenant:notFound";

    final public static String UserRead = "user:read";
    final public static String UserBatchStart = "user:batch:start";
    final public static String UserBatchStop = "user:batch:stop";
    final public static String UserCreated = "user:created";
    final public static String UserLoginFailure = "user:login:failure";
    final public static String UserLoginSuccess = "user:login:success";
    final public static String UserPasswordChanged = "user:password:changed";
    final public static String UserPasswordReset = "user:password:reset";
    final public static String UserRegistrationRequest = "user:registration:requested";
    final public static String UserUpdated = "user:updated";
    final public static String UserCreateFailedAlreadyExists = "user:createFailedAlreadyExists";
    final public static String UserDeleted = "user:deleted";
    final public static String UserNotFound = "user:notFound";

    final public static String RoleCreated = "role:created";
    final public static String RoleRead = "role:read";
    final public static String RoleCreateFailedAlreadyExists = "role:createFailedAlreadyExists";
    final public static String RoleNotFound = "role:notFound";
    final public static String RoleDeleted = "role:deleted";
    final public static String RoleUpdated = "role:updated";

    /*
     * Primary Core Events
     */
    final public static String ThingCreated = "thing:created";
    final public static String ThingCreateFailedAlreadyExists = "thing:createFailedAlreadyExists";
    final public static String ThingCreatedTransactional = "thing:createdTransactional:transactionId:";
    final public static String ThingTransactionalCreateFailure = "thing:TransactionalCreateFailure:transactionId:";
    final public static String ThingRead = "thing:read";
    final public static String ThingUpdated = "thing:updated";
    final public static String ThingDeactivated = "thing:deactivated";
    final public static String ThingNotFound = "thing:notFound";
    final public static String ThingDeleted = "thing:deleted";

    final public static String InteractionCreated = "interaction:created";
    final public static String InteractionRead = "interaction:read";

    final public static String InteractionSessionStart = "interaction:session:start";
    final public static String InteractionSessionRead = "interaction:session:read";
    final public static String InteractionSessionStop = "interaction:session:stop";

    final public static String RelationshipCreated = "relationship:created";
    final public static String RelationshipRead = "relationship:read";
    final public static String RelationshipDeleted = "relationship:deleted";

    /*
     * Secondary Events
     */
    final public static String BatchStatusReportAccessed = "BatchStatusReportAccessed";
    final public static String BatchTransmissionQueueError = "BatchTransmissionQueueError";
    final public static String BatchTransmissionQueueSuccess = "BatchTransmissionQueueSuccess";
    final public static String BatchTransmissionReceipt = "BatchTransmissionReceipt";
    final public static String BatchTransmissionRequest = "BatchTransmissionRequest";

    final public static String EmailProvidersAccessed = "EmailProvidersAccessed";
    final public static String EmailSent = "EmailSent";

    final public static String EventQuery = "EventQuery";
    final public static String EventRecorded = "EventRecorded";

    final public static String FileAccessed = "FileAccessed";
    final public static String FileDefined = "FileDefined";
    final public static String FileDeleted = "FileDeleted";
    final public static String FileRetrieval = "FileRetrieval";
    final public static String FileUploaded = "FileUploaded";

    final public static String NotificationAccessed = "NotificationAccessed";
    final public static String NotificationBroadcast = "NotificationBroadcast";
    final public static String NotificationDefined = "NotificationDefined";
    final public static String NotificationDeleted = "NotificationDeleted";
    final public static String NotificationEnroll = "NotificationEnroll";
    final public static String NotificationSubscribe = "NotificationSubscribe";
    final public static String NotificationSubscriptionConfirmed = "NotificationSubscriptionConfirmed";
    final public static String NotificationUnsubscribe = "NotificationUnsubscribe";
    final public static String NotificationWithdrawn = "NotificationWithdrawn";

    final public static String ThingAddressAccessed = "ThingAddressAccessed";
    final public static String ThingAddressDefined = "ThingAddressDefined";
    final public static String ThingAddressDeleted = "ThingAddressDeleted";
    final public static String ThingAddressUpdated = "ThingAddressUpdated";

    final public static String ThingBatchStart = "ThingBatchStart";
    final public static String ThingBatchStop = "ThingBatchStop";

    final public static String RealmCheck = "RealmCheck";
    final public static String RealmDefined = "RealmDefined";

    final public static String DeveloperLicenseSet = "SetDeveloperLicense";
    final public static String DeveloperLicenseUnset = "UnsetDeveloperLicense";

    final public static String TimelineEntryAccessed = "TimelineEntryAccessed";
    final public static String TimelineEntryDefined = "TimelineEntryDefined";
    final public static String TimelineEntryDeleted = "TimelineEntryDeleted";
    final public static String TimelineEntryHidden = "TimelineEntryHidden";
    final public static String TimelineEntryShown = "TimelineEntryShown";
    final public static String TimelineEntryUpdated = "TimelineEntryUpdated";

}
