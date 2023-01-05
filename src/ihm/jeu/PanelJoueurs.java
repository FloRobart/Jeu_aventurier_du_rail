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

		this.setBackground(new Color(68, 71, 90));
        this.setSize(500, 200);

        

        /*panel de chaque joueurs */
        this.tabPanels = new JPanel[3];
        this.tabBoutons = new JButton[this.tabPanels.length];

        /*panel joueurs */
        this.panelJoueurs = new JPanel();
        this.panelJoueurs.setBackground(new Color(40, 42, 54));
        this.panelJoueurs.setLayout(new GridLayout(this.tabPanels.length, 1, 0,1));

        for(int cpt=0; cpt< this.tabPanels.length; cpt++)
        {
            tabPanels[cpt] = new JPanel();
            tabPanels[cpt].setBackground(new Color(68, 71, 90));
            tabPanels[cpt].setLayout(new BorderLayout());

            JLabel lblNomJoueur = new JLabel("nom " + (cpt+1));
                   lblNomJoueur.setForeground(Color.WHITE);
            JLabel lblScoreJoueur = new JLabel("score " + (cpt+1));
                   lblScoreJoueur.setForeground(Color.WHITE);

            this.tabBoutons[cpt] = new JButton("", new ImageIcon("./donnees/images/IconJoueur.png"));
            this.tabBoutons[cpt].setBorderPainted(false);
            this.tabBoutons[cpt].setFocusPainted(false);
            this.tabBoutons[cpt].setContentAreaFilled(false);
            
            tabPanels[cpt].add(lblNomJoueur, BorderLayout.NORTH);
            tabPanels[cpt].add(this.tabBoutons[cpt], BorderLayout.WEST);
            tabPanels[cpt].add(lblScoreJoueur, BorderLayout.CENTER);

            this.panelJoueurs.add(tabPanels[cpt]);
        }

        /*JScrollPane */
        this.scrollJoueurs = new JScrollPane(panelJoueurs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollJoueurs.getVerticalScrollBar().setBackground(new Color(68, 71, 90));
        this.scrollJoueurs.setPreferredSize(new Dimension(200,200));
        this.scrollJoueurs.getVerticalScrollBar().setUnitIncrement(5);

        this.add(scrollJoueurs);

        /*afficher les infos d'un joueur en fonction du joueur selectionné */
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
                JDialog dialog = new JDialog();
                dialog.setSize(400,200);
                dialog.setLocation(200, 50);
                dialog.add(new PanelInfosJoueur(this.ctrl, (cpt+1)));
                dialog.pack();
                dialog.setVisible(true);
            }
        }

    }
   
}
