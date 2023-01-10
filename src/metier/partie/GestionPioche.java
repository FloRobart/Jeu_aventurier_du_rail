package metier.partie;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import metier.CarteObjectif;
import metier.Metier;

public class GestionPioche 
{
	private LinkedList<CarteWagon>    lstCartesWagon;
	private CarteWagon[]              tabCartesVisible;
    private LinkedList<CarteObjectif> lstCartesObjectif;

	public GestionPioche(Metier metier)
	{
		// Creation de la pile de cartes wagon
		this.lstCartesWagon = new LinkedList<CarteWagon>();
		this.tabCartesVisible = new CarteWagon[5];
		for (int cpt = 0; cpt < metier.getNbCarteLocomotive(); cpt++)
			this.lstCartesWagon.add(new CarteWagon(
				null, metier.getImageVersoCouleur(), metier.getImageRectoLocomotive()));
		
		List<Color> lstCouleurs = metier.getCouleurs();
		for ( int cptCoul = 1 ; cptCoul < lstCouleurs.size() ; cptCoul++)
			for ( int cptCarte = 0 ; cptCarte < metier.getNbCarteCoul() ; cptCarte++)
				this.lstCartesWagon.add(new CarteWagon(
					lstCouleurs.get(cptCoul), 
					metier.getImageVersoCouleur(), 
					metier.getImagesRectoCouleur().get(cptCoul - 1)
				));

		Collections.shuffle(this.lstCartesWagon);

		// Creation du tableau de cartes visible
		this.tabCartesVisible = new CarteWagon[5];
		for ( int cpt = 0 ; cpt < 5 ; cpt++)
			this.tabCartesVisible[cpt] = this.lstCartesWagon.remove(0);
		
		// Creation de la pile de cartes objectif
		this.lstCartesObjectif = new LinkedList<CarteObjectif>(List.copyOf(metier.getCarteObjectif()));
		Collections.shuffle(this.lstCartesObjectif);
	}

	public CarteWagon[] getTabCartesVisible()          { return this.tabCartesVisible;         }
	public CarteWagon   getCarteVisible    (int index) { return this.tabCartesVisible[index];  }
	public int          getSizeWagon       ()          { return this.lstCartesWagon   .size(); }
	public int          getSizeObjectif    ()          { return this.lstCartesObjectif.size(); }

	// gestion des cartes wagon
	public CarteWagon piocherCarteWagon()
	{
		if ( this.lstCartesWagon.size() == 0) return null;

		CarteWagon carteWagon = this.lstCartesWagon.remove(0);
		return carteWagon;
	}

	/**
	 * Pioche 3 cartes objectif et les retourne
	 * @return tableau de 3 cartes objectif
	 */
	public CarteObjectif[] piocherCartesObjectif()
	{
		CarteObjectif[] tabCartesObjectif = new CarteObjectif[3];

		for ( int cpt = 0 ; cpt < 3 ; cpt++)
		{
			//if ( this.lstCartesObjectif.size() == 0 )
				//tabCartesObjectif[cpt] = null;
			//else
				tabCartesObjectif[cpt] = this.lstCartesObjectif.remove(0);
		}

		//if ( this.lstCartesObjectif.size() == 0) return null;
		
		if(this.lstCartesObjectif.size() < 3)
		{
			for(int cpt=0; cpt<this.lstCartesObjectif.size(); cpt++)
				tabCartesObjectif[cpt] = this.lstCartesObjectif.remove(0);
		}

		return tabCartesObjectif;
	}

	public void remettreCarteWagon(CarteWagon carteWagon)
	{
		this.lstCartesWagon.add(carteWagon);
	}

	/**
	 * Remet une carte objectif dans la pioche
	 * @param carteObjectif
	 */
	public void remettreCarteObjectif(CarteObjectif carteObjectif) 
	{
		this.lstCartesObjectif.add(carteObjectif);
	}
}