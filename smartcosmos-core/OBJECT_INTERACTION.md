# ObjectInteraction
The Object Interaction is the major metaphor that the entire platform is effectively designed around. An Object Interaction captures context; _this_ Object did something with _that_ object at _this_ time. The interaction is typically decorated with additional characteristics, including metadata, tags, geospatial references, etc. 

> Every Object Interaction is read-only once submitted! Field values cannot be updated; records cannot be deleted once the interaction is persisted.

Object Interactions are inherently _bi-temporal_ in nature. There is the time of interaction as captured on the device, perhaps a smartphone or laptop, and there is the time of receipt as captured by the SnapBundle™ platform in the cloud. These two times could have a difference of minutes, hours, or perhaps days in those instances where the device lacked an active Internet connection.

## ObjectInteraction Fields
Two fields in particular are worth nothing here. The `object` is physically stored as an `IObject` and when retrieving the Object Interaction using a GET the full IObject will be returned. However, when inserting a new ObjectInteraction with a PUT, _only the `objectUrn` need be submitted in the JSON sent to the server._ See below for an example of the minimal fields required for creating an Object Interaction.


Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated |
object | IObject | true | false | Minimum |
recordedTimestamp | long | true | false | Standard |
receivedTimestamp | long | true | false | Full | Generated
hasSessionMembership | Boolean | false | false | Minimum |
objectInteractionSession | IObjectInteractionSession | false | false | Standard | null


## ObjectInteraction Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/interactions | PUT  | ObjectInteraction
/interactions/{urn} | GET | ObjectInteractionAccessed
/interactions?objectUrn=Foo | GET | ObjectInteractionAccessed (for each interaction)

## Minimal fields for creating an ObjectInteraction
In practice, an Object Interaction can be defined in as little as four JSON fields:

```
{
    "objectUrn" : "1A2B3C",
    "entityReferenceType" : "Object",
    "referenceUrn" : "urn:uuid:123456",
    "recordedTimestamp" : 1390530313
}
```