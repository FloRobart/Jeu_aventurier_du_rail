package metier;

import java.util.ArrayList;

public class Pioche<T> 
{
	private ArrayList<T> liste;

	public Pioche()
	{
		this.liste = new ArrayList<T>();
	}

	public void add(T t)
	{
		this.liste.add(t);
	}

	public T get(int i)
	{
		return this.liste.get(i);
	}

	public void remove(int i)
	{
		this.liste.remove(i);
	}

	public int size()
	{
		return this.liste.size();
	}

	public void shuffle()
	{
		java.util.Collections.shuffle(this.liste);
	}
}