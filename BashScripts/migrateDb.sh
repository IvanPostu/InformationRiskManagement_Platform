#!/bin/sh

# changelogSync, changelogSyncSQL, changelogSyncToTag, 
# changelogSyncToTagSQL, clearCheckSums, dbDoc, deactivateChangeLog, 
# diff, dropAll, futureRollbackSQL, generateChangeLog, help, 
# history, listLocks, registerChangeLog, releaseLocks, rollback, 
# rollbackOneChangeSet, rollbackOneChangeSetSQL, 
# rollbackOneUpdate, rollbackOneUpdateSQL, rollbackSQL, 
# status, syncHub, tag, update, updateSQL, updateTestingRollback

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."
DEFAULT_CONTEXTS="default,dev"

if [ "$1" = "" ]
then 
    echo 'Please specify liquibase task as first argument, for example: dropAll or update'
    exit -1
fi

$ROOT_PROJECT_DIR//mvnw -f ./irme-db-migration/pom.xml \
    process-resources \
    liquibase:$1 "-Dprofile=common" "-Dcontexts=${DEFAULT_CONTEXTS}" \


