package ihm.finPartie;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;


public class PanelResultat extends JPanel
{
    private Controleur ctrl;

    private List<List<JLabel>> lstLabels;

    public PanelResultat(Controleur ctrl)
    {
        this.ctrl = ctrl;

        int nbJoueurs = this.ctrl.getJoueurs().size();
        String[] enteteColonne = {"Nom", "Nombre de voie prisent", "Cartes objectifs validées", "Cartes objectifs ratées", "Points gagnées", "Points malus", "Score final"};
        this.setLayout(new GridLayout(nbJoueurs+1, 7));


        /* Liste de labels */
        this.lstLabels = new ArrayList<List<JLabel>>();

        /* Création de chaque ArrayList (une ArrayList = une ligne) */
        for (int i = 0; i < nbJoueurs; i++)
            this.lstLabels.add(new ArrayList<JLabel>());

        /* Ajoutes les labels dans la première Ligne */
        for (int i = 0; i < this.lstLabels.get(0).size(); i++)
            this.lstLabels.get(0).add(new JLabel(enteteColonne[i]));

        /* Ajoutes les labels des autres lignes  */
        for (int i = 1; i < this.lstLabels.size(); i++) {
            for (int j = 0; j < this.lstLabels.get(0).size(); j++) {
                String txt = "";

                switch (j)
                {
                    //case 0 -> txt =                this.ctrl.getJoueurs().get(i-1).getNom(); // Nom
                    //case 1 -> txt =                this.ctrl.getJoueurs().get(i-1).; // Nombre de voie prisent
                    //case 2 -> txt =                this.ctrl.getJoueurs().get(i-1).; // Cartes objectifs validées
                    //case 3 -> txt =                this.ctrl.getJoueurs().get(i-1).; // Cartes objectifs ratées
                    //case 4 -> txt = String.valueOf(this.ctrl.getJoueurs().get(i-1).getScore()); // Points gagnées
                    //case 5 -> txt =                this.ctrl.getJoueurs().get(i-1).; // Points malus
                    //case 6 -> txt =                this.ctrl.getJoueurs().get(i-1).; // Score final
                    default -> txt = "Erreur";
                }

                this.lstLabels.get(i).add(new JLabel(txt));
                this.lstLabels.get(i).get(j).setOpaque(false);
            }
        }


        /* Ajout des labels au panel */
        System.out.println(this.lstLabels.get(0).size());
        for (int i = 0; i < this.lstLabels.size(); i++) {
            for (int j = 0; j < this.lstLabels.get(0).size(); j++) {
                this.add(this.lstLabels.get(i).get(j));
            }
        }
    }

        /**
         * Applique le thème à tout les labels
         */
        public void appliquerTheme()
        {
            Color background = this.ctrl.getTheme().get("background").get(0);
            Color labelForeColor = this.ctrl.getTheme().get("labels").get(0);


            this.setBackground(background);

            for (int i = 0; i < this.lstLabels.size(); i++)
                for (int j = 0; j < this.lstLabels.size(); j++)
                {
                    this.lstLabels.get(i).get(j).setForeground(labelForeColor);
                    this.lstLabels.get(i).get(j).setOpaque(false);
                }
        }
}
