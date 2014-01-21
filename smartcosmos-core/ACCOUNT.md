
# Account

An _Account_ record is created automatically at the time of _registration confirmation_ (not at the time of registration), along with your administrator User record. Generally, the Account record's _name, description and integrationEndpointUrl_ are the only fields directly edited by the Account administrator. Each of the restricted keys are only updatable indirectly through other SnapBundle™ operations. For example, the _licenseType_ field is managed by the SnapBundle™ platform when a production license purchased or upgraded.

> The licenseType field is assigned based on your purchased license level. Valid license type values include: IndividualFree, IndividualPremium, Enterprise, Developer

**Enterprise licensees** have access to Object Interaction Sessions, the _integrationEndpointUrl_ field, and additional User creation capabilities. Developer licensees are provided with limited access to these features, restricted by volume. For example, a Developer licensee can only create 2 users and can only transmit a total of 100 events per day to the integrationEndpointUrl. 

Consult the [Tag Dynamics](http://www.tagdynamics.com, "Tag Dynamis Web Site") web site for a complete feature matrix across these different license types.

## Account Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
name | String  | true | true | Minimum | 
description | String  | false | true | Standard | 
activeFlag | Boolean  | true | false | Standard  | 
encodedPublicKey | String  | true | false | Standard | Generated
encodedPrivateKey | String  | true  | false | Restricted | Generated 
topicArn | String  | false | true | Restricted | 
subscriptionArn | String  | false | true | Restricted | 
integrationEndpointUrl | String  | false | true | Full | 
pendingConfirmation | Boolean  | false | true | Full | 
licenseType | LicenseType  | true | true | Restricted  | Assigned 


## Account Endpoints
**NOTE:** Access restricted to authenticated Users with an Administrator role type

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/admin/account | GET, POST  | AccountUpdated
