package ihm.jeu;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controleur.Controleur;
import metier.*;


public class PanelPlateau extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener
{
    private Controleur ctrl;

	private int[]   taillePlateau;
	private double  zoomFactor = 1;
    private boolean dragger;
    private boolean released;
    private double  xOffset = 0;
    private double  yOffset = 0;
    private int     xDiff;
    private int     yDiff;
    private Point   startPoint;

	private PanelImage panelImage;

    public PanelPlateau(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setLayout(null);

		this.taillePlateau = this.ctrl.getTaillePlateau();
		this.panelImage = new PanelImage(ctrl, this.taillePlateau);
		this.panelImage.setBounds(0, 0, this.taillePlateau[0], this.taillePlateau[1]);

		this.add(this.panelImage);

		this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

	public void majIHM()
	{
		if (dragger) 
		{
			this.panelImage.setBounds( (int) (xOffset + xDiff ), 
			                           (int) (yOffset + yDiff ),
			                           (int) (this.taillePlateau[0] * zoomFactor), 
									   (int) (this.taillePlateau[1] * zoomFactor) );

			if (released) 
			{
				xOffset += xDiff;
				yOffset += yDiff;
				dragger = false;
			}
		}
	}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
	{
        if (e.getWheelRotation() < 0) 
		{
            zoomFactor *= 1.1;
			this.panelImage.majZoom(zoomFactor);
        }

        if (e.getWheelRotation() > 0) 
		{
            zoomFactor /= 1.1;
			this.panelImage.majZoom(zoomFactor);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) 
	{
		if (SwingUtilities.isRightMouseButton(e))
		{
			Point curPoint = e.getLocationOnScreen();
			xDiff = curPoint.x - startPoint.x;
			yDiff = curPoint.y - startPoint.y;

			dragger = true;
			majIHM();
		}
    }

	public void mouseClicked(MouseEvent e) 
	{
		if (SwingUtilities.isLeftMouseButton(e))
		{
			Point p = new Point( (int) ((e.getX() - xOffset) * (1 / zoomFactor)), 
			                     (int) ((e.getY() - yOffset) * (1 / zoomFactor)) );

			this.panelImage.checkArete(p);
		}
	}

    public void mousePressed(MouseEvent e) 
	{
		if (SwingUtilities.isRightMouseButton(e))
		{
			released = false;
			startPoint = MouseInfo.getPointerInfo().getLocation();
		}
    }

    public void mouseReleased(MouseEvent e) 
	{
		if (SwingUtilities.isRightMouseButton(e))
		{
        	released = true;
        	majIHM();
		}
    }

	public void mouseMoved  (MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
}
