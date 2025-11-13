#!/usr/bin/env sh
##############################################################################
# Gradle start up script for UN*X
##############################################################################

APP_BASE_NAME=`basename "$0"`
APP_HOME=`dirname "$0"`
if [ -z "$APP_HOME" ] ; then
  APP_HOME="."
fi

exec java -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
