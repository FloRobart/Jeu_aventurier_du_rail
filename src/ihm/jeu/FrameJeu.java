package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import controleur.Controleur;
import ihm.accueil.MenuBarre;

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
        this.panelInfoPartie = new PanelInfoPartie(this.ctrl);
        this.panelGauche     = new JPanel(new GridLayout(3, 1));
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

    public void setScore      ( ) { this.panelMainJoueur.setScore(); this.panelJoueurs.setScore(); }
    public void setImageButton(int indice) { this.panelPioche.setImageButton(indice); }	
    public void setInfo    (int nbTours, String nomJoueurCourant){ this.panelInfoPartie.setInfo(nbTours, nomJoueurCourant); }

	public BufferedImage getImage() { return this.panelPlateau.getImage(); }

    public void piocherCarteObjectifDebutPartie(){ this.panelObjectif.piocherCarteObjectifDebutPartie(); }

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

    /**
     * Affiche la carte objectif dans la main du joueur
	 * @param icon carte objectif
     */
    public void afficherCarteObjectif(Icon icon) 
    {
        this.panelMainJoueur.afficherCarteObjectif(icon);
    }

	public void majIHM()
	{
		this.remove(this.panelMainJoueur);
		this.panelMainJoueur = new PanelMainJoueur(this.ctrl);
		this.add(this.panelMainJoueur, BorderLayout.SOUTH);
        if(!this.ctrl.getEstMulti())
        {
            this.remove(this.panelGauche);
            this.panelGauche = new JPanel(new GridLayout(3, 1));

            this.panelJoueurs = new PanelJoueurs(this.ctrl);
            this.panelJoueurs.appliquerTheme();
            this.panelGauche.add(this.panelJoueurs);
            this.panelGauche.add(this.panelObjectif);
            this.panelGauche.add(this.panelInfoPartie);
            

            this.add(this.panelGauche, BorderLayout.WEST );
        }
			
		this.revalidate();
		this.repaint();

		this.panelObjectif.majIHM();
		this.panelPioche  .majIHM();
		this.panelPlateau .majIHM();
        this.panelJoueurs .majIHM();
	}

	public void afficherErreur(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public boolean poserQuestion(String message)
	{
		int choix = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return choix == JOptionPane.YES_OPTION;
	}

    public void validerObjectif() 
    {
        this.panelMainJoueur.validerObjectif();
    }
}