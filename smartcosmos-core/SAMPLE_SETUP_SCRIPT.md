#### Request Account
curl https://objects.smartcosmos.net/v1/public/registration/register \
-d '{"emailAddress":"you@example.com", "realm" : "example.com"}' \
-H "Content-Type:application/json" \  
-X POST  

```
{
   "urn" : "urn:uuid:64618d0b-3b8c-456a-87d0-31bc1a0bc579",
   "lastModifiedTimestamp" : 1382828220349,
   "emailAddress" : "you@example.com",
   "realm" : "example.com",
   "emailVerificationToken" : "ISSDRGAROMPE"
}

```
#### Verify Account
curl https://objects.smartcosmos.net/v1/public/registration/confirm/ISSDRGAROMPE/you@foo.com

```
{
   "result" : 1,
   "message" : "1JPOAIOAML3#"
}

```
#### Change Account Password
curl https://objects.smartcosmos.net/v1/app/password \
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
#### Create an Account for Extensions
curl https://objects.smartcosmos.net/v1/public/registration/register \
-d '{"emailAddress":"them@bar.com", "realm" : "therustedroof.com"}' \  
-H "Content-Type:application/json" \  
-X POST  

```
{
   "urn" : "urn:uuid:fc64e9d6-47bf-45b7-86b9-74be2808d288",
   "lastModifiedTimestamp" : 1382830791841,
   "emailAddress" : "them@bar.com",
   "realm" : "therustedroof.com",
   "emailVerificationToken" : "WNRPQRMDKHBO"
}

```
#### Confirm the Account for Extensions
curl https://objects.smartcosmos.net/v1/public/registration/confirm/WNRPQRMDKHBO/them@bar.com

```
{
   "result" : 1,
   "message" : "1RPULZYODH8@"
}

```
#### Change the Account for Extensions Password
curl https://objects.smartcosmos.net/v1/app/password \
-d '{"emailAddress":"them@bar.com", "newPassword" : "password123"}' \  
-H "Content-Type:application/json" \  
-u them@bar.com:1RPULZYODH8@ \  
-X POST  

```
{
   "result" : 1,
   "message" : "Password successfully changed"
}

```
#### Define an Extension
curl https://objects.smartcosmos.net/v1/admin/extensions \  
-d '{"name" : "Event Stream Extension", \  
"redirectUrl" : "https://objects.smartcosmos.net/extension/registration/complete", \  
"shortDescription" : "short descr", \  
"extensionType" : "Reporting", \  
"permissions" :[{"permission" : "EventStream"}]}'  \  
-H "Content-Type:application/json" \  
-u them@bar.com:password123 \  
-X PUT  

```
{
   "urn" : "urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043",
   "clientId" : "973d6f32bcd2422a813b237e171d3941",
   "clientSecret" : "a46d69bcdc3c48d5a723ab7d5f754a84",
   "redirectUri" : "https://objects.smartcosmos.net/extension/registration/complete"
}

```

#### Turn on Developer Mode on Account for Extensions
curl https://objects.smartcosmos.net/v1/admin/account/configure/developer/true \
-u them@bar.com:password123 \  
-H "Content-Type:application/json" \  
-X POST


#### Enroll an Integration Endpoint on Account for Extensions
curl https://objects.smartcosmos.net/v1/admin/extensions/  urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043/integration/enroll \
-u them@bar.com:password123 \  
-d '{"integrationEndpointUrl" : "https://objects.smartcosmos.net/stream/event/sink"}' \
-H "Content-Type:application/json" \  
-X PUT  

```
{
   "result" : 1,
   "message" : "Ensure you confirm the subscription with the registration token"
}

```

##### Watch the Web Server Log for the Subscription Confirmation Message from AWS

