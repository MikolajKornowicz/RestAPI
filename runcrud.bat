call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors – breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto fail

:stoptomcat
call C:\apache-tomcat-9.0.60\bin\shutdown.bat

:copyfile
copy build\libs\crud.war C:\apache-tomcat-9.0.60\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
call C:\apache-tomcat-9.0.60\bin\startup.bat
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.