# ObjectInteractionSession

**NOTE:** Only Enterprise licensees have access to object interaction sessions. 

The object Interaction Session is used to logically batch together a collection of Object Interactions. An Object Interaction Session is, for all intents and purposes, a logical grouping construct with a start time and a stop time. This logical grouping construct conveys that an entire set of Object Interactions occurred within the context of a single unit of work; somehow, all of these Object Interactions are related. The specifics of the relationship are not inferred by the SnapBundle™ platform; it is an external concept. However, the general use case is built around the concept of reading numerous UHF RFID tags with a specialized RFID scanner.

## Object Interaction Session Fields

Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated
user | IUser | true | false | Minimum | Generated |
startTimestamp | long | true | false | Minimum | Generated
stopTimestamp | long | false | true | Minimum | Generated
sessionType | SessionType | true | false | Minimum |
 
## SessionType
The session type is used as a high level categorization to describe how the object interaction set represented by the session was assembled. The _Unknown_ enumeration is generally used strictly as a decorator to indicate a non-batch collection occurred.

## ObjectInteraction Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/interactions/sessions | PUT, GET  | InteractionSessionStart
/app/interactions/sessions/{urn} | GET, POST | InteractionSessionStop



