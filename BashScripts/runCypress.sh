#!/bin/sh


SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`
ROOT_PROJECT_DIR="$SCRIPTPATH/.."


npm --prefix $ROOT_PROJECT_DIR/cypress-integration-tests run cypress:open
