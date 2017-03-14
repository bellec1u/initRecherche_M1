/**
 * 
 */
package algorithme.formule;

import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 12, 2017
 */
public class MaxiRobuste implements FormuleSelection{
	
	public Noeud selectionner(Noeud noeud) {
		double min = Double.NEGATIVE_INFINITY;
		double bValeur = 0.0;
		
		int best = 0;
		Noeud enfant = null;
		
		for( int i = 0 ; i < noeud.retournerNbEnfant() ; i++ ) {
			enfant = noeud.retournerEnfant(i);
			bValeur = ( enfant.rapportVictoireSimulation() );
			if ( bValeur > min ) {
				min = bValeur;
				best = i;
			}
		}
		return noeud.retournerEnfant(best);
	}
}
