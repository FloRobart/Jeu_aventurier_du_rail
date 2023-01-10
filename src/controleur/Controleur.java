package controleur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;

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

	private Arete   areteSelectionnee;
	private int     couleurSelectionnee;
	private boolean enTrainDePiocher;

    public Controleur()
    {
        this.metier = new Metier(this);
		this.partie = null;
		this.joueur = null;
        this.ihm    = new Ihm(this);

		this.enTrainDePiocher = false;
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
		this.joueur = this.metier.getJoueurs().get(0);
		this.joueur.setCouleur(Color.PINK);

		this.partie = new Partie(this, this.metier, false, "Partie local");

		this.ihm.demarrerJeu(); 
	}

	/**
	 * Permet de lancer la partie multijoueur quand on est dans la salle d'attente.
	 * Elle peux être appeler uniquement par l'hote de la partie.
	 * C'est elle qui s'occupe de fermer la salle d'attente.
	 */
	public void lancerPartieMulti()
	{
		this.partie = new Partie(this, this.metier, true, "Partie multi-joueurs");

		this.ihm.demarrerJeu();
	}

	/**
	 * Permet de créer une partie multijoueur mais ne lance pas le jeu.
	 * Le jeu pourra être lancé par le créateur de la partie à l'intérieur de la fenêtre d'attente.
	 */
	public void creerPartieMulti(String password)
	{
		this.metier.creeServer(true, password);
		this.hostGame();
		this.ihm.demarrerAttente(true);
	}

	public Partie getPartie()
	{
		return this.partie;
	}

	public void setPartie(Partie partie)
	{
		this.partie = partie;
		partie.setJoueurCourrant(this.joueur);
	}



	/* --------------------------- */
	/*          Getters            */
	/* --------------------------- */
	public List<Joueur>        getJoueurs             () { return this.metier.getJoueurs         (); }
	public Joueur              getJoueur              () { return this.joueur                      ; }
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

	public int     getSizeWagon         () { return this.partie.getSizeWagon(); }
	public Arete   getAreteSelectionne  () { return this.areteSelectionnee;     }
	public int     getCouleurSelectionne() { return this.couleurSelectionnee;   }
	public boolean getEnTrainDePiocher  () { return this.enTrainDePiocher;      }

	// Méthodes
	public void setImageButton(int indice)  { if ( this.ihm != null ) this.ihm.setImageButton(indice); }
	public void	setNbTours	  (int nbTours) { this.ihm.setNbTours(nbTours);}

	public void switchEnTrainDePiocher()
	{
		this.enTrainDePiocher = !this.enTrainDePiocher;
	}

	public boolean ajouterJoueur(Joueur joueur)
	{
		return this.metier.ajouterJoueur(joueur);
	}

	public boolean estPrenable(Arete arete, int couleur)
	{
		try
		{
			if ((couleur == 1 && arete.getProprietaire1() == null) ||
		        (couleur == 2 && arete.getProprietaire2() == null)   )
			{
				Color coul = null;

				if (couleur == 1) coul = arete.getCouleur1();
				else              coul = arete.getCouleur2();
				
				// if : voix neutre | else : voix couleur
				if ( coul.equals(this.metier.getCouleurs().get(0)))
				{
					for (Color c : this.joueur.getAlCouleurs())
					{
						int nbCoul  = 0;
						int nbJoker = 0;

						if (c != null) 
							nbCoul  = this.joueur.gethashMapCarteWagons().get(c);

						if (this.joueur.getAlCouleurs().contains(null))
							nbJoker = this.joueur.gethashMapCarteWagons().get(null);

						if (nbCoul + nbJoker >= arete.getDistance()) return true;
					}
				}
				else
				{
					int nbCoul  = 0;
					int nbJoker = 0;

					if (this.joueur.getAlCouleurs().contains(coul))
						nbCoul  = this.joueur.gethashMapCarteWagons().get(coul);

					if (this.joueur.getAlCouleurs().contains(null))
						nbJoker = this.joueur.gethashMapCarteWagons().get(null);

					if (nbCoul + nbJoker >= arete.getDistance()) return true;
				}
			}
		}
		catch(Exception e) { return false; }
		
		return false;
	}

	public void setSelectionnee(Arete arete, int couleur)
	{
		this.areteSelectionnee   = arete;
		this.couleurSelectionnee = couleur;
	}
    
	public CarteWagon[] getTabCartesVisible() { return this.partie.getTabCartesVisible(); }

	public void majIHM()
	{
		this.ihm.majIHM();
	}

	public void verifierVisible()
	{
		this.partie.verifierVisible();
	}

	public void prendreArete(int indMain)
	{
		if (this.areteSelectionnee != null && this.areteSelectionnee.getDistance() <= joueur.getNbJetonsRestant())
		{
			if ((this.couleurSelectionnee == 1 && this.areteSelectionnee.getProprietaire1() != null) ||
				(this.couleurSelectionnee == 2 && this.areteSelectionnee.getProprietaire2() != null)   )
			{
				this.ihm.afficherErreur("Cette voie est déjà prise !");
				return;
			}

			boolean estValide = false;
			Color c = this.joueur.getAlCouleurs().get(indMain);
			int nbCarte = this.joueur.gethashMapCarteWagons().get(c);
			int nbJoker = this.joueur.gethashMapCarteWagons().get(null);

			Color cVoie;
			if (this.couleurSelectionnee == 1) cVoie = this.areteSelectionnee.getCouleur1();
			else                               cVoie = this.areteSelectionnee.getCouleur2();

			// Utilisation de carte joker uniquement
			if (c == null && nbCarte >= this.areteSelectionnee.getDistance())
			{
				this.joueur.gethashMapCarteWagons().put(c, nbCarte - this.areteSelectionnee.getDistance());
				estValide = true;

				int nbEnl = 0;
				Iterator<CarteWagon> it = this.joueur.getAlCartesWagons().iterator();
				while (it.hasNext() && nbEnl < this.areteSelectionnee.getDistance()) 
				{
					CarteWagon cw = it.next();
					if (cw.getCouleur() == null)
					{
						this.partie.ajouterCarteDefausse(cw);
						it.remove();
						nbEnl++;
					}
				}
			}
			// Utilisation de carte couleur sur une voie de la même couleur ou neutre
			else if (c != null && (c.equals(cVoie) || cVoie.equals(this.getCouleurs().get(0))) &&
				     nbCarte >= this.areteSelectionnee.getDistance()                             )
			{
				this.joueur.gethashMapCarteWagons().put(c, nbCarte - this.areteSelectionnee.getDistance());
				estValide = true;

				int nbEnl = 0;
				Iterator<CarteWagon> it = this.joueur.getAlCartesWagons().iterator();
				while (it.hasNext() && nbEnl < this.areteSelectionnee.getDistance()) 
				{
					CarteWagon cw = it.next();
					if (cw.getCouleur() == c)
					{
						this.partie.ajouterCarteDefausse(cw);
						it.remove();
						nbEnl++;
					}
				}
			}
			// Utilisation de carte couleur et joker sur une voie de la même couleur ou neutre
			else if (c != null && (c.equals(cVoie) || cVoie.equals(this.getCouleurs().get(0))) &&
				     nbCarte + nbJoker >= this.areteSelectionnee.getDistance()                   )
			{
				int nbJokerNeccessaire = this.areteSelectionnee.getDistance() - nbCarte;
				boolean confirmation = this.ihm.poserQuestion(
					"Voulez-vous utiliser " + nbJokerNeccessaire + " carte joker ?");

				if (confirmation)
				{
					this.joueur.gethashMapCarteWagons().put(c, 0);
					this.joueur.gethashMapCarteWagons().put(null, nbJoker - nbJokerNeccessaire);
					estValide = true;

					Iterator<CarteWagon> it = this.joueur.getAlCartesWagons().iterator();
					while (it.hasNext()) 
					{
						CarteWagon cw = it.next();
						if (cw.getCouleur() == c)
						{
							this.partie.ajouterCarteDefausse(cw);
							it.remove();
						}
					}

					int nbEnl = 0;
					it = this.joueur.getAlCartesWagons().iterator();
					while (it.hasNext() && nbEnl < nbJoker - nbJokerNeccessaire) 
					{
						CarteWagon cw = it.next();
						if (cw.getCouleur() == null)
						{
							this.partie.ajouterCarteDefausse(cw);
							it.remove();
							nbEnl++;
						}
					}
				}
				else
				{
					if (this.areteSelectionnee != null)
						this.ihm.afficherErreur("Aucune arête selectionné");
					else
						this.ihm.afficherErreur("Nombre de jeton insuffisant");
				}
			}

			if (estValide)
			{
				if (this.couleurSelectionnee == 1) this.areteSelectionnee.setProprietaire1(joueur);
				else                               this.areteSelectionnee.setProprietaire2(joueur);

				Iterator<Color> it = this.joueur.getAlCouleurs().iterator();
				while (it.hasNext()) 
				{
					Color coul = it.next();
					if (this.joueur.gethashMapCarteWagons().get(coul) == 0) 
					{
						it.remove();
						this.joueur.gethashMapCarteWagons().remove(coul);
					}
				}
				this.joueur.retirerJeton(this.areteSelectionnee.getDistance());

				this.areteSelectionnee = null;
				this.couleurSelectionnee = 0;

				this.ihm.majIHM();
			}
		}
	}

	public void piocherPioche ()        { this.partie.piocherPioche ();    }
	public void piocherVisible(int ind) { this.partie.piocherVisible(ind); }

	public boolean peuxJouer()
	{
		if ( this.partie != null || this.partie.getJoueurCourant() != null )
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
		this.joueur = new Joueur("Joueur 1");
		this.metier.ajouterJoueur(this.joueur);
		this.partie = new Partie(this, this.metier, true, "Partie multi-joueur");
		this.serverCtrl = new ServerControleur(this.metier,this.partie);
	}


	/**
	 * 
	 */
	public int joinGame(String ip, String nom, String password)
	{

		this.metier.creeClient(ip, nom, true, password);

		this.ihm.demarrerAttente(false);
		return 1;
		// this.joueur = new Joueur("Joueur 1");
		// this.metier.ajouterJoueur(this.joueur);
		// try 
		// {
		// 	this.clientCtrl = new ClientControleur(ip);

		// 	this.metier = clientCtrl.getMetier();
		// 	this.partie = clientCtrl.getPartie();
		// 	this.ihm.demarrerJeu();
		// }
		// catch (UnknownHostException e)	{ return 2;} 
		// catch (ConnectException e) 		{e.printStackTrace();} 
		// catch (IOException e) 			{e.printStackTrace();}

		// if (!password.equals(this.metier.getMotDePasse())) return 3;
		// return 1;

	}
	

	public Metier getMetier(){return this.metier;} // a tester supprimer apres

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
	 * Permet de récupérer la pioche de cartes objectifs
	 * @return un tableau de Carte Objectif
	 */
    public CarteObjectif getPiocheObjectif() 
	{
        return this.partie.getPiocheObjectif();
    }

	/**
	 * Ajouter une carte objectif dans la main du joueur
	 * @param cartesObjectifs : carte que l'on veut ajouter
	 */
	public void ajouterObjectifsJoueurs(CarteObjectif cartesObjectifs) 
	{
		this.metier.ajouterObjectifsJoueurs(cartesObjectifs);
		this.ihm.majIHM();
	}

	/**
	 * Permet de remettre les cartes non piochés par le joueur dans la pioche
	 * @param carteObjectif 
	 */
	public void remettreCarteObjectif(CarteObjectif carteObjectif) 
	{
		this.partie.remettreCarteObjectif(carteObjectif);
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

}
