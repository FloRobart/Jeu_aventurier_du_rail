package ihm.frames;

import controleur.Controleur;


public class Ihm 
{
    private Controleur ctrl;

    private FrameJeu frameJeu;
    private FrameMenu frameMenu;

    public Ihm(Controleur ctrl)
    {
        this.ctrl = ctrl;

		//this.frameMenu = new FrameMenu(this.ctrl);
        this.frameJeu = new FrameJeu(this.ctrl);

        this.appliquerTheme();
    }

    /**
     * Permet d'appliquer le thème à chaque élément de l'ihm qui en à besoins
     */
    public void appliquerTheme()
    {
        //this.frameJeu.appliquerTheme();
        //this.frameMenu.appliquerTheme();
    }
}
