package ihm.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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

    private Controleur ctrl;
    private JButton[]  tabCarteobjectif;

	public PanelPiocherObjectif(Controleur ctrl)
	{
		this.ctrl = ctrl;

		//Parametrage du panel
		this.setBackground(new Color(68, 71, 90));
		this.setLayout(new GridLayout(1,3));
		JLabel lblInfos = new JLabel("Infos sur le Joueurs");
        lblInfos.setForeground(Color.WHITE);

		//Creation des boutons
		this.tabCarteobjectif = new JButton[this.TAILLE];
        for (int cpt=0; cpt<this.TAILLE; cpt++)
        {
            this.tabCarteobjectif[cpt] = new JButton();
			this.tabCarteobjectif[cpt].setBackground(new Color(70, 73, 89));
            this.tabCarteobjectif[cpt].setPreferredSize(new Dimension(300, 200));
            this.tabCarteobjectif[cpt].setBorder(BorderFactory.createBevelBorder(1, new Color(32, 40, 44), new Color(32, 40, 44)));
			this.add(this.tabCarteobjectif[cpt]);
			this.tabCarteobjectif[cpt].addActionListener(this);;
        }
	}

	public void actionPerformed(ActionEvent e) 
    {

	}
}
