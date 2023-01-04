package metier;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class CarteWagon 
{
    private Color         couleur;
    private BufferedImage imageVerso;
    private boolean       joker;

    public CarteWagon(Color couleur, BufferedImage imageVerso)
    {
        if(couleur == null)
            this.joker = true;
        else
        {
            this.couleur    = couleur;
            this.joker = false;
        }

        this.imageVerso = imageVerso;
    }

    public Color getCouleur() { return this.couleur; }
    public BufferedImage getImageVerso() { return this.imageVerso; }
    public boolean isJoker() { return this.joker; }

    public void setCouleur(Color couleur) { this.couleur = couleur; }
    public void setImageVerso(BufferedImage imageVerso) { this.imageVerso = imageVerso; }
    public void setJoker(boolean joker) { this.joker = joker; }



}
