@echo off

:: Création des dossiers bin et donnees s'ils n'existent pas
IF NOT EXIST "./bin/"         ( mkdir "./bin" )
IF NOT EXIST "./bin/donnees/" ( mkdir "./bin/donnees/" )

XCOPY "./donnees" "./bin/donnees" /E /Y >NUL

echo Ca compile...
call javac -encoding utf8 "@compile.list"

echo Lancement du programme...
call java -cp ./bin;./bin/donnees;"%CLASSPATH%" controleur.Controleur

echo Fin de l'execution.
IF EXIST ".\bin\ihm"        rmdir /q /s ".\bin\ihm"       >NUL
IF EXIST ".\bin\controleur" rmdir /q /s ".\bin\controleur">NUL
IF EXIST ".\bin\metier"     rmdir /q /s ".\bin\metier"    >NUL
goto :eof