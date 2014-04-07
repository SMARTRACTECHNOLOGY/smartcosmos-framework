![alt text](images/snapbundle.png "Title")

# Platform API Documentation
  * The authoritative REST documentation can be found at [http://docs.snapbundle.apiary.io](http://docs.snapbundle.apiary.io "SnapBundle at apiary").
  * The latest stable Model SDK JavaDoc can be found at [http://snapbundle.github.io/sdk/stable/index.html](http://snapbundle.github.io/sdk/stable/index.html "SnapBundle Stable Model SDK").

### Extension Architecture
The SnapBundle platform was designed from scratch with a plugin model; we call them *extensions*. Remember how we said
that we aren't an analytical engine? We aren't, but maybe you are. Use any modern language- Python, Ruby, Java, .NET, or
anything that can talk to us using HTTP and JSON. We built our authorization model around the
[OAuth 2.0 Specification (RFC 6749)](http://tools.ietf.org/html/rfc6749 "OAuth 2.0 Specification"), and data owners can
grant you read or write access in a very granular fashion.

## Table of Contents
  * [Primary Data Types](DATA_TYPES.md#primary "Primary Data Type")
  * [Secondary Data Types](DATA_TYPES.md#secondary "Secondary Data Type")
  * [Extending the Platform](EXTENSION_FRAMEWORK.md "Extension Framework")
  * [OAuth 2.0 AuthN and AuthZ](OAUTH_GUIDE.md "OAuth 2.0 with Extensions")
 
