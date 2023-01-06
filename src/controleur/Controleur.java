package controleur;

import java.util.HashMap;
import java.util.List;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.File;

import ihm.Ihm;
import metier.*;
import metier.reseau.Client;
import metier.reseau.Server;


public class Controleur 
{
    private Metier metier;
    private Ihm    ihm;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm    = new Ihm(this);
    }

	public boolean ouvrir(File fichier)
	{
		return this.metier.lireFichier(fichier);
	}

	public void creerPartieSolo()
	{
		this.ihm.demarrerJeu();
	}

	/* --------------------------- */
	/*          Getters            */
	/* --------------------------- */
	public List<Joueur>        getJoueurs             () { return this.metier.getJoueurs      (); 			}
	public List<CarteWagon>    getCarteWagon          () { return this.metier.getCarteWagon   (); 			}
	public List<CarteObjectif> getCarteObjectif       () { return this.metier.getCarteObjectif(); 			}
	public List<Noeud>         getNoeuds              () { return this.metier.getNoeuds       (); 			}
	public List<Arete>         getAretes              () { return this.metier.getAretes       (); 			}
	public List<CarteWagon>    getLstDefausseCartesWagon() { return this.metier.getLstDefausseCartesWagon();}
	public CarteWagon[]		   getTabCarteWagon			() { return this.metier.getTabCarteWagon();         }
	public CarteObjectif[]	   getTabCarteObjectif		() { return this.metier.getTabCarteObjectif();     	}

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

	public void piocherDeck (char typeCarte, String nomJoueur)			 {this.metier.piocherDeck(typeCarte, nomJoueur);		}
	public void piocherTabWagon (int indiceTab, String nomJoueur)		 {this.metier.piocherTabWagon(indiceTab, nomJoueur);	}
	public void piocherTabObjectif (boolean[] tabBool, String nomJoueur) {this.metier.piocherTabObjectif(tabBool, nomJoueur);	}
	public void setImageButton(int indice) 								 { if ( this.ihm != null ) this.ihm.setImageButton(indice);}

	/**
     * Permet d'appliquer le thème à l'ihm
     */
    public void appliquerTheme()
	{
		this.ihm.appliquerTheme();
	}

    /**
     * Permet de à l'ihm de récupérer la hashmap contenant les couleurs du thème
     * @return HashMap contenant les couleurs du thème
     */
    public HashMap<String, List<Color>> getTheme()
    {
        return this.metier.getTheme();
    }

    /**
     * Change le thème à utilisé dans le fichier de sauvegarde.
     * Charge en mémoire le nouveau thème.
     * Met à jour l'ihm.
     * @param theme : Nom du thème à utiliser
     */
    public void changerTheme(String theme)
	{
		this.metier.setThemeUsed(theme);
	}
	/*
	 * 
	 */
	public void hostGame()
	{
		new ServerControleur(metier);
	}
	public void joinGame(String ip)
	{		
		ClientControleur clientCtrl = new ClientControleur(ip);
		this.metier = clientCtrl.getMetier();
		this.ihm.demarrerJeu();
	}
    public static void main(String[] args)
    {
        new Controleur();
    }

    public Joueur getJoueurSelect() {
        return null;
    }
}
