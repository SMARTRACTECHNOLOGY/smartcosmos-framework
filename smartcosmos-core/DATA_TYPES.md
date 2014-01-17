# SnapBundle™ Data Types
The SnapBundle™ platform consists of three distinct categories of data types:

* [Primary](#primary)
* [Secondary](#secondary)

The **Primary** types (both foundational and supporting) exist on their own. 

The **Secondary** types cannot exist on their own. In object oriented parlance, they cannot be an _is-a_ and are relegated to being composed role inside of a primary type.


## [Primary Types](id:primary)
The SnapBundle™ object model consists of a small number of primary data types. A primary data type is a _first class citizen_ in the SnapBundle™ platform. These types have the ability to stand on their own. Primary types are sub-categorized as _foundational_ or _supporting_. Again, all primary types can stand on their own. The primary types include:

### Foundational
* [Object](OBJECT.md "Object")
* [Relationship](RELATIONSHIP.md "Relationship]")
* [Object Interaction](OBJECT_INTERACTION.md "ObjectInteraction")

### Supporting
* [User](USER.md "User")
* [Device](DEVICE.md "Device")
* [File](FILE.md "File")
* [GeoRectification](GEORECTIFICATION.MD "GeoRectification")
* [Object Interaction Session](OBJECT_INTERACTION_SESSION.md "ObjectInteractionSession")

There is no physical limitation on how many instances can be created, though additional service fees may be applied to your account based on your subscription plan.

## [Secondary Types](id:secondary)
Each of the data types defined in this section are secondary types that are entirely contained within a primary data type or directly refer to a pre-existing primary data type entity; they cannot stand on their own. For example, `SecurityRole` is _not_ a primary data type; it only exists within the context of a User primary data type. Similarly, an `ObjectAddress`  is _not_ a primary data type because it must be associated with an existing `Obect` at creation time. (In OO parlance, an Object "has-a" Object Address)

* [EntityReferenceType](#ert)
* [Object Address](OBJECT_ADDRESS.md "ObjectAddress")
* [Metadata](METADATA.md "Metadata")
* [Tag](TAG.md "Tag")
* [AddressType](#at)
* [DeviceType](#dt)
* [ObjectType](#ot)
* [RoleType](#rt)
* [MetadataDataType](#mdt)
* [LicenseType](#lt)
* [SessionType](#st)
* [PermissionType](#pt)
* [EventType](#et)
* [RelationshipType](#relt)
* [GeorectificationType](#gt)

### [EntityReferenceType](id:ert)
An Entity Reference Type is used to provide context to a type-agnositc call, such as a search operation.  

* Account
* Device
* User
* Object
* ObjectAddress
* ObjectInteraction
* ObjectInteractionSession
* File
* Metadata
* Tag

### [AddressType](id:at)
An Address Type is used to categorize an address associated with an Object.

* Permanent
* LastKnown 
* Unknown

### [DeviceType](id:dt)
Device types are used to categorize the physical computing platform interacting with the world around it.

* Phone
* Tablet
* PC
* Blackberry
* iPhone
* iPad
* Android
* Kindle
* Nook
* Specialized
* Unknown

### [ObjectType](id:ot)
An Object Type is used to categorize the type of Object managed by the system.

* Animal
* Person
* ISBN
* DriversLicense
* UTMGridLocation
* HashTag
* FourSquareLocation
* VehicleIdentificationNumber
* IndependentlyDefined
* Barcode
* UhfRfid
* HfRfid
* QRCode
* ScrapbookElement
* Unknown
  
### [RoleType](id:rt)
Security roles are associated with a User and define the privilege levels for the User when interacting with the SnapBundle™ platform.

* Administrator
* Developer
* User

### [MetadataDataType](id:mdt)
Metadata Data Types are used to refine a key-value pair into a type-safe key-value pair.

* StringType
* DateType
* IntegerType
* LongType
* BooleanType
* FloatType
* DoubleType
* JSONType
* XMLType
* Custom

The `JSONType` and `XMLType` are convenient mechanism for storing structured metadata under a given metadata key. The platform persists all of the types _except_ for Custom using well-known byte[] formats. As described by the [Metadata](METADATA.md "Metadata") definition, there are well-known **public/** endpoints to ensure that the proper byte[] encoding is easily obtained. 

> If an entirely proprietary encoding scheme is required, use the `Custom` MetadataDataType.

### [SessionType](id:st)
Session Types are used by an Object Interaction Session to describe how the related Object Interactions would obtained.

* BatchScan
* Unknown

### [LicenseType](id:lt)
Every Account is associated with a License Type that constrains the operations of its Users. Various SnapBundle™ platform capabilities, such as Object Interaction Sessions, are only available to Enterprise subscribers (or Developer licenses building solutions atop the platform, for development only).

* IndividualFree
* IndividualPremium
* Enterprise
* Developer

### [PermissionType](id:pt)
Every Extension must explicitly declare a set of permissions that it is requesting from a User. 

* AccountRead
* AccountWrite
* UserRead
* UserWrite
* DeviceRead
* DeviceWrite
* ObjectRead
* ObjectWrite
* ObjectAddressRead
* ObjectAddressWrite
* ObjectInteractionRead
* ObjectInteractionSessionRead
* ObjectInteractionSessionWrite
* FileRead
* FileWrite
* MetadataRead
* MetadataWrite
* TagRead
* TagWrite
* TagAssignmentRead,
* TagAssignmentWrite,
* EventStream[^1]

[^1]: Implies all *Read permissions are assigned since object JSON is included in all events


### [EventType](id:et)
The following is meant to be an authoritative list of all known event types generated by the current (v1) version of the SnapBundle™ platform. These events are intended to be machine readable for easy parsing; their case is presented accurately below to match what will be included in the actual Event dispatched to the Extension's integration end point.

* RegistrationRequest
* NewAccountDefined
* AccountConfirmed
* UserDefined
* UserLoginSuccess
* UserLoginFailure
* DeviceDefined
* ObjectDefined
* ObjectInteraction
* FileDefined
* FileUploaded
* UserPasswordReset
* UserPasswordChanged
* ObjectBatchStart
* ObjectBatchStop
* UserBatchStart
* UserBatchStop
* DeviceBatchStart
* DeviceBatchStop
* ObjectUpdated
* DeviceUpdated
* UserUpdated
* AccountUpdated
* ObjectDeactivated
* PermanentObjectAddressDefined
* PermanentObjectAddressUpdated
* LastKnownObjectAddressDefine
* PermanentObjectAddressDeleted
* FileRetrieval
* InteractionSessionStart
* InteractionSessionStop
* NotificationEnroll
* NotificationSubscribe
* NotificationSubscriptionConfirmed
* NotificationBroadcast
* NotificationUnsubscribe
* NotificationWithdrawn
* SetDeveloperLicense
* UnsetDeveloperLicense
* MetadataDefined
* MetadataUpdated
* MetadataDeleted
* ExtensionDefined
* ExtensionUpdated
* ExtensionActivated
* ExtensionDeactivated
* ExtensionDeleted
* ExtensionScreenshotDefined
* ExtensionScreenshotDeleted
* ExtensionPermissionDefined
* ExtensionPermissionDeleted
* OAuthRequest
* OAuthAuthorizationCodeIssued
* OAuthRequestDenied
* OAuthTokenIssued
* OAuthLoginSuccess
* OAuthLoginFailure
* OAuthTokenRefreshed
* TokenRevocation
* IntegrationEndpointEnrollmentPending 
* IntegrationEndpointEnrolled
* IntegrationEndpointWithdrawn
* RelationshipDefined
* RelationshipDeleted
* GeorectificationDefined
* GeorectificationUpdated
* GeorectificationDeleted
* TimelineEntryDefined
* TimelineEntryUpdated
* TimelineEntryDeleted
* TimelineEntryHidden
* TimelineEntryShown




### [GeorectificationType](id:gt)
Each georectification is classified as a place (geo-fence), structure, or route. The platform makes no interpretation
based on this classification.

* Place
* Structure
* Route

### [RelationshipType](id:relt)
Every relationship is identified with a well-known Relationship Type that typically constrains the logical operations (arbitrarily defined by the developer). The following relationship types are presently supported:

* Follows
* FollowedBy
* Friend
* Parent
* Child
* Sibling
* Peer
* Neighbor
* Owner  

## [Search Types](id:search)

### SearchField
* ObjectUrn("objectUrn"),
* DeviceUrn("urn"),
* Name("name"),
* Moniker("moniker"),
* Description("description"),
* EmailAddress("emailAddress"),
* LastModifiedDate("lastModifiedTimestamp"),
* RecordedTimestamp("recordedTimestamp"),
* ReceivedTimestamp("receivedTimestamp"),
* InteractionSessionTimestamp("startTimestamp"),
* DeviceIdentification("identification"),
* ObjectType("objectType"),
* SessionType("sessionType"),
* DeviceType("deviceType"),
* InteractionData("data"),
* InteractionSession("objectInteractionSession"),
* HasInteractionSession("urn"),
* HasInteractionData("data"),
* HasGeoLocation("hasGeoLocation");

### SearchPredicate
* EQUALS(" = "),
* STARTS_WITH(" LIKE "),
* BEFORE(" <= "),
* AFTER(" >= ");
