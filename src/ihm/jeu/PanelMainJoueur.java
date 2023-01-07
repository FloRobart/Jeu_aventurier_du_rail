package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


import controleur.Controleur;

public class PanelMainJoueur extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JPanel     panelInfo;
    private JLabel     lblNom;
    private JLabel     lblNbJeton;
    private JLabel     lblIcon;

    private JPanel              panelMainWagon;
    private JButton[]           tabIconWagon;
    private JLabel[]            tabLblWagon;
    private List<BufferedImage> listImageWagon;

    private JPanel     panelMainObjectif;
    private JButton    btnIconObjectif;
       

    public PanelMainJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);

        //initialisation des composants
        this.panelInfo  = new JPanel();
        this.lblNom     = new JLabel("  nom   ");
        this.lblNbJeton = new JLabel("  jetons restants   ");
        this.lblIcon    = new JLabel(new ImageIcon("./donnees/images/IconJoueur.png"), JLabel.LEFT);
        
        this.panelMainWagon = new JPanel();
        this.listImageWagon = this.ctrl.getImagesRectoCouleur(); // joueur.getCarteWagon();
        this.listImageWagon.add(this.ctrl.getImageRectoLocomotive());
        int taille = this.listImageWagon.size();
        this.tabIconWagon   = new JButton[taille];
        this.tabLblWagon    = new JLabel[taille];
        
        this.panelMainObjectif = new JPanel();
        this.btnIconObjectif   = new JButton();

        //panelInfo Joueur
        JPanel panelLbl = new JPanel();
        panelLbl.setBackground(null);
        panelLbl.setLayout(new GridLayout(2,1)); // à modifier en fonction du nombre d'infos à afficher
        this.panelInfo.setBackground(new Color(68, 71, 90));
        this.panelInfo.setLayout(new BorderLayout());
        this.lblNom.setForeground(Color.WHITE);
        this.lblNbJeton.setForeground(Color.WHITE);

        panelLbl.add(this.lblNom);
        panelLbl.add(this.lblNbJeton);

        this.panelInfo.add(this.lblIcon, BorderLayout.NORTH);
        this.panelInfo.add(panelLbl, BorderLayout.CENTER);

        //panelMainWagon
        this.panelMainWagon.setBackground(new Color(68, 71, 90));

        System.out.println(this.listImageWagon.size());

        for(int i = 0; i < taille; i++)
        {
            this.tabLblWagon[i]  = new JLabel();
            this.tabLblWagon[i].setText("X"+ i);

            this.tabIconWagon[i] = new JButton();
            this.tabIconWagon[i].setIcon(new ImageIcon(creerCarte(this.listImageWagon.get(i), this.tabLblWagon[i])));
            this.tabIconWagon[i].setBorderPainted(false);
            this.tabIconWagon[i].setContentAreaFilled(false);
            this.tabIconWagon[i].setFocusPainted(false);
        
            this.panelMainWagon.add(this.tabIconWagon[i]);
        }   

        //panelMainObjectif
        this.panelMainObjectif.setBackground(new Color(68, 71, 90));
        this.btnIconObjectif.setIcon(new ImageIcon(creerCarte(this.ctrl.getCarteObjectif().get(2).getImageRecto(), null)));
        this.btnIconObjectif.setBorderPainted(false);
        this.btnIconObjectif.setContentAreaFilled(false);
        this.btnIconObjectif.setFocusPainted(false);
        this.panelMainObjectif.add(this.btnIconObjectif);


        //ajout des composants
        this.add(this.panelInfo, BorderLayout.EAST);
        this.add(this.panelMainWagon, BorderLayout.CENTER);
        this.add(this.panelMainObjectif, BorderLayout.WEST);

		this.setVisible(true);

        this.btnIconObjectif.addActionListener(this);
    }

    /**
     * Permet de retourner la carte et afficher le nombre de carte de cette couleur
     * @param bufferedImage image de la carte
     * @param lbl label contenant le nombre de carte de cette couleur
     * @return BufferedImage carte retourné avec le nombre de carte de cette couleur
     */
    private BufferedImage creerCarte(BufferedImage bufferedImage, JLabel lbl) 
    {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int taille = Math.max(width + 30, height);
        BufferedImage bi = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();

        g2d.rotate(1.57, width / 2, height / 2);
        g2d.drawImage(bufferedImage, (taille-width)/2, (taille-height)/2-30, width, height, null);
        g2d.rotate((1.57*3), taille / 2, taille / 2);
        g2d.setColor(Color.WHITE);
        if(lbl != null)
            g2d.drawString(lbl.getText(), 50, 50);

        return bi;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnIconObjectif)
        {
            JDialog dialog = new JDialog();
            dialog.setSize(400,200);
            dialog.setLocation(200, 50);
            dialog.add(new PanelObjectifs(this.ctrl));
            dialog.pack();
            dialog.setVisible(true);
        }
        
    }


    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        // TODO A compléter
    }
}
