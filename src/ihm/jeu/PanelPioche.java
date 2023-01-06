package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics2D;

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
        this.setLayout(new BorderLayout(3, 3));
		this.setBackground(new Color(68, 71, 90));

		//Creation des panels
		JPanel panelHaut = new JPanel();
		JPanel panelMilieu = new JPanel(new GridLayout(5,1));

		panelHaut.setBackground(new Color(68, 71, 90));
		panelMilieu.setBackground(new Color(68, 71, 90));

        this.tabCarteWagon = new JButton[this.TAILLE];
        for (int cpt=0; cpt<this.TAILLE; cpt++)
        {
            this.tabCarteWagon[cpt] = new JButton();
			this.tabCarteWagon[cpt].setBackground(new Color(70, 73, 89));
            this.tabCarteWagon[cpt].setPreferredSize(new Dimension(200, 100));
            this.tabCarteWagon[cpt].setBorder(BorderFactory.createBevelBorder(1, new Color(32, 40, 44), new Color(32, 40, 44)));
            this.setImageButton(cpt);
        }

        this.deckCarteWagon = new JButton(new ImageIcon(this.ctrl.getImageVersoCouleur()));
		this.deckCarteWagon.setBackground(new Color(70, 73, 89));
        this.deckCarteWagon.setPreferredSize(new Dimension(200, 150));
        this.deckCarteWagon.setBorder(BorderFactory.createBevelBorder(1, new Color(32, 40, 44), new Color(32, 40, 44)));

        //Ajout des composants
		this.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(this.deckCarteWagon);

		this.add(panelMilieu, BorderLayout.CENTER);
		for (int cpt=0; cpt<TAILLE; cpt++)
            panelMilieu.add(this.tabCarteWagon[cpt]);

		//Activation des composants
		this.deckCarteWagon.addActionListener(this);
		for (int cpt=0; cpt<TAILLE; cpt++)
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
}