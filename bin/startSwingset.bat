@echo off

if not "%AJAXSWING_HOME%" == "" goto noSetAjaxSwingHome
    set ORIGINAL_DIR=%cd%
    %~d0
    cd %~dp0..
    set AJAXSWING_HOME=%cd%
    cd %ORIGINAL_DIR%
:noSetAjaxSwingHome

call "%AJAXSWING_HOME%\bin\setEnv.bat"
cd "%AJAXSWING_HOME%\wcapps"

start javaw kolmafia

echo on
