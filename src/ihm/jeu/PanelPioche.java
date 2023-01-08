package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Controleur;

public class PanelPioche extends JPanel implements ActionListener
{
    private static final int TAILLE = 5;

    private Controleur ctrl;

    private JPanel     panelHaut;
    private JPanel     panelMilieu;

    private JButton[]  tabCarteWagon;
    private JButton    deckCarteWagon; 

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Parametrage du panel
        this.setLayout(new BorderLayout(3, 3));

		//Creation des panels
		this.panelHaut   = new JPanel();
		this.panelMilieu = new JPanel(new GridLayout(5,1));

        this.tabCarteWagon = new JButton[PanelPioche.TAILLE];
        for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
        {
            this.tabCarteWagon[cpt] = new JButton();
            this.tabCarteWagon[cpt].setPreferredSize(new Dimension(200, 100));
            this.setImageButton(cpt);
        }

        this.deckCarteWagon = new JButton(new ImageIcon(this.ctrl.getImageVersoCouleur()));
        this.deckCarteWagon.setPreferredSize(new Dimension(200, 150));

        //Ajout des composants
		this.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(this.deckCarteWagon);

		this.add(panelMilieu, BorderLayout.CENTER);
		for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
            panelMilieu.add(this.tabCarteWagon[cpt]);

		//Activation des composants
		this.deckCarteWagon.addActionListener(this);
		for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
			this.tabCarteWagon[cpt].addActionListener(this);
    }

    public void setImageButton(int indice)
    {
        BufferedImage resizedImage = new BufferedImage(150, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
		//g2d.drawImage(this.ctrl.getTabCarteWagon()[indice].getImageRecto(), 0, 0, 200, 100, null);
        g2d.dispose();
        this.tabCarteWagon[indice].setIcon(new ImageIcon (resizedImage));
    }

    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.deckCarteWagon )
                    System.out.println("Deck");

        if ( e.getSource() == this.tabCarteWagon[0] )
        {
            //this.ctrl.piocherTabWagon (0, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(0);
            System.out.println(1);
        }

        if ( e.getSource() == this.tabCarteWagon[1] )
        {
            //this.ctrl.piocherTabWagon (1, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(1);
            System.out.println(2);
        }

        if ( e.getSource() == this.tabCarteWagon[2] )
        {
            //this.ctrl.piocherTabWagon (2, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(2);
            System.out.println(3);
        }

        if ( e.getSource() == this.tabCarteWagon[3] )
        {
            //this.ctrl.piocherTabWagon (3, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(3);
            System.out.println(4);
        }

        if ( e.getSource() == this.tabCarteWagon[4] )
        {
            //this.ctrl.piocherTabWagon (4, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(4);
            System.out.println(5);
        }
    }


    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background       = theme.get("background"  ).get(0);
        Color titleBackColor   = theme.get("titles"      ).get(1);
        Color labelForeColor   = theme.get("labels"      ).get(0);
        Color btnForeColor     = theme.get("buttons"     ).get(0);


        /*--------*/
        /* Panels */
        /*--------*/
        /* Ce panel */
        this.setForeground(labelForeColor);
        this.setBackground(background    );

        /* Panel haut */
        this.panelHaut.setForeground(labelForeColor);
        this.panelHaut.setBackground(background    );

        /* Panel milieu */
        this.panelMilieu.setForeground(labelForeColor);
        this.panelMilieu.setBackground(background    );


        /*---------*/
        /* Boutons */
        /*---------*/
        /* Boutons carte wagon */
        for (int i = 0; i < tabCarteWagon.length; i++)
        {
            this.tabCarteWagon[i].setForeground(btnForeColor);
            this.tabCarteWagon[i].setBackground(background);
            this.tabCarteWagon[i].setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor)); //BorderFactory.createBevelBorder(1, /* couleur border haut et gauche */, /* couleur border bas et droite */)
        }

        /* Bouton deck */
        this.deckCarteWagon.setForeground(btnForeColor);
        this.deckCarteWagon.setBackground(background);
    }
}