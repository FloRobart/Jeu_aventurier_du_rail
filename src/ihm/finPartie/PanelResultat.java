package ihm.finPartie;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import controleur.Controleur;
import metier.Joueur;

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

        int nbJoueurs = this.ctrl.getJoueursPartie().length;
        String[] enteteColonne = {"Nom", "Voie prisent", "Objectifs validées", "Objectifs ratées", "Points gagnées", "Points malus", "Score final"};
        this.setLayout(new GridLayout(nbJoueurs+1, 7));


        /* Liste de labels */
        this.lstLabels = new ArrayList<List<JLabel>>();

        /* Création de chaque ArrayList (une ArrayList = une ligne) */
        for (int i = 0; i < nbJoueurs+1; i++)
            this.lstLabels.add(new ArrayList<JLabel>());


        /* Ajoutes les labels dans la première Ligne */
        Color titleBackColor = this.ctrl.getTheme().get("titles").get(1);
        for (int i = 0; i < enteteColonne.length; i++)
        {
            this.lstLabels.get(0).add(new JLabel(enteteColonne[i]));
            this.lstLabels.get(0).get(i).setOpaque(false);
            this.lstLabels.get(0).get(i).setHorizontalAlignment(JLabel.CENTER);
            this.lstLabels.get(0).get(i).setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
            
        }

        /* Ajoutes les labels des autres lignes  */
        for (int i = 1; i < this.lstLabels.size(); i++) {
            for (int j = 0; j < this.lstLabels.get(0).size(); j++) {

                Joueur joueur = this.ctrl.getJoueursPartie()[i-1];
				joueur.ajouterObjectifs();
                String txt = "";
                switch (j)
                {
                    case 0 -> txt =                joueur.getNom(); // Nom
                    case 1 -> txt = String.valueOf(joueur.getNbArete()); // Nombre de voie prisent
                    case 2 -> txt = String.valueOf(joueur.getAlObjectifsFinis().size()); // Cartes objectifs validées
                    case 3 -> txt = String.valueOf(joueur.getNbCartesObjectif()); // Cartes objectifs ratées
                    case 4 -> txt = String.valueOf(joueur.getScore()); // Points gagnées
                    case 5 -> txt = String.valueOf(joueur.getMalus()); // Points malus
                    case 6 -> txt = String.valueOf(this.calculerScroreFinal(joueur)); // Score final
                    default -> txt = "Erreur";
                } 

                this.lstLabels.get(i).add(new JLabel(txt));
                this.lstLabels.get(i).get(j).setOpaque(false);
                this.lstLabels.get(i).get(j).setHorizontalAlignment(JLabel.CENTER);
                this.lstLabels.get(i).get(j).setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
            }
        }


        /* Ajout des labels au panel */
        for (int i = 0; i < this.lstLabels.size(); i++) {
            for (int j = 0; j < this.lstLabels.get(0).size(); j++) {
                this.add(this.lstLabels.get(i).get(j));
            }
        }
    }


    public int calculerScroreFinal(Joueur j)
    {
        int score = 0;
        
        score += (j.getScore() - j.getMalus());

        return score;
    }


    public Joueur getVainqueur()
    {
        Joueur vainqueur = this.ctrl.getJoueursPartie()[0];
        int max = Integer.valueOf(this.lstLabels.get(1).get(6).getText());

        for (int i = 2; i < this.lstLabels.size(); i++) {
            if (max < Integer.valueOf(this.lstLabels.get(i).get(6).getText()))
            {
                vainqueur = this.ctrl.getJoueursPartie()[i-1];
                max = Integer.valueOf(this.lstLabels.get(i).get(6).getText());
            }
        }

        return vainqueur;
    }


    /**
     * Applique le thème à tout les labels
     */
    public void appliquerTheme()
    {
        Color background     = this.ctrl.getTheme().get("background").get(0);
        Color labelForeColor = this.ctrl.getTheme().get("labels"    ).get(0);
        Color titleBackColor = this.ctrl.getTheme().get("titles"    ).get(1);


        this.setForeground(labelForeColor);
        this.setBackground(background);


        /* Labels */
        for (int i = 0; i < this.lstLabels.size(); i++)
            for (int j = 0; j < this.lstLabels.get(0).size(); j++)
            {
                this.lstLabels.get(i).get(j).setOpaque(false);
                this.lstLabels.get(i).get(j).setForeground(labelForeColor);
                this.lstLabels.get(i).get(j).setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
            }
    }
}
