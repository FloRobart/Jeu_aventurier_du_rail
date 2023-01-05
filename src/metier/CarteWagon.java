package metier;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class CarteWagon 
{
    private Color         couleur;
    private BufferedImage imageVerso;
    private BufferedImage imageRecto;
    private boolean       joker;

    public CarteWagon(Color couleur, BufferedImage imageVerso, BufferedImage imageRecto)
    {
        if(couleur == null)
            this.joker = true;
        else
        {
            this.couleur    = couleur;
            this.joker = false;
        }

        this.imageVerso = imageVerso;
        this.imageRecto = imageRecto;
    }

    public Color getCouleur() { return this.couleur; }
    public BufferedImage getImageVerso() { return this.imageVerso; }
    public BufferedImage getImageRecto() { return this.imageRecto; }
    public boolean isJoker() { return this.joker; }

    public void setCouleur(Color couleur) { this.couleur = couleur; }
    public void setImageVerso(BufferedImage imageVerso) { this.imageVerso = imageVerso; }
    public void setImageRecto(BufferedImage imageVerso) { this.imageVerso = imageVerso; }
    public void setJoker(boolean joker) { this.joker = joker; }



}
