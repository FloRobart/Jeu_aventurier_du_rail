package metier;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarteObjectif implements Serializable
{
	private Noeud         noeud1;
	private Noeud         noeud2;
	private int           points;
	private transient BufferedImage imageRecto;

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
	private void writeObject(ObjectOutputStream out) throws IOException 
	{
		ByteArrayOutputStream baos;
		out.defaultWriteObject();

		baos = new ByteArrayOutputStream();
		ImageIO.write(imageRecto, "png", baos);
		out.writeObject(baos.toByteArray());

	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
	{
		in.defaultReadObject();
		imageRecto    = ImageIO.read(new ByteArrayInputStream((byte[]) in.readObject()));
	}
}
