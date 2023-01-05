package ihm.jeu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;


    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setBackground(Color.YELLOW);
		this.add(new JLabel("PanelJoueurs"));

		this.setVisible(true);
    }
}
