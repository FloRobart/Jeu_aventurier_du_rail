package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import controleur.Controleur;
import metier.CarteObjectif;
import metier.Joueur;
import metier.partie.CarteWagon;

public class PanelMainJoueur extends JPanel implements ActionListener
{
    private Controleur             ctrl;
    private Joueur                 joueur;

    private JDialog                      dialogObjectifs;
    private HashMap<String, List<Color>> theme;

    private JPanel         panelImgJoueur;
    private JPanel         panelInfoJoueur;
    private PanelObjectifsJoueur panelObjectifs;

    private JLabel     lblNom;
    private JLabel     lblNbJeton;
    private JLabel     lblIcon;
    private JLabel     lblScore;

    private PanelMain  panelMainWagon;
    

    private JPanel     panelMainObjectif;
    private JButton    btnIconObjectif;
    private JLabel     lblObjectif;
       

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl            = ctrl;
        this.dialogObjectifs = null;
        this.panelObjectifs  = null;
        this.theme           = this.ctrl.getTheme();

        this.setLayout(new BorderLayout());
        this.setBorder( BorderFactory.createLineBorder(this.ctrl.getTheme().get("titles").get(1), 3) );

        //initialisation des composants
        this.joueur = this.ctrl.getJoueur();
        this.panelImgJoueur  = new JPanel();

        this.lblNom     = new JLabel(this.joueur.getNom());
        this.lblNbJeton = new JLabel(this.joueur.getNbJetonsRestant() +"  jetons restants   "); 
        this.lblScore   = new JLabel("Score : " + this.joueur.getScore());

        String pathImage = "";
        if (this.ctrl.getThemeUsed().equals("dark"))
            pathImage = "./bin/donnees/images/IconJoueurWhite.png";
        else
            pathImage = "./bin/donnees/images/IconJoueurBlack.png";

        this.lblIcon    = new JLabel(new ImageIcon(pathImage), JLabel.LEFT);
        
        this.panelMainWagon    = new PanelMain(this.ctrl, this.ctrl.getJoueur());

        //panelImgJoueur Joueur
        this.panelInfoJoueur = new JPanel();
        this.panelInfoJoueur.setLayout(new GridLayout(3,1)); // à modifier en fonction du nombre d'infos à afficher
        this.panelImgJoueur.setLayout(new BorderLayout());

        this.panelInfoJoueur.add(this.lblNom);
        this.panelInfoJoueur.add(this.lblNbJeton);
        this.panelInfoJoueur.add(this.lblScore);

        this.panelImgJoueur.add(this.lblIcon, BorderLayout.NORTH);
        this.panelImgJoueur.add(this.panelInfoJoueur, BorderLayout.CENTER);

        //panelMainObjectif
        this.panelMainObjectif = new JPanel();
        this.panelMainObjectif.setLayout(new BorderLayout());

        this.btnIconObjectif   = new JButton();
        this.btnIconObjectif.setIcon(new ImageIcon(this.ctrl.getCarteObjectif().get(0).getImageRecto()));
        this.btnIconObjectif.setBorderPainted(false);
        this.btnIconObjectif.setContentAreaFilled(false);
        this.btnIconObjectif.setFocusPainted(false);

        this.lblObjectif = new JLabel("  " + this.joueur.getNbCartesObjectif() + " objectifs restants");

        this.panelMainObjectif.add(this.btnIconObjectif, BorderLayout.CENTER);
        this.panelMainObjectif.add(this.lblObjectif, BorderLayout.SOUTH);

        //ajout des composants
        this.add(this.panelImgJoueur, BorderLayout.EAST);
        this.add(this.panelMainWagon, BorderLayout.CENTER);
        this.add(this.panelMainObjectif, BorderLayout.WEST);

		this.setVisible(true);

