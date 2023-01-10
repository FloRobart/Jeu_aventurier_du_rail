package ihm;

import java.awt.image.BufferedImage;

import javax.swing.Icon;

import controleur.Controleur;
import ihm.accueil.FrameAccueil;
import ihm.attente.FrameAttente;
import ihm.jeu.FrameJeu;
import metier.CarteObjectif;

/**
 * Classe qui gère l'ensemble des fenêtres de l'application.
 */
public class Ihm
{
    private Controleur ctrl;

    private FrameJeu     frameJeu;
    private FrameAccueil frameAccueil;
    private FrameAttente frameAttente;


    public Ihm(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.frameJeu     = null;
		this.frameAccueil = new FrameAccueil(this.ctrl);
        this.frameAttente = null;

        this.appliquerTheme();
    }


    /**
     * Permet de démarrer le jeu et de fermer la fenêtre d'accueil.
     */
	public void demarrerJeu()
	{
		if (this.frameAccueil != null ) { this.frameAccueil.dispose(); this.frameAccueil = null; }
        if (this.frameAttente != null ) { this.frameAttente.dispose(); this.frameAttente = null; }

		this.frameJeu = new FrameJeu(this.ctrl);

        this.appliquerTheme();
	}

    /**
     * Permet d'ouvrir la fenêtre d'attente et de fermer la fenêtre d'accueil.
     */
    public void demarrerAttente(boolean hote)
    {
        this.frameAccueil.dispose();
        this.frameAccueil = null;
        this.frameAttente = new FrameAttente(this.ctrl, hote);

        this.appliquerTheme();
    }

    public void setScore      ( ) { this.frameJeu.setScore(); }
    public void setImageButton(int indice)  { if ( this.frameJeu != null ) this.frameJeu.setImageButton(indice); }
    public void setNbTours    (int nbTours) { this.frameJeu.setNbTours(nbTours); }

    public int getWidthFrameAccueil() { return this.frameAccueil.getWidth(); }

	public BufferedImage getImage() { return this.frameJeu.getImage(); }


    /**
     * Permet d'appliquer le thème à chaque élément de l'ihm qui en à besoins
     */
    public void appliquerTheme()
    {
        if (this.frameJeu     != null) { this.frameJeu    .appliquerTheme(); }
        if (this.frameAccueil != null) { this.frameAccueil.appliquerTheme(); }
        if (this.frameAttente != null) { this.frameAttente.appliquerTheme(); }
    }

    /**
     * Affiche la carte objectif dans la main du joueur
	 * @param icon carte objectif
     */
    public void afficherCarteObjectif(Icon icon) 
    {
        if (this.frameJeu != null) { this.frameJeu.afficherCarteObjectif(icon); }
    }

	public void majIHM()
	{
		if ( this.frameJeu != null ) this.frameJeu.majIHM();
        if ( this.frameAttente != null ) this.frameAttente.majIHM();
	}

    public void basculerEnJeu(Boolean b)
    {
        System.out.println("Demarage du jeu");
        if (b)
            this.demarrerJeu();

    }

	public void afficherErreur(String message)
	{
		if ( this.frameJeu != null ) this.frameJeu.afficherErreur(message);
	}

	public boolean poserQuestion(String message)
	{
		if ( this.frameJeu != null ) return this.frameJeu.poserQuestion(message);

		return false;
	}

}
