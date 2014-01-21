# User
The only requirement that SnapBundle places on a user is that it must be linked with a valid email address.

SnapBundle is not a directory service. The only metadata collected about a user includes first name, last name, email address, and role type. The use of the email address is expected to provide a suitable means of identification of an actual user within the account.

SnapBundle has chosen the Stormpath authentication and authorization PaaS as its default directory service provider. **This means that SnapBundle doesn't manage any user credentials, delegating this responsibility to Stormpath.com.**

While it is possible to change your password through the SnapBundle API, that request is merely delegated to Stormpath. **It is physically impossible to retrieve credential data through the SnapBundle API since it is neither stored nor managed by SnapBundle**.

## User Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
emailAddress | String  | true | false | Minimum | 
account | IAccount  | true | fase | Full | Generated
roleType | RoleType  | true  | true | Minimum | User 

## RoleType
The role type is used as a security filter that may restrict the user from certain sensitive operations in future API versions. SnapBundle has no expressly prohibited operations for User roles at this time. 

## User Endpoints
Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/users | PUT  | UserDefined
/users | POST | UserUpdated
/users/{urn} | GET | UserAccessed
/users/{emailAddress} | GET | UserAccessed

**NOTE** The PUT endpoint is idempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a User with the given `emailAddress` already existed in the database (no data is merged; existing record is left as-is).
 