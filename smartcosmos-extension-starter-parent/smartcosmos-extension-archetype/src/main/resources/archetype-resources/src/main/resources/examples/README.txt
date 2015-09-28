
# INTRODUCTION

This file (NOT intended to be run as a shell script) contains instructions for creating an initial account/user (by default bunkhouse@banzai.com,
with password Buckar00) and running a few curl commands for a basic test of the functionality provided in the SMART COSMOS example extension,
created from the SMART COSMOS Server Extension archetype.

Creation of an extension project based on the archetype is described here:

https://smartractechnology.atlassian.net/wiki/display/SCKB/Building+SMART+COSMOS+Server+Extensions+with+the+Sever+Extension+Archetype

and the following assumes you've done this, started the project, and reviewed the relevant source code, as described here:

https://smartractechnology.atlassian.net/wiki/display/SCKB/Creating+Data+Entities+and+Access+Points+in+SMART+COSMOS
https://smartractechnology.atlassian.net/wiki/display/SCKB/Creating+and+Using+Transaction+Handlers+in+SMART+COSMOS
https://smartractechnology.atlassian.net/wiki/display/SCKB/Using+Visitors+in+SMART+COSMOS

# A NOTE ABOUT APACHEDS

By default, the developer/devkit versions of SMART COSMOS Objects use ApacheDS as a directory service. ApacheDS creates two directories
in your workspace: apacheds and server-work. If you need to go back to a completely fresh database, i.e., with no users or accounts,
you will need to remove these directories as well before you start the server. The server will recreate them, empty, at startup if
they are not already there. If you prefer to manipulate the entries in ApacheDS instead of starting from scratch, help yourself.

# A NOTE ABOUT LOGGING

For the sake of keeping things shorter and easier to read, in the example extension code you will find logging code only in
MoreInterestingExampleExtensionPutRequestHandler and MoreInterestingExampleExtensionUpdateRequestHandler. You should, as a general rule,
insert logging code following these examples everywhere in your response handlers that a response is returned.

# THE COMMANDS

# Here we make sure the platform is up. This only checks the Jetty server; to verify our server point a browser at localhost:8080.
curl -XGET http://localhost:8080/admin/ping

# Here we register a user.
WE NEED THE EMAIL VERIFICATION TOKEN FOR THE NEXT STEP, SO KEEP TRACK OF IT!
curl -XPOST -H 'Content-Type:application/json' http://localhost:8080/rest/registration/register --data @register.json

# Here we confirm the registration, replacing SHMJHOIUYAMQ with the email verification token (See? Told you we'd need it!) from above.
# WE NEED THE ASSIGNED PASSWORD FOR THE NEXT STEP, SO KEEP TRACK OF IT!
curl -XGET -H 'Content-Type:application/json' http://localhost:8080/rest/registration/confirm/SHMJHOIUYAMQ/bunkhouse@banzai.com

# Now you have to do two things:
# 1. Update the changePassword.json file with the password in the response above (See? Told you we'd need it!).
# 2. Generate an Authorization: Basic string (see below) for the username and password, which we'll use to authenticate the password change. Once
#    that's done, all the commands that follow you can use as-is, because the Authorization: Basic string is for bunkhouse@banzai.com:Buckar00,
#    which is what we're setting the password to.
#
# To generate a Authorization: Basic string, we provide for your convenience a utility class (with main method) called BasicAuth, in this directory.
# To use it, just run (with the assigned password from above as argument):
#
# $ java BasicAuth '1GJVYHCKo2*2'
#
# then replace the Authorization: Basic string in the next line with the result. After that, user bunkhouse@banzai.com with password Buckar00
# is an admin user in the system, and you can leave all the following Authorization: Basic strings alone.
#
# NOTE: BasicAuth can also be called with username and password, i.e, 
#
# $ java BasicAuth 'MyTestUser@ourcompany.com' 'ExCeL1eNtP@sSw0rD'
#
curl -XPOST -H 'Content-Type:application/json' -H "Authorization: Basic YnVua2hvdXNlQGJhbnphaS5jb206MUdKVllIQ0tvMioy" http://localhost:8080/rest/account/password/change --data @changePassword.json

#
# OK, now we have a user, bunkhouse@banzai.com, with password Buckar00, and Authorization: Basic string YnVua2hvdXNlQGJhbnphaS5jb206QnVja2FyMDA=
#

# Here we put a pair of strings into our example table.
curl -XPUT -H 'Content-Type:application/json' -H "Authorization: Basic YnVua2hvdXNlQGJhbnphaS5jb206QnVja2FyMDA=" http://localhost:8080/rest/extension/example/putTwoStrings --data @twoStrings.json

# Here we insert an object, so we have something to reference when we add something to our moreInterestingExampleTable.
curl -XPUT -H 'Content-Type:application/json' -H "Authorization: Basic YnVua2hvdXNlQGJhbnphaS5jb206QnVja2FyMDA=" http://localhost:8080/rest/objects --data @oneObject.json

# NOTE: At this point you'll want to look at the log file, and see that the object visitors are in fact being called.

# Here we put two strings into our moreInterestingExample table, with the object we inserted in the command above as reference object (see the respective .json files in this directory).
curl -XPUT -H 'Content-Type:application/json' -H "Authorization: Basic YnVua2hvdXNlQGJhbnphaS5jb206QnVja2FyMDA=" http://localhost:8080/rest/extension/moreInterestingExample/moreInterestingPutTwoStrings --data @twoStringsMoreInteresting.json

# Now let's test our transaction handler. As mentioned in the source code, the "message" field in the response is populated with the platform-assigned transaction ID.
curl -XPOST -H 'Content-Type:application/json' -H "Authorization: Basic YnVua2hvdXNlQGJhbnphaS5jb206QnVja2FyMDA=" http://localhost:8080/rest/transaction/myExampleTransactionHandler --data @objectsCollectionForTransaction.json

# And that's pretty much the outline. We hope this helped.



