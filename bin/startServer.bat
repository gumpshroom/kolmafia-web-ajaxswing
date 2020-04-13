@echo off

if not "%AJAXSWING_HOME%" == "" goto noSetAjaxSwingHome
    set ORIGINAL_DIR=%cd%
    %~d0
    cd %~dp0..
    set AJAXSWING_HOME=%cd%
    cd %ORIGINAL_DIR%
:noSetAjaxSwingHome

call "%AJAXSWING_HOME%\bin\setEnv.bat"
if "%ERROR_LEVEL%"=="1" goto end

rem Make sure AjaxSwing libraries are compatible with the current JDK version
"%JAVA_HOME%\bin\java" -Dajaxswing.home="%AJAXSWING_HOME%" -classpath "%AJAXSWING_HOME%\lib\bootstrap.jar" com.creamtec.core.Bootstrapper
if "%ERROR_LEVEL%"=="1" goto end

set START_ARG=start
if "%1" == "" goto noStartArg
    set START_ARG=%1
:noStartArg

rem Add -security parameter to guarantee that AjaxSwing security manager doesn't conflict with Tomcat's security manager
rem If you do it, make sure to grant AllPermission to your classes in tomcat/conf/catalina.policy
rem call "%CATALINA_HOME%\bin\catalina.bat" %START_ARG% -security 
call "%CATALINA_HOME%\bin\catalina.bat" %START_ARG%

:end
