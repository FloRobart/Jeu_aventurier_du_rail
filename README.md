Première solution
    run.bat et run.sh compile toujours l'application avant de la lancer

Deuxième solution
    lanceur.bat et lanceur.sh compile l'application seulement si elle n'est pas déjà compilée puis lance l'application.
    Si l'application est déjà compilée, lanceur.bat et lanceur.sh ne la recompile pas.

Troisième solution
    commande java -jar Jeu_aventurier_du_rail.jar