package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Controleur;

public class PanelPioche extends JPanel implements ActionListener
{
    private static final int TAILLE = 5;

    private Controleur ctrl;
    private JButton[]  tabCarteWagon;
    private JButton    deckCarteWagon; 

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Parametrage du panel
        this.setLayout(new GridLayout(6,1));
		this.setBackground(new Color(68, 71, 90));

        //Creation des composants
        JPanel[] tabPanel = new JPanel[TAILLE+1];

        for ( int cpt=0; cpt <= TAILLE; cpt++ )
        {
            tabPanel[cpt] = new JPanel();
            tabPanel[cpt].setBackground(new Color(68, 71, 90));
            tabPanel[cpt].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        this.tabCarteWagon = new JButton[TAILLE];
        for (int cpt=0; cpt<TAILLE; cpt++)
        {
            this.tabCarteWagon[cpt] = new JButton();
			this.tabCarteWagon[cpt].setBackground(new Color(70, 73, 89));
            this.tabCarteWagon[cpt].setBorder(BorderFactory.createBevelBorder(1, new Color(32, 40, 44), new Color(32, 40, 44)));
            this.setImageButton(cpt);
        }

        this.deckCarteWagon = new JButton(new ImageIcon(this.ctrl.getImageVersoCouleur()));
		this.deckCarteWagon.setBackground(new Color(70, 73, 89));

        //Ajout des composants
        tabPanel[0].add(this.deckCarteWagon);

		for (int cpt=0; cpt<TAILLE; cpt++)
            tabPanel[cpt+1].add(this.tabCarteWagon[cpt]);

		for (int cpt=0; cpt<=TAILLE; cpt++)
            this.add(tabPanel[cpt]);
    }

    public void setImageButton(int indice)
    {
        this.tabCarteWagon[indice].setIcon(new ImageIcon (this.ctrl.getTabCarteWagon()[indice].getImageRecto()));
    }

    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.deckCarteWagon )

        if ( e.getSource() == this.tabCarteWagon[0] )
        {
            this.ctrl.piocherTabWagon (0, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(0);
        }

        if ( e.getSource() == this.tabCarteWagon[1] )
        {
            this.ctrl.piocherTabWagon (1, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(1);
        }

        if ( e.getSource() == this.tabCarteWagon[2] )
        {
            this.ctrl.piocherTabWagon (2, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(2);
        }

        if ( e.getSource() == this.tabCarteWagon[3] )
        {
            this.ctrl.piocherTabWagon (3, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(3);
        }

		this.setVisible(true);
        if ( e.getSource() == this.tabCarteWagon[4] )
        {
            this.ctrl.piocherTabWagon (4, this.ctrl.getJoueurSelect().getNom());
            this.setImageButton(4);
        }
    }
}