package ihm.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import controleur.Controleur;
import metier.CarteObjectif;
import metier.Joueur;
import metier.partie.CarteWagon;

public class PanelMain extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private Joueur joueur;

	private JButton[]              tabIconWagon;
    private List<BufferedImage>    listImageWagon;

	public PanelMain(Controleur ctrl, Joueur joueur)
	{
		this.ctrl = ctrl;
		this.joueur = joueur;

		this.listImageWagon = new ArrayList<BufferedImage>();
        for(Color c : this.ctrl.getJoueur().getAlCouleurs())
        {
			if (c != null)
				this.listImageWagon.add(this.ctrl.getImagesRectoCouleur().get(
					this.ctrl.getCouleurs().indexOf(c) - 1));
			else
				this.listImageWagon.add(this.ctrl.getImageRectoLocomotive());
        }

		int taille = this.listImageWagon.size();
        this.tabIconWagon   = new JButton[taille];

		int i = 0;
        for(Color c : this.ctrl.getJoueur().getAlCouleurs())
        {
            String val = "X" + this.joueur.gethashMapCarteWagons().get(c);
            
            this.tabIconWagon[i] = new JButton();
            this.tabIconWagon[i].setIcon(new ImageIcon(creerCarte(this.listImageWagon.get(i), val)));
            this.tabIconWagon[i].setBorderPainted(false);
            this.tabIconWagon[i].setContentAreaFilled(false);
            this.tabIconWagon[i].setFocusPainted(false);
        
            this.add(this.tabIconWagon[i]);
			i++;
        } 

		for (JButton btn : this.tabIconWagon)
			btn.addActionListener(this);

		this.setVisible(true);
	}

	
	/**
     * Permet de retourner la carte et afficher le nombre de carte de cette couleur
     * @param bufferedImage image de la carte
     * @param lbl label contenant le nombre de carte de cette couleur
     * @return BufferedImage carte retourné avec le nombre de carte de cette couleur
     */
    private BufferedImage creerCarte(BufferedImage bufferedImage, String val) 
    {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int taille = Math.max(width +30, height);
        BufferedImage bi = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();

        g2d.rotate(1.57, width / 2, height / 2);
        g2d.drawImage(bufferedImage, (taille-width), (taille-height)/2-30, width, height, null);
        g2d.rotate((1.57*3), taille / 2, taille / 2);
        g2d.drawString(val, 110, 200);

        return bi;
    }

	public void actionPerformed(ActionEvent e) 
	{
		if (this.ctrl.peuxJouer())
			for (int i = 0; i < this.tabIconWagon.length; i++)
				if (e.getSource() == this.tabIconWagon[i])
					this.ctrl.prendreArete(i);
	}
}
