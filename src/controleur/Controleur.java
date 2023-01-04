package controleur;

import frames.Ihm;
import metier.Metier;


public class Controleur 
{
    private Metier metier;
    private Ihm ihm;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new Ihm(this);
    }

    public static void main(String[] args)
    {
        new Controleur();
    }
}
