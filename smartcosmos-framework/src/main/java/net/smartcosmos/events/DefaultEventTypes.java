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
    final public static String AccountRead = "account:read";
    final public static String AccountConfirmed = "account:confirmed";
    final public static String AccountDefined = "account:created";
    final public static String AccountUpdated = "account:updated";

    final public static String UserAccessed = "UserAccessed";
    final public static String UserBatchStart = "UserBatchStart";
    final public static String UserBatchStop = "UserBatchStop";
    final public static String UserDefined = "UserDefined";
    final public static String UserLoginFailure = "UserLoginFailure";
    final public static String UserLoginSuccess = "UserLoginSuccess";
    final public static String UserPasswordChanged = "UserPasswordChanged";
    final public static String UserPasswordReset = "UserPasswordReset";
    final public static String UserRegistrationRequest = "RegistrationRequest";
    final public static String UserUpdated = "UserUpdated";

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

    final public static String MetadataCreated = "metadata:created";
    final public static String MetadataUpdated = "metadata:updated";
    final public static String MetadataUpserted = "metadata:upserted";
    final public static String MetadataRead = "metadata:read";
    final public static String MetadataDeleted = "metadata:deleted";

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
