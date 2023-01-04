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
        this.frameJeu = new FrameJeu(this.ctrl);
        //this.frameMenu = new FrameMenu(this.ctrl);
    }
}
