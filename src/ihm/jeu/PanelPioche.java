package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics2D;

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
        this.setBorder( BorderFactory.createLineBorder(Color.black, 3) );

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
		this.lblPioche = new JLabel("" + this.ctrl.getSizeWagon() + "/" + (this.ctrl.getSizeWagon()+5));

        //Ajout des composants
		this.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(this.deckCarteWagon);
		panelHaut.add(this.lblPioche);

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

		if ( this.tabCartesVisible[indice] != null )
		{
			BufferedImage image = this.tabCartesVisible[indice].getImageRecto();

			// Calcul du facteur de zoom maximal
			double zoomLargeur = (double) 150 / image.getWidth();
			double zoomHauteur = (double) 100 / image.getHeight();
			double facteurZoom = Math.min(zoomLargeur, zoomHauteur);
			
			AffineTransform at = new AffineTransform();
			at.scale(facteurZoom, facteurZoom);
			g2d.transform(at);
			
			g2d.drawImage(image, 0, 0, null);
		}
		else
		{
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, 150, 100);

			g2d.setColor(Color.WHITE);
			g2d.drawString("Pioche vide", 25, 25);
			
			this.tabCarteWagon[indice].setEnabled(false);
		}
		
        g2d.dispose();

        this.tabCarteWagon[indice].setIcon(new ImageIcon (resizedImage));
    }

    public void actionPerformed(ActionEvent e) 
    {
		if ( this.ctrl.peuxJouer())
		{
			if ( e.getSource() == this.deckCarteWagon )
			{
				this.ctrl.piocherPioche();
				this.ctrl.piocherPioche();
			}

			for (int i = 0 ; i < this.TAILLE ; i++)
				if ( e.getSource() == this.tabCarteWagon[i] )
				{
					this.tabCartesVisible = this.ctrl.getTabCartesVisible();

					if (this.tabCartesVisible[i] != null)
						if (this.tabCartesVisible[i].isJoker())
						{
							this.ctrl.piocherVisible(i);
						}
						else
						{
							this.ctrl.piocherVisible(i);
							this.ctrl.piocherPioche();
						}
				}


			this.ctrl.majIHM();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Ce n'est pas à votre tour de jouer", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
    }

	public void majIHM()
	{
		for (int cpt=0; cpt<PanelPioche.TAILLE; cpt++)
        {
            this.setImageButton(cpt);;
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
    }
}