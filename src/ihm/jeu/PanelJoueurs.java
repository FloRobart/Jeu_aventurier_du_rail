package ihm.jeu;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import controleur.Controleur;


public class PanelJoueurs extends JPanel implements ActionListener 
{
    private Controleur ctrl;

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
        this.panelInfosJoueur = null;

        this.setSize(500, 200);

        /*panel de chaque joueurs */
        this.tabPanels  = new JPanel[3];
        this.tabBoutons = new JButton[this.tabPanels.length];
        this.tabLblNom  = new JLabel[this.tabPanels.length];
        this.tabLblScore= new JLabel[this.tabPanels.length];

        /*panel joueurs */
        this.panelJoueurs = new JPanel();
        this.panelJoueurs.setLayout(new GridLayout(this.tabPanels.length, 1, 0,1));

        for(int cpt=0; cpt< this.tabPanels.length; cpt++)
        {
            this.tabPanels[cpt] = new JPanel();
            this.tabPanels[cpt].setLayout(new BorderLayout());

            this.tabLblNom  [cpt] = new JLabel("nom "   + (cpt+1));
            this.tabLblScore[cpt] = new JLabel("score " + (cpt+1));

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
        this.scrollJoueurs = new JScrollPane(panelJoueurs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollJoueurs.setPreferredSize(new Dimension(200,400));
        this.scrollJoueurs.getVerticalScrollBar().setUnitIncrement(5);

        this.add(scrollJoueurs);

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
                if (this.panelInfosJoueur == null || (this.panelInfosJoueur != null && this.panelInfosJoueur.getNumJoueur() != (cpt+1)))
                {
                    if (this.dialogInfosJoueur != null) { this.dialogInfosJoueur.dispose(); }
                    this.dialogInfosJoueur = new JDialog();
                    this.panelInfosJoueur  = new PanelInfosJoueur(this.ctrl, (cpt+1));

                    this.dialogInfosJoueur.setSize(400,200);
                    this.dialogInfosJoueur.setLocation(200, 50);
                    this.dialogInfosJoueur.setResizable(false);
                    this.dialogInfosJoueur.add(this.panelInfosJoueur);
                    this.dialogInfosJoueur.pack();
                    this.dialogInfosJoueur.setVisible(true);
                }
            }
        }
    }


    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color disableColor     = this.ctrl.getTheme().get("disableColor").get(0);
        Color titleForeColor   = this.ctrl.getTheme().get("titles"      ).get(0);
        Color titleBackColor   = this.ctrl.getTheme().get("titles"      ).get(1);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color saisiForeColor   = this.ctrl.getTheme().get("saisies"     ).get(0);
		Color saisiBackColor   = this.ctrl.getTheme().get("saisies"     ).get(1);
        Color placeholderColor = this.ctrl.getTheme().get("saisies"     ).get(2);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
		Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);

        if (this.panelInfosJoueur != null) { this.panelInfosJoueur.appliquerTheme(); }

        this.setBackground(background);
        this.panelJoueurs.setBackground(titleBackColor); // new Color(40, 42, 54)

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

            /* Border */
            this.tabBoutons[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

            /* Images */
            String pathImage = "";
            if (this.ctrl.getThemeUsed().equals("dark"))
                pathImage = "./bin/donnees/images/IconJoueurWhite.png";
            else
                pathImage = "./bin/donnees/images/IconJoueurBlack.png";

            for(int cpt=0; cpt< this.tabBoutons.length; cpt++)
                this.tabBoutons[cpt].setIcon(new ImageIcon(pathImage));


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
