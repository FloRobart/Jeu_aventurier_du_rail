#!/bin/bash

compilation()
{
    ls ./bin/         >> /dev/null 2>&1 || mkdir "./bin/"
    ls ./bin/donnees/ >> /dev/null 2>&1 || mkdir "./bin/donnees/"

    cp -r ./donnees/ ./bin/

    echo Compilation...
    javac -encoding utf8 @compile.list && (java -cp "./bin:$CLASSPATH" controleur.Controleur && echo Fin de l\'execution. || echo -e "\nErreur d'EXECUTION.") || echo -e "\nErreur de COMPILATION."
}


echo Lancement de l\'application...
java -cp "./bin:$CLASSPATH" controleur.Controleur && echo Fin de l\'execution. || compilation
