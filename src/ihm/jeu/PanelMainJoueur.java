package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelMainJoueur extends JPanel
{
    private Controleur ctrl;

    private JPanel     panelInfo;
    private JLabel     lblNom;
    private JLabel     lblNbJeton;
    private JLabel     lblIcon;

    private JPanel              panelMainWagon;
    private JLabel[]            tabIconWagon;
    private JLabel[]            tabLblWagon;
    private List<BufferedImage> listImageWagon;

    private JPanel     panelMainObjectif;

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);

        //initialisation des composants
        this.panelInfo  = new JPanel();
        this.lblNom     = new JLabel("  nom ");
        this.lblNbJeton = new JLabel("  jetons restants");
        this.lblIcon    = new JLabel();

        this.panelMainWagon = new JPanel();
        this.listImageWagon = this.ctrl.getImagesRectoCouleur();
        this.tabIconWagon   = new JLabel[this.listImageWagon.size()+1];
        this.tabLblWagon    = new JLabel[this.listImageWagon.size()+1];
        

        this.panelMainObjectif = new JPanel();


        //panelInfo Joueur
        JPanel panelLbl = new JPanel();
        panelLbl.setBackground(null);
        panelLbl.setLayout(new GridLayout(2,1));
        this.panelInfo.setBackground(new Color(68, 71, 90));
        this.lblNom.setForeground(Color.WHITE);
        this.lblNbJeton.setForeground(Color.WHITE);
        this.lblIcon.setIcon(new ImageIcon("./donnees/images/IconJoueur.png"));

        panelLbl.add(this.lblNom);
        panelLbl.add(this.lblNbJeton);

        this.panelInfo.add(this.lblIcon);
        this.panelInfo.add(panelLbl);

        //panelMainWagon
        this.panelMainWagon.setBackground(new Color(68, 71, 90));
        this.panelMainWagon.setLayout(new GridLayout(1, this.ctrl.getNbCarteCoul()+1));

        for(int i = 0; i < this.listImageWagon.size(); i++)
        {
            this.tabIconWagon[i] = new JLabel();
            this.tabLblWagon[i]  = new JLabel();
            this.tabIconWagon[i].setIcon(new ImageIcon(this.listImageWagon.get(i)));
            this.tabLblWagon[i].setText("");
            this.tabLblWagon[i].setForeground(Color.WHITE);
            this.panelMainWagon.add(this.tabIconWagon[i]);
            //this.panelMainWagon.add(this.tabLblWagon[i]);
        }      

        //panelMainObjectif


        //ajout des composants
        this.add(this.panelInfo, BorderLayout.EAST);
        this.add(this.panelMainWagon, BorderLayout.CENTER);
        this.add(this.panelMainObjectif, BorderLayout.WEST);

		this.setVisible(true);
    }
}
