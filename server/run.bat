@echo off

IF EXIST filename "spigot-1.8.8.jar" (goto run) ELSE (goto build)

:run
java -Xms512M -Xmx1024M -XX:MaxPermSize=128M -jar spigot-1.8.8.jar
pause
goto run

:build
set startdir=%~dp0
set bashdir="C:\Users\cyberpwn.DESKTOP-OP2F647\Documents\Development\bukkit\server\Git\bin\bash.exe"
%bashdir% --login -i -c "java -jar ""%startdir%\BuildTools.jar"""
pause
goto run