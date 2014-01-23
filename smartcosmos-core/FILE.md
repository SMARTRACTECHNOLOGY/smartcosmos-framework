# File
The SnapBundle platform can associate _any_ type of file with _any_ of the platform's primary entity types. Furthermore, there is no physical restriction on how many files may be associated with an object.

## File Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated
entityReferenceType | EntityReferenceType | true | false | Minimum |
referenceURN | String | true | false | Minimum |
url | String | true | false | Restricted | Generated
mimeType | String | true | false | Minimum |
timestamp | long | true | false | Standard |
pending | Boolean | true | true | Full |
digitalSignature | String | true | false | Full |
fileName | String | true | false | Standard | 

The `timestamp` differs from the `lastModifiedTimestamp` based on file creation time. In other words, the `timestamp` must represent the creation time of the media, while the `lastModifiedTimestamp` will represent the time at which the media was uploaded to the platform for storage.

The combination of `entityReferenceType` and `referenceURN` drives the ability for a File to be associated with any [primary data type](DATA_TYPES.md "Data Types"), such as the following primary data types:

   * Device
   * User
   * File
   * Metadata
   * Object
   * ObjectAddress
   * ObjectInteraction
   * ObjectInteractionSession
   * Relationship
   * Timeline
   * Georectification

The following arbitrary examples are intended to illustrate the power of this design:

   * A set of media could be associated with the Device, such as a picture of the actual laptop that shows its color, shape, etc.
   * A set of media could be associated with the User, such as the user's picture, their assigned company car, etc.
   * A set of media could be associated with an existing File definition, thus creating a nested tree of parent-child relationships. 
   * A set of media could be associated with a piece of metadata, linking say the model year of the car associated with the VIN (stored in a Object record) with a PDF of the user manual for that year vehicle.
   * A set of media could be associated with an Object, showing what the object looks like, where it placed on its host, etc.
   * A set of media could be associated with an Object Address, showing what the building looks like from the outside.
   * A set of media could be associated with an Object Interaction, capturing additional context, like a video of the machine associated with the object actually started and running
   * A set of media could be associated with an Object Interaction Session, such as capturing a narrative about the location where the batch scan was initiated.
   * Etc.

> The delivery of a File is inherently a two-step process. 

**First**, the actual database record (File) is defined. **Second**, the associated media file is physically uploaded to the SnapBundle™ platform. During the first operation, the File is marked as `pending = true` to indicate that the File record exists, but the actual file hasn't been physically uploaded to the cloud yet. After the file upload completes, the system automatically changes `pending = false` to indicate that the actual media is now available to the platform.

The `url` represents the physical URL of where the file is stored inside of the virtual file system (VFS). For security reasons, this URL isn't directly accessible and is therefore annotated as a Restricted field.

> The toggle of the `pending` flag to false is the only indication to an [Extension](EXTENSION_FRAMEWORK.md "Extension Framework") listening in on the Event Stream that the media is available for download. Instead of relying on the `pending` flag, integrators should instead look for the `FileUploaded` event (instead of `FileDefined`) for confirmation that the actual file is available for download.


## File Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/files | PUT  | FileDefined
/files/{urn}/octet | POST | FileUploaded
/files/{urn}/multipart | POST | FileUploaded
/files/{urn}/contents | GET | FileAccessed
/files/{urn} | GET | FileAccessed
/files/{entityReferenceType}/{referenceUrn} | GET | FileAccessed (for each file)
/files/{urn} | DELETE | FileDeleted

Developers must use the **PUT** endpoint to first define the file, then use one of the **POST** endpoints to physically upload the file to the Cloud for storage. The multipart endpoint expects a multi-part mime type, while the octet expects an octet-stream mime type. 

If the file content already exists at well-defined location on the web referenceable with an HTTP or HTTPS URL, then the developer may optionally choose to include a `contentUrl` field in the PUT request. The platform checks for the existence of `contentUrl` and `mimeType` fields, and if present, not-null, and the content is successfully retrieved, then the PUT operation is self-contained. In this case, self-contained means that the PUT operation generated the File record _and_ downloaded the content of the given MIME type from the referenced URL and placed it into platform storage. If the `contentUrl` field is included with a `null` value or cannot be successfully downloaded, of if the `mimeType` field is omitted, then an HTTP 400 status code will be returned to the developer to convey the fact that file content is missing or inaccessible. This also means that the File record was _not_ saved either. 


For retrieval, when requesting by `{entityReferenceType}/{referenceUrn}`, the system will always respond with a JSON array, detailing the set of File definitions associated with the referenced object. In contrast, the `{urn}` GET request represents the URN assigned to a specific File definition, returning the single JSON object defining the given File.