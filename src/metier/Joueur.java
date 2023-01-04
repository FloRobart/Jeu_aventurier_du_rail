package metier;

import java.util.List;

public class Joueur
{
    private String nom;
    private int score;
    private List<CarteObjectif> alCartesObjectif;
    private List<CarteWagon> alCartesWagon;
    private int nbJetonsPosés;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.score = 0;
        this.nbJetonsPosés = 0;
    }

    public String getNom() { return this.nom; }
    public int getScore() { return this.score; }
    public List<CarteObjectif> getAlCartesObjectif() { return this.alCartesObjectif; }
    public List<CarteWagon> getAlCartesWagon() { return this.alCartesWagon; }
    public int getNbJetonsPosés() { return this.nbJetonsPosés; }

    public void setNom(String nom) { this.nom = nom; }
    public void setScore(int score) { this.score = score; }
    public void setAlCartesObjectif(List<CarteObjectif> alCartesObjectif) { this.alCartesObjectif = alCartesObjectif; }
    public void setAlCartesWagon(List<CarteWagon> alCartesWagon) { this.alCartesWagon = alCartesWagon; }
    public void setNbJetonsPosés(int nbJetonsPosés) { this.nbJetonsPosés = nbJetonsPosés; }

    public void ajouterCarteObjectif(CarteObjectif carteObjectif)
    {
        this.alCartesObjectif.add(carteObjectif);
    }

    public void ajouterCarteWagon(CarteWagon carteWagon)
    {
        this.alCartesWagon.add(carteWagon);
    }

    public void ajouterScore(int score)
    {
        this.score += score;
    }

    public void ajouterNbJetonsPosés(int nbJetonsPosés)
    {
        this.nbJetonsPosés += nbJetonsPosés;
    }

    public void retirerCarteObjectif(CarteObjectif carteObjectif)
    {
        this.alCartesObjectif.remove(carteObjectif);
    }

    public void retirerCarteWagon(CarteWagon carteWagon)
    {
        this.alCartesWagon.remove(carteWagon);
    }

    public String toString()
    {
        return this.nom + " (" + this.score + " points)" + " : " + this.nbJetonsPosés + " jetons posés" + " -- " + this.alCartesObjectif.size() + " cartes objectif" + " : " + this.alCartesWagon.size() + " cartes wagon";
    }


    
}