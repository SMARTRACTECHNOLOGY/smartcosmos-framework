# Account Registration
Registering for an SnapBundle Account and then confirming the email address associated with the Account registration are the only perquisites for using the  platform. These two steps are required before any endpoints that require authentication can be successfully accessed.

#### Realms
The SnapBundle™ platform is a multi-tenant system by design. Each Account operates within its own _security realm_ in order to keep one Account's data separate from another Account's data. By default, the security realm is established _based on the Account registration email address' top level domain_. For example, registering for an Account with the email address developer@tagdynamics.com would result in the automatic assignment of the _tagdynamics.com_ realm, if it is available. It is possible to override this default behavior by including a _realm_ attribute in the JSON document submitted to the registration endpoint.

> If you are an Individual or a Developer registering with a popular email address from say yahoo.com, gmail.com, etc. then it is strongly recommended that you register using a unique, custom realm. This approach will allow the use of a individual email, e.g. you@yahoo.com, even though a realm like yahoo.com is prohibited. If you are an Enterprise, it is strongly recommended you omit the realm, which will automatically register your email address domain as your realm.

## Check Realm Availability
##### METHOD:
**GET** /registration/realm/{realm}

##### EXAMPLE REQUEST:
**curl** https://snapbundle.tagdynamics.net/v1/registration/realm/foo.com

##### SAMPLE RESPONSE:
    {
       "message": "foo.com",
       "result" : 0
    }

Result | Description
-----------: | ------------- 
0 | The realm is not available for use because it has already been registered
1 | The realm is available for use.


## Account Registration:
##### METHOD:
**POST** /registration/register


##### EXAMPLE REQUEST:
curl https://snapbundle.tagdynamics.net/registration/register \  
-d '{"emailAddress":"you@foo.com", "realm" : "foo.com"}' \  
-H "Content-Type:application/json" \  
-X POST  

##### SAMPLE RESPONSE:
```
{
   "urn" : "urn:uuid:64618d0b-3b8c-456a-87d0-31bc1a0bc579",
   "lastModifiedTimestamp" : 1382828220349,
   "emailAddress" : "you@foo.com",
   "realm" : "foo.com",
}
```

### Changing Your Password
This endpoint allows an authenticated user who knows their existing password to change to a new password.

##### METHOD:
**POST** /account/password/change

##### EXAMPLE REQUEST:
**curl** https://snapbundle.tagdynamics.net/v1/account/password/change \  
-d '{"oldPassword":"1JPOAIOAML3#", "newPassword" : "secret123"}' \  
-H "Content-Type:application/json" \  
-u you@foo.com:1JPOAIOAML3# \  
-X POST  

```
{
   "result" : 1,
   "message" : "Password successfully changed"
}
```

Result | Description
-----------: | ------------- 
1 | Password successfully changed
< 0  | Password was not changed

### Reset Password
This endpoint allows a user that has forgot their existing password to trigger a password reset workflow via email.

##### METHOD:
**POST** /account/password/reset

##### EXAMPLE REQUEST:
**curl** https://snapbundle.tagdynamics.net/v1/account/password/reset \  
-d '{"emailAddress":"you@foo.com"}' \  
-H "Content-Type:application/json" \  
-X POST  

```
{
   "result" : 1,
   "message" : "Password reset email has been sent"
}
```

Result | Description
-----------: | ------------- 
1 | Password reset successfully initiated
< 0  | Password was not changed