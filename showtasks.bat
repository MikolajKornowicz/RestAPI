call runcrud
if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo An error has occured
goto fail

:fail
echo.
echo There were errors

:openBrowser
echo.
echo Opening your browser
C:\Users\mikol\AppData\Local\Programs\Opera\launcher.exe --ran-launcher --remote http://localhost:8080/crud/v1/tasks/getTasks
echo Browser successfully opened


