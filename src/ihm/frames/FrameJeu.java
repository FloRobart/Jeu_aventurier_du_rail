package frames;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;

public class FrameJeu extends JFrame
{
    private Controleur ctrl;
    private JPanel panelJoueurs;
    private JPanel panelMainJoueur;
    private JPanel panelPioche;
    private JPanel panelPlateau;


    public FrameJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Creation des composants
        this.panelJoueurs = new JPanel();
        this.panelMainJoueur = new JPanel();
        this.panelPioche = new JPanel();
        this.panelPlateau = new JPanel();


        //Ajout des composants
    }
}