package ihm.attente;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controleur.Controleur;

public class FrameAttente extends JFrame
{
	private Controleur ctrl;

	public FrameAttente(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Attente");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(new JLabel("Futur salle d'attente"));

		this.setVisible(true);
	}
}
