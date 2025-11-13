@echo off
REM ---------------------------------------------------------------------------
REM Gradle startup script for Windows
REM ---------------------------------------------------------------------------
set DIR=%~dp0
java -jar "%DIR%gradle\wrapper\gradle-wrapper.jar" %*
