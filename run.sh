#!/bin/bash

# Création des dossiers bin et donnees s'ils n'existent pas #
ls ./bin/         >> /dev/null 2>&1 || mkdir "./bin/"
ls ./bin/donnees/ >> /dev/null 2>&1 || mkdir "./bin/donnees/"

cp -r ./donnees/ ./bin/

echo Compilation...
javac -encoding utf8 @compile.list && (echo Lancement de l\'application... ; java -cp "./bin:$CLASSPATH" controleur.Controleur && echo Fin de l\'execution. || echo -e "\nErreur de L\'EXECUTION.") || echo -e "\nErreur de COMPILATION."
