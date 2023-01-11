package metier;

import java.awt.Color;
import java.io.Serializable;


public class Arete implements Serializable
{
	private Noeud  noeud1;
	private Noeud  noeud2;
	private int    distance;
	private Color  couleur1;
	private Color  couleur2;
	private Joueur proprietaire1;
	private Joueur proprietaire2;

	public Arete(Noeud noeud1, Noeud noeud2, int distance, Color c1, Color c2)
	{
		this.noeud1   = noeud1;
		this.noeud2   = noeud2;
		this.distance = distance;
		this.couleur1 = c1;
		this.couleur2 = c2;

		this.proprietaire1 = null;
		this.proprietaire2 = null;
	}

	public Noeud getNoeud1  ()       { return this.noeud1;   }
	public Noeud getNoeud2  ()       { return this.noeud2;   }
	public int   getDistance()       { return this.distance; }
	public Color getCouleur1()       { return this.couleur1; }
	public Color getCouleur2()       { return this.couleur2; }
	public Joueur getProprietaire1() { return this.proprietaire1; }
	
	public Joueur getProprietaire2() 
	{ 
		if (this.is2Sens())
		{
			return this.proprietaire2; 
		}
		else
		{
			return null;
		}
	}

	public void setNoeud1(Noeud n) 
	{ 
		this.noeud1 = n; 
	}

	public void setNoeud2(Noeud n) 
	{ 
		this.noeud2 = n; 
	}

	public void setdistance(int distance) 
	{ 
		this.distance = distance; 
	}
	public void setCouleur1(Color c) 
	{ 
		this.couleur1 = c; 
	}
	public void setCouleur2(Color c) 
	{ 
		this.couleur2 = c; 
	}

	public void setProprietaire1(Joueur j) 
	{ 
		if(this.proprietaire1 == null)
			this.proprietaire1 = j; 
	}
	public void setProprietaire2(Joueur j) 
	{ 
		if(this.is2Sens() && this.proprietaire2 == null)
			this.proprietaire2 = j; 
	}

	public String toString()
	{
		return this.noeud1.getNom() + " - " + this.noeud2.getNom();
	}

    public boolean is2Sens() 
	{
        return this.couleur2 != null;
    }
}
