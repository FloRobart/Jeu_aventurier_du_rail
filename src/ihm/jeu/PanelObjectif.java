package ihm.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controleur.Controleur;

public class PanelObjectif extends JPanel implements ActionListener 
{
    private Controleur ctrl;
	private JButton btnCarteObjectif;

    public PanelObjectif(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Parametrage du panel
		this.setBackground(new Color(68, 71, 90));

		//Creation des composants
		this.btnCarteObjectif = new JButton(new ImageIcon(this.ctrl.getImageVersoObjectif()));
		this.btnCarteObjectif.setBackground(new Color(68, 71, 90));
		this.btnCarteObjectif.setPreferredSize(new Dimension(200, 100));

        //Ajout des composants
		this.add(this.btnCarteObjectif);

		//Activation des composants
		this.btnCarteObjectif.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnCarteObjectif)
        {
            JDialog dialog = new JDialog();
            dialog.setSize(600,300);
            dialog.setLocation(200, 300);
            //dialog.add(new(this.ctrl, new PanelPiocherObjectif()));
            dialog.setVisible(true);
        }
    }
}
