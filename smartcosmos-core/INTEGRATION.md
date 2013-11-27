# External System Integration

In v1 of the SnapBundle™ platform, only **Account** and **Extension** objects support integration end points. The process is virtually identical; only the endpoints used are different between the two data types. For a complete example, please see the [Extension](EXTENSION_FRAMEWORK.md "Extension Framework") documentation's _Sample Script for Extension Definnition_.

## Account External System Integration Endpoints
**NOTE:** Access restricted to authenticated Users with an Administrator role type

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/admin/integration/{action} | PUT  | IntegrationEndpointEnrollmentPending, IntegrationEndpointWithdrawn
/admin/integration/confirm/{token} | PUT | IntegrationEndpointEnrolled
/admin/integration/status | GET | 

The `action` value in the URL must be one of the following case-sensitive values:

* enroll
* withdraw

Enrollment **should not** be attempted unless you have an active Web application running at the endpoint you define capable of capturing the AWS subscription confirmation message. 

> For a complete sample web application that can capture this subscription confirmation token and demonstrate how to use OAuth 2 to sign onto the SnapBundle™ platform on behalf of an existing Account, please check out the **samples** repository.

