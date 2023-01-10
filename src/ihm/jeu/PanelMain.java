package ihm.jeu;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur;
import metier.Joueur;


public class PanelMain extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private Joueur     joueur;

	private JButton[]           tabIconWagon;
    private List<BufferedImage> listImageWagon;
	private JPanel              panelBtn;
	private JScrollPane         spBtn;

	public PanelMain(Controleur ctrl, Joueur joueur)
	{
		this.ctrl   = ctrl;
		this.joueur = joueur;

		this.panelBtn = new JPanel();

		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		int largeur = (int)tailleEcran.getWidth();
		this.spBtn = new JScrollPane(panelBtn, JScrollPane.VERTICAL_SCROLLBAR_NEVER, 
		                                              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.spBtn.setPreferredSize(new Dimension((int) (largeur * ((double) 75/100)), this.ctrl.getImageVersoCouleur().getWidth()+50));
	
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
            this.tabIconWagon[i].setIcon(new ImageIcon(this.creerCarte(this.listImageWagon.get(i), val)));
            this.tabIconWagon[i].setBorderPainted(false);
            this.tabIconWagon[i].setContentAreaFilled(false);
            this.tabIconWagon[i].setFocusPainted(false);

			if(!this.ctrl.getEnTrainDePiocher())
				this.tabIconWagon[i].addActionListener(this);
        
			this.panelBtn.add(this.tabIconWagon[i]);
			i++;
        }

		this.add(spBtn);

		this.setVisible(true);
		this.appliquerTheme();
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

        
        //g2d.rotate(1.57, width / 2, height / 2);
        g2d.drawImage(bufferedImage, (taille-width), (taille-height)/2-30, width, height, null);
        g2d.setColor(this.ctrl.getTheme().get("labels").get(0));
        g2d.drawString(val, (taille-width) + (width/2)-((val.length()+2)/2), ((taille-height)/2-30)+height+20);
        g2d.rotate(1.57, taille / 2, taille / 2);
        

        return bi;
    }


	public void actionPerformed(ActionEvent e) 
	{
		if (this.ctrl.peuxJouer())
			for (int i = 0; i < this.tabIconWagon.length; i++)
				if (e.getSource() == this.tabIconWagon[i])
					this.ctrl.prendreArete(i);
	}


    public void appliquerTheme()
    {
        Color labelForeColor = this.ctrl.getTheme().get("labels").get(0);

        
        this.spBtn.getHorizontalScrollBar().setBackground(this.ctrl.getTheme().get("background").get(0));

        int i = 0;
        for(Color c : this.ctrl.getJoueur().getAlCouleurs())
        {
            String val = "X " + this.joueur.gethashMapCarteWagons().get(c);

            this.tabIconWagon[i].setIcon(new ImageIcon(this.creerCarte(this.listImageWagon.get(i), val)));
        
            this.panelBtn.add(this.tabIconWagon[i]);
            i++;
        }

		this.spBtn.setBackground(this.ctrl.getTheme().get("background").get(0));
		this.panelBtn.setBackground(this.ctrl.getTheme().get("background").get(0));
    }
}
