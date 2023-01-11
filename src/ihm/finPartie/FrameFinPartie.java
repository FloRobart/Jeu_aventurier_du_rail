package ihm.finPartie;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import metier.Joueur;
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
        this.setLocation(dimEcran.width/4, dimEcran.height/4);
        this.setTitle("Fin De Partie");

        this.panelFinPartie = new PanelFinPartie(this.ctrl);
        this.add(panelFinPartie);

        this.pack();
        this.setVisible(true);
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
