# Relationship
SnapBundle supports a simple, flexible, and incredibly powerful relationship capability. This flexible model allows the declaration that object A, for example, could `follows` object B. Or, the relationship could be defined where object A is a `neighbor` of object B. Verbs are supported as well, such that person A "drove" car B, where A and B are just Objects with a `type` of person and car, respectively. 

The relationship `type` field is used to capture the high level categorization that describes the logical interaction between the two referenced objects. The platform makes no inferences from any relationships defined.  


## Relationship Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated
entityReferenceType | EntityReferenceType | true | false | Minimum |
referenceURN | String | true | false | Minimum |
type | String | true | false | Minimum | 
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


## Relationship Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------

** to be documented **