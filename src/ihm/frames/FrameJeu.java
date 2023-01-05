package ihm.frames;

import javax.swing.JFrame;

import ihm.panels.*;
import controleur.Controleur;


public class FrameJeu extends JFrame
{
    private Controleur ctrl;
    
    private PanelPartie     panelPartie;
    private PanelJoueurs    panelJoueurs;
    private PanelMainJoueur panelMainJoueur;
    private PanelPioche     panelPioche;
    private PanelPlateau    panelPlateau;


    public FrameJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Creation des composants */
        this.panelPartie     = new PanelPartie    (this.ctrl);
        this.panelJoueurs    = new PanelJoueurs   (this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);
        this.panelPioche     = new PanelPioche    (this.ctrl);
        this.panelPlateau    = new PanelPlateau   (this.ctrl);


        /* Ajout des composants */


        /* Activation des composants */
    }


    public void appliquerTheme()
    {
        this.panelPartie    .appliquerTheme();
        //this.panelJoueurs   .appliquerTheme();
        //this.panelMainJoueur.appliquerTheme();
        //this.panelPioche    .appliquerTheme();
        //this.panelPlateau   .appliquerTheme();
    }
}