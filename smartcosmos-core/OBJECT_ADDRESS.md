# ObjectAddress
Every SnapBundle™ Object may optionally be linked to an ObjectAddress. Addresses can either be broadly defined as _physical_ or _last known_. The difference between the two is simply this: a _physical_ address is **fixed and cannot be removed**, where a _last known_ address could change from hour to hour, day to day, week to week, etc. Once an Object is assigned a _physical_ address, it cannot support a last known address concept. In other words, if an Object is assigned a physical address then it cannot have a last known addresses. Likewise, if an object is assigned just one last known address, it can never have a physical address.

Both physical and last known addresses support a postal address _and_ a GPS coordinate that consists of latitude, longitude, and altitude. These two options, postal address and GPS, are not mutually exclusive.

## ObjectAddress Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
object | IObject | true | false | Standard |
addressType | AddressType | true | false | Minimum |
line1 | String | false | true | Minimum | null
line2 | String | false | true | Minumum | null
city | String | false | true | Minimum | null
stateProvince | String | false | true | Minimum | null
countryAbbreviation | String | false | true | Minimum | null
timestamp | long | true | true | Standard |
hasGeoLocation | Boolean | false | false | Standard |
lat | double | false | false | Standard |
lon | double | false | false | Standard |
alt | double | false | false | Standard |

The **timestamp** differs from the _lastModifiedTimestamp_ based on context. In the case of a physical address, the timestamp would be conceptually equivalent to a creation timestamp. In the case of a last known address, the timestamp would physically capture the timestamp of the address capture on the device itself, which may be significantly different than the last modified timestamp due to network latency.

## AddressType
The address type is used as a high level categorization to describe a location. The Unknown enumeration is generally used strictly as a decorator to indicate an explicit unknown address, should an external system require such a concept.

## ObjectAddress Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/app/objects/{objectUrn}/address | PUT, GET, POST, DELETE | PermanentObjectAddressDefined, PermanentObjectAddressUpdated, PermanentObjectAddressDeleted, LastKnownObjectAddressDefine 

**NOTE:** An HTTP POST is used solely for updating a Permanent\* address. Likewise, an HTTP DELETE is only valid on a Permanent\* address; any attempt to DELETE a _last known_ address will throw an exception. 

