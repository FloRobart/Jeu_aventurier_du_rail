#!/bin/bash

cp -r ./donnees/ ./bin/

echo Ca compile...
javac -encoding utf8 @compile.list && echo Compilation terminee. || echo Erreur de compilation.
