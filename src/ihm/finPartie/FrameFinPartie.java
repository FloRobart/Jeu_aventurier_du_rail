package ihm.finPartie;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import controleur.Controleur;


public class FrameFinPartie extends JFrame
{
    private Controleur ctrl;

    private PanelFinPartie panelFinPartie;


    public FrameFinPartie(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Parametrage de la frame
        Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(dimEcran.width/4, dimEcran.height/4);
        this.setTitle("Fin De Partie");

        this.panelFinPartie = new PanelFinPartie(this.ctrl);
        this.add(panelFinPartie);

        this.pack();
        this.setVisible(true);

        this.addWindowListener(new WindowListener()
        {
            public void windowClosing    (WindowEvent e) { ctrl.disposeFrameFinPartie(); ctrl.disposeFrameJeu(); }
            public void windowOpened     (WindowEvent e) {}
            public void windowClosed     (WindowEvent e) {}
            public void windowIconified  (WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated  (WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) { if (panelFinPartie.getFermerFrame()) { ctrl.disposeFrameFinPartie(); ctrl.disposeFrameJeu(); } else { setVisible(true); } }
        });
    }


    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        this.panelFinPartie.appliquerTheme();

        this.setBackground(this.ctrl.getTheme().get("background"  ).get(0));
    }
}
