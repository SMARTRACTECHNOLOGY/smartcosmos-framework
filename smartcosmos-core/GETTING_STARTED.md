
# SnapBundle™ SDK
## Overview
The SnapBundle™ SDK is built around a set of RESTful web services that use JavaScript Object Notation (JSON) to represent state. The web services are referred to as the SnapBundle™ API. While not strictly required for integration with the platform, we offer a single Java .jar library that wraps these RESTful JSON calls, easing platform integration by allowing developers to work with the higher semantics of Java interfaces and classes instead of working with the lower level JSON. If your development environment requires some other development language, the platform's RESTful JSON architecture means you can easily integrate without using the pre-built library.

For additional details on the SnapBundle™ Java library, please refer to the library's JavaDocs.

## SnapBundle™ Java Framework
### Overview
The SnapBundle™ Java Framework is available here at GitHub. For specifics on its API, please see JavaDocs that accompany the .jar file.

## SnapBundle™ REST API
### Overview
The SnapBundle™ API is built atop RESTful design principles. It is organized around the four foundational objects: Users, Devices, Objects, and Files. Additionally, there are a number of supporting objects that provide a rich set of interactions, searching, etc.

### Working with the API
The first step in using the SnapBundle™ platform is to register for an account. Registration is typically tied to the domain name of the email address being used. You must own the domain from which you are registering, or you must provide a unique realm value during the registration process. Popular domains like gmail.com, yahoo.com, etc. are not supported as valid realm values at this time!

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
Whenever the word Object is capitalized, it refers to the actual [Object](OBJECT.md "Object") primary data type. When the word object is presented in lower-case, then it should convey _any_ of the primary data types. To illustrate this concept, consider the following statement: _Every object maintains a `lastModifiedTimestamp` for tracking the timestamp of the last data modification. Here, the _object_ could be a File, a piece of Metadata, an ObjectAddress, etc. because the lowercase _object_ was used. 

### Tracking Modification Timestamps
All SnapBundle™ objects maintain a lastModifiedTimestamp field to track when the record was last updated. The purpose of this timestamp is to provide a valid eTag for HTTP cache support.

### Moniker: Arbitrary External System Alias
All SnapBundle™ objects support a `moniker` property that can be used as the developer sees fit. This field is **never** interpreted by the SnapBundle™ platform, and it may be null if the developer chooses not to use it.

> The SnapBundle™ SDK promises developers that the platform itself will never rely on the moniker value for any feature, now or in the future, leaving it entirely available for 3rd party developers to use as they see fit in their applications.

A popular usage of the `moniker` field is to provide a logical link between an SnapBundle™ object, say an RFID Object, and some type of proprietary (abstract) key in your back office account, e.g. ADDR-123-43-2013. This could be a link to a legacy key, or a JSON or XML stanza for more structure content.


**NOTE:** The `moniker` field is limited to a maximum of 2048 characters at this time.

### JSON Serialization Views
All SnapBundle™ objects can be serialized to JSON at different levels of verbosity. Generally, this fact is encapsulated and of little concern to most developers. However, it is possible in rare situations to observe the same identical object, as indicated by its URN key, serialized with more (or less) fields. The serialization levels supported by the platform include:

   * **Published** - those fields that may be publicly accessible without prior platform authorization (see [Extensions](EXTENSION_FRAMEWORK.md#published "Extension Framework"))

   * **Minimum** - only the most pertinent fields are serialized

   * **Standard** - Minimum fields, plus additional contextual fields

   * **Full** - Standard fields, plus fields not normally referenced or regularly read by applications

   * **Restricted** - Opaque fields accessible only to the SnapBundle™ platform's cloud-side code and never transmitted over the wire under any circumstance. These fields are never updatable by the developer.

> All Event Stream events and nearly all GET requests are serialized using the `Standard` JSON view. If an Extension requires access to a field only serialized at the Full level, it must make a specific GET request on that entity to object the complete definition.  

To explicitly request a different serialization level, include a query string parameter with one of either {`Published`, `Minimum`, `Standard`, or `Full`}. _Any value outside of this range automatically defaults to `Standard`. Here is an example:

GET /v1/app/path/to/some/endpoint**?view=Full**  

The default serialization view for all POST responses is `Standard` and cannot be changed at this time.

### Security Domains
The SnapBundle™ API was designed around four clearly conveyed security domains:

* public/
* admin/
* app/
* oauth2/

These physical security domains are intentionally designed as a physical part of the URL structure so there is never any doubt about which domain a call is accessing. 

#### public/
The public security domain does not require any authentication. It is predominately used for SnapBundle™ registration, and for providing a publicly searching list of 3rd party application extensions.

#### admin/
The admin security domain is only accessible to those accounts created with the Administrator role, which includes the initial account used to register with the service. Specifically, only administrators have the necessary privileges to create Users or Devices. Administrators also have the unique privilege of performing a batch upload of users, devices, and objects using a CSV file. In an Enterprise subscription, administrators also have the requisite permissions to register an integration end point, which is a URL within the corporate domain where SnapBundle™ events for the account are pushed via HTTP POST in near real-time.

All developer accounts are implicitly administrator accounts, and as such have the ability to define and deploy application extensions and extension screen shots.

#### app/
The app security domain is where the vast majority of users and operations take place. In fact, the SnapBundle™ security model only identifies three types of security roles: Administrator, Developer and User. The app security domain includes support for defining objects, performing object interactions, uploading documents into the virtual file system and searching. Enterprise accounts also include support for the concept of an object interaction session, a useful metaphor for grouping together a sequence of scans under a single logical unit of work.

#### oauth2/
The OAuth 2.0 security domain is used strictly by Extension objects that are initiating an OAuth 2.0 three legged authentication and authorization procedure. For more details, please refer to the [SnapBundle™ OAuth 2.0](OAUTH_GUIDE.md "SnapBundle™ OAuth 2.0 Guide") documentation.

> The production base URL for the REST API is **https://snapbundle.tagdynamics.net/v1/**

### Licensing
The SnapBundle™ Platform is a licensed technology. Different license plans are available. If an API call is with an unlicensed operation request, an [HTTP error code 412, _Precondition Failed_](http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.13 "RFC - 10.4.13 412 Precondition Failed"), will be returned to the caller.

### Support
The fastest way to obtain support is by using the [snapbundle] tag on Stack Overflow. The Tag Dynamics engineer team monitors and answers questions marked with this tag throughout the day. If you feel a question is too sensitive for the forum, you may alternatively email <engineering@tagdynamics.net>.