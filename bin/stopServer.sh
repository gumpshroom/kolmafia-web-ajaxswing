#! /bin/sh
#
# Shell script to shutdown the web server

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '.*/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`

# Only set AJAXSWING_HOME if not already set
[ -z "$AJAXSWING_HOME" ] && AJAXSWING_HOME=`cd "$PRGDIR/.." ; pwd`

echo Using AJAXSWING_HOME=$AJAXSWING_HOME

if [ -r "$AJAXSWING_HOME"/bin/setEnv.sh ]; then
  . "$AJAXSWING_HOME"/bin/setEnv.sh
fi

exec $CATALINA_HOME/bin/shutdown.sh

