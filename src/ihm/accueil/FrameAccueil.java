package ihm.accueil;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controleur.Controleur;


public class FrameAccueil extends JFrame
{
    private Controleur ctrl;

	private MenuBarre    menuBarre;
    private PanelAccueil panelAccueil;

    public FrameAccueil(Controleur ctrl)
    {
        this.ctrl = ctrl;

		Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Frame Jeu");
        
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.menuBarre    = new MenuBarre   (this.ctrl);
        this.panelAccueil = new PanelAccueil(ctrl);

		this.setJMenuBar(this.menuBarre);
		this.add(this.panelAccueil);

        this.pack();
        this.setLocation((dimEcran.width/2) - (this.getSize().width/2), (dimEcran.height/2) - (this.getSize().height/2));
		this.setVisible(true);
    }


	public void appliquerTheme()
    {
        this.menuBarre   .appliquerTheme();
        this.panelAccueil.appliquerTheme();
    }
}
