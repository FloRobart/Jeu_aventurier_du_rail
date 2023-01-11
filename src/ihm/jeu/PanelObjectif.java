package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.CarteObjectif;


public class PanelObjectif extends JPanel implements ActionListener 
{
    private Controleur ctrl;

    private JDialog dialogPiocherObjectifs;
    private JDialog dialogPiocherDebut;
    private PanelPiocherObjectif panelPiocherObjectif;

	private JLabel lblPiocheObjectif;
	private JButton btnCarteObjectif;

    public PanelObjectif(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.dialogPiocherObjectifs  = null;
        this.dialogPiocherDebut    = null;
        this.panelPiocherObjectif = null;
        this.setBorder( BorderFactory.createLineBorder(this.ctrl.getTheme().get("titles").get(1), 2) );
        this.setLayout(new BorderLayout());

		//Creation des composants
		this.lblPiocheObjectif = new JLabel("" + this.ctrl.getSizeObjectif() + "/" + this.ctrl.getSizeObjectif(), JLabel.CENTER);

		this.btnCarteObjectif = new JButton(new ImageIcon(this.ctrl.getImageVersoObjectif()));
        this.btnCarteObjectif.setSize(new Dimension(200, 150));

        //Ajout des composants
		this.add(this.lblPiocheObjectif, BorderLayout.NORTH);
		this.add(this.btnCarteObjectif, BorderLayout.CENTER);
        this.add(new JLabel("    "), BorderLayout.SOUTH);
        this.add(new JLabel("    "), BorderLayout.WEST);
        this.add(new JLabel("    "), BorderLayout.EAST);

		//Activation des composants
		this.btnCarteObjectif.addActionListener(this);
	}

    public void piocherCarteObjectifDebutPartie()
    {
        if (this.ctrl.peuxJouer()) 
        { 
            if (this.panelPiocherObjectif == null)
				this.panelPiocherObjectif = new PanelPiocherObjectif(this.ctrl, this, true); 

            /* Création du JDialog */
            if (this.dialogPiocherDebut == null)
            {
                this.dialogPiocherDebut = new JDialog();

                this.dialogPiocherDebut.setSize(750,250);
                this.dialogPiocherDebut.setLocation(200, 300);
                this.dialogPiocherDebut.setResizable(false);
				this.dialogPiocherDebut.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                this.dialogPiocherDebut.add(this.panelPiocherObjectif);
                this.dialogPiocherDebut.pack();
                this.dialogPiocherDebut.setVisible(true);
            }
            else
            {
                this.dialogPiocherDebut.setVisible(true);
            }

            /* Permet de detecter la fermeture de la fenêtre de dialogue */
            this.dialogPiocherDebut.addWindowListener(new WindowListener()
            {
                private boolean fermetureForce = true;    
                public void windowClosing    (WindowEvent e) {}
                public void windowOpened     (WindowEvent e) {}
                public void windowClosed     (WindowEvent e) { 
                    fermetureForce = false; 
                    dialogPiocherDebut.dispose();
                }
                public void windowIconified  (WindowEvent e) {}
                public void windowDeiconified(WindowEvent e) {}
                public void windowActivated  (WindowEvent e) {}
                public void windowDeactivated(WindowEvent e) { 
                    if (fermetureForce) dialogPiocherDebut.setVisible(true);
                }
            });
        }
        else
            this.ctrl.afficherErreur("Ce n'est à pas votre tour de jouer");
    }

	public void actionPerformed(ActionEvent e) 
    {
        if(this.ctrl.peuxJouer())
        {
            /* Création du panel */
            if (this.panelPiocherObjectif == null) { this.panelPiocherObjectif = new PanelPiocherObjectif(this.ctrl, this, false); }

            /* Création du JDialog */
            if (this.dialogPiocherObjectifs == null)
            {
                this.dialogPiocherObjectifs = new JDialog();

                this.dialogPiocherObjectifs.setSize(750,250);
                this.dialogPiocherObjectifs.setLocation(200, 300);
                this.dialogPiocherObjectifs.setResizable(false);
				this.dialogPiocherObjectifs.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                this.dialogPiocherObjectifs.add(this.panelPiocherObjectif);
                this.dialogPiocherObjectifs.pack();
                this.dialogPiocherObjectifs.setVisible(true);
            }
            else
            {
                this.dialogPiocherObjectifs.setVisible(true);
            }

            /* Permet de detecter la fermeture de la fenêtre de dialogue */
            this.dialogPiocherObjectifs.addWindowListener(new WindowListener()
            {
				private boolean fermetureForce = true;

                public void windowClosing    (WindowEvent e) {}
                public void windowOpened     (WindowEvent e) {}
                public void windowClosed     (WindowEvent e) { 
					fermetureForce = false; 
					dialogPiocherObjectifs.dispose();
				}
                public void windowIconified  (WindowEvent e) {}
                public void windowDeiconified(WindowEvent e) {}
                public void windowActivated  (WindowEvent e) {}
                public void windowDeactivated(WindowEvent e) { 
					if (fermetureForce) dialogPiocherObjectifs.setVisible(true);
				}
            });
        }
		else
			this.ctrl.afficherErreur("Ce n'est à pas votre tour de jouer");
    }

	public void disposePioche()
	{
        if ( this.dialogPiocherDebut != null)
		{
            this.dialogPiocherDebut.dispose();
		}

        if ( this.dialogPiocherObjectifs != null)
		{
            this.dialogPiocherObjectifs.dispose();
		}
	}

	public void majIHM()
	{
		this.btnCarteObjectif.setEnabled(!this.ctrl.getEnTrainDePiocher());
        if(this.ctrl.getCarteObjectif().size()==0)
            this.btnCarteObjectif.setEnabled(false);

		int nbCartes = this.ctrl.getSizeObjectif();
		CarteObjectif[] cartes = this.panelPiocherObjectif.getCartesObjectifs();
		if (cartes != null)
			for (int i = 0; i < cartes.length; i++)
				if (cartes[i] != null)
					nbCartes++;

		String text = this.lblPiocheObjectif.getText();
		this.lblPiocheObjectif.setText("" + nbCartes + text.substring(text.indexOf("/")));
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
		this.lblPiocheObjectif.setForeground(labelForeColor);
		this.lblPiocheObjectif.setBackground(background);
    }
}
