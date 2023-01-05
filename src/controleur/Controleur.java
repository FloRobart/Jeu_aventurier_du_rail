package controleur;

import java.util.HashMap;
import java.util.List;

import java.awt.Color;

import ihm.frames.Ihm;
import metier.Metier;


public class Controleur 
{
    private Metier metier;
    private Ihm    ihm;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm    = new Ihm(this);
    }

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

    public static void main(String[] args)
    {
        new Controleur();
    }
}
