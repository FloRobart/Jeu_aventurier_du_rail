package ihm.jeu;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import controleur.Controleur;
import metier.Joueur;

public class PanelInfosJoueur extends JPanel
{
    private Controleur ctrl;
    private Joueur     joueur;

    private JPanel      panelInfos;
    private JPanel      panelIcon;

    private JLabel        lblNom;
    private JLabel        lblNbJeton;
    private JLabel        lblNbCartesWagon;
    private JLabel        lblNbCartesObjectif;
    private JLabel        lblIcon;
    private JLabel        lblInfos;

    private int nbWagons;
    private int nbCartesObjectif;
    private int nbCartesWagon;

    public PanelInfosJoueur(Controleur ctrl, Joueur joueur)
    {
        this.ctrl = ctrl;
        this.joueur = joueur;

        //initialisation des composants
        //this.joueur = ctrl.getJoueurCourant();
        this.setLayout(new BorderLayout());
        this.nbWagons            = this.joueur.getNbJetonsRestant();
        this.nbCartesObjectif    = this.joueur.getNbCartesObjectif();
        this.nbCartesWagon       = this.joueur.getNbCartesWagon();

        this.lblNom              = new JLabel(this.joueur.getNom());
        //this.lblNom.setForeground(this.joueur.getCouleur());
        this.lblNbJeton          = new JLabel(this.nbWagons  + " jetons restants ");
        this.lblNbCartesWagon    = new JLabel(this.nbCartesWagon    + " cartes wagon ");
        this.lblNbCartesObjectif = new JLabel(this.nbCartesObjectif + " cartes objectif ");
        this.lblIcon             = new JLabel();


        this.lblInfos = new JLabel(" Infos sur le Joueurs");

        this.panelInfos = new JPanel();
        this.panelInfos.setLayout(new GridLayout(3,1));

        this.panelIcon  = new JPanel();


        /*-----NE PAS SUPPRIMER------ */
        if(this.nbWagons <= 1)
            this.lblNbJeton.setText(this.nbWagons + " wagon placé ");
        
        if(this.nbCartesObjectif <= 1)
            this.lblNbCartesObjectif.setText(this.nbCartesObjectif + " carte objectif ");

        if(this.nbCartesWagon <= 1)
            this.lblNbCartesWagon.setText(this.nbCartesWagon + " carte wagon ");

        
        //ajout des composants
        GroupLayout layout = new GroupLayout(this.panelIcon);
        this.panelIcon.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblNom, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5,5,5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblNom))
                .addGap(5,5,5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblIcon, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                )
        );

        this.panelInfos.add(this.lblNbJeton         );
        this.panelInfos.add(this.lblNbCartesWagon   );
        this.panelInfos.add(this.lblNbCartesObjectif);

        this.add(this.lblInfos  , BorderLayout.NORTH);
        this.add(this.panelIcon , BorderLayout.CENTER);
        this.add(this.panelInfos, BorderLayout.EAST);

        this.appliquerTheme();
    }

    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background       = theme.get("background"  ).get(0);
        Color titleForeColor   = theme.get("titles"      ).get(0);
        Color titleBackColor   = theme.get("titles"      ).get(1);
        Color labelForeColor   = theme.get("labels"      ).get(0);


        /* Panels */
        this.setBackground(titleBackColor);
        this.setForeground(titleForeColor);

        this.panelIcon .setForeground(labelForeColor);
        this.panelIcon .setBackground(background    );

        this.panelInfos.setForeground(labelForeColor);
        this.panelInfos.setBackground(background    );


        /* Labels */
        this.lblNom             .setForeground(labelForeColor);
        this.lblInfos           .setForeground(labelForeColor);
        this.lblNbJeton         .setForeground(labelForeColor);
        this.lblNbCartesWagon   .setForeground(labelForeColor);
        this.lblNbCartesObjectif.setForeground(labelForeColor);


        String pathImage = "";
        if (this.ctrl.getThemeUsed().equals("dark"))
            pathImage = "./bin/donnees/images/IconJoueurWhite.png";
        else
            pathImage = "./bin/donnees/images/IconJoueurBlack.png";

        this.lblIcon.setIcon(new ImageIcon(pathImage));
    }
}
