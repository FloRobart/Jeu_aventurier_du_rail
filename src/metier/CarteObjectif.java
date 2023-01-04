package metier;

import java.awt.image.BufferedImage;

public class CarteObjectif 
{
	private Noeud         noeud1;
	private Noeud         noeud2;
	private int           points;
	private BufferedImage imageRecto;

	public CarteObjectif(Noeud noeud1, Noeud noeud2, int points, BufferedImage imageRecto)
	{
		this.noeud1     = noeud1;
		this.noeud2     = noeud2;
		this.points     = points;
		this.imageRecto = imageRecto;
	}

	public Noeud getNoeud1() { return this.noeud1; }
	public Noeud getNoeud2() { return this.noeud2; }
	public int   getPoints() { return this.points; }
	public BufferedImage getImageRecto() { return this.imageRecto; }

	public void setNoeud1(Noeud noeud1) { this.noeud1 = noeud1; }
	public void setNoeud2(Noeud noeud2) { this.noeud2 = noeud2; }
	public void setPoints(int   points) { this.points = points; }
	public void setImageRecto(BufferedImage imageRecto) { this.imageRecto = imageRecto; }

	public String toString()
	{
		return this.noeud1.getNom() + " - " + this.noeud2.getNom();
	}
}
