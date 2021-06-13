#!/bin/sh

# Liquibase tasks:
# changelogSync, changelogSyncSQL, changelogSyncToTag, 
# changelogSyncToTagSQL, clearCheckSums, dbDoc, deactivateChangeLog, 
# diff, dropAll, futureRollbackSQL, generateChangeLog, help, 
# history, listLocks, registerChangeLog, releaseLocks, rollback, 
# rollbackOneChangeSet, rollbackOneChangeSetSQL, 
# rollbackOneUpdate, rollbackOneUpdateSQL, rollbackSQL, 
# status, syncHub, tag, update, updateSQL, updateTestingRollback

SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR=`dirname $SCRIPTPATH`
isError=0

source $SCRIPTPATH/__common.sh

processArgumentsSimpleImpl $@

if [ "$g_contexts" = "" ]
then 
    echo "${red}Please specify --contexts param, example --contexts \"dev,default,test\"${reset}"
    isError=1
fi

if [ "$g_task" = "" ]
then 
    echo "${red}Please specify --task param, example --task \"status\"${reset}"
    isError=1
fi

if [ "$g_profile" = "" ]
then 
    echo "${red}Please specify --profile param, example --profile [test|dev|prod]${reset}"
    isError=1
fi

if [[ $isError -ne 0 ]]; then
    exit -1
fi

$ROOT_PROJECT_DIR/mvnw \
    -f ./irme-db-migration/pom.xml \
    process-resources \
    liquibase:$g_task \
    "-Dprofile=${g_profile}" \
    "-Dliquibase.contexts=${g_contexts}" \

exit $?

