package ihm.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

import controleur.Controleur;

public class PanelPiocherObjectif extends JPanel implements ActionListener
{
    private static final int TAILLE = 3;

    private Controleur 	ctrl;

	private JPanel		panelBtnPiocher;

    private JButton[]	tabCarteobjectif;
	private JButton		btnPiocher;

	private JLabel     	lblChoisirCartes;

	public PanelPiocherObjectif(Controleur ctrl)
	{
		this.ctrl = ctrl;

		//Parametrage du panel
		this.setLayout( new BorderLayout() );

		//Creation des composants
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(1,3));

		this.panelBtnPiocher = new JPanel();
		this.panelBtnPiocher.setLayout(new GridLayout(1,5));

		this.lblChoisirCartes = new JLabel(" Choisissez les cartes que vous voulez : ");

		//Creation des boutons
		this.btnPiocher = new JButton("Piocher");
		this.btnPiocher.setPreferredSize(new Dimension(20,20));

		Color titleBackColor = this.ctrl.getTheme().get("titles").get(1);
		this.tabCarteobjectif = new JButton[PanelPiocherObjectif.TAILLE];
        for (int cpt = 0; cpt < PanelPiocherObjectif.TAILLE; cpt++)
        {
            this.tabCarteobjectif[cpt] = new JButton();
            this.tabCarteobjectif[cpt].setPreferredSize(new Dimension(300, 200));
			
            this.tabCarteobjectif[cpt].setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));

			panelPrincipal.add(this.tabCarteobjectif[cpt]);
			this.tabCarteobjectif[cpt].addActionListener(this);;
        }
		this.btnPiocher.addActionListener(this);

		this.panelBtnPiocher.add(new JLabel(""));
		this.panelBtnPiocher.add(new JLabel(""));
		this.panelBtnPiocher.add(this.btnPiocher);
		this.panelBtnPiocher.add(new JLabel(""));
		this.panelBtnPiocher.add(new JLabel(""));

		this.add(this.lblChoisirCartes	, BorderLayout.NORTH);
		this.add(panelPrincipal			, BorderLayout.CENTER);
		this.add(this.panelBtnPiocher	, BorderLayout.SOUTH);
	

		this.appliquerTheme();
	}

	public void actionPerformed(ActionEvent e) 
    {
		for (int i = 0; i < PanelPiocherObjectif.TAILLE; i++)
		{
			if (e.getSource() == this.tabCarteobjectif[i])
			{
				// action à faire en cas de clic sur un bouton
			}
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

		/* Ce panel */
		this.setBackground(background);
		this.setForeground(labelForeColor);
		this.panelBtnPiocher.setBackground(background);

		/* Label */
		this.lblChoisirCartes.setOpaque(false);
		this.lblChoisirCartes.setForeground(labelForeColor);

		/*---------*/
		/* Boutons */
		/*---------*/
		for (int i = 0; i < PanelPiocherObjectif.TAILLE; i++)
		{
			this.tabCarteobjectif[i].setForeground(btnForeColor);
			this.tabCarteobjectif[i].setBackground(btnBackColor);
		}

		this.btnPiocher.setForeground(btnForeColor);
        this.btnPiocher.setBackground(background  );
	}
}
