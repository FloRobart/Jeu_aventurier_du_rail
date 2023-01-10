package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import controleur.Controleur;

public class PanelInfoPartie extends JPanel implements ActionListener
{
    private Controleur 	ctrl;

    private JButton     btnArreterPartie;
    private JButton     btnPause;

    private JLabel      lblNbTours;
    private JLabel      lblTimer;

    public PanelInfoPartie( Controleur ctrl )
    {
		this.ctrl = ctrl;

		//Parametrage du panel
        this.setBorder( BorderFactory.createLineBorder(Color.black, 2) );
		this.setLayout( new GridLayout(5,1) );

        this.btnArreterPartie = new JButton("Arrêter la partie");
        this.btnPause = new JButton("Pause");;

        this.lblNbTours = new JLabel("  Tours : ");
        this.lblTimer = new JLabel("  Temps de la partie : ");

        this.add(this.btnArreterPartie);
        this.add(this.btnPause);
        this.add(new JLabel(""));
        this.add(this.lblTimer);
        this.add(this.lblNbTours);

        this.btnArreterPartie.addActionListener(this);
        this.btnPause.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnArreterPartie )
        {

        }

        if ( e.getSource() == this.btnPause )
        {
            
        }
    }

    /*
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color labelBackColor     = this.ctrl.getTheme().get("labels"     ).get(1);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
        Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);

        /*--------*/
        /* Panels */
        /*--------*/
        /* Ce panel */
        this.setForeground(labelForeColor);
        this.setBackground(background    );


        /*---------*/
        /* JButton */
        /*---------*/
        this.btnArreterPartie.setForeground(btnForeColor);
        this.btnArreterPartie.setBackground(btnBackColor);
        this.btnPause.setForeground(btnForeColor);
        this.btnPause.setBackground(btnBackColor);

        /*---------*/
        /* JLabels */
        /*---------*/
	    this.lblNbTours.setForeground(labelForeColor);
        this.lblNbTours.setBackground(labelBackColor);

        this.lblTimer.setForeground(labelForeColor);
        this.lblTimer.setBackground(labelBackColor);
    }
}
