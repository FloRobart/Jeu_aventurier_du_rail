@echo off

:: Création des dossiers bin et donnees s'ils n'existent pas
IF NOT EXIST "./bin/"         ( mkdir "./bin" )
IF NOT EXIST "./bin/donnees/" ( mkdir "./bin/donnees/" )

XCOPY "./donnees" "./bin/donnees" /E /Y >NUL

echo Lancement du programme...
call java -cp ./bin;./bin/donnees;"%CLASSPATH%" controleur.Controleur && Fin de l'execution. || echo. & echo Veulliez compiler le projet java avant de la lancer.

goto :eof