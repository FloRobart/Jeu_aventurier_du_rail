package metier;

import java.util.HashMap;
import java.util.List;
import java.awt.Color;
import java.io.Serializable;

public class Joueur implements Serializable
{
    private String nom;
    private int score;
    private List<CarteObjectif> alCartesObjectif;
    private HashMap<Color,Integer> hashMapCarteWagons;
    private int nbJetonsRestant;
    private Color  couleur;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.score = 0;
        this.nbJetonsRestant = 0;
    }

    public String getNom() { return this.nom; }
    public int getScore() { return this.score; }
    public List<CarteObjectif> getAlCartesObjectif() { return this.alCartesObjectif; }
    public int getNbCartesObjectif() { return this.alCartesObjectif.size(); }
    public HashMap<Color,Integer> gethashMapCarteWagons() { return this.hashMapCarteWagons; }
    public int getNbCartesWagon() { return this.hashMapCarteWagons.size(); }
    public int getNbJetonsPosés() { return this.nbJetonsRestant; }
    public Color getCouleur() { return this.couleur; }

    public void setNom(String nom) { this.nom = nom; }
    public void setScore(int score) { this.score = score; }
    public void setAlCartesObjectif(List<CarteObjectif> alCartesObjectif) { this.alCartesObjectif = alCartesObjectif; }
    public void sethashMapCarteWagons(HashMap<Color,Integer> hashMapCarteWagons) { this.hashMapCarteWagons = hashMapCarteWagons; }
    public void setNbJetonsPosés(int nbJetonsRestant) { this.nbJetonsRestant = nbJetonsRestant; }
    public void setCouleur(Color couleur) { this.couleur = couleur; }

    public void ajouterCarteObjectif(CarteObjectif carteObjectif)
    {
        this.alCartesObjectif.add(carteObjectif);
    }

    public void ajouterCarteWagon(CarteWagon carteWagon)
    {
        Color coul = carteWagon.getCouleur();
        Integer nbCarte = this.hashMapCarteWagons.get(coul) + 1 ;
        this.hashMapCarteWagons.replace(coul,nbCarte);
    }

    public void ajouterScore(int score)
    {
        this.score += score;
    }

    public void ajouterNbJetonsPosés(int nbJetonsRestant)
    {
        this.nbJetonsRestant += nbJetonsRestant;
    }

    public void retirerCarteObjectif(CarteObjectif carteObjectif)
    {
        this.alCartesObjectif.remove(carteObjectif);
    }

    public void retirerCarteWagon(CarteWagon carteWagon)
    {
        Color coul = carteWagon.getCouleur();
        Integer nbCarte = this.hashMapCarteWagons.get(coul)  -1 ;
        this.hashMapCarteWagons.replace(coul,nbCarte);
    }

    public String toString()
    {
        return this.nom + " (" + this.score + " points)" + " : " + this.nbJetonsRestant + " jetons posés" + " -- " + this.alCartesObjectif.size() + " cartes objectif" + " : " + this.hashMapCarteWagons.size() + " cartes wagon";
    }


    
}