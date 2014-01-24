# ObjectInteractionSession

The object Interaction Session is used to logically batch together a collection of Object Interaction records. An Object Interaction Session is, for all intents and purposes, simply a logical grouping construct with a start time and a stop time. This logical grouping construct conveys that an entire set of Object Interactions occurred within the context of a single unit of work; somehow, all of these Object Interactions are related. What that relationship is must be arbitrarily defined by the developer using the `type` field. The contents of the `type` field are never evaluated by the platform; it is an external concept. One example use case might be built around the concept of reading numerous UHF RFID tags with a specialized RFID scanner.

## Object Interaction Session Fields

Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated
startTimestamp | long | true | false | Minimum | Generated
stopTimestamp | long | false | true | Minimum | Generated
type | String | true | false | Minimum |
 

## ObjectInteraction Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/sessions | PUT  | InteractionSessionStart
/sessions | POST  | InteractionSessionStop
/sessions/{urn} | GET | ObjectInteractionSessionAccessed
/sessions?nameLike | GET | ObjectInteractionSessionAccessed (for each session)



