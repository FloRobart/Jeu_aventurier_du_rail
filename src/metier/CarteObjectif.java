package metier;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarteObjectif implements Serializable
{
	private Noeud                   noeud1;
	private Noeud                   noeud2;
	private int                     points;
	private transient BufferedImage imageRecto;

	/* ==================== */
	/*     CONSTRUCTEUR     */
	/* ==================== */
	public CarteObjectif(Noeud noeud1, Noeud noeud2, int points, BufferedImage imageRecto)
	{
		this.noeud1     = noeud1;
		this.noeud2     = noeud2;
		this.points     = points;
		this.imageRecto = imageRecto;
	}

	/* ==================== */
	/*        GETTERS       */
	/* ==================== */
	public Noeud         getNoeud1    () { return this.noeud1;     }
	public Noeud         getNoeud2    () { return this.noeud2;     }
	public int           getPoints    () { return this.points;     }
	public BufferedImage getImageRecto() { return this.imageRecto; }

	/* ==================== */
	/*        SETTERS       */
	/* ==================== */
	public void setNoeud1    (Noeud         noeud1    ) { this.noeud1     = noeud1;     }
	public void setNoeud2    (Noeud         noeud2    ) { this.noeud2     = noeud2;     }
	public void setPoints    (int           points    ) { this.points     = points;     }
	public void setImageRecto(BufferedImage imageRecto) { this.imageRecto = imageRecto; }

	/* ==================================== */
	/*  METHODES DE VALIDATION D'OBJECTIFS  */
	/* ==================================== */

	// methode qui vérifie si l'ojectif à été rempli par le joueur rentré en paramètre
	public boolean estValide(Joueur j)
	{
		List<Arete> alAretes = j.getControleur().getAretes();

		// algorithme de parcours en profondeur du noeud 1 au noeud 2
		List<Noeud> alNoeudsVisites = new ArrayList<Noeud>();
		Stack<Noeud> pile = new Stack<Noeud>();
		pile.push(this.noeud1);

		while (!pile.empty())
		{
			Noeud n = pile.pop();

			if (n == this.noeud2) return true;

			alNoeudsVisites.add(n);
			
			for (Arete a : alAretes)
			{
				if (a.getNoeud1() == n && !alNoeudsVisites.contains(a.getNoeud2()) && 
				    (a.getProprietaire1() == j || a.getProprietaire2() == j))
					pile.push(a.getNoeud2());
					
				if (a.getNoeud2() == n && !alNoeudsVisites.contains(a.getNoeud1()) && 
				    (a.getProprietaire1() == j || a.getProprietaire2() == j))
					pile.push(a.getNoeud1());
			}
		}

		return false;
	}

	/* ================================ */
	/*  METHODES DE TRANSACTION RESEAU  */
	/* ================================ */
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

	/* ================= */
	/*  AUTRES METHODES  */
	/* ================= */
	public String toString()
	{
		return this.noeud1.getNom() + " - " + this.noeud2.getNom();
	}
}
