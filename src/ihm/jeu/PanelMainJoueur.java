package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;
import java.awt.geom.AffineTransform;
import java.awt.Shape;

import controleur.Controleur;


public class PanelMainJoueur extends JPanel
{
    private Controleur ctrl;

    private JPanel     panelInfo;
    private JLabel     lblNom;
    private JLabel     lblNbJeton;
    private JLabel     lblIcon;

    private JPanel              panelMainWagon;
    private JButton[]            tabIconWagon;
    private JLabel[]            tabLblWagon;
    private List<BufferedImage> listImageWagon;

    private JPanel     panelMainObjectif;
    private JLabel     lblIconObjectif;

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);

        //initialisation des composants
        this.panelInfo  = new JPanel();
        this.lblNom     = new JLabel("  nom ");
        this.lblNbJeton = new JLabel("  jetons restants");
        this.lblIcon    = new JLabel(new ImageIcon("./donnees/images/IconJoueur.png"), JLabel.LEFT);

        this.panelMainWagon = new JPanel();
        this.listImageWagon = this.ctrl.getImagesRectoCouleur();
        this.tabIconWagon   = new JButton[this.listImageWagon.size()+1];
        this.tabLblWagon    = new JLabel[this.listImageWagon.size()+1];
        

        this.panelMainObjectif = new JPanel();
        this.lblIconObjectif   = new JLabel(new ImageIcon(this.ctrl.getCarteObjectif().get(2).getImageRecto()), JLabel.LEFT);


        //panelInfo Joueur
        JPanel panelLbl = new JPanel();
        panelLbl.setBackground(null);
        panelLbl.setLayout(new GridLayout(2,1));
        this.panelInfo.setBackground(new Color(68, 71, 90));
        this.lblNom.setForeground(Color.WHITE);
        this.lblNbJeton.setForeground(Color.WHITE);
        //this.lblIcon.setIcon(new ImageIcon("./donnees/images/IconJoueur.png"));

        panelLbl.add(this.lblNom);
        panelLbl.add(this.lblNbJeton);

        this.panelInfo.add(this.lblIcon);
        this.panelInfo.add(panelLbl);

        //panelMainWagon
        this.panelMainWagon.setBackground(new Color(68, 71, 90));
        this.panelMainWagon.setLayout(new GridLayout(2, 5));

       
        for(int i = 0; i < this.listImageWagon.size(); i++)
        {
            this.tabIconWagon[i] = new JButton();
            this.tabLblWagon[i]  = new JLabel();

            //this.tabIconWagon[i].setIcon(new ImageIcon(this.creerCarte(this.listImageWagon.get(i))));
            this.tabIconWagon[i].setBorderPainted(false);
            this.tabIconWagon[i].setContentAreaFilled(false);
            this.tabIconWagon[i].setFocusPainted(false);
            this.tabLblWagon[i].setText("X"+ i);
            this.tabLblWagon[i].setForeground(Color.WHITE);
        
            this.panelMainWagon.add(this.tabIconWagon[i]);
        }   
        
        int taille = this.listImageWagon.size();
        this.tabIconWagon[taille] = new JButton();
        this.tabLblWagon[taille]  = new JLabel();
        //this.tabIconWagon[taille].setIcon(new ImageIcon(this.creerCarte(this.listImageWagon.get(taille))));
        this.tabIconWagon[taille].setBorderPainted(false);
        this.tabIconWagon[taille].setContentAreaFilled(false);
        this.tabIconWagon[taille].setFocusPainted(false);
        this.tabLblWagon[taille].setText("X"+ taille);
        this.tabLblWagon[taille].setForeground(Color.WHITE);
        this.panelMainWagon.add(this.tabIconWagon[taille]);
        
        for(int i = 0; i < this.listImageWagon.size()+1; i++)
        {
            this.panelMainWagon.add(this.tabLblWagon[i]);
        }


        //panelMainObjectif
        this.panelMainObjectif.setBackground(new Color(68, 71, 90));
        this.panelMainObjectif.add(this.lblIconObjectif);


        //ajout des composants
        this.add(this.panelInfo, BorderLayout.EAST);
        this.add(this.panelMainWagon, BorderLayout.CENTER);
        this.add(this.panelMainObjectif, BorderLayout.WEST);

		this.setVisible(true);
    }

    private BufferedImage creerCarte(BufferedImage bufferedImage) 
    {
        BufferedImage bi = bufferedImage;
        Shape fig1 = (Shape) bi;
        int milieuXcarré = bi.getWidth()/2;
        int milieuYcarré = bi.getHeight()/2;

        AffineTransform t = new AffineTransform();
        t.rotate(1.57, milieuXcarré, milieuYcarré);
        Shape fig2 = t.createTransformedShape(fig1);

        bi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(Color.WHITE);
        g2d.fill(fig2);
        g2d.dispose();
   
        return bi;
    }
}
