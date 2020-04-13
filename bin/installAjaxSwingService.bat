@echo off

set ORIGINAL_DIR=%cd%
%~d0
cd %~dp0..
set AJAXSWING_HOME=%cd%
cd %ORIGINAL_DIR%

call "%AJAXSWING_HOME%\bin\setEnv.bat"

if "%ERROR_LEVEL%"=="1" goto end

set CATALINA_BASE=%CATALINA_HOME%
set EXECUTABLE=%CATALINA_HOME%\bin\tomcat8.exe

set WC_CLASSPATH=%AJAXSWING_HOME%\wcapps\lib\classes;%AJAXSWING_HOME%\lib\boot\asboot.jar

rem Add all additional libs from lib directory to classpath
for %%f in ("%AJAXSWING_HOME%\lib\*.jar") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f
for %%f in ("%AJAXSWING_HOME%\lib\*.zip") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f


rem Include all .jar and .zip from wcapps/lib
for %%f in ("%AJAXSWING_HOME%\wcapps\lib\*.jar") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f
for %%f in ("%AJAXSWING_HOME%\wcapps\lib\*.zip") do call "%AJAXSWING_HOME%\bin\_addlib.bat" %%f

set APP_CLASSPATH=

set SERVICE_NAME=AjaxSwingServer
set PR_DISPLAYNAME=AjaxSwing Server
set PR_DESCRIPTION=AjaxSwing Server running on Tomcat
set PR_INSTALL=%EXECUTABLE%
set PR_LOGPATH=%CATALINA_HOME%\logs
set PR_CLASSPATH=%JAVA_HOME%\lib\tools.jar;%CATALINA_HOME%\bin\bootstrap.jar;%CATALINA_HOME%\lib\servlet-api.jar;%CATALINA_HOME%\bin\tomcat-juli.jar;%WC_CLASSPATH%;%APP_CLASSPATH%

rem Set the server jvm frrom JAVA_HOME
rem You can use the 'set PR_JVM=auto' for default JVM
rem set PR_JVM=%JAVA_HOME%\jre\bin\server\jvm.dll

"%EXECUTABLE%" //IS//%SERVICE_NAME% --StartClass org.apache.catalina.startup.Bootstrap --StopClass org.apache.catalina.startup.Bootstrap --StartParams start --StopParams stop
rem Clear the environment variables. They are not needed any more.
set PR_DISPLAYNAME=
set PR_DESCRIPTION=
set PR_INSTALL=
set PR_LOGPATH=
rem set PR_CLASSPATH=
set PR_JVM=
rem Set extra parameters
"%EXECUTABLE%" //US//%SERVICE_NAME% --JvmOptions "-Dcatalina.base=%CATALINA_BASE%;-Dcatalina.home=%CATALINA_HOME%;-Djava.endorsed.dirs=%CATALINA_HOME%\common\endorsed" --StartMode jvm --StopMode jvm --Startup auto --Classpath "%PR_CLASSPATH%" 
rem More extra parameters
set PR_STDOUTPUT=%CATALINA_HOME%\logs\stdout.log
set PR_STDERROR=%CATALINA_HOME%\logs\stderr.log
"%EXECUTABLE%" //US//%SERVICE_NAME% ++JvmOptions "-Djava.io.tmpdir=%CATALINA_BASE%\temp;-Dajaxswing.home=%AJAXSWING_HOME%;-Xbootclasspath/p:%AJAXSWING_HOME%\lib\boot\asboot.jar" --JvmMs 128 --JvmMx 256 


echo The service '%SERVICE_NAME%' has been installed.

:end
