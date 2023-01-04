package metier;

import java.awt.Color;

public class Noeud 
{
	private static int nbNoeud = 0;

	private int    id;
	private String nom;
	private int    x;
	private int    y;
	private int    xNom;
	private int    yNom;
	private Color  couleur;

	public Noeud(String nom, int x, int y, int xNom, int yNom, Color c)
	{
		this.id      = ++Noeud.nbNoeud;
		if (nom.length() == 0)
			this.nom = "Noeud #" + this.id;
		else
			this.nom     = nom;
		this.x       = x;
		this.y       = y;
		this.xNom    = xNom;
		this.yNom    = yNom;
		this.couleur = c;
	}

	public int    getId     () { return this.id;      }
	public String getNom    () { return this.nom;     }
	public int    getX      () { return this.x;       }
	public int    getY      () { return this.y;       }
	public int    getXNom   () { return this.xNom;    }
	public int    getYNom   () { return this.yNom;    }
	public Color  getCouleur() { return this.couleur; }

	public void setNom(String nom) 
	{ 
		this.nom = nom; 
	}
	public void setXY(int x, int y) 
	{ 
		this.x = x; 
		this.y = y; 
	}
	public void setXYNom(int xNom, int yNom) 
	{ 
		this.xNom = xNom; 
		this.yNom = yNom; 
	}
	public void setCouleur(Color c) 
	{ 
		this.couleur = c; 
	}
	
	public static void reinitialiserId()
	{
		Noeud.nbNoeud = 0;
	}

	public String toString()
	{
		return this.nom + " (" + this.x + ", " + this.y + ")";
	}
}
