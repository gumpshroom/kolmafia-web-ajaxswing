#! /bin/sh
#
# Shell script to start/stop AjaxSwing

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

case "$1" in
    start)
        echo ""

        # Uncomment the following lines to run X Virtual Frame Buffer that provides simulated graphics environment
        echo "Running Xvfb"
        exec Xvfb :1 -screen 0 640x480x24 -nolisten tcp  &
        echo "Running ajaxSwing"
        exec $AJAXSWING_HOME/bin/startServer.sh
        ;;

    stop)
        exec $AJAXSWING_HOME/bin/stopServer.sh
        ;;
esac

exit 0
