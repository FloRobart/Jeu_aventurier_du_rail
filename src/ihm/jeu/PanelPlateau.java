package ihm.jeu;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import controleur.Controleur;
import metier.CarteObjectif;

public class PanelPlateau extends JPanel implements ActionListener, MouseWheelListener, MouseListener, MouseMotionListener
{
    private Controleur ctrl;
	private int[]      taillePlateau;

	private JButton btnCentrer;

	// attributs pour le zoom
	private double facteurZoom    = 1;
	private double facteurZoomMax = 2;
	private double facteurZoomMin = 0.75;

	// attributs pour le drag
    private boolean cliqueGaucheDrag;
    private boolean estDrag;
    private double  xDecalage = 0;
    private double  yDecalage = 0;
    private int     xDiff;
    private int     yDiff;
    private Point   pDebutDrag;

	private PanelImage panelImage;

    public PanelPlateau(Controleur ctrl)
    {
        this.ctrl = ctrl;
		this.taillePlateau = this.ctrl.getTaillePlateau();

		this.setLayout(null);

		// Création des composants
		this.panelImage = new PanelImage(ctrl, this.taillePlateau);
		//this.panelImage.setBounds(0, 0, this.taillePlateau[0], this.taillePlateau[1]);

		this.btnCentrer = new JButton("Centrer Plateau");
		this.btnCentrer.setBounds(0, 0, 150, 30);

		// Ajout des composants
		this.add(this.panelImage);
		this.add(this.btnCentrer);

		// Ajout des listeners
		this.btnCentrer.addActionListener(this);

		this.addMouseWheelListener (this);
        this.addMouseMotionListener(this);
        this.addMouseListener      (this);

    }

	public void majIHM()
	{
		if (cliqueGaucheDrag) 
		{
			this.panelImage.setBounds( (int) (this.xDecalage + this.xDiff ), 
			                           (int) (this.yDecalage + this.yDiff ),
			                           (int) (this.taillePlateau[0] * this.facteurZoom), 
									   (int) (this.taillePlateau[1] * this.facteurZoom) );

			if (this.estDrag) 
			{
				this.xDecalage += this.xDiff;
				this.yDecalage += this.yDiff;
				this.cliqueGaucheDrag = false;
			}
		}
	}

	public void centrer(int largeur, int hauteur)
	{
		largeur = this.getWidth();
		hauteur = this.getHeight();
		

		// Calcul du facteur de zoom maximal
		double zoomLargeur = (double) largeur / this.taillePlateau[0];
		double zoomHauteur = (double) hauteur / this.taillePlateau[1];
		this.facteurZoom = Math.min(zoomLargeur, zoomHauteur);

		// Vérification des limites du zoom
		if (this.facteurZoom > this.facteurZoomMax)
			this.facteurZoomMax = this.facteurZoom + 1.0;
		
		if (this.facteurZoom < this.facteurZoomMin)
			this.facteurZoomMin = this.facteurZoom - 0.5;


		// Calcul du décalage pour centrer l'image
		this.xDecalage = (largeur - (this.taillePlateau[0] * this.facteurZoom)) / 2;
		this.yDecalage = (hauteur - (this.taillePlateau[1] * this.facteurZoom)) / 2;

		// Mise à jour de l'image
		this.panelImage.setBounds( (int)  this.xDecalage, (int) this.yDecalage,
		                           (int) (this.taillePlateau[0] * facteurZoom), 
								   (int) (this.taillePlateau[1] * facteurZoom) );
		this.panelImage.majZoom(this.facteurZoom);
	}

	public BufferedImage getImage()
	{
		return this.panelImage.getImage();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.centrer(this.getWidth(), this.getHeight());
	}

    public void mouseWheelMoved(MouseWheelEvent e) 
	{
        if (e.getWheelRotation() < 0 && this.facteurZoom * 1.1 < this.facteurZoomMax) 
		{
            this.facteurZoom *= 1.1;
			this.panelImage.majZoom(this.facteurZoom);
        }

        if (e.getWheelRotation() > 0 && this.facteurZoom / 1.1 > this.facteurZoomMin) 
		{
            this.facteurZoom /= 1.1;
			this.panelImage.majZoom(this.facteurZoom);
        }
    }

    public void mouseDragged(MouseEvent e) 
	{
		if (SwingUtilities.isRightMouseButton(e))
		{
			Point pointActu = e.getLocationOnScreen();

			this.xDiff = pointActu.x - this.pDebutDrag.x;
			this.yDiff = pointActu.y - this.pDebutDrag.y;

			this.cliqueGaucheDrag = true;
			majIHM();
		}
    }

	public void mouseClicked(MouseEvent e) 
	{
		if (SwingUtilities.isLeftMouseButton(e))
		{
			Point p = new Point( (int) ((e.getX() - this.xDecalage) * (1 / this.facteurZoom)), 
			                     (int) ((e.getY() - this.yDecalage) * (1 / this.facteurZoom)) );

			this.panelImage.checkArete(p);
		}
	}

    public void mousePressed(MouseEvent e) 
	{
		if (SwingUtilities.isRightMouseButton(e))
		{
			this.estDrag = false;
			this.pDebutDrag = MouseInfo.getPointerInfo().getLocation();
		}
    }

    public void mouseReleased(MouseEvent e) 
	{
		if (SwingUtilities.isRightMouseButton(e))
		{
        	this.estDrag = true;
        	majIHM();
		}
    }

	public void mouseMoved  (MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}


	/**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
		Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
		Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);


		/* Ce panel */
		this.setForeground(labelForeColor);
		this.setBackground(background    );

		/* Bouton pour centrer l'image */
		this.btnCentrer.setForeground(btnForeColor);
        this.btnCentrer.setBackground(btnBackColor);
		this.btnCentrer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

}
