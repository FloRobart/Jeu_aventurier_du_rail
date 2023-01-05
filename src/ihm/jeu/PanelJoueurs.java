package ihm.jeu;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import controleur.Controleur;


public class PanelJoueurs extends JPanel implements ActionListener 
{
    private Controleur ctrl;

    private JPanel      panelJoueurs;
    private JScrollPane scrollJoueurs;
    private JPanel[]    tabPanels;
    private JButton[]   tabBoutons;


    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setBackground(Color.YELLOW);
        this.setSize(500, 200);

        this.panelJoueurs = new JPanel();
        this.panelJoueurs.setBackground(new Color(40, 42, 54));
        this.panelJoueurs.setLayout(new GridLayout(4, 1, 1, 1));

        this.tabPanels = new JPanel[4];
        this.tabBoutons = new JButton[4];

        for(int cpt=0; cpt< 4; cpt++)
        {
            tabPanels[cpt] = new JPanel();
            tabPanels[cpt].setBackground(new Color(68, 71, 90));
            tabPanels[cpt].setLayout(new BorderLayout(2,2));

            JLabel lblNomJoueur = new JLabel("nom" + (cpt+1));
                   lblNomJoueur.setForeground(Color.WHITE);
            JLabel lblScoreJoueur = new JLabel("score" + (cpt+1));
                   lblScoreJoueur.setForeground(Color.WHITE);
            JLabel lblIcon = new JLabel();
                   lblIcon.setIcon(new ImageIcon("./donnees/images/IconJoueur.png"));

            this.tabBoutons[cpt] = new JButton();
            this.tabBoutons[cpt].setBackground(new Color(68, 71, 90));
            this.tabBoutons[cpt].setIcon(lblIcon.getIcon());
            this.tabBoutons[cpt].setBorderPainted(false);
            this.tabBoutons[cpt].setFocusPainted(false);
            this.tabBoutons[cpt].setContentAreaFilled(false);
            
            tabPanels[cpt].add(lblNomJoueur, BorderLayout.NORTH);
            tabPanels[cpt].add(this.tabBoutons[cpt], BorderLayout.WEST);
            tabPanels[cpt].add(lblScoreJoueur, BorderLayout.EAST);

            this.panelJoueurs.add(tabPanels[cpt]);
        }

        this.scrollJoueurs = new JScrollPane(panelJoueurs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollJoueurs.getVerticalScrollBar().setBackground(new Color(68, 71, 90));
        this.scrollJoueurs.setPreferredSize(new Dimension(200,200));

        this.add(scrollJoueurs);
 
		this.setVisible(true);

        for(int cpt=0; cpt< tabBoutons.length; cpt++)
        {
            this.tabBoutons[cpt].addActionListener(this);
        }
       
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for(int cpt=0; cpt< tabBoutons.length; cpt++)
        {
            if(e.getSource() == this.tabBoutons[cpt])
            {
                this.ouvrirDialog((cpt+1));
            }
        }

    }

    private void ouvrirDialog(int numJoueur)
    {
        JDialog dialog = new JDialog();
        dialog.setSize(400,200);
        dialog.setLocation(200, 50);
		dialog.add(new PanelInfosJoueur(this.ctrl, numJoueur));
        dialog.pack();
		dialog.setVisible(true);
    }
   
}
