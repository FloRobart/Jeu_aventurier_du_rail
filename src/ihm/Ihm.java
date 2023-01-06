package ihm;

import java.io.Serializable;

import controleur.Controleur;
import ihm.accueil.FrameAccueil;
import ihm.jeu.FrameJeu;

public class Ihm
{
    private Controleur ctrl;

    private FrameJeu     frameJeu;
    private FrameAccueil frameAccueil;

    public Ihm(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.frameAccueil = new FrameAccueil(this.ctrl);
        this.frameJeu     = null;

        this.appliquerTheme();
    }

	public void demarrerJeu()
	{
		this.frameAccueil.dispose();
        this.frameAccueil = null;
		this.frameJeu     = new FrameJeu(this.ctrl);
	}

    public void setImageButton(int indice) { if ( this.frameJeu != null ) this.frameJeu.setImageButton(indice); }						


    public int getWidthFrameAccueil() { return this.frameAccueil.getWidth(); }

    /**
     * Permet d'appliquer le thème à chaque élément de l'ihm qui en à besoins
     */
    public void appliquerTheme()
    {
        if (this.frameJeu     != null) { this.frameJeu    .appliquerTheme(); }
        if (this.frameAccueil != null) { this.frameAccueil.appliquerTheme(); }
    }
}
