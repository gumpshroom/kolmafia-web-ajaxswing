#! /bin/sh
#
# Shell script to setup AjaxSwing after the installation


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
[ -z "$AJAXSWING_HOME" ] && AJAXSWING_HOME=`cd "$PRGDIR/../.." ; pwd`

echo Setting files permissions...
chmod 777 $AJAXSWING_HOME/bin/*.sh
chmod 777 $AJAXSWING_HOME/bin/setup/*.sh
chmod 777 $AJAXSWING_HOME/tomcat/bin/*.sh

echo Setup complete
