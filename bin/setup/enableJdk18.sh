#! /bin/sh
#
# Shell script to install AjaxSwing libraries for JDK 1.8

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

if [ -r "$AJAXSWING_HOME"/bin/setEnv.sh ]; then
  . "$AJAXSWING_HOME"/bin/setEnv.sh
fi

echo Copying AjaxSwing libraries for JDK 1.8...

cp $AJAXSWING_HOME/lib/install/ajaxswing.jar.jdk1.8 $AJAXSWING_HOME/lib/ajaxswing.jar
cp $AJAXSWING_HOME/lib/install/asboot.jar.jdk1.8    $AJAXSWING_HOME/lib/boot/asboot.jar


. "$AJAXSWING_HOME"/bin/setup/applyPatches.sh 
  
echo Deleting temporary Tomcat files...
rm -r $AJAXSWING_HOME/tomcat/work/*

echo Done
