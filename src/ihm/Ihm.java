package ihm;

import java.awt.image.BufferedImage;

import javax.swing.Icon;

import controleur.Controleur;
import ihm.accueil.FrameAccueil;
import ihm.attente.FrameAttente;
import ihm.attente.FrameAttenteLocal;
import ihm.jeu.FrameJeu;
import ihm.finPartie.FrameFinPartie;
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
    private FrameAttenteLocal frameAttenteLocal; 
	private FrameFinPartie frameFinDePartie;

    public Ihm(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.frameJeu     = null;
		this.frameAccueil = new FrameAccueil(this.ctrl);
        this.frameAttente = null;
        this.frameAttenteLocal = null;
		this.frameFinDePartie = null;

        this.appliquerTheme();
    }

    /**
     * Permet de démarrer le jeu et de fermer la fenêtre d'accueil.
     */
	public void demarrerJeu()
	{
		if (this.frameAccueil      != null ) { this.frameAccueil     .dispose(); this.frameAccueil      = null; }
        if (this.frameAttente      != null ) { this.frameAttente     .dispose(); this.frameAttente      = null; }
		if (this.frameAttenteLocal != null ) { this.frameAttenteLocal.dispose(); this.frameAttenteLocal = null; }

		this.frameJeu = new FrameJeu(this.ctrl);

        this.appliquerTheme();
		
        this.frameJeu.piocherCarteObjectifDebutPartie();
	}

    /**
     * Permet d'ouvrir la fenêtre d'attente local et de fermer la fenêtre d'accueil.
     */
    public void demarrerAttenteLocal()
    {
        if (frameAccueil != null)
        {
            this.frameAccueil.dispose();
            this.frameAccueil = null;
        }

        this.frameAttenteLocal = new FrameAttenteLocal(this.ctrl);

        this.appliquerTheme();
    }

	public void piocherCarteObjectifDebutPartie() { this.frameJeu.piocherCarteObjectifDebutPartie(); }

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

	public void ouvrirFinPartie()
	{
		this.frameFinDePartie = new FrameFinPartie(this.ctrl);
        this.frameFinDePartie.appliquerTheme();
	}

    public void setScore      ( ) { this.frameJeu.setScore(); }
    public void setImageButton(int indice)  { if ( this.frameJeu != null ) this.frameJeu.setImageButton(indice); }
	public void setInfo    (int nbTours, String nomJoueurCourant){ this.frameJeu.setInfo(nbTours, nomJoueurCourant); }

    public int getWidthFrameAccueil() { return this.frameAccueil.getWidth(); }

    public void disposeFrameJeu	() { this.frameJeu.dispose(); }
    public void disposeFrameFinPartie() { this.frameFinDePartie.dispose(); }

	public BufferedImage getImage() { return this.frameJeu.getImage(); }


    /**
     * Permet d'appliquer le thème à chaque élément de l'ihm qui en à besoins
     */
    public void appliquerTheme()
    {
        if (this.frameJeu          != null) { this.frameJeu         .appliquerTheme(); }
        if (this.frameAccueil      != null) { this.frameAccueil     .appliquerTheme(); }
        if (this.frameAttente      != null) { this.frameAttente     .appliquerTheme(); }
        if (this.frameAttenteLocal != null) { this.frameAttenteLocal.appliquerTheme(); }
    }

    /**
     * Affiche la carte objectif dans la main du joueur
	 * @param icon carte objectif
     */
    public void afficherCarteObjectif(Icon icon) 
    {
        if (this.frameJeu != null) { this.frameJeu.afficherCarteObjectif(icon); }
    }

    public void RetourALaceuille()
    {
        if ( this.frameJeu != null ) this.frameJeu.dispose();
        if ( this.frameAttente != null ) this.frameAttente.dispose();
        if ( this.frameAccueil == null )
        {
            this.frameAccueil = new FrameAccueil(this.ctrl);
            this.appliquerTheme();
        }
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


    public void validerObjectif() 
    {
        if (this.frameJeu != null) { this.frameJeu.validerObjectif(); }
    }

}
