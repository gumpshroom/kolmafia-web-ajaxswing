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

echo Updating asboot.jar for Xvfb compatibility...

$JAVA_HOME/bin/jar xf $AJAXSWING_HOME/lib/boot/asboot.jar
# Old asboot.jar should be removed as we don't want to have it inside a new asboot.jar
rm $AJAXSWING_HOME/lib/boot/asboot.jar
# remove X11GraphicsEnvironment.class as it's already present in environment in non-headless mode
rm sun/awt/X11GraphicsEnvironment.class
$JAVA_HOME/bin/jar cvf $AJAXSWING_HOME/lib/boot/asboot.jar -C $AJAXSWING_HOME/lib/boot/ .

echo Update complete, be sure to set agent.usePlatformGraphicsEnvironment=true in AjaxSwing/conf/default.properties
