package ihm.jeu;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import controleur.Controleur;


public class PanelMainJoueur extends JPanel
{
    private Controleur ctrl;

    private JPanel     panelInfo;
    private JLabel     lblNom;
    private JLabel     lblNbJeton;
    private JLabel     lblIcon;

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);


        this.panelInfo  = new JPanel();
        this.lblNom     = new JLabel("  nom ");
        this.lblNbJeton = new JLabel("  jetons restants");
        this.lblIcon    = new JLabel();


        //panelInfo Joueur
        JPanel panelLbl = new JPanel();
        panelLbl.setBackground(null);
        panelLbl.setLayout(new GridLayout(2,1));
        this.panelInfo.setBackground(new Color(68, 71, 90)); 
        //this.panelInfo.setLayout(new GridLayout(1,2));
        this.lblNom.setForeground(Color.WHITE);
        this.lblNbJeton.setForeground(Color.WHITE);
        this.lblIcon.setIcon(new ImageIcon("./donnees/images/IconJoueur.png"));

        panelLbl.add(this.lblNom);
        panelLbl.add(this.lblNbJeton);

        this.panelInfo.add(this.lblIcon);
        this.panelInfo.add(panelLbl);

        this.add(this.panelInfo, BorderLayout.EAST);

		this.setVisible(true);
    }
}
