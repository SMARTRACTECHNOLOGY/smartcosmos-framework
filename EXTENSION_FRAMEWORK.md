# Extension Framework
The SMART COSMOS™ Extension security model uses the open standard OAuth 2.0 specification. For a detailed explanation of
the SMART COSMOS™ OAuth security model, please refer to the SMART COSMOS™ OAuth 2.0 Guide _once you've finished setting up
your Extension._

## Extension
The SMART COSMOS™ Extension is a declaration of an external actor that may be granted access to one or more SMART COSMOS™
Accounts where access is restricted to a finite set of permissions.

##Extension Permissions
Extension authorization is managed using the OAuth 2.0 `scope` concept. As such, an extension declares what permissions
it is seeking during the OAuth 2.0 handshake. If the user authorizes the scopes requested by the extension, then the
extension has those permissions until the token is either revoked or permanently expires.

Review the [Permission Types](DATA_TYPES.md#pt "Permission Type") data types for a listing of available scope names.

#### Setup an HTTPS Endpoint for the Event Sink
Consider using the sample web application at <https://github.com/SMARTRACTECHNOLOGY/samples/> for this step until you are comfortable with the architecture of an event sink.

#### Define the Extension Integration Endpoint for Event Stream Delivery
**curl** https://objects.smartcosmos.net/v1/admin/extensions/urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043/integration/enroll \
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
Simultaneously, the **AWS Simple Notification Service** will POST an SubscriptionConfirmation message to your endpoint. You _**MUST**_ take the `Token` from that POST and submit it through the SMART COSMOS™ platform. Here is a sample from the web log you can expect to see if you are using the SMART COSMOS™ sample **stream.war** web application:

````
Oct 27, 2013 6:57:28 PM com.snapbundle.demo.event.EventSinkResource processSubscriptionEvent
INFO: ASW subscription confirmation token:

2336412f37fb687f5d51e6e241d164b05333005609dc4f12b6c9501c15c59b3db7e63a3323efbe3a08bb40e30751a77eaeacffcd709f71b74b0215211870d9ae0cb3a4afecefd6c1e3c97e066deefae7c837fc514a67ca5d802a92d64e3be19825a5cc81a6d4bfffe661cab85e01111623705fbbf348e1340f19265ded9aec60f2cc1389c443212c28504a07656b5220


Oct 27, 2013 6:57:28 PM org.restlet.engine.log.LogFilter afterHandle
INFO: 2013-10-27	18:57:28	72.21.217.96	-	10.224.117.153	443	POST	/stream/event/sink	-	204	0	1723	127	https://objects.smartcosmos.net	Amazon Simple Notification Service Agent	-
````

Using the AWS subscription confirmation token echoed above, confirm the subscription _**throught**_ SMART COSMOS™:

**curl** https://objects.smartcosmos.net/v1/admin/extensions/urn:uuid:bc834586-4778-4c39-89f8-55e031dc6043/integration/confirm/2336412f37fb687f5d51e6e241d164b05333005609dc4f12b6c9501c15c59b3db7e63a3323efbe3a08bb40e30751a77eaeacffcd709f71b74b0215211870d9ae0cb3a4afecefd6c1e3c97e066deefae7c837fc514a67ca5d802a92d64e3be19825a5cc81a6d4bfffe661cab85e01111623705fbbf348e1340f19265ded9aec60f2cc1389c443212c28504a07656b5220 \
-u them@bar.com:password123 \  
-X POST


```
{
   "message" : "Integration endpoint URL has been confirmed",
   "result" : 1
}

```

At this point, the Extension is fully configured for Event Stream notification, if it is a permission requested by your extension. Regardless of whether the Extension requests the `EventStream` Permission Type or not, _**all of these steps must be completed**_.

#### Activate the Extension
The final step is to _activate_ your Extension, which has the effect of publishing your extension, announcing to all that your Extension is ready for business.  

**curl** https://objects.smartcosmos.net/v1/admin/extensions \
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

#### [Published Extension Catalog](id:published)
You may choose to verify that your Extension is published by using the **/public** space to query all published Extension objects.

**curl** https://objects.smartcosmos.net/v1/public/extensions

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
 
