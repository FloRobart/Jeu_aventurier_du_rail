package ihm.jeu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

import controleur.Controleur;

public class PanelInfoPartie extends JPanel implements ActionListener
{
    private Controleur 	ctrl;
    private JButton     btnTourSuivant;

    public PanelInfoPartie( Controleur ctrl )
    {
		this.ctrl = ctrl;

		//Parametrage du panel
        this.setBorder( BorderFactory.createLineBorder(Color.black, 2) );
		this.setLayout( new GridLayout(5,1) );

        this.btnTourSuivant = new JButton("Tour suivant");
        this.btnTourSuivant.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.btnTourSuivant);

        this.btnTourSuivant.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
		if (e.getSource() == this.btnTourSuivant)
		{
			// action à faire en cas de clic sur un bouton
		}
	}

        /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
        Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);


        /*--------*/
        /* Panels */
        /*--------*/
        /* Ce panel */
        this.setForeground(labelForeColor);
        this.setBackground(background    );

        /*---------*/
        /* Boutons */
        /*---------*/
	    this.btnTourSuivant.setForeground(btnForeColor);
        this.btnTourSuivant.setBackground(btnBackColor);
    }
}
