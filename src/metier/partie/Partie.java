package metier.partie;

import controleur.Controleur;
import metier.Joueur;
import metier.Metier;

public class Partie 
{
	private Controleur ctrl;

	private GestionPioche gestionPioche;
	private Joueur[]      joueurs;
	private Joueur        joueurCourant;
	private int           nbJetonFin;
	private int           tour;

	public Partie(Controleur ctrl, Metier metier)
	{
		this.ctrl = ctrl;
		this.gestionPioche = new GestionPioche(metier);

		this.joueurs = new Joueur[metier.getJoueurs().size()];
		for (int i = 0; i < this.joueurs.length; i++)
		{
			this.joueurs[i] = metier.getJoueurs().get(i);
			this.joueurs[i].setNbJetonsRestant(metier.getNbJetonJoueur());

			// attributions des cartes de départ
			for (int cpt = 0 ; cpt < 4 ; cpt++)
				this.joueurs[i].ajouterCarteWagon(this.gestionPioche.piocherCarteWagon());
		}

		this.nbJetonFin = metier.getNbJetonFin();
		this.tour = 1;

		while (!this.estTerminee())
			this.jouerTour();
	}

	public boolean estTerminee()
	{
		for (Joueur joueur : this.joueurs)
			if (joueur.getNbJetonsRestant() <= this.nbJetonFin)
				return true;

		return false;
	}

	public void jouerTour()
	{
		for (Joueur joueur : this.joueurs)
		{
			this.joueurCourant = joueur;
			//this.joueurCourant.jouerTour(this.ctrl, this.gestionPioche);
			this.tour++;
		}
	}
}
