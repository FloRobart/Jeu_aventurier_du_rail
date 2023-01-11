package ihm.aide;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import controleur.Controleur;

public class PanelAideJeu extends JPanel
{
    private Controleur  ctrl;
    private JPanel      panelScroll;

    private JScrollPane scrollPanel;

    public PanelAideJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(new Color(100,100,100));

        this.panelScroll = new JPanel();
        this.panelScroll.setLayout(new GridLayout(5, 1));

        for ( int cpt=1; cpt<= 5; cpt++ )
        {
            JPanel panel = new JPanel();
            panel.setBackground(this.ctrl.getTheme().get("background"  ).get(0));
            JLabel lblImage = new JLabel();
            lblImage.setIcon(new ImageIcon("./donnees/images/aideJoueur/" + cpt + ".png"));
            panel.add(lblImage);
            this.panelScroll.add(panel);
        }

        /*JScrollPane */
        this.scrollPanel = new JScrollPane(this.panelScroll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollPanel.setPreferredSize(new Dimension(1440, 750)); 
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
    }
}
