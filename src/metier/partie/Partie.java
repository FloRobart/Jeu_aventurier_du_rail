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

	private transient GestionPioche  gestionPioche;
	private List<Arete>   alArete;
	private Joueur[]      joueurs;
	private transient Joueur        joueurCourant;
	private int           nbJetonFin;
	private int           tour;
	private boolean       estMulti; // mettre le serveur au lieu d'un boolean

	public void setJoueurCourrant(Joueur j)
	{
		this.joueurCourant = j;
	}

	public Partie(Controleur ctrl, Metier metier, boolean estMulti)
	{
		this.ctrl          = ctrl;
		this.gestionPioche = new GestionPioche(metier);
		this.alArete       = metier.getAretes();

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

		this.nbJetonFin    = metier.getNbJetonFin();
		this.tour          = 0;

		if (this.joueurs[0] != null) this.joueurCourant = this.joueurs[0];
		else 					     this.joueurCourant = null;

		this.estMulti      = estMulti;
	}

	public boolean estTerminee()
	{
		for (Joueur joueur : this.joueurs)
			if (joueur.getNbJetonsRestant() <= this.nbJetonFin)
				return true;

		return false;
	}

	public void joueurSuivant()
	{
		int indJoueur = 0;

		for (int cpt = 0; cpt < this.joueurs.length; cpt++)
			if (this.joueurs[cpt] == this.joueurCourant)
				indJoueur = (cpt++) % this.joueurs.length;
		
		this.joueurCourant = this.joueurs[indJoueur];
		if (indJoueur == 0)
		{
			this.tour++;
			this.ctrl.setNbTours(this.tour);
		} 
	}

	public Joueur 		getJoueurCourant()	  { return this.joueurCourant; }
	public CarteWagon[] getTabCartesVisible() { return this.gestionPioche.getTabCartesVisible(); }
	public int          getSizeWagon       () { return this.gestionPioche.getSizeWagon(); }
	public int			getTours()			  { return this.tour;}

	public void piocherPioche()
	{
		CarteWagon carte = this.gestionPioche.piocherCarteWagon();

		if (carte != null)
			this.joueurCourant.ajouterCarteWagon(carte);
	}

	public void piocherVisible(int ind)
	{
		CarteWagon carte = this.gestionPioche.getCarteVisible(ind);

		if (carte != null)
			this.joueurCourant.ajouterCarteWagon(carte);
		
		this.gestionPioche.getTabCartesVisible()[ind] = this.gestionPioche.piocherCarteWagon();

		this.verifierVisible();
	}

	public CarteObjectif[] getPiocheObjectif() 
	{
		return this.gestionPioche.piocherCartesObjectif();
	}

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

	public void remettreCarteObjectif(CarteObjectif carteObjectif) 
	{
		this.gestionPioche.remettreCarteObjectif(carteObjectif);
	}
}
