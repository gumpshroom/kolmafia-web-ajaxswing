@echo off

if not "%AJAXSWING_HOME%" == "" goto noSetAjaxSwingHome
    set ORIGINAL_DIR=%cd%
    %~d0
    cd %~dp0..
    set AJAXSWING_HOME=%cd%
    cd %ORIGINAL_DIR%
:noSetAjaxSwingHome

set KEEP_CURRENT_DIR=true

call "%AJAXSWING_HOME%\bin\setEnv.bat"

if "%ERROR_LEVEL%"=="1" goto eof

if "%JAVA_HOME%"=="" goto javaHomeNotSet
   rem If we know which JDK to use, call its registry
   start "AjaxSwing RMI Registry" "%JAVA_HOME%\bin\rmiregistry" %1
   goto :eof
:javaHomeNotSet
   rem Try to run the registry anyway
   start "AjaxSwing RMI Registry" rmiregistry %1


:eof
