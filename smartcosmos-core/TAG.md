# Tag
Tags have grown in popularity across the Web for assigning quick search terms to everything from blog postings to Twitter posts. The platform provides a Tag object for this purpose as well. The following data types support a Tag association:

* Device
* File
* Object
* ObjectAddress
* ObjectInteraction
* ObjectInteractionSession
* Georectification
* Timeline
* Relationship
    
## Tag Fields

Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
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

**NOTE:** Tag labels are case-sensitive and Tag definition idempotent (you can explicitly define the same Tag label 100 times, but the system will only store the first one).

## Deleting a Tag
**Deletion of a Tag requires the Administrator role.** When a Tag is deleted, any referential object instance referring to that Tag  


## Tag Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/tags | PUT  | TagUpserted
/tags/tag/{tagName} | DELETE | TagDeleted, TagRevoked
/tags/tag/{tagName} | GET | TagAccessed
/tags/{urn} | GET | TagAccessed
/tags/{entityReferenceType/{referenceUrn} | PUT | TagAssigned
/tags [?tagName=Foo&entityReferenceType=Bar&referenceUrn=Baaq] | GET | TagAccessed (for each tag)

**NOTE** The GET query parameters can be combined for flexible searching, like all tags with a name like Foo and of entity reference type Object, for example.

**NOTE** The PUT endpoint at */tags* is idempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a Tag with the given `name` already existed in the database (no data is merged; existing record is left as-is).

**NOTE:** The PUT endpoint at */tags/{entityReferenceType}/{referenceUrn}* expects a JSON Array.  The endpoint _automatically creates a Tag_ if the named Tag doesn't already exist. Submitting a single JSON object is not supported at this time. The following JSON Array could be PUT into the Tag assignment endpoint without any prior Tag related RESTful calls, and the result would be the automatic creation of two new Tag objects, alpha and bravo, and an association between those two Tag objects and the referenced entity defined in the URL.

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

