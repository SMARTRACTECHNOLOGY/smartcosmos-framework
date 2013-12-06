# Relationship
SnapBundle™ supports a simple, flexible, and incredibly powerful relationship capability. This flexible model allows the declaration that object A, for example, could `follows` object B. Or, the relationship could be defined where object A is a `neighbor` of object B. 

> SnapBundle™ makes no inferences from any relationships defined.  

## Relationship Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated
entityReferenceType | EntityReferenceType | true | false | Minimum |
referenceURN | String | true | false | Minimum |
relationshipType | RelationshipType | true | false | Minimum | 
relatedEntityReferenceType | EntityReferenceType | true | false | Minimum |
relatedReferenceURN | String | true | false | Minimum |

The `relatedEntityReferenceType` and `relatedReferenceUrn` SHOULD always be treated as the subordinate object in the relationship that is described by the `relationshipType` field. Consider these examples that illustrate this concept:

`````
Entity Reference Type `Object` identified by Reference URN 1A2B3C has a Child relationship type with Related Entity Reference Type `Object` identified by Reference URN 4D5E6F.
`````  

`````
Entity Reference Type `ObjectInteractionSession` identified by Reference URN 1A2B3C has a Child relationship type with Related Entity Reference Type `Device` identified by Reference URN 4D5E6F.
`````  

`````
Entity Reference Type `Object` identified by Reference URN 1A2B3C has a Peer relationship type with Related Entity Reference Type `Object` identified by Reference URN 4D5E6F.
`````  

## RelationshipType
The relationship type is used as a high level categorization to describe the types of logical interactions supported by the two referenced objects. The SnapBundle™ platform never makes any inferences from the relationships defined; they are totaly arbitrary and managed by the developer.

## Relationship Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/relationship/{entityReferenceType}/{referenceUrn} | PUT | RelationshipDefined
/app/relationship/{entityReferenceType}/{referenceUrn}/{relatedEntityReferenceType}/{relatedReferenceUrn}/{relationshipType} | GET, DELETE | RelationshipAccessed, RelationshipDeleted
/app/relationship/{urn} | GET, DELETE | RelationshipAccessed, RelationshipDeleted 
/app/relationship/query/{entityReferenceType}/{referenceUrn} | GET | RelationshipAccessed
/app/relationship/exists/{entityReferenceType}/{referenceUrn} | GET | 


**NOTE** The PUT endpoint is idempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a matching Relationship with the same `entityReferenceType`, `referenceUrn`, `relationshipType`, `relatedEntityReferenceType`, and `relatedReferenceUrn` is submitted. The `moniker` field will be updated; the `relationshipType` field is immutable.

### Querying and Existence Check Endpoints
Both the /query and /exists endpoints are *query string* driven endpoints. Consider the following examples that show the complete range of queries and existence checks that the platform suppots.

#### Query Endpoints

##### Query by Relationship Type
Query for all relationships regardless of related entity reference type that match the specified relationship type.

`````
/app/relationship/query/Object/1A2B3C?relationshipType=Peer  
`````

##### Query by Related Entity Reference Type
Query for all relationships regardless of relationship type that match the specified related entity reference type.

`````
/app/relationship/query/Object/1A2B3C?relatedEntityReferenceType=Object
`````

##### Query by Related Entity Reference Type and Relationship Type
Query for all relationships of the specified relationship type that match the specified related entity reference type.

`````
/app/relationship/query/Object/1A2B3C?relatedEntityReferenceType=Object&relationshipType=Peer  
`````


#### Existence Check Endpoints
##### Existence of Specific Related Entity Reference Type
Determines if any relationships exist that matches the specified related entity reference type. The result will be a `ResponseObject` where the `result` will be 1 for true (existence) and 0 for false (non-existent).

`````
/app/relationship/exists/Object/1A2B3C?relatedEntityReferenceType=Object  
`````

##### Existence of Specific Relationship Type
Determines if any relationships exist that matches the specified relationship type. The result will be a `ResponseObject` where the `result` will be 1 for true (existence) and 0 for false (non-existent).

`````
/app/relationship/exists/Object/1A2B3C?relationshipType=Peer  
`````

##### Existence of Any Relationship
Determines if any relationships exist regardless of relationship type or related entity reference type. The result will be a `ResponseObject` where the `result` will be 1 for true (existence) and 0 for false (non-existent).

`````
/app/relationship/exists/Object/1A2B3C?relatedEntityReferenceType=Object&relatedReferenceUrn=4D5E6F  
`````