package metier.partie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controleur.Controleur;
import metier.Arete;
import metier.CarteObjectif;
import metier.Joueur;
import metier.Metier;

public class Partie implements Serializable
{
	private transient Controleur ctrl;
	private static final long serialVersionUID = 2L;

	private String         nomPartie;
	private GestionPioche  gestionPioche;
	private List<Arete>    alArete;
	private Joueur[]       joueurs;
	private int            joueurCourrantId;
	private int            nbJetonFin;

	private int            tour;
	private boolean        estMulti;
	private int            joueurFin;
	private Integer[]      scoreFinal;

	/* ==================== */
	/*     CONSTRUCTEUR     */
	/* ==================== */
	public Partie(Controleur ctrl, Metier metier, boolean estMulti, String nomPartie)
	{
		this.ctrl          = ctrl;
		this.gestionPioche = new GestionPioche(metier);
		this.alArete       = metier.getAretes();
		this.nomPartie     = nomPartie;

		this.joueurs = new Joueur[metier.getJoueurs().size()];
		for (int i = 0; i < this.joueurs.length; i++)
		{
			this.joueurs[i] = metier.getJoueurs().get(i);
			this.joueurs[i].setNbJetonsRestant(metier.getNbJetonJoueur());

			// attributions des cartes de départ
			CarteWagon carte;
			for (int cpt = 0 ; cpt < 4 ; cpt++)
			{
				carte = this.gestionPioche.piocherCarteWagon();

				if (carte != null)
					this.joueurs[i].ajouterCarteWagon(carte);
			}

		}

		this.scoreFinal	   = new Integer[this.joueurs.length];

		this.nbJetonFin    = metier.getNbJetonFin();
		this.tour          = 1;

		this.joueurCourrantId = 0;

		this.estMulti = estMulti;
		this.joueurFin = -1;

	}

	/* ==================== */
	/*        SETTERS       */
	/* ==================== */
	public void setCtrl(Controleur ctrl)
	{
		this.ctrl = ctrl;
		for (Joueur j : this.joueurs)
		{
			j.setCtrl(ctrl);
		}
	}

	/* ==================== */
	/*       GETTERS        */
	/* ==================== */
	public Joueur 		getJoueurCourant   () { return this.joueurs[this.joueurCourrantId];      }
	public Joueur[]     getJoueurs         () {	return this.joueurs;                             }
	public List<Joueur> getJoueursList     () { return new ArrayList<Joueur>(java.util.Arrays.asList(this.joueurs)); }
	public CarteWagon[] getTabCartesVisible() { return this.gestionPioche.getTabCartesVisible(); }
	public int          getSizeWagon       () { return this.gestionPioche.getSizeWagon();        }
	public int          getSizeObjectif    () { return this.gestionPioche.getSizeObjectif();     }
	public List<Arete>  geAretes           () { return this.alArete;                             }
	public int			getTours           () { return this.tour;                                }
	public boolean      getEstMulti        () { return this.estMulti;                            }

	/* ========================= */
	/*  GESTION TOUR ET JOUEURS  */
	/* ========================= */
	public void joueurSuivant()
	{
		int indJoueur = 0;
		
		// si un joueur n'a plus assez de jeton, alors la partie se terminera à son prochain tour
		if (this.joueurFin == -1 & this.getJoueurCourant().getNbJetonsRestant() <= this.nbJetonFin)
			this.joueurFin = this.joueurCourrantId;

		indJoueur = (this.joueurCourrantId + 1) % this.joueurs.length;
		
		this.joueurCourrantId = indJoueur;
		this.ctrl.setInfo(this.tour, this.joueurs[this.joueurCourrantId].getNom());
		
		if (this.joueurCourrantId == this.joueurFin)
		{
			this.ctrl.ouvrirFinPartie(true);
		}
		
		if (indJoueur == 0)
		{
			this.tour++;
		}
		
		if (!estMulti) this.ctrl.changerJoueur(this.getJoueurCourant());
	}

	/* ======================= */
	/*  GESTION PIOCHE WAGONS  */
	/* ======================= */

	// pioche une carte wagon et la donne au joueur courant
	public void piocherPioche()
	{
		CarteWagon carte = this.gestionPioche.piocherCarteWagon();

		if (carte != null)
			this.getJoueurCourant().ajouterCarteWagon(carte);

		if (this.gestionPioche.getSizeWagon() == 0)
		{
			this.gestionPioche.transfertPioche();
			this.ctrl.majIHM();
		}
	}

	// prend une carte wagon visible et la donne au joueur courant
	public void piocherVisible(int ind)
	{
		CarteWagon carte = this.gestionPioche.getCarteVisible(ind);

		if (carte != null)
			this.getJoueurCourant().ajouterCarteWagon(carte);
		
		this.gestionPioche.getTabCartesVisible()[ind] = this.gestionPioche.piocherCarteWagon();

		this.verifierVisible();
		if (this.gestionPioche.getSizeWagon() == 0)
		{
			this.gestionPioche.transfertPioche();
			this.ctrl.majIHM();
		}
	}

	// verifie que les cartes wagon visible ne contiennent pas 3 jockers
	public void verifierVisible()
	{
		int nbJoker = 0;
		CarteWagon[] tabCartes = this.gestionPioche.getTabCartesVisible();

		for (CarteWagon carte : tabCartes)
			if (carte != null && carte.isJoker()) nbJoker++;

		if (nbJoker >= 3)
		{
			for (int i = 0 ; i < 5 ; i++)
				if (tabCartes[i] != null && tabCartes[i].isJoker()) 
				{
					//défausser
					tabCartes[i] = this.gestionPioche.piocherCarteWagon();
				}

			this.verifierVisible();
		}
	}

	// met une carte wagon utilisée dans la defausse
	public void ajouterCarteDefausse(CarteWagon carte)
	{
		this.gestionPioche.ajouterCarteDefausse(carte);
	}

	/* ========================= */
	/*  GESTION PIOCHE OBJETIFS  */
	/* ========================= */

	// pioche une carte objectif
	public CarteObjectif getPiocheObjectif() 
	{
		return this.gestionPioche.piocherCartesObjectif();
	}

	// remet une carte objectif dans le fond de lapioche
	public void remettreCarteObjectif(CarteObjectif carteObjectif) 
	{
		this.gestionPioche.remettreCarteObjectif(carteObjectif);
	}
}
