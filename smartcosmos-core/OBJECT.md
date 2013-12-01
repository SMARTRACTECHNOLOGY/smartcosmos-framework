# Object
The SnapBundle™ platform maintains a _serial number agnostic_ architecture. Specifically, this means that no assumptions are made about the composition of an object identifier, and the platform _never_ attempts to infer any logic from an Object's identifier. To illustrate this concept, an Object could be a social security number, a manufacturer serial number from an NFC tag, a VIN off of a car, a Twitter hashtag, or even some custom sequencing algorithm perhaps as simple as 1, 2, 3, 4, 5, etc.

The **objectUrn** field is where the identifier is formally defined. Again, the SnapBundle™ platform makes no inferences about the format of the data stored in this field; it may be arbitrarily defined by either the User or software developer. The only restriction is that the Object's identifier is read-only. Once an Object is created with a given identifier, that Object and its identifier are immutable.

## Object Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
name | String  | true | true | Minimum | 
description | String  | false | true | Standard | 
activeFlag | Boolean  | true | false | Standard  | 
account | IAccount  | true | fase | Full | Generated
objectUrn | String | true | false | Minimum 
objectType | ObjectType | true | true | Minimum | Unknown 

## ObjectType
The object type is used as a high level categorization to describe the Object. The _IndependentlyDefined_ enumeration is generally used for manually defined identifiers, such as a custom serial number system.

**NOTE:** The Terms of Service forbid the storage of either Social Security Numbers or Credit Card numbers on the SnapBundle™ platform. At this time, the platform has not been submitted for PCI certification, but may be in the future if there is strong demand. Please follow the lead of the health care industry, creating an abstract identification key and categorizing it as an _IndependentlyDefined_ object type, keeping SSNs and credit card data fully under your control.

## Object Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/objects | PUT, GET, POST  | ObjectDefined,ObjectUpdated
/app/objects/{objectUrn} | GET |

**NOTE:** Every PUT operation includes a check against the account-unique `objectUrn` field for prior existence. Developers should pay attention to the HTTP status code returned from the PUT operation:

 *  **201 Created** indicates that a new record was inserted into the database with the developer-assigned `objectUrn` field
 *  **200 OK** indicates that no action was taken because the developer-assigned `objectUrn` field was already found in the database
 
If after receiving a 200 the developer wants to update the record, they may issue a POST operation.