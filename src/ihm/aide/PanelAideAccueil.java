package ihm.aide;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controleur.Controleur;

public class PanelAideAccueil extends JPanel
{
    private Controleur  ctrl;
    private JPanel      panelScroll;
    
    private JPanel      panelBas;
    private JPanel      panelCentral;
    private JPanel      panelGauche;
    private JPanel      panelDroite;

    private JScrollPane scrollPanel;

    private JLabel      lblPanelBas;
    private JLabel      lblpanelCentral;
    private JLabel      lblpanelGauche;
    private JLabel      lblpanelDroite;

    public PanelAideAccueil(Controleur ctrl)
    {
        this.ctrl = ctrl;
        //this.setLayout(new BorderLayout());

        this.panelScroll = new JPanel();
        this.panelScroll.setLayout(new GridLayout(4, 1));

        this.panelBas = new JPanel();
        this.panelCentral = new JPanel();
        this.panelGauche = new JPanel();
        this.panelDroite = new JPanel();

        this.lblPanelBas = new JLabel();
        this.lblpanelCentral = new JLabel();
        this.lblpanelGauche = new JLabel();
        this.lblpanelDroite = new JLabel();

        this.lblPanelBas    .setIcon(new ImageIcon("./bin/donnees/images/aideJoueur/6.png"   ));
        this.lblpanelCentral.setIcon(new ImageIcon("./bin/donnees/images/aideJoueur/panelCentre.png"));
        this.lblpanelGauche .setIcon(new ImageIcon("./bin/donnees/images/aideJoueur/panelGauche.png"));
        this.lblpanelDroite .setIcon(new ImageIcon("./bin/donnees/images/aideJoueur/panelDroite.png"));

        this.panelBas    .add(this.lblPanelBas    );
        this.panelCentral.add(this.lblpanelCentral);
        this.panelDroite .add(this.lblpanelGauche );
        this.panelGauche .add(this.lblpanelDroite );

        this.panelScroll.add(this.panelBas    );
        this.panelScroll.add(this.panelCentral);
        this.panelScroll.add(this.panelDroite );
        this.panelScroll.add(this.panelGauche );

        /*JScrollPane */
        this.scrollPanel = new JScrollPane(this.panelScroll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollPanel.setPreferredSize(new Dimension(650,500));
        this.scrollPanel.getVerticalScrollBar().setUnitIncrement(5);


        this.add(this.scrollPanel);

        this.appliquerTheme();
    }

    public void appliquerTheme()
    {
		Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);

		/* Ce panel */
		this.setBackground(background);
		this.setForeground(labelForeColor);

        this.panelBas    .setBackground(labelForeColor);
        this.panelCentral.setBackground(labelForeColor);
        this.panelGauche .setBackground(labelForeColor);
        this.panelDroite .setBackground(labelForeColor);

		/* Label */
        this.lblPanelBas.setOpaque(false);
    }
}