        this.btnIconObjectif.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnIconObjectif)
        {
            /* Création d'un Panel */
            this.panelObjectifs = new PanelObjectifsJoueur(this.ctrl);

            /* Création d'un JDialog */
            this.dialogObjectifs = new JDialog();

            this.dialogObjectifs.setSize(400,200);
            this.dialogObjectifs.setLocation(200, 50);
            this.dialogObjectifs.setResizable(false);
            this.dialogObjectifs.add(this.panelObjectifs);
            this.dialogObjectifs.pack();
            this.dialogObjectifs.setVisible(true);

            /* Permet de detecter la fermeture de la fenêtre de dialogue */
            this.dialogObjectifs.addWindowListener(new WindowListener()
            {
                public void windowClosing    (WindowEvent e) {}
                public void windowOpened     (WindowEvent e) {}
                public void windowClosed     (WindowEvent e) {}
                public void windowIconified  (WindowEvent e) {}
                public void windowDeiconified(WindowEvent e) {}
                public void windowActivated  (WindowEvent e) {}
                public void windowDeactivated(WindowEvent e) { dialogObjectifs.dispose(); }
            });
        }
    }

    public void setScore( ) { this.lblScore.setText("Score : " + this.joueur.getScore()); }

	public void majIHM()
	{
		this.remove(this.panelMainWagon);
		this.panelMainWagon = new PanelMain(this.ctrl, this.ctrl.getJoueur());
		this.add(this.panelMainWagon, BorderLayout.CENTER);
        this.lblObjectif.setText("  " + this.joueur.getNbCartesObjectif() + " objectifs restants");
		this.lblNbJeton .setText(this.joueur.getNbJetonsRestant() +"  jetons restants   "); 

		this.revalidate();
		this.repaint();
		this.appliquerTheme();
	}

    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.theme.get("background"  ).get(0);
        Color labelForeColor   = this.theme.get("labels"      ).get(0);
        Color btnForeColor     = this.theme.get("buttons"     ).get(0);
		Color btnBackColor     = this.theme.get("buttons"     ).get(1);


        if (this.dialogObjectifs != null) { this.panelObjectifs.appliquerTheme(); }
        if (this.panelMainWagon  != null) { this.panelMainWagon.appliquerTheme(); }

        /*----------*/
        /* Ce panel */
        /*----------*/
        this.repaint();
        this.setForeground(labelForeColor);
        this.setBackground(background);


        /*----------------*/
        /* panelImgJoueur */
        /*----------------*/
        this.panelImgJoueur.setForeground(labelForeColor);
        this.panelImgJoueur.setBackground(background    );

        /*-----------------*/
        /* panelInfoJoueur */
        /*-----------------*/
        this.panelInfoJoueur.setForeground(labelForeColor);
        this.panelInfoJoueur.setBackground(background    );

        /*-------------------*/
        /* panelMainObjectif */
        /*-------------------*/
        this.panelMainObjectif.setForeground(labelForeColor);
        this.panelMainObjectif.setBackground(background    );

        /*----------------*/
        /* panelMainWagon */
        /*----------------*/
        this.panelMainWagon.setForeground(labelForeColor);
        this.panelMainWagon.setBackground(background    );


        /*=========*/
        /* Buttons */
        /*=========*/
        /* btnIconObjectif */
        this.btnIconObjectif.setForeground(btnForeColor);
        this.btnIconObjectif.setBackground(btnBackColor);


        /*========*/
        /* Labels */
        /*========*/
        /* List labels cartes wagons */
       /* for (int i = 0; i < this.tabIconWagon.length; i++)
        {
            this.tabLblWagon[i].setOpaque(false);
            this.tabLblWagon[i].setForeground(labelForeColor);
        }*/

        /* label image joueur */
        String pathImage = "";
        if (this.ctrl.getThemeUsed().equals("dark"))
            pathImage = "./bin/donnees/images/IconJoueurWhite.png";
        else
            pathImage = "./bin/donnees/images/IconJoueurBlack.png";

        this.lblIcon.setIcon(new ImageIcon(pathImage));

        
        /* lblNom */
        this.lblNom.setOpaque(false);
        this.lblNom.setForeground(labelForeColor);

        /* lblNbJeton */
        this.lblNbJeton.setOpaque(false);
        this.lblNbJeton.setForeground(labelForeColor);

        /*lblScore */
        this.lblScore.setOpaque(false);
        this.lblScore.setForeground(labelForeColor);

        /* lblIcon */
        this.lblIcon.setOpaque(false);
        this.lblIcon.setForeground(labelForeColor);

        /* lblObjectif */
        this.lblObjectif.setOpaque(false);
        this.lblObjectif.setForeground(labelForeColor);
    }

    /**
     * Affiche la carte sélectionné dans la main du joueur
     * @param icon : image de la carte
     */
    public void afficherCarteObjectif(Icon icon) 
    {
        this.btnIconObjectif.setIcon(icon);
        this.dialogObjectifs.dispose();
    }

    public void validerObjectif(CarteObjectif co) 
    {
        this.panelObjectifs.validerObjectif(co);
        this.majIHM();
    }
}