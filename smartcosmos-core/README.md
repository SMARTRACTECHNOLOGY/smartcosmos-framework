<img src="http://smartractechnology.github.io/smartcosmos-sdk-java/images/smart-cosmos-250px-wide.png"/>

# SMART COSMOS REST Web Services Documentation
  * Profiles REST documentation: [http://docs.smartcosmosprofiles.apiary.io](http://docs.smartcosmosprofiles.apiary.io "Profiles at apiary").
  * Objects REST documentation: [http://docs.smartcosmosobjects.apiary.io](http://docs.smartcosmosobjects.apiary.io "Objects at apiary").
  * Flows REST documentation: [http://docs.smartcosmosflows.apiary.io](http://docs.smartcosmosflows.apiary.io "Flows at apiary").

# SMART COSMOS JavaDoc (stable)
  * The latest stable model SDK JavaDoc can be found at [http://SMARTRACTECHNOLOGY.github.io/smartcosmos-sdk-java/stable/index.html](http://SMARTRACTECHNOLOGY.github.io/smartcosmos-sdk-java/stable/index.html "Stable JavaDoc URL").

# Maven
Java developers that rely on Maven can access the SMART COSMOS Model SDK using the following coordinates:

    <dependency>
      <groupId>net.smartcosmos</groupId>
      <artifactId>sdk</artifactId>
      <version>2.7</version>
    </dependency>

Likewise, Java developers who want to use the SMART COSMOS Client library for easy access to the platform, eliminating
the need to deal directly with HTTP, JSON, and REST calls, the Maven coordinates are:

    <dependency>
      <groupId>net.smartcosmos</groupId>
      <artifactId>client</artifactId>
      <version>1.3</version>
    </dependency>

### Extension Architecture (EXPERIMENTAL)
The SMART COSMOS platform was designed from scratch with a plugin model; we call them *extensions*. Use any modern
language- Python, Ruby, Java, .NET, or anything that can talk to us using HTTP and JSON. We built our authorization
model around the [OAuth 2.0 Specification (RFC 6749)](http://tools.ietf.org/html/rfc6749 "OAuth 2.0 Specification"),
and data owners can grant you read or write access in a very granular fashion.

## Platform Data Types
  * [Primary Data Types](https://github.com/SMARTRACTECHNOLOGY/smartcosmos-objects-api/blob/master/DATA_TYPES.md#primary "Primary Data Type")
  * [Secondary Data Types](https://github.com/SMARTRACTECHNOLOGY/smartcosmos-objects-api/blob/master/DATA_TYPES.md#secondary "Secondary Data Type")

