#!  /bin/sh

if [ -z "$AJAXSWING_HOME" ] ;  then
  export AJAXSWING_HOME=..
fi

#. "$AJAXSWING_HOME"/bin/setEnv.sh

# Make sure AjaxSwing libraries are compatible with the current JDK version
"$JAVA_HOME"/bin/java -Dajaxswing.home="$AJAXSWING_HOME" -classpath "$AJAXSWING_HOME"/lib/bootstrap.jar com.creamtec.core.Bootstrapper
  
cd $AJAXSWING_HOME/wcapps

export CLASSPATH=$CLASSPATH:$AJAXSWING_HOME/lib/ajaxswing.jar:$AJAXSWING_HOME/lib/boot/asboot.jar

# Reset JAVA_OPTS to append AjaxSwing boot classes instead of prepending them. This helps avoid java.awt.Component conflicts
JAVA_OPTS="-Xbootclasspath/a:${AJAXSWING_HOME}/lib/boot/asboot.jar -Dajaxswing.home=${AJAXSWING_HOME}" 
JAVA_OPTS="${JAVA_OPTS} -Dajaxswing.home=$AJAXSWING_HOME"
JAVA_OPTS="${JAVA_OPTS} -Denv.JAVA_HOME=$JAVA_HOME"

$JAVA_HOME/bin/java $JAVA_OPTS   com.creamtec.ajaxswing.ui.AjaxSwingConsole &
