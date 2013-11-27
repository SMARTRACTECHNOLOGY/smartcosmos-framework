# Tag
Tags have grown in popularity across the Web for assigning quick search terms to everything from blog postings to Twitter posts. The SnapBundle™ platform provides a Tag object for this purpose as well. The following data types support a Tag association:

* Device
* File
* Object
* ObjectAddress
* ObjectInteraction
* ObjectInteractionSession
    
## Tag Fields

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

## Defining a Tag    
Tag definition can occur in two different ways: explicitly or implicitly. An explicit Tag definition occurs when a Tag is defined by providing a name, description, and optional moniker value. The result of an explicit Tag definition is a Tag but _unassigned to any object_ under the Account.

An implicit Tag definition occurs when a Tag Assignment is made between a referential object, i.e. one of the supported data types listed above, and a previously undefined label.

**NOTE:** Tag labels are case-sensitive and Tag definition indempotent (you can explicitly define the same Tag label 100 times, but the system will only store the first one).

## Deleting a Tag
**Deletion of a Tag requires the Administrator role.** When a Tag is deleted, any referential object instance referring to that Tag  


## Tag Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/tags | PUT  | TagUpserted
/app/tags/{name} | GET, DELETE | TagDeleted 

**NOTE** The PUT endpoint is indempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a Tag with the given `name` already existed in the database (no data is merged; existing record is left as-is).

## Tag Query Endpoints
Three query endpoints are provided for finding objects with assigned tags. The first endpoint will return a list of all objects that are associated with the Tag regardless of entity reference type. In other words, the result might include Devices, Files, Objects, etc. marked with the Tag.

The second endpoint will limit the query to a specific object data type, e.g. only Devices marked with the Tag.

The final endpoint will limit the query to a specific object uniquely defined by the entity type and reference URN, returning all assigned Tags to the given object.
 
Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/tags/query/{name} | GET |
/app/tags/query/{name}/{entityReferenceType} | GET |
/app/tags/query/specific/{entityReferenceType}/{referenceUrn} | GET |

## Tag Assignment and Deletion Endpoints
Two endpoints are provided for Tag - object assignment and revocation. 

> Multiple invocations with the same values will not result in an exception or redundent Tag assignments.  

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/tags/{entityReferenceType}/{referenceUrn} | PUT  | TagAssigned
/app/tags/{entityReferenceType}/{referenceUrn}/{name} | DELETE | TagRevoked

**NOTE:** The **PUT** endpoint expects a JSON Array.  The endpoint _automatically creates a Tag_ if the named Tag doesn't already exist. Submitting a single JSON object is not supported at this time. The following JSON Array could be PUT into the Tag assignment endpoint without any prior Tag related RESTful calls, and the result would be the automatic creation of two new Tag objects, alpha and bravo, and an association between those two Tag objects and the referenced entity defined in the URL.

````
[
    {
        "name": "alpha"
    },
    {
        "name": "bravo"
    }
]
````

