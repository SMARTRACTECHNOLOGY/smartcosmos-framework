# Object
The SnapBundle supports arbitrarily defined object types. This means that the platform makes no assumptions about the composition of an object identifier, and the platform _never_ attempts to infer any logic from either the Object's identifier or its arbitarily defined `type` field. To illustrate this concept, an Object could represent a Person, a Bank Account, a vehicle, an animal, or even a place (optionally complimented by a geospatial definition to precisely define the place).

The `objectUrn` field is the formally defined identifier. Again, the platform makes no inferences about the format of the data stored in this field; it may be arbitrarily defined by either the User or software developer. The only restriction is that the Object's identifier is read-only. Once an Object is created with a given identifier, that identifier is immutable.

## Object Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
name | String  | true | true | Minimum | 
description | String  | false | true | Standard | 
activeFlag | Boolean  | true | false | Standard  | 
account | IAccount  | true | fase | Full | Generated
objectUrn | String | true | false | Minimum 
type | String | true | true | Minimum |  

## Object Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/objects | PUT | ObjectDefined
/objects/{urn} | GET | ObjectAccessed
/objects/object/{objectUrn} | GET | ObjectAccessed
/objects | POST | ObjectUpdated
/objects | GET | ObjectAccessed (for each object)

**NOTE:** Every PUT operation includes a check against the account-unique `objectUrn` field for prior existence. Developers should pay attention to the HTTP status code returned from the PUT operation:

 *  **201 Created** indicates that a new record was inserted into the database with the developer-assigned `objectUrn` field
 *  **200 OK** indicates that no action was taken because the developer-assigned `objectUrn` field was already found in the database
 
If after receiving a 200 the developer wants to update the record, they may issue a POST operation.

The /objects GET endpoint provide a powerful set of query parameters for searching. When used in combination, these parameters are ANDed together; all must match to be included in the JSONArray response.

Query Parameter Name | Default Value | Notes  
------------ | ------------- | ------------
modifiedAfter | 0 | From the start of time
monikerLike | * | Ignored by default
nameLike | * | Ignored by default
objectUrnLike | * | Ignored by default