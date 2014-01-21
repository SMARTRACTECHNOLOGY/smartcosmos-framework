
# Account

An _Account_ record is created automatically at the time of _registration confirmation_ (not at the time of registration), along with your administrator User record. The Account record holds your account _name_, and account _description_.   

## Account Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
name | String  | true | true | Minimum | 
description | String  | false | true | Standard | 
activeFlag | Boolean  | true | false | Standard  | 


## Account Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/account | GET, POST  | AccountUpdated
