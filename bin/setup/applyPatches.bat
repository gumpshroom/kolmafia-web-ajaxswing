@echo off

if not "%AJAXSWING_HOME%" == "" goto noSetAjaxSwingHome
    set ORIGINAL_DIR=%cd%
    %~d0
    cd %~dp0..
    cd ..
    set AJAXSWING_HOME=%cd%
    cd %ORIGINAL_DIR%
:noSetAjaxSwingHome

call "%AJAXSWING_HOME%\bin\setEnv.bat"

cd "%AJAXSWING_HOME%\lib\boot"

"%JAVA_HOME%\bin\java" -version

echo Extracting platform files...
set RT_JAR_PATH="%JAVA_HOME%\jre\lib\rt.jar"
if exist %RT_JAR_PATH% goto rt_jar_path_valid
set RT_JAR_PATH="%JAVA_HOME%\lib\rt.jar"
if exist %RT_JAR_PATH% goto rt_jar_path_valid
echo ERROR: unable to autodetect path to JDK rt.jar. Please set the path manually in applyPatches.bat
exit 1
:rt_jar_path_valid
echo rt.jar path is %RT_JAR_PATH%

echo Applying patches...
"%JAVA_HOME%/bin/java" -Dcom.creamtec.home="%AJAXSWING_HOME%" -cp "%AJAXSWING_HOME%/lib/boot/asboot.jar;%AJAXSWING_HOME%/lib/asm-all-5.2.jar" com.creamtec.core.ClassPatcher

echo Repackaging jars...
"%JAVA_HOME%\bin\jar" uf asboot.jar java/ -J-Xmx1024m -J-Xms512m
rem k"%JAVA_HOME%\bin\jar" uf asboot.jar javax

echo Deleting temporary files...
rmdir /Q /S java
rmdir /Q /S javax

echo Done
