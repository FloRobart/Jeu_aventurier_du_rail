package ihm.attente;

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
import ihm.jeu.PanelImage;

public class PanelPreviewMappe extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener
{
    private Controleur ctrl;
	private int[]      taillePlateau;


	// attributs pour le zoom
	private double facteurZoom    = 1;
	private double facteurZoomMax = 2;
	private double facteurZoomMin = 0.1;

	// attributs pour le drag
    private boolean cliqueGaucheDrag;
    private boolean estDrag;
    private double  xDecalage = 0;
    private double  yDecalage = 0;
    private int     xDiff;
    private int     yDiff;
    private Point   pDebutDrag;

	private PanelImage panelImage;

    public PanelPreviewMappe(Controleur ctrl)
    {
        this.ctrl = ctrl;
		this.taillePlateau = this.ctrl.getTaillePlateau();

		this.setLayout(null);

		// Création des composants
		this.panelImage = new PanelImage(ctrl, this.taillePlateau);


		// Ajout des composants
		this.add(this.panelImage);


		/* Listener sourie */
		this.addMouseWheelListener (this);
        this.addMouseMotionListener(this);
        this.addMouseListener      (this);
    }


	public void setMappe()
	{
		this.panelImage = new PanelImage(this.ctrl, this.ctrl.getTaillePlateau());
		this.removeAll();
		this.add(this.panelImage);
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
			this.centrer(this.getWidth(), this.getHeight());
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


		/* Ce panel */
		this.setForeground(labelForeColor);
		this.setBackground(background    );
    }
}
