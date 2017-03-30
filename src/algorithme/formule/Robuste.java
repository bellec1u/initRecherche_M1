package algorithme.formule;

import arbre.Noeud;

public class Robuste implements FormuleSelection {

	public Noeud selectionner(Noeud noeud) {
		int k = 0;
		int maxi = Integer.MIN_VALUE;
		int indice = 0;
		int value = 0;
		int enfants = noeud.retournerNbEnfant();
		System.out.println(enfants);
		while(k < enfants ) {
			value = noeud.retournerEnfant(k).retournerNbSimulation();
			if (value > maxi) {
				maxi = value;
				indice = k;
			}
			k++;
		}

		return noeud.setAction( noeud.retournerEnfant(indice).getAction() );
	}

}
