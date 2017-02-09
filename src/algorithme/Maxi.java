package algorithme;

import arbre.Noeud;

public class Maxi implements FormuleSelection{

	public Noeud selectionner(Noeud noeud) {
		int k = 0;
		double maxi = Integer.MIN_VALUE;
		int indice = 0;
		double value = 0;
		int enfants = noeud.retournerNbEnfant();
		while(k < enfants ) {
			value = noeud.retournerEnfant(k).retournerNbVictoire();
			if( value > maxi) {
				maxi = value;
				indice = k;
			}
			k++;
		}
		return noeud.setAction( noeud.retournerEnfant(indice).getAction() );
	}

}
