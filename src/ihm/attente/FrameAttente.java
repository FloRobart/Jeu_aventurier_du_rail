package ihm.attente;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import controleur.Controleur;

public class FrameAttente extends JFrame
{
	private Controleur ctrl;

	private PanelAttente panelAttente;

	public FrameAttente(Controleur ctrl)
	{
		this.ctrl = ctrl;

		Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("Attente");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelAttente = new PanelAttente(this.ctrl);
		this.add(this.panelAttente);

		this.pack();
		this.setLocation((dimEcran.width/2) - (this.getSize().width/2), (dimEcran.height/2) - (this.getSize().height/2));
		this.setVisible(true);
	}

	public void appliquerTheme()
	{
		this.panelAttente.appliquerTheme();
	}
}
