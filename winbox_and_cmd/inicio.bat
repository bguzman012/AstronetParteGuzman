@ECHO OFF
:start
START /WAIT python hola.py
cls
timeout /nobreak 20
goto start 
