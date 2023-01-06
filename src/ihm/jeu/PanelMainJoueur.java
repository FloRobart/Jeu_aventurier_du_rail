package ihm.jeu;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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
		//this.add(new JLabel("PanelMainJoueur"));

        //panelInfo Joueur
        this.panelInfo = new JPanel();
        this.panelInfo.setBackground(new Color(68, 71, 90));

        this.lblNom = new JLabel("nom ");
        this.lblNom.setForeground(Color.WHITE);
        this.lblNbJeton = new JLabel(" jetons restants");
        this.lblNbJeton.setForeground(Color.WHITE);
        this.lblIcon = new JLabel();
        this.lblIcon.setIcon(new ImageIcon("./donnees/images/IconJoueur.png"));

        this.panelInfo.add(this.lblIcon);
        this.panelInfo.add(this.lblNom);
        this.panelInfo.add(this.lblNbJeton);

        this.add(this.panelInfo, BorderLayout.EAST);

		this.setVisible(true);
    }
}
