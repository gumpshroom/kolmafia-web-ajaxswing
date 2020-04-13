@echo off

if not "%AJAXSWING_HOME%" == "" goto noSetAjaxSwingHome
    set ORIGINAL_DIR=%cd%
    %~d0
    cd %~dp0..
    set AJAXSWING_HOME=%cd%
    cd %ORIGINAL_DIR%
:noSetAjaxSwingHome

call "%AJAXSWING_HOME%\bin\setEnv.bat"

rem Make sure AjaxSwing libraries are compatible with the current JDK version
"%JAVA_HOME%\bin\java" -Dajaxswing.home="%AJAXSWING_HOME%" -classpath "%AJAXSWING_HOME%\lib\bootstrap.jar" com.creamtec.core.Bootstrapper
if "%ERROR_LEVEL%"=="1" goto end

rem Add asboot.jar because setcp doesn't put it into the CLASSPATH
set CLASSPATH=%CLASSPATH%;%AJAXSWING_HOME%\lib\ajaxswing.jar;%AJAXSWING_HOME%\lib\boot\asboot.jar

rem Reset JAVA_OPTS to append AjaxSwing boot classes instead of prepending them. This helps avoid java.awt.Component conflicts
set JAVA_OPTS=-Xbootclasspath/a:"%AJAXSWING_HOME%\lib\boot\asboot.jar" -Dajaxswing.home="%AJAXSWING_HOME%" 
set JAVA_OPTS=%JAVA_OPTS% -Dajaxswing.home="%AJAXSWING_HOME%"
set JAVA_OPTS=%JAVA_OPTS% -Denv.JAVA_HOME="%JAVA_HOME%" 

:start
"%JAVA_HOME%\bin\java" %JAVA_OPTS% com.creamtec.ajaxswing.ui.AjaxSwingConsole

:eof