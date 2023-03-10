package ihm.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur;
import metier.CarteObjectif;
import metier.Joueur;
import metier.Noeud;


public class PanelObjectifsJoueur extends JPanel implements ActionListener
{
    private Controleur                   ctrl;
    private Joueur                       joueur;
    private HashMap<String, List<Color>> theme;

    /* Panels */
    private JPanel                       panelPrincipale;
    private JPanel                       container;

    private JScrollPane                  scrollPane;

    private JButton[]                    tabBtnObjectifs;

 
    public PanelObjectifsJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.theme = this.ctrl.getTheme();


        //initialisation des composants
        this.joueur = this.ctrl.getJoueur();
    
        int taille = this.joueur.getAlCartesObjectif().size();
        //int taille = 5;
        
        int grid = taille;
        if(taille%2 != 0)
            grid = taille+1;


        this.panelPrincipale = new JPanel();
        this.panelPrincipale.setLayout(new GridLayout(grid/2, 2, 0, 1));

        this.tabBtnObjectifs = new JButton[taille];

        for (int i = 0; i < taille; i++)
        {
            this.tabBtnObjectifs[i] = new JButton();
            this.tabBtnObjectifs[i].setBorderPainted(false);
            this.tabBtnObjectifs[i].setFocusPainted(false);
            this.tabBtnObjectifs[i].setContentAreaFilled(false);

            this.tabBtnObjectifs[i].setIcon(new ImageIcon(creerCarte(this.joueur.getAlCartesObjectif().get(i))));
            this.tabBtnObjectifs[i].setPreferredSize(new Dimension(200, 150));
            this.panelPrincipale.add(this.tabBtnObjectifs[i]);
        }

        this.container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.container.setBackground(new Color(68, 71, 90));
        this.container.add(this.panelPrincipale);
        this.scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setPreferredSize(new Dimension(500,300));
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(5);

        //ajout des composants
        this.add(scrollPane);   
        
        //ajout des listeners
        for(JButton btn : this.tabBtnObjectifs)
            btn.addActionListener(this);

        this.appliquerTheme();
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

        if(carteObjectif.estValide(this.joueur))
        {
            g2.setColor(Color.GREEN);
            g2.fillRect(0, 0, 200, 150);
        }


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


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for(int i = 0; i < this.tabBtnObjectifs.length; i++)
        {
            if(e.getSource() == this.tabBtnObjectifs[i])
            {
                this.ctrl.afficherCarteObjectif(this.tabBtnObjectifs[i].getIcon());
            }
            
        } 
    }

    public void appliquerTheme()
    {
        Color background       = this.theme.get("background"  ).get(0);
        Color labelForeColor   = this.theme.get("labels"      ).get(0);
        Color btnForeColor     = this.theme.get("buttons"     ).get(0);
		Color btnBackColor     = this.theme.get("buttons"     ).get(1);


        /*--------*/
        /* Panels */
        /*--------*/
        /* Ce panel */
        this.setForeground(labelForeColor);
        this.setBackground(background    );

        /* Panel Principale */
        this.panelPrincipale.setForeground(labelForeColor);
        this.panelPrincipale.setBackground(background    );

        /* Panel container */
        this.container.setForeground(labelForeColor);
        this.container.setBackground(background    );


        /*------------*/
        /* ScrollPane */
        /*------------*/
        this.scrollPane.getVerticalScrollBar().setBackground(background);


        /*-------------------*/
        /* Boutons objectifs */
        /*-------------------*/
        for (int i = 0; i < tabBtnObjectifs.length; i++)
        {
            this.tabBtnObjectifs[i].setForeground(btnForeColor);
            this.tabBtnObjectifs[i].setBackground(btnBackColor);
        }
    }


    public void validerObjectif() 
    {
        this.ctrl.afficherCarteObjectif(new ImageIcon(this.ctrl.getImageVersoObjectif()));
    }
}