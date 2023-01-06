package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Controleur;

public class FrameJeu extends JFrame
{
    private Controleur ctrl;

    private PanelJoueurs    panelJoueurs;
    private PanelMainJoueur panelMainJoueur;
    private PanelPioche     panelPioche;
    private PanelPlateau    panelPlateau;
    private PanelObjectif panelObjectif;

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
        this.panelObjectif   = new PanelObjectif  (this.ctrl);
        JPanel panelGauche   = new JPanel();
        panelGauche.setLayout(new GridLayout(2,1));

        /* Ajout des composants */
		this.add(panelGauche , BorderLayout.WEST);
		this.add(this.panelMainJoueur, BorderLayout.SOUTH);
		this.add(this.panelPioche    , BorderLayout.EAST);
		this.add(this.panelPlateau   , BorderLayout.CENTER);
        panelGauche.add(this.panelJoueurs);
        panelGauche.add(this.panelObjectif);

        this.setVisible(true);
		
		this.panelPlateau.centrer(this.panelPlateau.getWidth(), this.panelPlateau.getHeight());
    }

    public void setImageButton(int indice) { this.panelPioche.setImageButton(indice); }	

    public void appliquerTheme()
    {
        //this.panelJoueurs   .appliquerTheme();
        //this.panelMainJoueur.appliquerTheme();
        //this.panelPioche    .appliquerTheme();
        //this.panelPlateau   .appliquerTheme();
    }
}