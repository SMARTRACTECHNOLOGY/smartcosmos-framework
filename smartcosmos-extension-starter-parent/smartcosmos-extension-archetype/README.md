
# The SMART COSMOS Objects Extension Archetype

##Introduction

This document describes the SMART COSMOS Objects Extension Archetype, a Maven archetype which allows you to get off to 
a quick start building extensions for the SMART COSMOS Objects platform service.

**NOTE:** *There is absolutely, positively no need for you to clone this repository!* The Maven archetype itself is 
available via Maven Central, and by following the instructions below, you can build a Maven project based on this 
archetype on your local machine without having to clone this repository.

##Getting Started

### Creating the project

To create a Maven project for a new SMART COSMOS server extension, run the following command (notes on arguments below):

```bash
mvn archetype:generate \
	-B \
	-DarchetypeGroupId=net.smartcosmos.extension \
	-DarchetypeArtifactId=smartcosmos-extension-archetype \
	-DarchetypeVersion=LATEST-VERSION \
	-DgroupId=com.example \
	-DartifactId=foo-extension \
	-Dversion=0.1-SNAPSHOT \
	-Dpackage=com.example.extension.foo
```

**"-B"** means non-interactive; maven will simply create the project. Leave it out, and Maven will ask for any missing 
arguments, and for a confirmation before it does the actual generation.

**"-DarchetypeGroupId", "-DarchetypeArtifactId" and "-DarchetypeVersion"**  are the Maven coordinates for the SMART COSMOS 
Objects Extension archetype itself. Leave these as they are.

**"-DgroupId"** is where (typically) the top two levels of your URL go.

**"-DartifactId"** is the name of your extension project.
 
**"-Dversion"** is the initial version number of the generated project. 

Together, {groupId, artifactId, version} create the unique Maven coordinates for your generated Extension project. 
The command line above will result in coordinates in the generated pom.xml file that look like:

```
    <groupId>com.extension</groupId>
    <artifactId>foo-extension</artifactId>
    <version>0.1-SNAPSHOT</version>
    <packaging>jar</packaging>
```

**"-Dpackage"** defines the Java package name placed into the generated Java files. If you set this to 
*com.example.extension.foo*, the package statement would look like this: 


```
package com.example.extension.foo.smartcosmos.extension.jpa.impl;
```

and .java files in this package will reside in the following directory:
 
*src/main/java/com/example/extension/foo/smartcosmos/extension/jpa/impl*

**NOTE:** The archetype maintains the distinction between package and the groupId/artifactId combination. You have to 
set both, but your Java packaging does not have to match your Maven coordinates!

For your convenience, here is the command in a single line:

```
mvn archetype:generate -B -DarchetypeGroupId=net.smartcosmos.extension -DarchetypeArtifactId=smartcosmos-extension-archetype -DarchetypeVersion=2.10.13 -DgroupId=com.example -DartifactId=foo-extension -Dversion=0.1-SNAPSHOT -Dpackage=com.example.extension.foo
```

### Building and Running the Project

After running the command above, build your new project:

```
cd foo-extension
mvn install
```

When this completes successfully, you have a runnable SMART COSMOS extension which includes the server extension.
Look inside src/main/resources/objects.yml, and find the section that looks like this:

```
  # db username
  user: demo

  # db password
  password: demo

  # db JDBC URL
  url: jdbc:mysql://localhost/exampledb?autoReconnect=true

```

Either create a MySQL database with this database name/user name/user password combination, or adjust this part of 
objects.yml to match an existing empty MySQL database.

THIS SECTION NEEDS TO BE ADJUSTED TO MATCH NEW PACKAGING SCHEME

To start the server, run the following command:

```
java -jar target/foo-extension-1.0-SNAPSHOT.jar server src/main/resources/objects.yml
```

Open http://localhost:8080 in a browser, and you should see a page which includes the following text:
 
*SMART COSMOS Objects is up and running!*

Congratulations! You now have a running SMART COSMOS server on your local system. Now is the time to look at the 
README.md file in the generated project itself, and see what you can do with the SMART COSMOS platform.

# Learning More About SMART COSMOS

## EXPLORE
### SMART COSMOS Lessons
If you are interested in completing web-based training that, when successfully completed, authorizes you to describe
yourself as a SMART COSMOS Certified Engineer, please visit http://lessons.smart-cosmos.com/self-signup to create
an account. If you don't have a *code* required to complete the self-registration form, please reach out to us at
api@smartrac-group.com. From time to time we have free accounts that we can issue to developers pursuing certification.

## CREATE
### SMART COSMOS Partner Portal
We categorize both individual developers and companies as partners, and we invite you to register as a parter at
https://partner.smart-cosmos.com. Once registered, you'll have access to SMART COSMOS white papers, information about
any current contests that may be running, and the opportunity to register as a Seller on the SMART COSMOS Market.

## SELL
### SMART COSMOS Market
The SMART COSMOS Market helps developers *Monetize the IoT*. After creating your amazing Extensions, you can upload 
them to the Market, set your recurring billing model, your price, and enter other marketing related information. Once
your Extension has passed the test harness and been validated by SMARTRAC, you can then decide if you want to publish
your work and monetize it.




