#! /bin/sh
#

# SYNTAX: ClientAgent <appName> <rmiPort> <id>


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

if [ -r "$AJAXSWING_HOME"/bin/setEnv.sh ]; then
  . "$AJAXSWING_HOME"/bin/setEnv.sh
fi

# Make sure AjaxSwing libraries are compatible with the current JDK version
"$JAVA_HOME"/bin/java -Dajaxswing.home=$AJAXSWING_HOME -classpath $AJAXSWING_HOME/lib/bootstrap.jar com.creamtec.core.Bootstrapper

JAVA_OPTS="${JAVA_OPTS} -Xbootclasspath/p:${AJAXSWING_HOME}/lib/boot/asboot.jar -Dajaxswing.home=${AJAXSWING_HOME}"
JAVA_OPTS="${JAVA_OPTS} -Djava.rmi.dgc.leaseValue=5000"

JAVA_OPTS="${JAVA_OPTS} -Djava.awt.fonts=${JAVA_HOME}/jre/lib/fonts"
# Use the line below instead of the line above to make component sizes to be the same as on Windows
# JAVA_OPTS="${JAVA_OPTS} -Djava.awt.fonts=${AJAXSWING_HOME}/lib/fonts"


$JAVA_HOME/bin/java $JAVA_OPTS com.creamtec.ajaxswing.JVMProcess $1 $2 $3 >$AJAXSWING_HOME/logs/$1_$3.out 2>&1 &

