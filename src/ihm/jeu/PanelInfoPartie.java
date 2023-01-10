package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

import controleur.Controleur;

public class PanelInfoPartie extends JPanel
{
    private Controleur 	ctrl;
    private JLabel      lblNbTours;
    private JLabel      lblTimer;

    public PanelInfoPartie( Controleur ctrl )
    {
		this.ctrl = ctrl;

		//Parametrage du panel
        this.setBorder( BorderFactory.createLineBorder(Color.black, 2) );
		this.setLayout( new GridLayout(5,1) );

        this.lblNbTours = new JLabel("  Tours : ");
        this.lblTimer = new JLabel("  Temps de la partie : ");

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.lblTimer);
        this.add(this.lblNbTours);
    }

    /*
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color labelBackColor     = this.ctrl.getTheme().get("labels"     ).get(1);

        /*--------*/
        /* Panels */
        /*--------*/
        /* Ce panel */
        this.setForeground(labelForeColor);
        this.setBackground(background    );

        /*---------*/
        /* JLabels */
        /*---------*/
	    this.lblNbTours.setForeground(labelForeColor);
        this.lblNbTours.setBackground(labelBackColor);

        this.lblTimer.setForeground(labelForeColor);
        this.lblTimer.setBackground(labelBackColor);
    }
}
