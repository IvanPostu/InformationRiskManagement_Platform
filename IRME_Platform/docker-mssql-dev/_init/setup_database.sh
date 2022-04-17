#!/usr/bin/env bash

#run the setup script to create the DB and the schema in the DB
#do this in a loop because the timing for when the SQL instance is ready is indeterminate
for i in {1..50};
do
    /opt/mssql-tools/bin/sqlcmd \
        -S localhost -U sa \
        -P ${MSSQL_SA_PASSWORD} \
        -d master -i ./_init/init.sql
        
    if [ $? -eq 0 ]
    then
        echo "STARTUP SQL SCRIPT EXECUTED WITH SUCCESS!!! (init.sql)"
        break
    else
        echo "Wait while the server rises to execute the initial script(s) [$i]"
        sleep 1
    fi
done


