package ihm.jeu;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;

import controleur.Controleur;
import metier.Joueur;


public class PanelJoueurs extends JPanel implements ActionListener 
{
    private Controleur ctrl;
    private List<Joueur> lstJoueurs;
    private JPanel      panelJoueurs;
    private JScrollPane scrollJoueurs;
    private JPanel[]    tabPanels;
    private JButton[]   tabBoutons;
    private JLabel[]    tabLblNom;
    private JLabel[]    tabLblScore;

    private PanelInfosJoueur panelInfosJoueur;
    private JDialog          dialogInfosJoueur;


    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.dialogInfosJoueur = null;
        this.panelInfosJoueur  = null;
        this.setLayout(new BorderLayout());

        this.setSize(200, 400);
        this.setBorder( BorderFactory.createLineBorder(Color.black, 2) );

        /*panel de chaque joueurs */
        //this.lstJoueurs = this.ctrl.getJoueurs();
        this.tabPanels  = new JPanel [4]; //[this.lstJoueurs.size()];
        this.tabBoutons = new JButton[this.tabPanels.length];
        this.tabLblNom  = new JLabel [this.tabPanels.length];
        this.tabLblScore= new JLabel [this.tabPanels.length];

        /*panel joueurs */
        this.panelJoueurs = new JPanel();
        this.panelJoueurs.setLayout(new GridLayout(this.tabPanels.length, 1));

        for(int cpt=0; cpt< this.tabPanels.length; cpt++)
        {
            this.tabPanels[cpt] = new JPanel();
            Color titleBackColor = this.ctrl.getTheme().get("titles").get(1);
            this.tabPanels[cpt].setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
            this.tabPanels[cpt].setLayout(new BorderLayout());

            this.tabLblNom  [cpt] = new JLabel("nom "   + (cpt+1)); //this.lstJoueurs.get(cpt).getNom());
            this.tabLblScore[cpt] = new JLabel("score :" + (cpt+1)); //this.lstJoueurs.get(cpt).getScore());

            this.tabBoutons[cpt] = new JButton();
            this.tabBoutons[cpt].setBorderPainted    (false);
            this.tabBoutons[cpt].setFocusPainted     (false);
            this.tabBoutons[cpt].setContentAreaFilled(false);
            
            this.tabPanels[cpt].add(this.tabLblNom  [cpt], BorderLayout.NORTH );
            this.tabPanels[cpt].add(this.tabBoutons [cpt], BorderLayout.WEST  );
            this.tabPanels[cpt].add(this.tabLblScore[cpt], BorderLayout.CENTER);

            this.panelJoueurs.add(tabPanels[cpt]);
        }

        /*JScrollPane */
        this.scrollJoueurs = new JScrollPane(this.panelJoueurs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollJoueurs.setPreferredSize(new Dimension(200, 385)); // 400 c'est trop, ça déborder du panel
        this.scrollJoueurs.getVerticalScrollBar().setUnitIncrement(5);

        this.add(scrollJoueurs, BorderLayout.CENTER);

        /*afficher les infos d'un joueur en fonction du joueur selectionné */
        for(int cpt=0; cpt< tabBoutons.length; cpt++)
        {
            this.tabBoutons[cpt].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for(int cpt=0; cpt< tabBoutons.length; cpt++)
        {
            if(e.getSource() == this.tabBoutons[cpt])
            {
                this.dialogInfosJoueur = new JDialog();
                this.panelInfosJoueur  = new PanelInfosJoueur(this.ctrl, (cpt+1));

                this.dialogInfosJoueur.setSize(400,200);
                this.dialogInfosJoueur.setLocation(200, 50);
                this.dialogInfosJoueur.setResizable(false);
                this.dialogInfosJoueur.add(this.panelInfosJoueur);
                this.dialogInfosJoueur.pack();
                this.dialogInfosJoueur.setVisible(true);


                /* Permet de detecter la fermeture de la fenêtre de dialogue */
                this.dialogInfosJoueur.addWindowListener(new WindowListener()
                {
                    public void windowClosing    (WindowEvent e) {}
                    public void windowOpened     (WindowEvent e) {}
                    public void windowClosed     (WindowEvent e) {}
                    public void windowIconified  (WindowEvent e) {}
                    public void windowDeiconified(WindowEvent e) {}
                    public void windowActivated  (WindowEvent e) {}
                    public void windowDeactivated(WindowEvent e) { dialogInfosJoueur.dispose(); }
                });
            }
        }
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

        if (this.panelInfosJoueur != null) { this.panelInfosJoueur.appliquerTheme(); }

        this.setBackground(background);

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
            this.tabBoutons[i].setForeground(btnForeColor);

            /* Background */
            this.tabBoutons[i].setBackground(btnBackColor);

            /* Images */
            String pathImage = "";
            if (this.ctrl.getThemeUsed().equals("dark"))
                pathImage = "./bin/donnees/images/IconJoueurWhite.png";
            else
                pathImage = "./bin/donnees/images/IconJoueurBlack.png";
            
            this.tabBoutons[i].setIcon(new ImageIcon(pathImage));


            /*--------*/
            /* Labels */
            /*--------*/
            /* Lables Nom */
            this.tabLblNom  [i].setOpaque(false);
            this.tabLblNom  [i].setForeground(labelForeColor);

            /* Lables Score */
            this.tabLblScore[i].setOpaque(false);
            this.tabLblScore[i].setForeground(labelForeColor);
        }
    }
}
