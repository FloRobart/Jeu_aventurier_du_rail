package ihm.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controleur.Controleur;


public class PanelObjectif extends JPanel implements ActionListener 
{
    private Controleur ctrl;

    private JDialog dialogInfosJoueur;
    private PanelPiocherObjectif panelPiocherObjectif;

	private JButton btnCarteObjectif;

    public PanelObjectif(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.dialogInfosJoueur    = null;
        this.panelPiocherObjectif = null;
        this.setBorder( BorderFactory.createLineBorder(this.ctrl.getTheme().get("titles").get(1), 2) );
        this.setLayout(new BorderLayout());

		//Creation des composants
		this.btnCarteObjectif = new JButton(new ImageIcon(this.ctrl.getImageVersoObjectif()));
        this.btnCarteObjectif.setSize(new Dimension(200, 150));

        //Ajout des composants
		this.add(this.btnCarteObjectif, BorderLayout.CENTER);
        this.add(new JLabel("    "), BorderLayout.SOUTH);
        this.add(new JLabel("    "), BorderLayout.NORTH);
        this.add(new JLabel("    "), BorderLayout.WEST);
        this.add(new JLabel("    "), BorderLayout.EAST);

		//Activation des composants
		this.btnCarteObjectif.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnCarteObjectif)
        {
            /* Création du panel */
            if (this.panelPiocherObjectif == null) { this.panelPiocherObjectif = new PanelPiocherObjectif(this.ctrl, this); }

            /* Création du JDialog */
            if (this.dialogInfosJoueur == null)
            {
                this.dialogInfosJoueur = new JDialog();

                this.dialogInfosJoueur.setSize(750,250);
                this.dialogInfosJoueur.setLocation(200, 300);
                this.dialogInfosJoueur.setResizable(false);
                this.dialogInfosJoueur.add(this.panelPiocherObjectif);
                this.dialogInfosJoueur.pack();
                this.dialogInfosJoueur.setVisible(true);
            }
            else
            {
                this.dialogInfosJoueur.setVisible(true);
            }

            /* Permet de detecter la fermeture de la fenêtre de dialogue */
            this.dialogInfosJoueur.addWindowListener(new WindowListener()
            {
                public void windowClosing    (WindowEvent e) {}
                public void windowOpened     (WindowEvent e) {}
                public void windowClosed     (WindowEvent e) {}
                public void windowIconified  (WindowEvent e) {}
                public void windowDeiconified(WindowEvent e) {}
                public void windowActivated  (WindowEvent e) {}
                public void windowDeactivated(WindowEvent e) {  }
            });
        }
    }

	public void disposePioche()
	{
		dialogInfosJoueur.dispose();
	}

	public void majIHM()
	{
		this.btnCarteObjectif.setEnabled(!this.ctrl.getEnTrainDePiocher());
	}

    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);


        /*--------*/
        /* Panels */
        /*--------*/
        /* Ce panel */
        this.setForeground(labelForeColor);
        this.setBackground(background    );

        /* Panel pioche objectif */
        if (this.panelPiocherObjectif != null)
            this.panelPiocherObjectif.appliquerTheme();


        /*---------*/
        /* Boutons */
        /*---------*/
	    this.btnCarteObjectif.setForeground(btnForeColor);
        this.btnCarteObjectif.setBackground(background  );
    }
}
