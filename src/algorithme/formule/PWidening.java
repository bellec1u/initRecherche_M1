/**
 * 
 */
package algorithme.formule;

import java.util.LinkedList;
import java.util.List;

import algorithme.adaptateur.AdaptateurContinue;
import arbre.Action;
import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 15, 2017
 */
public class PWidening implements FormuleSelection{

	private final double C = 2.44;
	private double alpha = 0.666; // ]O,1[

	public Noeud selectionner(Noeud noeud) {
		noeud.visiter(0.0); // nbVisits
		int t = noeud.retournerNbSimulation();
		int k = (int)(C * Math.pow(t, alpha));
		/*
		 * On va maintenant échantillonner
		 * le noeud avec les k prochaines Actions possibles
		 */
		Noeud n = new AdaptateurContinue(noeud);
		Noeud adapt = new AdaptateurContinue(n, k);
		List<Action> actions = new LinkedList<Action>(adapt.actionsPossible());
		List<Action> mem = new LinkedList<Action>(actions);

		Noeud enfant = null;
		int best = 0;
		double min = Double.NEGATIVE_INFINITY;
		double bValeur = 0.0;
		for(int i = 0 ; i < actions.size() ; i++) {
			enfant = adapt.ajouterEnfant(actions.get(i));
			int nb = 0;
			for ( int l = 0 ; l < t ; l++ ) {
				nb += 1;
			}

			if ( nb == 0 ) {
				bValeur = Integer.MAX_VALUE;
			} else {
				bValeur = ( enfant.resultat() / (nb + 1));
				bValeur += k * Math.sqrt( Math.log( t ) / (nb + 1));
			}
			
			if ( bValeur > min ) {
				min = bValeur;
				best = i;
			}
		}
		System.out.println("noeud enfant " + noeud.retournerNbEnfant());
		System.out.println("adapt enfant " + adapt.retournerNbEnfant());
		return noeud.ajouterEnfant( mem.get(best) );
	}

}
