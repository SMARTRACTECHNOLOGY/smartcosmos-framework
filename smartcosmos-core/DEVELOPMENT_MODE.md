# Developer Mode

## Toggle Development License
Any Account may choose to activate (or deactivate) a development license at anytime. Activating a Development License is only necessary if the Account is going to actively develop an **SnapBundle™ Extension**. Short of this, there is no reason to access these endpoints.

### Development License Endpoint


Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/admin/account/configure/developer/{flag} | POST  | SetDeveloperLicense, UnsetDeveloperLicense 

Valid values for the `flag` parameter are **true** and **false**




