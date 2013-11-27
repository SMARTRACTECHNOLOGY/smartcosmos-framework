# User
SnapBundle™ requires a user be linked with a valid email address. In the case of an Enterprise account, the email address must be from the same domain as the account's realm. In other words, it isn't permitted to have an account realm off foo.com with a user assigned an email address of bar.com. There is no technical reason for this restriction; it is a security feature requested by early access program participants.

SnapBundle™ is not intended to be a directory service. There is no real metadata collected about a user, such as a first name or last name. The use of the email address is expected to provide a suitable means of identification of an actual user within the account.

Notice that the user's login credentials are securely stored using a dynamically salted hash, ensuring that two users with the same password are represented with different hash values in the database. The passwordHash is a restricted field, and it is never transmitted over the wire by the server.

## User Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
emailAddress | String  | true | false | Minimum | 
account | IAccount  | true | fase | Full | Generated
activeFlag | Boolean  | true | false | Standard  | true
object | IObject  | false | true | Standard | null
roleType | RoleType  | true  | true | Minimum | User 
passwordHash | String  | true | true | Restricted | Generated

## RoleType
The role type is used as a security moniker to restrict the user from certain sensitive operations. The User enumeration is explicitly prohibited from accessing any end points under the **admin/** security domain.

## User Endpoints
**NOTE:** Access restricted to authenticated Users with an Administrator role type

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/admin/users | PUT, GET, POST  | UserDefined, UserUpdated
/admin/users/{emailAddress} | GET | 

**NOTE** The PUT endpoint is indempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a User with the given `emailAddress` already existed in the database (no data is merged; existing record is left as-is).
 