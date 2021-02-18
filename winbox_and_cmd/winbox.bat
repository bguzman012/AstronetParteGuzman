@ECHO OFF
:start
START /WAIT python winbox.py
cls
timeout /nobreak 2
goto start /WAIT