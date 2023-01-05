package jeu;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


import controleur.Controleur;
import metier.Joueur;

public class PanelInfosJoueur extends JPanel
{
    private Controleur ctrl;
    private Joueur     joueur;

    private JLabel        lblNom;
    private JLabel        lblNbJeton;
    private JLabel        lblNbCartesWagon;
    private JLabel        lblNbCartesObjectif;
    private JLabel        lblIcon;
    private BufferedImage imgJoueur;

    private int nbWagons;
    private int nbCartesObjectif;
    private int nbCartesWagon;

    public PanelInfosJoueur()
    {
        //this.ctrl = ctrl;

        initComponents();
    }

    private void initComponents() 
    {
        //this.joueur = ctrl.getJoueurSelect();
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(40, 42, 54));

        this.lblNom              = new JLabel("nom");
        this.lblNom.setForeground(Color.WHITE);
        this.lblNbJeton          = new JLabel("jetons");
        this.lblNbJeton.setForeground(Color.WHITE);
        this.lblNbCartesWagon    = new JLabel("cartes wagon");
        this.lblNbCartesWagon.setForeground(Color.WHITE);
        this.lblNbCartesObjectif = new JLabel("cartes objectif");
        this.lblNbCartesObjectif.setForeground(Color.WHITE);
        this.lblIcon             = new JLabel();

        this.nbWagons            = 0;
        this.nbCartesObjectif    = 0;
        this.nbCartesWagon       = 0;

        JLabel lblInfos = new JLabel("Infos sur le Joueurs");
        lblInfos.setForeground(Color.WHITE);


        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new GridLayout(3,1));
        panelInfos.setBackground(new Color(68, 71, 90));

        JPanel panelIcon  = new JPanel();
        panelIcon.setBackground(new Color(68, 71, 90));

        /*this.lblNom.setText(joueur.getNom());

        
        if(joueur.getNbJetonsPosés() <= 1)
            this.lblNbJeton.setText(joueur.getNbJetonsPosés() + " wagon placé");
        else
            this.lblNbJeton.setText(joueur.getNbJetonsPosés() + " wagons placés");
        
        if(joueur.getNbCartesObjectif() <= 1)
            this.lblNbCartesObjectif.setText(joueur.getNbCartesObjectif() + " carte objectif");
        else
            this.lblNbCartesObjectif.setText(joueur.getNbCartesObjectif() + " cartes objectif");

        if(joueur.getNbCartesWagon() <= 1)
            this.lblNbCartesWagon.setText(joueur.getNbCartesWagon() + " carte wagon");
        else
            this.lblNbCartesWagon.setText(joueur.getNbCartesWagon() + " cartes wagon");*/
        
        try {
            this.imgJoueur = ImageIO.read(new File("./donnees/images/IconJoueur.png"));

           /*for (int x=0; x < imgJoueur.getWidth(); x++)
                for (int y=0; y < imgJoueur.getHeight(); y++)
                    if (imgJoueur.getRGB(x, y) == (new Color(237, 237, 237).getRGB()))
                        this.imgJoueur.setRGB(x, y, Color.BLACK.getRGB());*/
            
            this.lblIcon.setIcon(new ImageIcon(this.imgJoueur));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        GroupLayout layout = new GroupLayout(panelIcon);
        panelIcon.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblNom, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5,5,5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblNom))
                .addGap(5,5,5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblIcon, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                )
        );

        panelInfos.add(this.lblNbJeton);
        panelInfos.add(this.lblNbCartesWagon);
        panelInfos.add(this.lblNbCartesObjectif);

        this.add(lblInfos, BorderLayout.NORTH);
        this.add(panelIcon, BorderLayout.CENTER);
        this.add(panelInfos, BorderLayout.EAST);
    }

    /*public static void main(String[] args) 
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.add(new PanelInfosJoueur());
        frame.pack();
        frame.setVisible(true);
    }*/
   
    
}
