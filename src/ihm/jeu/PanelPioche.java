package ihm.jeu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelPioche extends JPanel
{
    private Controleur ctrl;

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setBackground(Color.RED);
		this.add(new JLabel("PanelPioche"));

		this.setVisible(true);
    }
}
