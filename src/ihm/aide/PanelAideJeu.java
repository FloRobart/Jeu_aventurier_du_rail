package ihm.aide;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;

import controleur.Controleur;

public class PanelAideJeu extends JPanel
{
    private Controleur  ctrl;
    private JLabel      lblCarteObjectif;
    private JPanel      panelBas;
    private JPanel      panelCentral;
    private JPanel      panelGauche;
    private JPanel      panelDroite;

    public PanelAideJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;
        /*this.panel = new JPanel();
        this.setBackground(new Color(100,100,100));

        this.add(this.panel);*/

        this.appliquerTheme();
    }

    public void appliquerTheme()
    {
		Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
		Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);

		/* Ce panel */
		this.setBackground(background);
		this.setForeground(labelForeColor);

		/* Label */
		this.lblCarteObjectif.setOpaque(false);
		this.lblCarteObjectif.setForeground(labelForeColor);

    }
}
