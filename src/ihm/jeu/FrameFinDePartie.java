package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import metier.Joueur;
import controleur.Controleur;

public class FrameFinDePartie extends JFrame implements ActionListener
{
    private Controleur ctrl;

    private JLabel     lblFinDePartie;

    private List<Joueur> lstJoueurs;
    private JPanel      panelResumePartie;
    private JPanel      panelHaut;
    private JPanel      panelCentral;
    private JPanel      panelBas;
    private JLabel[]    tabLblIconJoueur;
    private JLabel[]    tabLblNom;
    private JLabel[]    tabLblScore;

    private JButton    btnQuitter;

    public FrameFinDePartie(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Parametrage de la frame
        Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimEcran.width/2, dimEcran.height/2); // Définition d'une taille minimum (obligatoire)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(dimEcran.width/4, dimEcran.height/4);
        this.setTitle("Frame Fin De Partie");
        this.setLayout(new BorderLayout());

        //Creation des composants
        this.lblFinDePartie = new JLabel("  FIN DE LA PARTIE ");;
        
        this.btnQuitter = new JButton("Quitter");

        this.panelHaut = new JPanel();
        this.panelHaut.setLayout(new GridLayout(1,3));

        this.panelCentral = new JPanel();

        this.panelBas = new JPanel();
        this.panelBas.setLayout(new GridLayout(1,5));

        this.lstJoueurs = this.ctrl.getJoueurs();

        this.tabLblIconJoueur = new JLabel[this.lstJoueurs.size()];
        this.tabLblNom  = new JLabel [this.lstJoueurs.size()];
        this.tabLblScore= new JLabel [this.lstJoueurs.size()];

        this.panelResumePartie = new JPanel();
        this.panelResumePartie.setLayout(new GridLayout(this.lstJoueurs.size(), 1));

        //Ajout des composants à la frame
        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelCentral, BorderLayout.CENTER);
        this.add(this.panelBas, BorderLayout.SOUTH);

        //Ajout des composants aux panels
        //this.panelResumePartie.add();

        this.panelHaut.add(new JLabel(""));
        this.panelHaut.add(this.lblFinDePartie);
        this.panelHaut.add(new JLabel(""));

        this.panelCentral.add(this.panelResumePartie);

        this.panelBas.add(new JLabel(""));
        this.panelBas.add(new JLabel(""));
        this.panelBas.add(this.btnQuitter);
        this.panelBas.add(new JLabel(""));
        this.panelBas.add(new JLabel(""));

        //Ajout des composants au action listener
        this.btnQuitter.addActionListener(this);    

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnQuitter )
        {
            this.dispose();
            this.ctrl.disposeFrameJeu();
        }
    }

        /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
		Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);

        this.setBackground(background);
        this.panelCentral.setBackground(background);
;

        /*---------*/
        /* Boutons */
        /*---------*/
        /* Foreground */
        this.btnQuitter.setForeground(btnForeColor);

        /* Background */
        this.btnQuitter.setBackground(btnBackColor);

        for (int i = 0; i < this.lstJoueurs.size(); i++)
        {
            /*--------*/
            /* Panels */
            /*--------*/

            /* Images */
            String pathImage = "";
            if (this.ctrl.getThemeUsed().equals("dark"))
                pathImage = "./bin/donnees/images/IconJoueurWhite.png";
            else
                pathImage = "./bin/donnees/images/IconJoueurBlack.png";
            
            this.tabLblIconJoueur[i].setIcon(new ImageIcon(pathImage));


            /*--------*/
            /* Labels */
            /*--------*/
            /* Lables Nom */
            this.tabLblNom  [i].setOpaque(false);
            this.tabLblNom  [i].setForeground(labelForeColor);

            this.tabLblIconJoueur[i].setBackground(btnBackColor);
            this.tabLblIconJoueur[i].setForeground(labelForeColor);

            /* Lables Score */
            this.tabLblScore[i].setOpaque(false);
            this.tabLblScore[i].setForeground(labelForeColor);
        }
    }
}
