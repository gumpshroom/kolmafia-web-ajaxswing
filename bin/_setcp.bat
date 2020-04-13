@echo off

if not "%AJAXSWING_HOME%" == "" goto gotAjaxSwingHome
  echo Environment variable AJAXSWING_HOME must be set to point to AjaxSwing installation directory
  goto eof
:gotAjaxSwingHome

rem Add your jars that are not stored in wcapps/lib here
set APP_CLASSPATH=

set WC_CLASSPATH=%AJAXSWING_HOME%\wcapps\lib\classes

rem Add all additional libs from lib directory to classpath
for %%f in ("%AJAXSWING_HOME%\lib\*.jar") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f
for %%f in ("%AJAXSWING_HOME%\lib\*.zip") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f

rem Include all .jar and .zip from wcapps/lib
for %%f in ("%AJAXSWING_HOME%\wcapps\lib\*.jar") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f
for %%f in ("%AJAXSWING_HOME%\wcapps\lib\*.zip") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f

set CLASSPATH=%WC_CLASSPATH%;%APP_CLASSPATH%;%CLASSPATH%

rem Have to add servlet.jar because webapp is loaded from CLASSPATH, not using standard Tomcat's classloader
set CLASSPATH=%CATALINA_HOME%\lib\servlet-api.jar;%CLASSPATH%

rem WARNING: This echo can hault clientAgent.bat because we are currently not reading input/output from batch files
rem echo CLASSPATH=%CLASSPATH%

:eof