#!/bin/sh

ACTIVE_PROFILE="dev"

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."


$ROOT_PROJECT_DIR/mvnw -f ./irme-central-server/irme-admin-mvc/pom.xml \
    jetty:run

