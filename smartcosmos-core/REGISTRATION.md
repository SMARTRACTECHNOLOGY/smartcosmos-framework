# Account Registration
Registering for an SnapBundle™ Account and then confirming the email address associated with the Account registration are the only perquisites for using the SnapBundle™ platform. These two steps are required before any **admin/** or **app/** endpoints can be successfully accessed.

#### Realms
The SnapBundle™ platform is a multi-tenant system by design. Each Account operates within its own _security realm_ in order to keep one Account's data separate from another Account's data. By default, the security realm is established _based on the Account registration email address' top level domain_. For example, registering for an Account with the email address developer@tagdynamics.com would result in the automatic assignment of the _tagdynamics.com_ realm. It is possible to override this default behavior by including a _realm_ attribute in the JSON document submitted to the **public/registration/register** endpoint:

> If you are an Individual or a Developer, it is strongly recommended that you register using a unique, custom realm. This approach will allow the use of a individual email, e.g. you@yahoo.com. If you are an Enterprise, it is strongly recommended you omit the realm, which will automatically register your email address domain as your realm.

## Check Realm Availability
##### METHOD:
**GET** public/realm/{realm}

##### EXAMPLE REQUEST:
**curl** https://snapbundle.tagdynamics.net/v1/public/realm/foo.com

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
**POST** public/registration/register


##### EXAMPLE REQUEST:
curl https://snapbundle.tagdynamics.net/v1/public/registration/register \  
-d '{"emailAddress":"you@foo.com", "realm" : "foo.com"}' \  
-H "Content-Type:application/json" \  
-X POST  

##### SAMPLE RESPONSE:
```
{
   "urn" : "urn:uuid:64618d0b-3b8c-456a-87d0-31bc1a0bc579",
   "lastModifiedTimestamp" : 1382828220349,
   "emailAddress" : "you@foo.com",
   "realm" : "tagdynamics.com",
   "emailVerificationToken" : "ISSDRGAROMPE"
}
```

### Email Verification
The email verification token is generally **intended for non-developers** who are going to receive the token in their email. However, developers may wish to use the emailVerificationToken property against the following endpoint to confirm their subscription programmatically:

##### METHOD:
**GET** public/registration/confirm/{emailVerificationToken}/{emailAddress}

##### EXAMPLE REQUEST:
**curl** https://snapbundle.tagdynamics.net/v1/public/registration/confirm/ZEREJYZUGVUW/foo@bar

##### SAMPLE RESPONSE:
    {
       "message": "1GKQAGDDJZ1#",
       "result" : 1
    }

Result | Description
-----------: | ------------- 
\> 0 | Email address confirmed; initial password attached in message field
0 | Email address previously verified
-1 | Unrecognized email address
-2 | Unrecognized verification token

### Changing User Password
This endpoint allows the authenticated User to change their own password.

##### METHOD:
**POST** app/password

##### EXAMPLE REQUEST:
**curl** https://snapbundle.tagdynamics.net/v1/app/password \  
-d '{"emailAddress":"you@foo.com", "newPassword" : "secret123"}' \  
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
