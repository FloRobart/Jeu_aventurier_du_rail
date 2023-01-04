@echo off

echo Lancement de l'application...
call java -cp ./bin;./bin/donnees;"%CLASSPATH%" controleur.Controleur && ( echo Fin de l'EXECUTION. & goto :eof ) || ( call compilation & goto :eof )

:compilation
    IF NOT EXIST "./bin/"         ( mkdir "./bin" )
    IF NOT EXIST "./bin/donnees/" ( mkdir "./bin/donnees/" )

    XCOPY "./donnees" "./bin/donnees" /E /Y >NUL

    echo Compilation...
    call javac -encoding utf8 "@compile.list" && ( call java -cp ./bin;./bin/donnees;"%CLASSPATH%" controleur.Controleur && echo Fin de l'execution. || echo. & echo Erreur d'EXECUTION. ) || echo. & echo Erreur de COMPILATION.
goto :eof