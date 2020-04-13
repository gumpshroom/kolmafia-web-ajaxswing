#! /bin/sh
#
# Shell script to startup uhe server

if [ -z "$AJAXSWING_HOME" ] ;  then
  export AJAXSWING_HOME=..
fi

. ${AJAXSWING_HOME}/bin/setEnv.sh

if [ ! -z "$JAVA_HOME" ] ; then
   # If we know which JDK to use, call its registry
   "$JAVA_HOME"\bin\rmiregistry $*
else
   # Try to run the registry anyway
   rmiregistry $*
fi