@echo off

if not "%AJAXSWING_HOME%" == "" goto noSetAjaxSwingHome
    set ORIGINAL_DIR=%cd%
    %~d0
    cd %~dp0..
    cd ..
    set AJAXSWING_HOME=%cd%
    cd %ORIGINAL_DIR%
:noSetAjaxSwingHome

echo Copying AjaxSwing libraries for JDK 1.8...
copy "%AJAXSWING_HOME%\lib\install\ajaxswing.jar.jdk1.8" "%AJAXSWING_HOME%\lib\ajaxswing.jar"
copy "%AJAXSWING_HOME%\lib\install\asboot.jar.jdk1.8"   "%AJAXSWING_HOME%\lib\boot\asboot.jar"

call "%AJAXSWING_HOME%\bin\setup\applyPatches.bat"

echo Deleting temporary Tomcat files...
del /Q /S /F  "%AJAXSWING_HOME%\tomcat\work\*.*"

echo Done
