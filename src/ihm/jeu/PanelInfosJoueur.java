package jeu;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.GroupLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

        this.lblNom              = new JLabel("nom");
        this.lblNbJeton          = new JLabel("jetons");
        this.lblNbCartesWagon    = new JLabel("cartes wagon");
        this.lblNbCartesObjectif = new JLabel("cartes objectif");
        this.lblIcon             = new JLabel();

        this.nbWagons            = 0;
        this.nbCartesObjectif    = 0;
        this.nbCartesWagon       = 0;

        JLabel lblInfos = new JLabel("Infos sur le Joueurs");


        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new GridLayout(3,1));
        JPanel panelIcon  = new JPanel();
        panelIcon.setLayout(new GridLayout(2,1));

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

            BufferedImage resizedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            this.imgJoueur = resizedImage;

            this.lblIcon.setIcon(new ImageIcon(this.imgJoueur));

            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        GroupLayout layout = new GroupLayout(panelIcon);
        panelIcon.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblInfos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblNom, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblIcon, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))

            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblInfos, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblNom))
                .addGap(18,18,18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblIcon, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                
                )
        );

        panelInfos.add(this.lblNbJeton);
        panelInfos.add(this.lblNbCartesWagon);
        panelInfos.add(this.lblNbCartesObjectif);

        this.add(panelIcon, BorderLayout.WEST);
        this.add(panelInfos, BorderLayout.EAST);
    }

    private BufferedImage resize(BufferedImage img, int x, int y) 
    {
        Image tmp = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, x, y, null);
        g2d.dispose();
        return resized;
    }

   
    
}
