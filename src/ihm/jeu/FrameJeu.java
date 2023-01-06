package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

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

        Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Frame Jeu");
        this.setSize(dimEcran.width, dimEcran.height); // Définition d'une taille minimum (obligatoire)
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvre la fenêtre en pleine écran
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

        /* Creation des composants */
        this.panelJoueurs    = new PanelJoueurs   (this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);
        this.panelPioche     = new PanelPioche    (this.ctrl);
        this.panelPlateau    = new PanelPlateau   (this.ctrl);

        /* Ajout des composants */
		this.add(this.panelJoueurs   , BorderLayout.WEST);
		this.add(this.panelMainJoueur, BorderLayout.SOUTH);
		this.add(this.panelPioche    , BorderLayout.EAST);
		this.add(this.panelPlateau   , BorderLayout.CENTER);

        this.setVisible(true);
		
		this.panelPlateau.centrer(this.panelPlateau.getWidth(), this.panelPlateau.getHeight());
    }


    public void appliquerTheme()
    {
        //this.panelJoueurs   .appliquerTheme();
        //this.panelMainJoueur.appliquerTheme();
        //this.panelPioche    .appliquerTheme();
        //this.panelPlateau   .appliquerTheme();
    }
}