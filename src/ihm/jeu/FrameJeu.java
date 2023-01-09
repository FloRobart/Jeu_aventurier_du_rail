package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import controleur.Controleur;
import ihm.accueil.MenuBarre;
import metier.CarteObjectif;

public class FrameJeu extends JFrame
{
    private Controleur      ctrl;

    private MenuBarre       menuBarre;
    private PanelInfoPartie panelInfoPartie;
    private PanelJoueurs    panelJoueurs;
    private PanelMainJoueur panelMainJoueur;
    private PanelPioche     panelPioche;
    private PanelPlateau    panelPlateau;
    private PanelObjectif   panelObjectif;
    private JPanel          panelGauche;

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
        this.menuBarre       = new MenuBarre      (this.ctrl);
        this.panelJoueurs    = new PanelJoueurs   (this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);
        this.panelPioche     = new PanelPioche    (this.ctrl);
        this.panelPlateau    = new PanelPlateau   (this.ctrl);
        this.panelObjectif   = new PanelObjectif  (this.ctrl);
        this.panelInfoPartie = new PanelInfoPartie  (this.ctrl);
        this.panelGauche     = new JPanel();
        this.panelGauche.setLayout(new GridLayout(3, 1));
        this.panelGauche.setBorder( BorderFactory.createLineBorder(Color.black, 2) );

        /* Ajout des composants */
        this.setJMenuBar(this.menuBarre);
		this.add(this.panelGauche    , BorderLayout.WEST);
		this.add(this.panelMainJoueur, BorderLayout.SOUTH);
		this.add(this.panelPioche    , BorderLayout.EAST);
		this.add(this.panelPlateau   , BorderLayout.CENTER);
        this.panelGauche.add(this.panelJoueurs);
        this.panelGauche.add(this.panelObjectif);
        this.panelGauche.add(this.panelInfoPartie);


        this.setVisible(true);
		this.panelPlateau.centrer(this.panelPlateau.getWidth(), this.panelPlateau.getHeight());
    }

    public void setImageButton(int indice) { this.panelPioche.setImageButton(indice); }	

	public BufferedImage getImage() { return this.panelPlateau.getImage(); }


    /**
     * Permet d'appliquer le thème à l'ensemble de l'IHM (tout les panels existant)
     */
    public void appliquerTheme()
    {
        this.panelGauche    .setForeground(this.ctrl.getTheme().get("labels"    ).get(0));
        this.panelGauche    .setBackground(this.ctrl.getTheme().get("background").get(0));

        this.menuBarre      .appliquerTheme();
        this.panelInfoPartie.appliquerTheme();
        this.panelJoueurs   .appliquerTheme();
        this.panelMainJoueur.appliquerTheme();
        this.panelPioche    .appliquerTheme();
        this.panelPlateau   .appliquerTheme();
        this.panelObjectif  .appliquerTheme();
    }

    public void afficherCarteObjectif(Icon icon) 
    {
        this.panelMainJoueur.afficherCarteObjectif(icon);
    }

}