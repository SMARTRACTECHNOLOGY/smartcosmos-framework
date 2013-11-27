# OAuth 2.0 Guide

Every SnapBundle™ Extension connects to the SnapBundle™ platform using [OAuth 2.0](http://oauth.net/2/ "OAuth 2.0 Reference"), a standard used by most APIs for authenticating and authorizing users. This guide will walk you through authenticating an Extension that wishes to use the SnapBundle™ API.

**Testing:** When the Account administrator toggles on [Development mode](DEVELOPMENT_MODE.md "Development Mode"), the `redirect_uri` for an Extension may be set to one of following local http endpoints suitable for development:     

* http://127.0.0.1
* http://0.0.0.0
* http://localhost.

Every _production_ Extension **must** have an HTTPS endpoint URL, or it will be omitted from the publicly searchable Extension catalog.

## Sample End-to-End OAuth Test
### Leg 1: Initiate the authentication and authorization process

Perform a `GET` or a `POST`  to `https://snapbundle.tagdynamics.net/v1/oauth2/authorize` with the following parameters:


### Leg 2: Exchange the Authorization Code for a Bearer Access Token
**curl** http://localhost:8080/v1/oauth2/token \  
-d 'grant_type=authorization_code&client_id=9830720b548243cb978b6767ba796c81&client_secret=8b851605e18644d9bd2f8ded0a9fdb1c&code=3af3ce03000fbf61757fc8862fa0657b36c0a00e' \  
-X POST  

```
{
   "tokenType" : "bearer",
   "token" : "e55efc4e834c13a85e283503d7a92a4e7fcb2b7f4d3019638bdff2a187f21a12975d264fec18258e",
   "expiresIn" : 3600,
   "refreshToken" : "47363427626bfe4378d02cfdf0fa2fe5672dc2aa572da7ab93a722f085cd5f43c0efd50e2f14daf7"
}
```
### Do a bunch of work on behalf of the authorizing Account
**curl** http://localhost:8080/v1/app/session \  
-H "Authorization: Bearer e55efc4e834c13a85e283503d7a92a4e7fcb2b7f4d3019638bdff2a187f21a12975d264fec18258e" 

```
{
   "urn" : "urn:uuid:d5fc741f-e6a7-48b6-bb40-631a1139c75c",
   "name" : "hostaccount.com Account",
   "description" : "Your SnapBundle(TM) Account",
   "activeFlag" : true,
   "encodedPublicKey" : "308201b73082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a038184000281806d33b405e7a36a443f8b17e11f7f4089ec46437524c15cb4985e163b71bef625959c0ebecb135e76e25b369c31a6cedfcdf8e04dc9d84103b84269a1a0cad59b32a0bece1f8a3a638b51a093065b51c87388ef3a1945962b8bdccf736bbb54cb35c766b2f07778d2e5ef70178d6fa23903b7ffef806ad7f41d75afd5971cf94c"
}
```

### Leg 3: Refresh the Bearer Access Token
**curl** http://localhost:8080/v1/oauth2/token \  
-d 'grant_type=refresh_token&refresh_token=47363427626bfe4378d02cfdf0fa2fe5672dc2aa572da7ab93a722f085cd5f43c0efd50e2f14daf7&client_id=9830720b548243cb978b6767ba796c81&client_secret=8b851605e18644d9bd2f8ded0a9fdb1c' \  
-X POST  

```
{
   "tokenType" : "bearer",
   "token" : "a0bd79e5b86ab60e2ed05c1f27fc3f40018d949b9e55cebfdc8043ad7c01b02878ac540554bde2e4",
   "expiresIn" : 3600,
   "refreshToken" : "403777ce93d98858a881beb7b51715df36c08894dc9be0ebe2fecb4774b8aecb141ecaf2aa06ff2e"
}
```
### Do _more_ work on behalf of the authorizing Account with the new Bearer Access Token

**curl** http://localhost:8080/v1/app/session \  
-H "Authorization: Bearer a0bd79e5b86ab60e2ed05c1f27fc3f40018d949b9e55cebfdc8043ad7c01b02878ac540554bde2e4"  

```
{
   "urn" : "urn:uuid:d5fc741f-e6a7-48b6-bb40-631a1139c75c",
   "name" : "hostaccount.com Account",
   "description" : "Your SnapBundle(TM) Account",
   "activeFlag" : true,
   "encodedPublicKey" : "308201b73082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a038184000281806d33b405e7a36a443f8b17e11f7f4089ec46437524c15cb4985e163b71bef625959c0ebecb135e76e25b369c31a6cedfcdf8e04dc9d84103b84269a1a0cad59b32a0bece1f8a3a638b51a093065b51c87388ef3a1945962b8bdccf736bbb54cb35c766b2f07778d2e5ef70178d6fa23903b7ffef806ad7f41d75afd5971cf94c"
}
```
