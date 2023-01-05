package ihm;

import controleur.Controleur;
import ihm.accueil.FrameAccueil;
import ihm.jeu.FrameJeu;

public class Ihm 
{
    private Controleur ctrl;

    private FrameJeu     frameJeu;
    private FrameAccueil frameAccueil;

    public Ihm(Controleur ctrl)
    {
        this.ctrl = ctrl;

		//this.frameAccueil = new FrameAccueil(this.ctrl);
        this.frameJeu     = new FrameJeu(this.ctrl);

        this.appliquerTheme();
    }

    /**
     * Permet d'appliquer le thème à chaque élément de l'ihm qui en à besoins
     */
    public void appliquerTheme()
    {
        //this.frameJeu.appliquerTheme();
        //this.frameAccueil.appliquerTheme();
    }
}
