@echo off
if "%1"=="" (
    echo Usage: imagecompare.bat ^<subdirectory^>
    echo Example: imagecompare.bat monsters\output
    echo Example: imagecompare.bat spells\output
    echo Example: imagecompare.bat items\output
    exit /b 1
)

if not exist "%1" (
    echo Error: Directory '%1' does not exist
    exit /b 1
)

powershell -ExecutionPolicy Bypass -Command "Set-Location '%~dp0%1'; & '%~dp0imagecompare.ps1'"
