#!/bin/bash

compilation()
{
    ls ./bin/         >> /dev/null 2>&1 || mkdir "./bin/"
    ls ./bin/donnees/ >> /dev/null 2>&1 || mkdir "./bin/donnees/"

    cp -r ./donnees/ ./bin/

    echo Compilation...
    javac -cp "$CLASSPATH:$CD\bin\donnees\jar_libraries\jdom-2.0.6.jar" -encoding utf8 -d "./bin" @compile.list && (java -cp "$CLASSPATH:$CD\bin:$CD\bin\donnees\jar_libraries\jdom-2.0.6.jar" controleur.Controleur && echo Fin de l\'execution. || echo -e "\nErreur d'EXECUTION.") || echo -e "\nErreur de COMPILATION."
}


export CLASSPATH="$CLASSPATH:./bin/donnees/jar_librairies/jdom-2.0.6.jar"
echo Lancement de l\'application...
java -cp "$CLASSPATH:$CD\bin:$CD\bin\donnees\jar_libraries\jdom-2.0.6.jar" controleur.Controleur && echo Fin de l\'execution. || compilation
