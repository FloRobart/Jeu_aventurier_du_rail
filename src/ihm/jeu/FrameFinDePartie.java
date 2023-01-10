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
    private JPanel      panelJoueurs;
    private JPanel      panelHaut;
    private JPanel      panelCentral;
    private JPanel      panelBas;
    private JScrollPane scrollJoueurs;
    private JPanel[]    tabPanels;
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
        this.panelHaut.setLayout(new BorderLayout());

        this.panelCentral = new JPanel();
        this.panelCentral.setLayout(new GridLayout(1,3));

        this.panelBas = new JPanel();
        this.panelBas.setLayout(new GridLayout(1,5));

        this.lstJoueurs = this.ctrl.getJoueurs();
        this.tabPanels  = new JPanel [this.lstJoueurs.size()];
        this.tabLblIconJoueur = new JLabel[this.tabPanels.length];
        this.tabLblNom  = new JLabel [this.tabPanels.length];
        this.tabLblScore= new JLabel [this.tabPanels.length];

        this.panelJoueurs = new JPanel();
        this.panelJoueurs.setLayout(new GridLayout(this.tabPanels.length, 1));

        for(int cpt=0; cpt< this.tabPanels.length; cpt++)
        {
            this.tabPanels[cpt] = new JPanel();
            Color titleBackColor = this.ctrl.getTheme().get("titles").get(1);
            this.tabPanels[cpt].setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
            this.tabPanels[cpt].setLayout(new BorderLayout());

            this.tabLblNom  [cpt] = new JLabel(" " + this.lstJoueurs.get(cpt).getNom());
            this.tabLblScore[cpt] = new JLabel("Score : " + this.lstJoueurs.get(cpt).getScore());

            this.tabLblIconJoueur[cpt] = new JLabel();
            
            this.tabPanels[cpt].add(this.tabLblNom  [cpt], BorderLayout.NORTH );
            this.tabPanels[cpt].add(this.tabLblIconJoueur [cpt], BorderLayout.WEST  );
            this.tabPanels[cpt].add(this.tabLblScore[cpt], BorderLayout.CENTER);

            this.panelJoueurs.add(tabPanels[cpt]);
        }

        /*JScrollPane */
        this.scrollJoueurs = new JScrollPane(this.panelJoueurs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollJoueurs.setPreferredSize(new Dimension(200, 385)); // 400 c'est trop, ça déborder du panel
        this.scrollJoueurs.getVerticalScrollBar().setUnitIncrement(5);

        //Ajout des composants à la frame
        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelCentral, BorderLayout.CENTER);
        this.add(this.panelBas, BorderLayout.SOUTH);

        //Ajout des composants aux panels
        this.panelHaut.add(this.lblFinDePartie, BorderLayout.CENTER);

        this.panelCentral.add(new JLabel(""));
        this.panelCentral.add(this.scrollJoueurs);
        this.panelCentral.add(new JLabel(""));

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
            this.dispose();
        
    }

        /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background       = theme.get("background"  ).get(0);
        Color labelForeColor   = theme.get("labels"      ).get(0);
        Color btnForeColor     = theme.get("buttons"     ).get(0);
		Color btnBackColor     = theme.get("buttons"     ).get(1);


        this.setBackground(background);
        this.panelCentral.setBackground(background);

        this.scrollJoueurs.getVerticalScrollBar().setBackground(background);

        for (int i = 0; i < this.tabPanels.length; i++)
        {
            /*--------*/
            /* Panels */
            /*--------*/
            /* Foreground */
            this.tabPanels[i].setForeground(background);

            /* Background */
            this.tabPanels[i].setBackground(background);


            /*---------*/
            /* Boutons */
            /*---------*/
            /* Foreground */
            this.btnQuitter.setForeground(btnForeColor);

            /* Background */
            this.btnQuitter.setBackground(btnBackColor);

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
