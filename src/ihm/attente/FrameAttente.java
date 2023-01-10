package ihm.attente;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import controleur.Controleur;
import ihm.accueil.MenuBarre;


public class FrameAttente extends JFrame
{
	private Controleur ctrl;

	private MenuBarre    menuBarre;
	private PanelAttente panelAttente;


	public FrameAttente(Controleur ctrl, boolean hote)
	{
		this.ctrl = ctrl;

		Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("Attente");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.menuBarre    = new MenuBarre   (this.ctrl);
		this.panelAttente = new PanelAttente(this.ctrl, hote);

		this.setJMenuBar(this.menuBarre);
		this.add(this.panelAttente);

		this.pack();
		this.setLocation((dimEcran.width/2) - (this.getSize().width/2), (dimEcran.height/2) - (this.getSize().height/2));
		this.setVisible(true);
	}

	public void majIHM()
	{
		this.panelAttente.majIHM();
	}

	public void appliquerTheme()
	{
		this.menuBarre   .appliquerTheme();
		this.panelAttente.appliquerTheme();
	}
}
