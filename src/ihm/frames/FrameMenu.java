package ihm.frames;

import javax.swing.JFrame;

import controleur.Controleur;


public class FrameMenu extends JFrame
{
    private Controleur ctrl;

    public FrameMenu(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }
}