```
Oct 27, 2013 6:57:28 PM com.snapbundle.demo.event.EventSinkResource processSubscriptionEvent
INFO: ASW subscription confirmation token:

2336412f37fb687f5d51e6e241d164b05333005609dc4f12b6c9501c15c59b3db7e63a3323efbe3a08bb40e30751a77eaeacffcd709f71b74b0215211870d9ae0cb3a4afecefd6c1e3c97e066deefae7c837fc514a67ca5d802a92d64e3be19825a5cc81a6d4bfffe661cab85e01111623705fbbf348e1340f19265ded9aec60f2cc1389c443212c28504a07656b5220

Oct 27, 2013 6:57:28 PM org.restlet.engine.log.LogFilter afterHandle
INFO: 2013-10-27	18:57:28	72.21.217.96	-	10.224.117.153	443	POST	/stream/event/sink	-	204	0	1723	127 https://objects.smartcosmos.net Amazon Simple Notification Service Agent	-
```

#### Confirm Integration Endpoint Subscription on Account for Extensions
curl https://objects.smartcosmos.net/v1/admin/extensions/urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043/integration/confirm/2336412f37fb687f5d51e6e241d164b05333005609dc4f12b6c9501c15c59b3db7e63a3323efbe3a08bb40e30751a77eaeacffcd709f71b74b0215211870d9ae0cb3a4afecefd6c1e3c97e066deefae7c837fc514a67ca5d802a92d64e3be19825a5cc81a6d4bfffe661cab85e01111623705fbbf348e1340f19265ded9aec60f2cc1389c443212c28504a07656b5220 \
-u them@bar.com:password123 \  
-X POST  


```
{
   "message" : "Integration endpoint URL has been confirmed",
   "result" : 1
}

```

#### Activate Extension Sample
curl https://objects.smartcosmos.net/v1/admin/extensions \
-u them@bar.com:password123 \  
-d '{"urn" : "urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043", "activeFlag" : true}' \  
-H "Content-Type:application/json" \  
-X POST

```   
{
   "urn" : "urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043",
   "lastModifiedTimestamp" : 0,
   "moniker" : null,
   "name" : "Event Stream Extension",
   "description" : null,
   "activeFlag" : true,
   "encodedPublicKey" : "308201b83082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a038185000281810086d0d4f41a5559257e4a016f78a70f0f4d82cedaefb0ff185d178221cdfea4db5ad5da0147a5eeb0266c23fa6b99dbd19802cbd81c24a97f69acd4cc7f6c98068582a9c75efe010f5bbfac100094999b2a8c7f5c31e781337c9ef6ddcf8c7b45fd243bb4c8807bb3719b29e97bc0121e7f7ca21ea210bfd1acf1e04f28534684",
   "integrationEndpointUrl" : "https://objects.smartcosmos.net/stream/event/sink",
   "pendingConfirmation" : false,
   "supportEmail" : "them@bar.com",
   "webSiteUrl" : null,
   "clientId" : "973d6f32bcd2422a813b237e171d3941",
   "clientSecret" : "a46d69bcdc3c48d5a723ab7d5f754a84",
   "redirectUrl" : "https://objects.smartcosmos.net/extension/registration/complete",
   "appCatalogUrl" : "public/extensions/urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043",
   "shortDescription" : "short descr",
   "longDescription" : null,
   "extensionType" : "Reporting",
   "version" : 91
}

```
#### Verify Extension is Active and Published
curl https://objects.smartcosmos.net/v1/public/extensions

```
[
   {
      "activeFlag" : true,
      "appCatalogUrl" : "public/extensions/urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043",
      "description" : null,
      "encodedPublicKey" : "308201b83082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a038185000281810086d0d4f41a5559257e4a016f78a70f0f4d82cedaefb0ff185d178221cdfea4db5ad5da0147a5eeb0266c23fa6b99dbd19802cbd81c24a97f69acd4cc7f6c98068582a9c75efe010f5bbfac100094999b2a8c7f5c31e781337c9ef6ddcf8c7b45fd243bb4c8807bb3719b29e97bc0121e7f7ca21ea210bfd1acf1e04f28534684",
      "extensionType" : "Reporting",
      "lastModifiedTimestamp" : 0,
      "name" : "Event Stream Extension",
      "shortDescription" : "short descr",
      "supportEmail" : "them@bar.com",
      "urn" : "urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043",
      "webSiteUrl" : null
   }
]

```
