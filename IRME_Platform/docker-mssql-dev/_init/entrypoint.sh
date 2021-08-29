#!/usr/bin/env bash
set -m
    /opt/mssql/bin/sqlservr & /bin/bash ./_init/setup_database.sh 
fg

