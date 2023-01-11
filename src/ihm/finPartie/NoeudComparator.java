package ihm.finPartie;

import java.util.Comparator;
import java.util.HashMap;

import metier.Noeud;

public class NoeudComparator implements Comparator<Noeud> {
    private HashMap<Noeud, Integer> distances;

    public NoeudComparator(HashMap<Noeud, Integer> distances) {
        this.distances = distances;
    }

    @Override
    public int compare(Noeud n1, Noeud n2) {
        return distances.get(n1) - distances.get(n2);
    }
}