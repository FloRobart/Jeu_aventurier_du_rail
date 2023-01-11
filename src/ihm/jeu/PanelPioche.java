package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.partie.CarteWagon;

public class PanelPioche extends JPanel implements ActionListener
{
    private static final int TAILLE = 5;

    private Controleur   ctrl;
	private CarteWagon[] tabCartesVisible;

    private JPanel     panelHaut;
    private JPanel     panelMilieu;

    private JButton[]  tabCarteWagon;
    private JButton    deckCarteWagon; 
	private JLabel     lblPioche;

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;
		this.tabCartesVisible = this.ctrl.getTabCartesVisible();

        //Parametrage du panel
        this.setLayout(new BorderLayout(3, 3));
        this.setBorder( BorderFactory.createLineBorder(this.ctrl.getTheme().get("titles").get(1), 3) );

		//Creation des panels
		this.panelHaut   = new JPanel();
		this.panelMilieu = new JPanel(new GridLayout(PanelPioche.TAILLE,1));

        this.tabCarteWagon = new JButton[PanelPioche.TAILLE];
        for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
        {
            this.tabCarteWagon[cpt] = new JButton();
            this.tabCarteWagon[cpt].setPreferredSize(new Dimension(200, 100));
			this.setImageButton(cpt);
        }


		/* Image du deck */
        this.deckCarteWagon = new JButton();
		this.deckCarteWagon.setSize(new Dimension(200, 150));
        this.deckCarteWagon.setPreferredSize(new Dimension(200, 150));

		BufferedImage bfImg = this.ctrl.getImageVersoCouleur();
		double zoomLargeur = (double) this.tabCarteWagon[0].getWidth()  / bfImg.getWidth();
		double zoomHauteur = (double) this.tabCarteWagon[0].getHeight() / bfImg.getHeight();
		double facteurZoom = Math.min(zoomLargeur, zoomHauteur)-0.1;
		ImageIcon imgIcon = new ImageIcon(bfImg.getScaledInstance(((int)(this.deckCarteWagon.getWidth()*facteurZoom)), ((int)(this.deckCarteWagon.getHeight()*facteurZoom)), Image.SCALE_SMOOTH));
		this.deckCarteWagon.setIcon(imgIcon);

		this.lblPioche = new JLabel("" + this.ctrl.getSizeWagon() + "/" + (this.ctrl.getSizeWagon()+5));

        //Ajout des composants
		this.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(this.deckCarteWagon);
		panelHaut.add(this.lblPioche);

		this.add(this.panelMilieu, BorderLayout.CENTER);
		for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
            this.panelMilieu.add(this.tabCarteWagon[cpt]);

		//Activation des composants
		this.deckCarteWagon.addActionListener(this);
		for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
			this.tabCarteWagon[cpt].addActionListener(this);

		this.ctrl.verifierVisible();
    }

    public void setImageButton(int ind)
    {
		if ( this.tabCartesVisible[ind] == null )
		{
			this.tabCarteWagon[ind].setEnabled(false);
		}
		else
		{
			BufferedImage bfImg = this.tabCartesVisible[ind].getImageRecto();
			double zoomLargeur = 200  / bfImg.getWidth();
			double zoomHauteur = 100 / bfImg.getHeight();
			double facteurZoom = Math.min(zoomLargeur, zoomHauteur)-0.1;

			ImageIcon imgIcon = new ImageIcon(bfImg.getScaledInstance(((int)(200*facteurZoom)), ((int)(100*facteurZoom)), Image.SCALE_SMOOTH));
			this.tabCarteWagon[ind].setIcon(imgIcon);
			this.tabCarteWagon[ind].setEnabled(true);

			if (this.tabCartesVisible[ind].isJoker() && this.ctrl.getEnTrainDePiocher())
				this.tabCarteWagon[ind].setEnabled(false);
		}
    }

    public void actionPerformed(ActionEvent e) 
    {
		if ( this.ctrl.peuxJouer())
		{
			if ( e.getSource() == this.deckCarteWagon )
			{
				this.ctrl.piocherPioche();
				this.ctrl.switchEnTrainDePiocher();

				if (this.ctrl.getSizeWagon() == 0 && this.ctrl.getEnTrainDePiocher())
				{
					this.ctrl.switchEnTrainDePiocher();
				}

				this.ctrl.joueurSuivant();
			}

			for (int i = 0 ; i < PanelPioche.TAILLE ; i++)
			{
				if ( e.getSource() == this.tabCarteWagon[i] )
				{
					this.tabCartesVisible = this.ctrl.getTabCartesVisible();

					if (this.tabCartesVisible[i] != null)
					{
						if (this.tabCartesVisible[i].isJoker())
						{
							this.ctrl.piocherVisible(i);
						}
						else
						{
							this.ctrl.piocherVisible(i);
							this.ctrl.switchEnTrainDePiocher();

							if (this.ctrl.getSizeWagon() == 0 && this.ctrl.getEnTrainDePiocher())
							{
								this.ctrl.switchEnTrainDePiocher();
							}
						}
					}

					this.ctrl.joueurSuivant();
				}
			}


			this.ctrl.majIHM();
		}
		else
			this.ctrl.afficherErreur("Ce n'est à pas votre tour de jouer");
    }

	public void majIHM()
	{
		this.tabCartesVisible = this.ctrl.getTabCartesVisible();
		for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
        {
            this.setImageButton(cpt);
        }
		
		String text = this.lblPioche.getText();
		this.lblPioche.setText("" + this.ctrl.getSizeWagon() + text.substring(text.indexOf("/")));

		if (this.ctrl.getSizeWagon() == 0)
			this.deckCarteWagon.setEnabled(false);
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

		this.lblPioche.setForeground(labelForeColor);
		this.lblPioche.setBackground(background);
    }
}