package metier.partie;

import controleur.Controleur;
import metier.Joueur;
import metier.Metier;

public class Partie 
{
	private Controleur ctrl;

	private GestionPioche gestionPioche;
	private Joueur[]      joueurs;

	public Partie(Controleur ctrl, Metier metier)
	{
		this.ctrl = ctrl;
		this.gestionPioche = new GestionPioche(metier);
	}
}
