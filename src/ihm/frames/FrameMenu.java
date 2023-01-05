package ihm.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controleur.Controleur;
import ihm.menuBarre.MenuBarre;
import ihm.panels.PanelPartie;


public class FrameMenu extends JFrame
{
    private Controleur ctrl;

	private MenuBarre       menuBarre;
    private PanelPartie     panelPartie;

    public FrameMenu(Controleur ctrl)
    {
        this.ctrl = ctrl;

		Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Frame Jeu");
        this.setSize(dimEcran.width, dimEcran.height); // Définition d'une taille minimum (obligatoire)
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvre la fenêtre en pleine écran
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.menuBarre       = new MenuBarre      (this.ctrl);
        this.panelPartie     = new PanelPartie    (this.ctrl);

		this.setJMenuBar(this.menuBarre);

		this.add(this.panelPartie);


		this.setVisible(true);
    }


	public void appliquerTheme()
    {
        this.menuBarre      .appliquerTheme();

        this.panelPartie    .appliquerTheme();
        //this.panelJoueurs   .appliquerTheme();
        //this.panelMainJoueur.appliquerTheme();
        //this.panelPioche    .appliquerTheme();
        //this.panelPlateau   .appliquerTheme();
    }
}
