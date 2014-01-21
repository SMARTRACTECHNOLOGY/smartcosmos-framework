# Device
SnapBundle may be used to capture metadata about devices in order to make the association that _this user used this device_. The _identification_ field is the most important field in this data type, as it represents the unique identifier of the device. On a laptop, this could be the computer's network MAC address, or serial number printed on an asset tag affixed to the computer, or on a cell phone it could be the manufacturer's IMEI string.

> SnapBundle™ makes no inferences from the device identification. Theoretically, an account with 100 devices could simply sequence the identification of each device, i.e. 1, 2, 3, etc.

## Device Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
name | String  | true | true | Minimum | 
description | String  | false | true | Standard | 
activeFlag | Boolean  | true | false | Standard  | 
account | IAccount  | true | false | Full | Generated
type | String | true | true | Minimum |  
identification | String | true | false | Minimum |

The `identification` key MUST be treated as a unique value within your Account's Device set. Multiple devices with an identical `identification` value is explicitly _not_ supported.

## Type
The `type` field is available to create an ontology or high level categories that can be used to group related devices. The platform makes no inferences about this case-sensitive field.

## Device Endpoints
Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/devices | PUT | DeviceDefined  
/devices | POST | DeviceUpdated 
/devices/{urn} | GET | DeviceAccessed
/devices?nameLike=foo | GET  | DeviceAccessed (per matching record)
/devices/device/{identification} | GET | DeviceAccessed

**NOTE** The PUT endpoint is idempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a Device with the given `identification` already existed in the database (no data is merged; existing record is left as-is).
