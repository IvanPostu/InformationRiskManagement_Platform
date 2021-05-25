#!/bin/sh

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."

if [ "$1" = "" ]
then 
    exit -1
fi

$ROOT_PROJECT_DIR//mvnw -f ./irme-db-migration/pom.xml \
    process-resources \
    liquibase:$1 "-Dprofile=dev" \


