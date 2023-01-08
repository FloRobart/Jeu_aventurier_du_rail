package ihm;

import java.awt.image.BufferedImage;

import controleur.Controleur;
import ihm.accueil.FrameAccueil;
import ihm.attente.FrameAttente;
import ihm.jeu.FrameJeu;

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
		this.frameAccueil.dispose();
        this.frameAccueil = null;
		this.frameJeu     = new FrameJeu(this.ctrl);

        this.appliquerTheme();
	}

    /**
     * Permet d'ouvrir la fenêtre d'attente et de fermer la fenêtre d'accueil.
     */
    public void demarrerAttente()
    {
        this.frameAccueil.dispose();
        this.frameAccueil = null;
        this.frameAttente = new FrameAttente(this.ctrl);

        this.appliquerTheme();
    }

    public void setImageButton(int indice) { if ( this.frameJeu != null ) this.frameJeu.setImageButton(indice); }

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
}
