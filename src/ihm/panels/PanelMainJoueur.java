package ihm.panels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelMainJoueur extends JPanel
{
    private Controleur ctrl;

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setBackground(Color.BLUE);
		this.add(new JLabel("PanelMainJoueur"));

		this.setVisible(true);
    }
}
