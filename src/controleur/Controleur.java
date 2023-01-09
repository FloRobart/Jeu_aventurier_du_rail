package controleur;

import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ihm.Ihm;
import metier.*;
import metier.partie.Partie;
import metier.partie.CarteWagon;
import metier.partie.Partie;
import metier.reseau.Server;


public class Controleur
{
    private Metier metier;
	private Partie partie;
	private Joueur joueur;
    private Ihm    ihm;

	private ServerControleur serverCtrl;
	private ClientControleur clientCtrl;
    public Controleur()
    {
        this.metier = new Metier(this);
		this.partie = null;
		this.joueur = null;
        this.ihm    = new Ihm(this);

    }
	public void superMethodeDeDebug() { 
		this.joueur.ajouterCarteWagon(new CarteWagon(null, getImageVersoCouleur(), getImageRectoLocomotive()));
		System.out.println(this.joueur.getAlCartesWagons().size());
	}

	/**
	 * Permet de lire le fichier xml contenant toutes les informations du plateau.
	 * @param fichier :  fichier xml à lire
	 * @return boolean : true si le fichier a été lu correctement, sinon false
	 */
	public boolean ouvrir(File fichier) { return this.metier.lireFichier(fichier); }

	/**
	 * Permet de créer une partie solo.
	 * Cette méthode lance le jeu directement.
	 */
	public void creerPartieSolo() 
	{ 
		this.joueur = new Joueur("Joueur 1");
		this.metier.ajouterJoueur(this.joueur);

		this.partie = new Partie(this, this.metier, false);

		this.ihm.demarrerJeu(); 
	}

	/**
	 * Permet de créer une partie multijoueur mais ne lance pas le jeu.
	 * Le jeu pourra être lancé par le créateur de la partie à l'intérieur de la fenêtre d'attente.
	 */
	public void creerPartieMulti()
	{
		this.metier.creeServer(true);
		this.ihm.demarrerAttente(true);
	}



	/* --------------------------- */
	/*          Getters            */
	/* --------------------------- */
	public List<Joueur>        getJoueurs             () { return this.metier.getJoueurs         (); }
	public Joueur              getJoueur              () { return this.joueur                    ; }
	public List<CarteObjectif> getCarteObjectif       () { return this.metier.getCarteObjectif   (); }
	public List<Noeud>         getNoeuds              () { return this.metier.getNoeuds          (); }
	public List<Arete>         getAretes              () { return this.metier.getAretes          (); }
	public CarteWagon[]        getTabCarteWagon       () { return this.metier.getTabCarteWagon   (); }
	public CarteObjectif[]	   getTabCarteObjectif    () { return this.metier.getTabCarteObjectif(); }

	public int[]         getTaillePlateau () { return this.metier.getTaillePlateau (); }
	public BufferedImage getImagePlateau  () { return this.metier.getImagePlateau  (); }
	public Color         getCouleurPlateau() { return this.metier.getCouleurPlateau(); }
	public Font          getPolicePlateau () { return this.metier.getPolicePlateau (); }

	public int getNbJoueursMin     () { return this.metier.getNbJoueursMin     (); }
	public int getNbJoueursMax     () { return this.metier.getNbJoueursMax     (); }
	public int getNbCarteCoul      () { return this.metier.getNbCarteCoul      (); }
	public int getNbCarteLocomotive() { return this.metier.getNbCarteLocomotive(); }
	public int getNbJetonJoueur    () { return this.metier.getNbJetonJoueur    (); }
	public int getNbJetonFin       () { return this.metier.getNbJetonFin       (); }

	public List<Color>         getCouleurs            () { return this.metier.getCouleurs            (); }
	public BufferedImage       getImageVersoCouleur   () { return this.metier.getImageVersoCouleur   (); }
	public BufferedImage       getImageRectoLocomotive() { return this.metier.getImageRectoLocomotive(); }
	public List<BufferedImage> getImagesRectoCouleur  () { return this.metier.getImagesRectoCouleur  (); }
	public List<Integer>       getPoints              () { return this.metier.getPoints              (); }

