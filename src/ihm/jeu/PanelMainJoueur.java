package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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


import controleur.Controleur;

public class PanelMainJoueur extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JDialog    dialogObjectifs;
    private HashMap<String, List<Color>> theme;

    private JPanel         panelImgJoueur;
    private JPanel         panelInfoJoueur;
    private PanelObjectifs panelObjectifs;

    private JLabel     lblNom;
    private JLabel     lblNbJeton;
    private JLabel     lblIcon;

    private JPanel              panelMainWagon;
    private JButton[]           tabIconWagon;
    private JLabel[]            tabLblWagon;
    private List<BufferedImage> listImageWagon;

    private JPanel     panelMainObjectif;
    private JButton    btnIconObjectif;
       

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl            = ctrl;
        this.dialogObjectifs = null;
        this.panelObjectifs  = null;
        this.theme           = this.ctrl.getTheme();

        this.setLayout(new BorderLayout());

        //initialisation des composants
        this.panelImgJoueur  = new JPanel();
        this.lblNom     = new JLabel("  nom   ");
        this.lblNbJeton = new JLabel("  jetons restants   ");

        String pathImage = "";
        if (this.ctrl.getThemeUsed().equals("dark"))
            pathImage = "./bin/donnees/images/IconJoueurWhite.png";
        else
            pathImage = "./bin/donnees/images/IconJoueurBlack.png";

        this.lblIcon    = new JLabel(new ImageIcon(pathImage), JLabel.LEFT);
        
        this.panelMainWagon = new JPanel();
        this.listImageWagon = this.ctrl.getImagesRectoCouleur(); // joueur.getCarteWagon();
        this.listImageWagon.add(this.ctrl.getImageRectoLocomotive());
        int taille = this.listImageWagon.size();
        this.tabIconWagon   = new JButton[taille];
        this.tabLblWagon    = new JLabel[taille];
        
        this.panelMainObjectif = new JPanel();
        this.btnIconObjectif   = new JButton();


        //panelImgJoueur Joueur
        this.panelInfoJoueur = new JPanel();
        this.panelInfoJoueur.setLayout(new GridLayout(2,1)); // à modifier en fonction du nombre d'infos à afficher
        this.panelImgJoueur.setLayout(new BorderLayout());

        this.panelInfoJoueur.add(this.lblNom);
        this.panelInfoJoueur.add(this.lblNbJeton);

        this.panelImgJoueur.add(this.lblIcon, BorderLayout.NORTH);
        this.panelImgJoueur.add(panelInfoJoueur, BorderLayout.CENTER);

        //panelMainWagon
        System.out.println(this.listImageWagon.size());

        for(int i = 0; i < taille; i++)
        {
            this.tabLblWagon[i]  = new JLabel();
            this.tabLblWagon[i].setText("X"+ i);

            this.tabIconWagon[i] = new JButton();
            this.tabIconWagon[i].setIcon(new ImageIcon(creerCarte(this.listImageWagon.get(i), this.tabLblWagon[i])));
            this.tabIconWagon[i].setBorderPainted(false);
            this.tabIconWagon[i].setContentAreaFilled(false);
            this.tabIconWagon[i].setFocusPainted(false);
        
            this.panelMainWagon.add(this.tabIconWagon[i]);
        }   

        //panelMainObjectif
        this.btnIconObjectif.setIcon(new ImageIcon(creerCarte(this.ctrl.getCarteObjectif().get(2).getImageRecto(), null)));
        this.btnIconObjectif.setBorderPainted(false);
        this.btnIconObjectif.setContentAreaFilled(false);
        this.btnIconObjectif.setFocusPainted(false);
        this.panelMainObjectif.add(this.btnIconObjectif);


        //ajout des composants
        this.add(this.panelImgJoueur, BorderLayout.EAST);
        this.add(this.panelMainWagon, BorderLayout.CENTER);
        this.add(this.panelMainObjectif, BorderLayout.WEST);

		this.setVisible(true);

        this.btnIconObjectif.addActionListener(this);
    }

    /**
     * Permet de retourner la carte et afficher le nombre de carte de cette couleur
     * @param bufferedImage image de la carte
     * @param lbl label contenant le nombre de carte de cette couleur
     * @return BufferedImage carte retourné avec le nombre de carte de cette couleur
     */
    private BufferedImage creerCarte(BufferedImage bufferedImage, JLabel lbl) 
    {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int taille = Math.max(width + 30, height);
        BufferedImage bi = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();

        g2d.rotate(1.57, width / 2, height / 2);
        g2d.drawImage(bufferedImage, (taille-width)/2, (taille-height)/2-30, width, height, null);
        g2d.rotate((1.57*3), taille / 2, taille / 2);
        g2d.setColor(this.theme.get("labels").get(0));
        if(lbl != null)
            g2d.drawString(lbl.getText(), 50, 50);

        return bi;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnIconObjectif)
        {
            /* Création d'un Panel */
            this.panelObjectifs = new PanelObjectifs(this.ctrl);

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


        for (int i = 0; i < this.listImageWagon.size(); i++)
        {
            this.tabIconWagon[i].setIcon(new ImageIcon(creerCarte(this.listImageWagon.get(i), this.tabLblWagon[i])));
            this.tabIconWagon[i].setOpaque(false);
        }
    
        /*========*/
        /* Panels */
        /*========*/
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
        for (int i = 0; i < this.tabIconWagon.length; i++)
        {
            this.tabLblWagon[i].setOpaque(false);
            this.tabLblWagon[i].setForeground(labelForeColor);
        }

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

        /* lblIcon */
        this.lblIcon.setOpaque(false);
        this.lblIcon.setForeground(labelForeColor);
    }
}
