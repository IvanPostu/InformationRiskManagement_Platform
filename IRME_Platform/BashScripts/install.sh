#!/bin/sh

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."


$ROOT_PROJECT_DIR/mvnw -f ./irme-central-server/pom.xml install -DskipTests=true

