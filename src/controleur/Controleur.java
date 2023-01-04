package controleur;

public class Controleur 
{
    private Metier metier;
    private FrameJeu ihm;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new Ihm(this);
    }
}
