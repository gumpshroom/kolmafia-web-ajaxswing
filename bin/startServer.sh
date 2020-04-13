#! /bin/sh
#
# Shell script to startup uhe server

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

# Make sure AjaxSwing libraries are compatible with the current JDK version
"$JAVA_HOME"/bin/java -Dajaxswing.home="$AJAXSWING_HOME" -classpath "$AJAXSWING_HOME/lib/bootstrap.jar" com.creamtec.core.Bootstrapper

# Add -security parameter to guarantee that AjaxSwing security manager doesn't conflict with Tomcat's security manager
# If you do it, make sure to grant AllPermission to your classes in tomcat/conf/catalina.policy
#$CATALINA_HOME/bin/startup.sh -security

exec $CATALINA_HOME/bin/startup.sh 
