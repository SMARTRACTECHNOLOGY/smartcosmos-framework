# Device
SnapBundle™ requires knowledge of a Device in order to make the association that _this user used this device to interact with this object_. The _identification_ field is the most important field in this data type, as it represents the unique identifier of the device. On a laptop, this could be the computer's network MAC address, or serial number printed on an asset tag affixed to the computer, or on a cell phone it could be the manufacturer's IMEI string.

> SnapBundle™ makes no inferences from the device identification. Theoretically, an account with 100 devices could simply sequence the identification of each device, i.e. 1, 2, 3, etc.

## Device Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
name | String  | true | true | Minimum | 
description | String  | false | true | Standard | 
activeFlag | Boolean  | true | false | Standard  | 
account | IAccount  | true | fase | Full | Generated
deviceType | DeviceType | true | true | Minimum | Unknown 
identification | String | true | false | Minimum |

The `identification` key MUST be treated as a unique value within your Account's Device set. Multiple devices with an identical `identification` value is explicitly _not_ supported.

## DeviceType
The device type is used as a high level categorization to describe the system providing the input to the SnapBundle™ platform. The Specialized enumeration is generally used for high end, custom manufactured equipment.

## Device Endpoints
**NOTE:** Access restricted to authenticated Users with an Administrator role type

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/admin/devices | PUT, GET, POST  | DeviceDefined, DeviceUpdated
/admin/devices/{identification} | GET | 

**NOTE** The PUT endpoint is idempotent and will respond with a **201- SUCCESS_CREATED ** if the record is new, or a **200 - SUCCESS_OK** if a Device with the given `identification` already existed in the database (no data is merged; existing record is left as-is).
