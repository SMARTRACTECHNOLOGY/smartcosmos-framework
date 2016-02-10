#set( $H = '#' )
#!/bin/sh

#
# This is an example archetype command, probably very similar
# to the one you ran to create this project. You don't need
# to run it here; we only put it here so you have a local
# copy which you can tune for creation of your own projects.
#

mvn archetype:generate -B \
    -DarchetypeGroupId=net.smartcosmos.extension \
    -DarchetypeArtifactId=smartcosmos-extension-archetype \
    -DarchetypeVersion=2.12.2-development-SNAPSHOT \
    -DgroupId=com.example \
    -DartifactId=example-server-extension \
    -Dversion=0.1.0-SNAPSHOT \
    -Dpackage=com.example.smartcosmos.server.extension.example
