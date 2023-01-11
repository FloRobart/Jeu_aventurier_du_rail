package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.CarteObjectif;
import metier.Noeud;

public class PanelPiocherObjectif extends JPanel implements ActionListener
{
    private static final int TAILLE = 3;

    private Controleur    ctrl;
	private PanelObjectif panelObjectif;
	private HashMap<String, List<Color>> theme;

	private boolean 	debutPartie;

	private JPanel		panelBtnPiocher;

    private JButton[]	tabCarteobjectif;
	private boolean[]   tabChoixCarte;
	private JButton		btnPiocher;
	private CarteObjectif[] cartesObjectifs;

	private JLabel     	lblChoisirCartes;

	public PanelPiocherObjectif(Controleur ctrl, PanelObjectif panelObjectif, boolean debutPartie)
	{
		this.ctrl = ctrl;
		this.debutPartie = debutPartie;
		this.panelObjectif = panelObjectif;
		this.theme = this.ctrl.getTheme();
		
		//Parametrage du panel
		this.setLayout( new BorderLayout() );

		//Creation des composants
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(1,3));

		this.panelBtnPiocher = new JPanel();
		this.panelBtnPiocher.setLayout(new GridLayout(1,5));

		this.lblChoisirCartes = new JLabel(" Choisissez les cartes objectif que vous voulez : ");

		this.cartesObjectifs = new CarteObjectif[PanelPiocherObjectif.TAILLE];

		for(int cpt = 0; cpt < PanelPiocherObjectif.TAILLE; cpt++)
			this.cartesObjectifs[cpt] = this.ctrl.getPiocheObjectif();

		this.tabChoixCarte = new boolean[PanelPiocherObjectif.TAILLE];

		//Creation des boutons
		this.btnPiocher = new JButton("Piocher");
		this.btnPiocher.setPreferredSize(new Dimension(20,20));
		this.btnPiocher.setEnabled(false);

		Color titleBackColor = this.ctrl.getTheme().get("titles").get(1);
		this.tabCarteobjectif = new JButton[PanelPiocherObjectif.TAILLE];
        for (int cpt = 0; cpt < PanelPiocherObjectif.TAILLE; cpt++)
        {
            this.tabCarteobjectif[cpt] = new JButton();
            this.tabCarteobjectif[cpt].setPreferredSize(new Dimension(300, 200));
            this.tabCarteobjectif[cpt].setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
			this.tabCarteobjectif[cpt].setFocusPainted(false);

			this.tabCarteobjectif[cpt].setIcon(new ImageIcon(creerCarte(this.cartesObjectifs[cpt])));

			panelPrincipal.add(this.tabCarteobjectif[cpt]);
			this.tabCarteobjectif[cpt].addActionListener(this);
			this.tabChoixCarte[cpt] = false;
        }
		
		this.panelBtnPiocher.add(new JLabel(""));
		this.panelBtnPiocher.add(new JLabel(""));
		this.panelBtnPiocher.add(this.btnPiocher);
		this.panelBtnPiocher.add(new JLabel(""));
		this.panelBtnPiocher.add(new JLabel(""));

		this.add(this.lblChoisirCartes	, BorderLayout.NORTH);
		this.add(panelPrincipal			, BorderLayout.CENTER);
		this.add(this.panelBtnPiocher	, BorderLayout.SOUTH);

		this.btnPiocher.addActionListener(this);

