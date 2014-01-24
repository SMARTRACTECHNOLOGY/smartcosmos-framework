# ObjectAddress
Every SnapBundle Object may optionally be linked to an ObjectAddress. Addresses can either be broadly defined with the `type` field, e.g. _physical_ or _last known_. The difference between object address `type` values is arbitrarily defined by the developer.

**NOTE:** There is no limit on how many different address types an object could be associated with. For example, using the `type` field one could define a _physical_ and a _mailing_ record set. Likewise, with the `timestamp` field one could insert multiple addresses, capturing a _last known_ concept.

## ObjectAddress Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
object | IObject | true | false | Standard |
type | String | true | false | Minimum |
line1 | String | false | true | Minimum | null
line2 | String | false | true | Minumum | null
city | String | false | true | Minimum | null
stateProvince | String | false | true | Minimum | null
countryAbbreviation | String | false | true | Minimum | null
timestamp | long | true | true | Standard |

The **timestamp** differs from the _lastModifiedTimestamp_ based on context. In the case of a _last known_ address `type`, for example, the timestamp would be conceptually equivalent to the last time the object was seen at the given address. 

## ObjectAddress Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
** to be documented **