	public BufferedImage       getImageVersoObjectif() { return this.metier.getImageVersoObjectif(); }
	public BufferedImage       getImage             () { return this.ihm   .getImage             (); }

	//public void piocherDeck (char typeCarte, String nomJoueur)			 {this.metier.piocherDeck(typeCarte, nomJoueur);		}
	//public void piocherTabWagon (int indiceTab, String nomJoueur)		 {this.metier.piocherTabWagon(indiceTab, nomJoueur);	}
	//public void piocherTabObjectif (boolean[] tabBool, String nomJoueur) {this.metier.piocherTabObjectif(tabBool, nomJoueur);	}
	public void setImageButton(int indice) 								 { if ( this.ihm != null ) this.ihm.setImageButton(indice);}
    
	public CarteWagon[] getTabCartesVisible() { return this.partie.getTabCartesVisible(); }

	public void majIHM()
	{
		this.ihm.majIHM();
	}

	public void piocherPioche ()        { this.partie.piocherPioche ();    }
	public void piocherVisible(int ind) { this.partie.piocherVisible(ind); }

	public boolean peuxJouer()
	{
		if ( this.partie != null )
			return this.partie.getJoueurCourant().equals(this.joueur);
		else
			return false;
	}
	
	/**
     * Permet d'appliquer le thème à l'ihm
     */
    public void appliquerTheme() { this.ihm.appliquerTheme(); }

    /**
     * Permet de à l'ihm de récupérer la hashmap contenant les couleurs du thème
     * @return HashMap contenant les couleurs du thème
     */
    public HashMap<String, List<Color>> getTheme() { return this.metier.getTheme(); }

	/**
	 * Permet de récupérer le nom du thème utilisé
	 * @return Nom du thème utilisé
	 */
	public String getThemeUsed() { return this.metier.getThemeUsed(); }


    /**
     * Change le thème à utilisé dans le fichier de sauvegarde.
     * Charge en mémoire le nouveau thème.
     * Met à jour l'ihm.
     * @param theme : Nom du thème à utiliser
     */
    public void changerTheme(String theme) { this.metier.setThemeUsed(theme); }



	/**
	 * permet d'éberger une partie
	 */
	public void hostGame()
	{
		this.partie = new Partie(this, this.metier,true);
		this.serverCtrl = new ServerControleur(this.metier,this.partie);
	}


	/**
	 * 
	 */
	public int joinGame(String ip, String password)
	{

		// this.metier.creeClient(ip, true, password);

		// return 1;
		try 
		{
			this.clientCtrl = new ClientControleur(ip);

			this.metier = clientCtrl.getMetier();
			this.partie = clientCtrl.getPartie();
			this.ihm.demarrerJeu();
		}
		catch (UnknownHostException e)	{ return 2;} 
		catch (ConnectException e) 		{e.printStackTrace();} 
		catch (IOException e) 			{e.printStackTrace();}

		if (!password.equals(this.metier.getMotDePasse())) return 3;
		return 1;

	}
    public static void main(String[] args)
    {
        new Controleur();
		//Les commandes pour voir l'IP de la machine
		InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
	
    public void creerPartie() 
	{
		this.hostGame();
    }

    public Joueur getJoueurCourant() {
        return null;
    }

	/**
	 * Affiche la carte objectif dans la main du joueur
	 * @param icon carte objectif
	 */
    public void afficherCarteObjectif(Icon icon) 
	{
		this.ihm.afficherCarteObjectif(icon);
    }

	/**
	 * @Author Duc
	 * methode qui actualise le IHM de jeu (update Partie) quand y a un changement
	 */
	public void updateMapOnline()
	{
		if (this.serverCtrl!= null)
		{
			this.serverCtrl.updateMap();
		}
		if (this.clientCtrl!= null)
		{
			this.clientCtrl.updateMap();
		}
	}
}