		this.appliquerTheme();
	}

	public void actionPerformed(ActionEvent e) 
    {
		if(e.getSource() == this.btnPiocher)
		{
			if ( this.debutPartie == true )
			{
				if(this.getnbSelection() >= 2)
				{		
					for(int i=0; i<PanelPiocherObjectif.TAILLE; i++)
					{	
						if(this.tabChoixCarte[i] == true)
						{
							this.ctrl.ajouterObjectifsJoueurs(this.cartesObjectifs[i]);
						}
						else if(this.cartesObjectifs[i] != null)
							this.ctrl.remettreCarteObjectif(this.cartesObjectifs[i]);
					}
					this.btnPiocher.setEnabled(false);
					this.initCarteObjectifs();
					this.panelObjectif.disposePioche();
					
					this.ctrl.joueurSuivant();
					this.debutPartie = false;
				}
				else
				{
					this.ctrl.afficherErreur("Vous devez piocher au moins 2 cartes !");
				}
				
			}

			if ( this.debutPartie == false )
			{
				if(this.getnbSelection() > 0)
				{		
					for(int i=0; i<PanelPiocherObjectif.TAILLE; i++)
					{	
						if(this.tabChoixCarte[i] == true)
						{
							this.ctrl.ajouterObjectifsJoueurs(this.cartesObjectifs[i]);
						}
						else if(this.cartesObjectifs[i] != null)
							this.ctrl.remettreCarteObjectif(this.cartesObjectifs[i]);
					}
					this.btnPiocher.setEnabled(false);
					this.initCarteObjectifs();
					this.panelObjectif.disposePioche();
					this.ctrl.joueurSuivant();
				}
			}
		}

		for (int i = 0; i < PanelPiocherObjectif.TAILLE; i++)
		{	
			if (e.getSource() == this.tabCarteobjectif[i])
			{
				this.btnPiocher.setEnabled(true);
				if(this.cartesObjectifs[i] != null)
				{	
					if(this.tabChoixCarte[i] == false)
					{
						this.tabCarteobjectif[i].setBackground(Color.GREEN);
					}
					else
					{
						this.tabCarteobjectif[i].setBackground(this.theme.get("buttons").get(1));
					}
					this.tabChoixCarte[i] = !this.tabChoixCarte[i];
				}
			}
		}
	}

	public CarteObjectif[] getCartesObjectifs() 
	{
		return this.cartesObjectifs;
	}
	
	private int getnbSelection() 
	{
		int nbSelection = 0;
		for(int i=0; i<PanelPiocherObjectif.TAILLE; i++)
		{
			if(this.tabChoixCarte[i] == true)
				nbSelection++;
		}
		return nbSelection;
	}

	/**
	 * repioche les cartes objectifs après la pioche du joueur
	 */
	private void initCarteObjectifs() 
	{
		for(int cpt = 0; cpt < PanelPiocherObjectif.TAILLE; cpt++)
			this.cartesObjectifs[cpt] = this.ctrl.getPiocheObjectif();

		for (int cpt = 0; cpt < PanelPiocherObjectif.TAILLE; cpt++)
		{
			if(this.cartesObjectifs[cpt] != null)
			{
				this.tabCarteobjectif[cpt].setIcon(new ImageIcon(creerCarte(this.cartesObjectifs[cpt])));				
			}
			else
			{
				this.tabCarteobjectif[cpt].setIcon(null);
			}

			this.tabCarteobjectif[cpt].setBackground(this.theme.get("buttons").get(1));
			this.tabChoixCarte[cpt] = false;
			
		}
	}

	/**
     * creer les cartes objectifs
     * @param carteObjectif carte que l'on souhaite afficher
     * @return BufferedImage de la carte
     */
    private BufferedImage creerCarte(CarteObjectif carteObjectif) 
    {
        Color labelForeColor   = this.theme.get("labels").get(0);

        Noeud noeud1 = carteObjectif.getNoeud1();
        Noeud noeud2 = carteObjectif.getNoeud2();
        int nbPoints = carteObjectif.getPoints();

        FontMetrics metrics;
        int width;

        BufferedImage img = new BufferedImage(200, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

		BufferedImage bi = this.ctrl.getImagePlateau();
        Graphics2D g2 = (Graphics2D) g;
        //zoom de l'image du plateau
		double zoomLargeur = (double) 150 / bi.getWidth ();
		double zoomHauteur = (double) 150 / bi.getHeight();
	    AffineTransform at = new AffineTransform();
		at.scale(zoomLargeur, zoomHauteur);
        g2.transform(at);

        g2.drawImage(bi, 0, 0, null);

       //Ligne
        g2.setColor(Color.BLACK);
        g2.drawLine(noeud1.getX()+10, noeud1.getY()+10, noeud2.getX()+10, noeud2.getY()+10);

        //NOEUD 1
        metrics = g2.getFontMetrics(g2.getFont());
        width = metrics.stringWidth(noeud1.getNom());

        g2.setColor(noeud1.getCouleur());
        g2.fillOval(noeud1.getX(), noeud1.getY(), 30, 30);
        g2.setFont(g2.getFont().deriveFont(50f));

        g2.setColor(Color.WHITE);
		g2.fillRect(noeud1.getX() + noeud1.getXNom() - (noeud1.getNom().length() * 3), noeud1.getY() + noeud1.getYNom() -25, noeud1.getNom().length()+width, 50);
				
        g2.setColor(Color.BLACK);
        g2.drawString(noeud1.getNom(), noeud1.getX() + noeud1.getXNom() - (noeud1.getNom().length() * 3), noeud1.getY() + noeud1.getYNom() + 4);

        //NOEUD 2
        metrics = g2.getFontMetrics(g2.getFont());
        width = metrics.stringWidth(noeud2.getNom());

        g2.setColor(noeud2.getCouleur());
        g2.fillOval(noeud2.getX(), noeud2.getY(), 30, 30);

        g2.setColor(Color.WHITE);
		g2.fillRect(noeud2.getX() + noeud2.getXNom() - (noeud2.getNom().length() * 3), noeud2.getY() + noeud2.getYNom() -25, noeud2.getNom().length()+width, 50);
				
        g2.setColor(Color.BLACK);
        g2.drawString(noeud2.getNom(), noeud2.getX() + noeud2.getXNom() - (noeud2.getNom().length() * 3), noeud2.getY() + noeud2.getYNom() + 4);


        //Nombre de points
        at.scale(35, 35);
        g2.transform(at);

        g2.setColor(labelForeColor);
        g2.setFont(g2.getFont().deriveFont(10f));
        g2.drawString(nbPoints + " points", 0, 10);

        return img;
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

		/* Ce panel */
		this.setBackground(background);
		this.setForeground(labelForeColor);
		this.panelBtnPiocher.setBackground(background);

		/* Label */
		this.lblChoisirCartes.setOpaque(false);
		this.lblChoisirCartes.setForeground(labelForeColor);

		/*---------*/
		/* Boutons */
		/*---------*/
		for (int i = 0; i < PanelPiocherObjectif.TAILLE; i++)
		{
			this.tabCarteobjectif[i].setForeground(btnForeColor);
			this.tabCarteobjectif[i].setBackground(btnBackColor);
		}

		this.btnPiocher.setForeground(btnForeColor);
        this.btnPiocher.setBackground(background  );
	}
}
