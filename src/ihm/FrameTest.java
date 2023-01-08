package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ihm.accueil.*;
import ihm.jeu.*;
import ihm.attente.*;
import controleur.Controleur;
import controleur.Controleur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;


public class FrameTest extends JFrame
{
    private Controleur ctrl;

    private MenuBarre mb;
    private JPanel panel;

    private PanelObjectif panelATester;


    public FrameTest(Controleur ctrl)
    {
        this.ctrl = ctrl;
        Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mb           = new MenuBarre(ctrl);
        this.panel        = new JPanel();
        this.panelATester = new PanelObjectif(ctrl);

        this.setJMenuBar(this.mb);
        this.panel.add(this.panelATester);

        this.add(panel);

        this.pack();
        this.setLocation((dimEcran.width/2) - (this.getSize().width/2), (dimEcran.height/2) - (this.getSize().height/2));
        this.setVisible(true);
    }

    public void appliquerTheme()
    {
        this.panel.setBackground(Color.BLACK);
        this.mb.appliquerTheme();
        this.panelATester.appliquerTheme();
    }
}
