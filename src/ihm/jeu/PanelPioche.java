package ihm.jeu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.CarteObjectif;
import metier.CarteWagon;
import metier.Pioche;

public class PanelPioche extends JPanel
{
    private Controleur ctrl;

	private Pioche<CarteWagon>    piocheWagon;
	private Pioche<CarteObjectif> piocheObjectif;

	private JButton   btnPiocheWag;
	private JButton[] btnPiocheVisu;
	private JButton   btnPiocheObj;


    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setLayout(new BorderLayout(3, 3));

		// Création des panels
		JPanel panelHaut   = new JPanel();
		JPanel panelMilieu = new JPanel(new GridLayout(5, 1));
		JPanel panelBas    = new JPanel();

		// Création des composants
		this.btnPiocheWag = new JButton(new ImageIcon(this.ctrl.getImageVersoCouleur()));

		this.btnPiocheVisu = new JButton[5];
		for (int i = 0; i < 5; i++)
			this.btnPiocheVisu[i] = new JButton(new ImageIcon(this.ctrl.getImageVersoCouleur()));

		this.btnPiocheObj = new JButton(new ImageIcon(this.ctrl.getImageVersoObjectif()));

		// Ajout des composants
		this.add(panelHaut, BorderLayout.NORTH);
		panelHaut.add(this.btnPiocheWag);

		this.add(panelMilieu, BorderLayout.CENTER);
		for (int i = 0; i < 5; i++)
			panelMilieu.add(this.btnPiocheVisu[i]);

		this.add(panelBas, BorderLayout.SOUTH);
		panelBas.add(this.btnPiocheObj);

		this.setVisible(true);
    }
}
