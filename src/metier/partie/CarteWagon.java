package metier.partie;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class CarteWagon implements Serializable
{
    private Color         couleur;
    private transient BufferedImage imageVerso;
    private transient BufferedImage imageRecto;
    private boolean       joker;

    public CarteWagon(Color couleur, BufferedImage imageVerso, BufferedImage imageRecto)
    {
        if(couleur == null)
            this.joker = true;
        else
        {
            this.couleur    = couleur;
            this.joker = false;
        }

        this.imageVerso = imageVerso;
        this.imageRecto = imageRecto;
    }

    public Color getCouleur() { return this.couleur; }
    public BufferedImage getImageVerso() { return this.imageVerso; }
    public BufferedImage getImageRecto() { return this.imageRecto; }
    public boolean isJoker() { return this.joker; }

    public void setCouleur(Color couleur) { this.couleur = couleur; }
    public void setImageVerso(BufferedImage imageVerso) { this.imageVerso = imageVerso; }
    public void setImageRecto(BufferedImage imageVerso) { this.imageVerso = imageVerso; }
    public void setJoker(boolean joker) { this.joker = joker; }

	private void writeObject(ObjectOutputStream out) throws IOException 
	{
		ByteArrayOutputStream baos;
		out.defaultWriteObject();

		baos = new ByteArrayOutputStream();
		ImageIO.write(imageVerso, "png", baos);
		out.writeObject(baos.toByteArray());

		baos = new ByteArrayOutputStream();
		ImageIO.write(imageRecto, "png", baos);
		out.writeObject(baos.toByteArray());


	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
	{
		in.defaultReadObject();
		imageVerso    = ImageIO.read(new ByteArrayInputStream((byte[]) in.readObject()));
		imageRecto   = ImageIO.read(new ByteArrayInputStream((byte[]) in.readObject()));

	}
}
