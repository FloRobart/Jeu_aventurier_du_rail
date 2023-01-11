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
import ihm.finPartie.FrameFinPartie;


public class PanelInfoPartie extends JPanel implements ActionListener
{
    private Controleur 	ctrl;

    private FrameFinPartie frmFinDePartie;
    private JButton     btnArreterPartie;

    private JLabel      lblNbTours;
	private JLabel      lblJoueurCourant;

    public PanelInfoPartie( Controleur ctrl )
    {
		this.ctrl = ctrl;

		//Parametrage du panel
        this.setBorder( BorderFactory.createLineBorder(this.ctrl.getTheme().get("titles").get(1), 2) );
		this.setLayout( new GridLayout(5,1) );

        this.btnArreterPartie = new JButton("Arrêter la partie");
        this.frmFinDePartie = null;

        this.lblNbTours = new JLabel("  Tours : 1");
		this.lblJoueurCourant = new JLabel("  Joueur courant : " + this.ctrl.getJoueurCourant().getNom());

        this.add(new JLabel(""));
        this.add(this.btnArreterPartie);
        this.add(new JLabel(""));
        this.add(this.lblNbTours);
        this.add(this.lblJoueurCourant);

        this.btnArreterPartie.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnArreterPartie )
        {
            this.frmFinDePartie = new FrameFinPartie(this.ctrl);
        }
    }

    public void setInfo(int nbTours, String nomJoueurCourant)
	{
        this.lblNbTours.setText("  Tours : " + nbTours);
		this.lblJoueurCourant.setText("  Joueur courant : " + nomJoueurCourant); 
    }

	public void disposeFrameFinPartie()
	{
		if ( this.frmFinDePartie != null )
			this.frmFinDePartie.dispose();
	}

    /*
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color labelBackColor   = this.ctrl.getTheme().get("labels"      ).get(1);
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

        /*---------*/
        /* JLabels */
        /*---------*/
	    this.lblNbTours.setForeground(labelForeColor);
        this.lblNbTours.setBackground(labelBackColor);
    }
}
