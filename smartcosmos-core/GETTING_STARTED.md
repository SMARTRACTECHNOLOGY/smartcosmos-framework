
# SnapBundle™ SDK
## Technical Overview
The SnapBundle SDK is built around a set of RESTful web services endpoints that use JavaScript Object Notation (JSON) to represent state. The platform's RESTful JSON architecture means you can easily adopt the platform using any modern programmaing language.


## SnapBundle™ Java Framework
### Overview
The SnapBundle™ Java Framework is available at GitHub. Today, there isn't a dedicated Java client .jar file available-- but one is in the works. The framework's interace and POJO definitions are published as open source and intended to be the definitive reference for what types of data each entity can manage. For specifics on these classes, please refer to the JavaDocs.

## SnapBundle™ REST API
### Overview
The SnapBundle™ API is built using RESTful design principles. The primary datatypes are organized around the three foundation entities: *`Objects`, `Relationships`, and `Interactions`*. There are also supporting entities: *`Metadata`, `Devices`, `Tags`, `Files`, `Geospatial`, and `Timelines`*.


### Working with the API
**The first step in using the SnapBundle platform is to register for an account.** Registration is typically tied to the domain name of the email address being used. You must own the domain from which you are registering, or you must provide a unique realm value during the registration process. Popular domains like gmail.com, yahoo.com, etc. are not supported as valid realm names.

Sample API calls are provided throughout using cURL, a standard command line tool. The cURL tool makes it possible to test calls from the command line very easily. Alternatively, you may opt to use POSTMAN, an easy-to-use Chrome extension.

> The production base URL for the REST API is https://snapbundle.tagdynamics.net/v1/

### Wire Level Format
Requests and responses are always formatted using JavaScript Object Notation (JSON). 

### Date Format
All SnapBundle™ dates, including those sent with a request and those included with a response, should be formatted using **RFC 3339**. The preferred way to pass in a date is by converting all times to UTC, e.g.

__2013-07-04T09:00:00-00:00__

### URNs as Keys
All SnapBundle™ objects are keyed off of a **UUID**, represented using the standard URN nomenclature for a UUID:  **urn:uuid:<opaque key>**

> Any API call that requires a `{referenceUrn}` parameter must provide a full URN UUID reference.

### Object versus object
Whenever the word `Object` is capitalized and highlighted, it refers to the actual [Object](OBJECT.md "Object") primary data type. When the word object is presented in lower-case with no highlight, then it conveys _any_ of the primary data types. To illustrate this concept, consider the following statement: _Every object maintains a `lastModifiedTimestamp` for tracking the timestamp of the last data modification. Here, the _object_ could be a File, a piece of Metadata, an ObjectAddress, etc. because the lowercase o was used in _object_. 

### Tracking Modification Timestamps
All SnapBundle objects maintain a `lastModifiedTimestamp` field to track when the record was last updated. The purpose of this timestamp is to provide a valid eTag for HTTP cache support.

### Moniker: Arbitrary External System Alias
All SnapBundle objects support a `moniker` property that can be used as the developer sees fit. This field is **never** interpreted by the platform, and it may be null if the developer chooses not to use it.

> The SnapBundle authors promise developers that the platform itself will never rely on the moniker value for any feature, now or in the future, leaving it entirely available for 3rd party developers to use as they see fit in their applications.

A popular usage of the `moniker` field is to provide a logical link between an SnapBundle™ object, say an RFID Object, and some type of proprietary (abstract) key in your back office account, e.g. ADDR-123-43-2013. This could be a link to a legacy key, or a JSON or XML stanza for more structure content.


**NOTE:** The `moniker` field is **limited to a maximum of 2048 characters** at this time. If you need more space than that, create a File and use the moniker to store the file's URN.

### JSON Serialization Views
All SnapBundle objects can be serialized to JSON at different levels of verbosity. Generally, this fact is encapsulated and of little concern to most developers. However, it is possible in rare situations to observe the same identical object, as indicated by its URN key, serialized with more (or less) fields. The serialization levels supported by the platform include:

   * **Published** - Publicly accessible fields (specific to [Extensions](EXTENSION_FRAMEWORK.md#published "Extension Framework"))

   * **Minimum** - All Published fields, plus highly pertinent fields

   * **Standard** - All Minimum fields, plus additional contextual fields

   * **Full** - All Standard fields, plus fields rarely referenced

   * **Restricted** - Opaque fields accessible only to the SnapBundle platform's cloud-side code and **never transmitted over the wire** under any circumstance. These fields are **never updatable** by the developer.

#### Requesting a Specific Serialization View
All Event Stream events and all default GET requests are serialized using the `Standard` JSON view. If an Extension requires access to a field serialized at the Full level, for example, it must include a query parameter on the GET request like this: **?view=Full **


The query string parameter `view` must be a case-sensitive value matching one of the following: {`Published`, `Minimum`, `Standard`, or `Full`}. _Any value outside of this range automatically defaults to `Standard`. 

**NOTE:** The default serialization view for any POST that returns a response is `Standard` and cannot be changed at this time.

### Licensing
The SnapBundle™ Platform is a licensed technology. Different license plans are available. If an API call is made to an endpoint that would result in an unlicensed operation, an [HTTP error code 412, _Precondition Failed_](http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.13 "RFC - 10.4.13 412 Precondition Failed"), will be returned to the caller.

### Support
The fastest way to obtain support is by using the [snapbundle] tag on [Stack Overflow](http://stackoverflow.com "Stack Overflow URL"). The Tag Dynamics engineer team monitors and answers questions marked with this tag. If you feel a question is too sensitive for the forum, you may alternatively email <engineering@tagdynamics.net>.