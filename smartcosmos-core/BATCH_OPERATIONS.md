# Batch Administration Operations
Batch Operations provide a means of performing a mass object definition. For example, if you wanted to setup 10,000 assets you could export the data from the current system of record for the assets, then simply upload that content in a prescribed format to the SnapBundle™ platform.

Batch Operations are supported only for the following **admin/** managed primary data types:  

* [User](USER.md "User")
* [Device](DEVICE.md "Device")
* [Object](OBJECT.md "Object")


## Batch Operation Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/admin/batch/users | POST  | UserBatchStart, ObjectDefined, UserDefined, UserBatchStop
/admin/batch/devices | POST | DeviceBatchStart, DeviceDefined, DeviceBatchStop
/admin/batch/objects | POST | ObjectBatchStart, ObjectDefined, ObjectBatchStop

**NOTE:** The `ObjectDefined` event will be generated from a batch User operation only if the User is being defined with an Object that they may use for authentication. For example, if the User was assigned an RFID proximity read badge, the serial number of the badge could be setup as an Object owned by the User and available for authenticatin in lieu of the traditional email address (username).

