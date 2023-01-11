package ihm.aide;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import controleur.Controleur;

public class PanelAideMenu extends JPanel
{
    private Controleur ctrl;

    public PanelAideMenu(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background      = this.ctrl.getTheme().get("background").get(0);
		Color labelForeColor  = this.ctrl.getTheme().get("labels"    ).get(0);
		Color labelBackColor  = this.ctrl.getTheme().get("labels"    ).get(1);

        this.setBackground(background);
    }
}
