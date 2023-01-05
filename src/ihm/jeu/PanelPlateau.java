package ihm.jeu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelPlateau extends JPanel
{
    private Controleur ctrl;

    public PanelPlateau(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setBackground(Color.GREEN);
		this.add(new JLabel("PanelPlateau"));

		this.setVisible(true);
    }
}
