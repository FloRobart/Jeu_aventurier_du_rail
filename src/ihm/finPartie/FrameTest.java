package ihm.finPartie;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;

import controleur.Controleur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;


public class FrameTest extends JFrame
{
    Controleur ctrl;

    /* Panel */
    private PanelResultat PanelResultat;


    public FrameTest(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* Panel */
        PanelResultat = new PanelResultat(ctrl);

        this.add(PanelResultat);

        this.pack();
        this.setVisible(true);
    }
}