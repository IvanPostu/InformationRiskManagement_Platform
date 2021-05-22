#!/bin/sh

ACTIVE_PROFILE="dev"

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."

ATTACH_DEBUGGER_FLAG='-a'

if [ "$1" = "$ATTACH_DEBUGGER_FLAG" ]
then 
    $ROOT_PROJECT_DIR/mvnw -f ./irme-central-server/pom.xml spring-boot:run \
        -Dspring-boot.run.jvmArguments="-Xdebug \
        -Xrunjdwp:transport=dt_socket,\
        server=y,suspend=y,address=5005 \
        -Dspring.profiles.active=$ACTIVE_PROFILE"
else
    echo "If you want to debug remotely add flag $ATTACH_DEBUGGER_FLAG"

    $ROOT_PROJECT_DIR/mvnw -f ./irme-central-server/pom.xml spring-boot:run \
        -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=$ACTIVE_PROFILE"
fi


