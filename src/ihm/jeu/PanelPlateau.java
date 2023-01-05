package ihm.jeu;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.*;


public class PanelPlateau extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener
{
    private Controleur ctrl;

	private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;

    public PanelPlateau(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

	@Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = false;
        }

        if (dragger) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }

        }

        // All drawings go here
		int[] taillePlateau   = this.ctrl.getTaillePlateau();
		List<Noeud> lstNoeud = this.ctrl.getNoeuds();

		// affichage de la couleur de fond
		g2.setColor(this.ctrl.getCouleurPlateau());
		g2.fillRect(0, 0, taillePlateau[0], taillePlateau[1]);

		// affichage de l'image de fond
		BufferedImage img = this.ctrl.getImagePlateau();
		if (img != null && taillePlateau[0] > 0 && taillePlateau[1] > 0)
		{
			// on redimensionne l'image de fond pour qu'elle corresponde à la taille du plateau
			BufferedImage imgPlateau = new BufferedImage(taillePlateau[0], taillePlateau[1], img.getType());
			Graphics2D gImg = imgPlateau.createGraphics();
			gImg.drawImage(img, 0, 0, null);

			g2.drawImage(imgPlateau, 0, 0, this);
		}

		// définition de la police d'écriture
		Font police = this.ctrl.getPolicePlateau();
		if (police != null)
       		g2.setFont(police);

		// affichage des aretes
		for (Arete arete : this.ctrl.getAretes())
		{
			Point n1, n2;
			// on calcul l'angle de rotation à partir de la tangante de notre angle
			double angle = Math.atan((double) (arete.getNoeud2().getY() - arete.getNoeud1().getY()) / 
			                                  (arete.getNoeud2().getX() - arete.getNoeud1().getX())  );

			// si couleur 2 est null alors nous somme sur une arete simple
			// sinon nous sommes sur une arete double
			if (arete.getCouleur2() == null)
			{
				n1 = new Point(arete.getNoeud1().getX(), arete.getNoeud1().getY());
				n2 = new Point(arete.getNoeud2().getX(), arete.getNoeud2().getY());
				this.paintArete(g2, n1, n2, arete.getDistance(), arete.getCouleur1(), angle);
			}
			else
			{
				int adj = (int) (12 * Math.cos(angle + 1.57)); //90° = 1.57
				int opp = (int) (12 * Math.sin(angle + 1.57));

				n1 = new Point(arete.getNoeud1().getX() + adj, arete.getNoeud1().getY() + opp);
				n2 = new Point(arete.getNoeud2().getX() + adj, arete.getNoeud2().getY() + opp);
				this.paintArete(g2, n1, n2, arete.getDistance(), arete.getCouleur1(), angle);

				n1 = new Point(arete.getNoeud1().getX() - adj, arete.getNoeud1().getY() - opp);
				n2 = new Point(arete.getNoeud2().getX() - adj, arete.getNoeud2().getY() - opp);
				this.paintArete(g2, n1, n2, arete.getDistance(), arete.getCouleur2(), angle);
			}
		}
		
		// affichage des noeuds
		int i = 0;
        for (Noeud noeud : lstNoeud)
        {
			int midX = noeud.getX();
			int midY = noeud.getY();

			// contour du noeud
			g2.setColor(Color.BLACK);
			g2.fillOval(midX-12, midY-12, 24, 24);

			// noeud
            g2.setColor(noeud.getCouleur());
            g2.fillOval(midX-10, midY-10, 20, 20);

			// contour du nom du noeud
			FontMetrics metrics = g.getFontMetrics();
			int width = metrics.stringWidth(noeud.getNom());

			g2.setColor(Color.WHITE);
			g2.fillRect(midX + noeud.getXNom() - (noeud.getNom().length() * 3), 
			            midY + noeud.getYNom() - 7, 
			            width, 14);

			// nom du noeud	
			g2.setColor(Color.BLACK);
            g2.drawString(noeud.getNom(), 
			              midX + noeud.getXNom() - (noeud.getNom().length() * 3), 
			              midY + noeud.getYNom() + 4);

        }
    }

	private void paintArete(Graphics2D g2, Point n1, Point n2, int d, Color c, double angle)
	{
		for (double cpt = 1 ; cpt < d + 1 ; cpt++)
		{
			// on récupère les coordonnées centrale de notre tronçon
			int x = (int) (n1.getX() + ((n2.getX() - n1.getX()) * (cpt / (d + 1))));
			int y = (int) (n1.getY() + ((n2.getY() - n1.getY()) * (cpt / (d + 1))));

			// on créer notre tronçon sans son angle
			RoundRectangle2D fig1 = new RoundRectangle2D.Double(x - 25, y - 10, 50, 20, 25, 25);

			// on créer un autre tronçon mais avec son angle cette fois-ci
			AffineTransform t = new AffineTransform();
			t.rotate(angle, fig1.getX()+25, fig1.getY()+10);
			Shape fig2 = t.createTransformedShape(fig1);

			// on dessine notre troncon
			g2.setColor(c);
			g2.fill(fig2);
			g2.setColor(Color.BLACK);
			g2.draw(fig2);
		}
	}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        zoomer = true;

        //Zoom in
        if (e.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0) {
            zoomFactor /= 1.1;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getLocationOnScreen();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;

        dragger = true;
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        released = false;
        startPoint = MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        released = true;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
