#!/bin/bash

# Création des dossiers bin et donnees s'ils n'existent pas #
#if ![ -d "./bin/"];         then mkdir "./bin/" fi
#if ![ -d "./bin/donnees/"]; then mkdir "./bin/donnees/" fi

cp -r ./donnees/ ./bin/

echo Ca compile...
javac -encoding utf8 @compile.list

#echo Lancement du programme...
#echo Lancement des tests XML
#java -cp ./bin:$CLASSPATH metier.Metier
echo Lancement de l\'interface graphique...
java -cp "./bin:$CLASSPATH" controleur.Controleur

echo Fin de l\'execution.

#if [ -d ".\bin\ihm"       ]; then rm -r ".\bin\ihm"       >NUL
#if [ -d ".\bin\controleur"]; then rm -r ".\bin\controleur">NUL
#if [ -d ".\bin\metier"    ]; then rm -r ".\bin\metier"    >NUL
