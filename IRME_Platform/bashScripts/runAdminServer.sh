#!/bin/sh

ACTIVE_PROFILE="dev"

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."


$ROOT_PROJECT_DIR/mvnw -f ./irme-central-server/irme-admin-mvc/pom.xml spring-boot:run \
  -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n, -Dspring.profiles.active=$ACTIVE_PROFILE"

