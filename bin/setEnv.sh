#! /bin/sh
#
# Edit this file to setup AjaxSwing environment and your application parameters

# JAVA_HOME   		- optional environment variable
# AJAXSWING_HOME 	- can be set to explicitly specify AjaxSwing installation directory.
# 			  If not set, AjaxSwing will attempt to locate it automatically
# JAVA_OPTS      	- Add your application options such as -D parameters to JVM here,
# 			  for example JAVA_OPTS=-Dajaxswing.home=c:\ajaxswing4.0
# KEEP_CURRENT_DIR 	- If set to any value, for example "true", AjaxSwing will preserve the current directory,
# 			  otherwise AjaxSwing will change directory to $AJAXSWING_HOME\wcapps
#                         Note, that if this variable is used AJAXSWING_HOME must be set to a full path to
# 			  AjaxSwing installation directory.


# export JAVA_HOME=/usr/jdk1.3
# export AJAXSWING_HOME=
# export JAVA_OPTS=
# export KEEP_CURRENT_DIR=true
DISPLAY=:0.0
export DISPLAY

# -------------------------------------------------------------------------------------------------------

if [ -z "$AJAXSWING_HOME" ] ;  then
  AJAXSWING_HOME=..
  export AJAXSWING_HOME
fi

if [ ! -f "$AJAXSWING_HOME/bin/startServer.sh" ] ;  then
    echo AJAXSWING_HOME does not point to AjaxSwing installation directory. Try setting it manually in setEnv.sh
    exit 1
fi


# Change to the apps directory for AjaxSwing
if [ -z "$KEEP_CURRENT_DIR" ] ;  then
  cd "$AJAXSWING_HOME/wcapps"
fi


# java command line options required for AjaxSwing
JAVA_OPTS="${JAVA_OPTS} -Xbootclasspath/p:${AJAXSWING_HOME}/lib/boot/asboot.jar -mx312m -Dajaxswing.home=${AJAXSWING_HOME}"

# java command line option that need for the Linux OS
JAVA_OPTS="${JAVA_OPTS} -Djava.awt.headless=false"

# Determine the JDK fonts directory
if [ -z "$JAVA_FONTS_DIR" ] ;  then
  if [ -d ${JAVA_HOME}/jre/lib/fonts ]; then
    JAVA_FONTS_DIR=${JAVA_HOME}/jre/lib/fonts
  else
    if [ -d ${JAVA_HOME}/lib/fonts ]; then
      JAVA_FONTS_DIR=${JAVA_HOME}/lib/fonts
    fi
  fi
fi

if [ ! -d ${JAVA_FONTS_DIR} ]; then
  echo "** WARNING **: unable to find Java fonts directory ${JAVA_FONTS_DIR}. Set JAVA_HOME to JDK installation directory or set JAVA_FONTS_DIR environment variable to point to the fonts directory"
  echo " "
fi

JAVA_OPTS="${JAVA_OPTS} -Djava.awt.fonts=${AJAXSWING_HOME}/lib/fonts:${JAVA_FONTS_DIR}"
if [ "`uname -s`" = "AIX" ]; then
    JAVA_OPTS="$JAVA_OPTS -Djava2d.font.usePlatformFont=true"
fi

# Uncomment for remote debugging
# JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"

# Uncomment for connecting JConsole
# JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8004 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

export JAVA_OPTS

# Built-in Tomcat home
CATALINA_HOME="${AJAXSWING_HOME}/tomcat"
export CATALINA_HOME

. "${AJAXSWING_HOME}/bin/_setcp.sh"
