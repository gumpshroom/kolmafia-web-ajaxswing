#! /bin/sh

if [ -z "$AJAXSWING_HOME" ] ;  then
  echo AJAXSWING_HOME must be set to point to AjaxSwing installation directory
  exit 1
fi

# Add your jars here
APP_CLASSPATH=

WC_CLASSPATH=${AJAXSWING_HOME}/wcapps/lib/classes

# Add all additional libs from lib directory to classpath
for file in ${AJAXSWING_HOME}/lib/*.jar; do WC_CLASSPATH=$WC_CLASSPATH:$file; done
for file in ${AJAXSWING_HOME}/lib/*.zip; do WC_CLASSPATH=$WC_CLASSPATH:$file; done

# Add all additional libs from wcapps/lib directory to classpath
for file in ${AJAXSWING_HOME}/wcapps/lib/*.jar; do WC_CLASSPATH=$WC_CLASSPATH:$file; done
for file in ${AJAXSWING_HOME}/wcapps/lib/*.zip; do WC_CLASSPATH=$WC_CLASSPATH:$file; done

CLASSPATH=$WC_CLASSPATH:$CLASSPATH

# Have to add servlet.jar because webapp is loaded from CLASSPATH, not using standard Tomcat's classloader
CLASSPATH=${CATALINA_HOME}/lib/servlet-api.jar:$CLASSPATH

export CLASSPATH
#echo CLASSPATH=$CLASSPATH
