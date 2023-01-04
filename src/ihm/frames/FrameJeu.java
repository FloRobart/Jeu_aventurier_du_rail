package ihm.frames;

import javax.swing.JFrame;

import ihm.panels.*;
import controleur.Controleur;


public class FrameJeu extends JFrame
{
    private Controleur ctrl;

    private PanelJoueurs    panelJoueurs;
    private PanelMainJoueur panelMainJoueur;
    private PanelPioche     panelPioche;
    private PanelPlateau    panelPlateau;


    public FrameJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Creation des composants */
        this.panelJoueurs    = new PanelJoueurs   (this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);
        this.panelPioche     = new PanelPioche    (this.ctrl);
        this.panelPlateau    = new PanelPlateau   (this.ctrl);


        /* Ajout des composants */


        /* Activation des composants */
    }
}