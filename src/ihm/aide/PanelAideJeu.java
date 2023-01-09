package ihm.aide;

import java.awt.Color;

import javax.swing.JPanel;

import controleur.Controleur;

public class PanelAideJeu extends JPanel
{
    private Controleur ctrl;

    public PanelAideJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    public void appliquerTheme()
    {
        Color foregroundColor = this.ctrl.getTheme().get("menuBar").get(0);
        Color backgroundColor = this.ctrl.getTheme().get("menuBar").get(1);
    }
}
