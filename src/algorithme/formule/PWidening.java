/**
 * 
 */
package algorithme.formule;

import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 15, 2017
 */
public class PWidening implements FormuleSelection{

	private final double C = 2.44;
	private final double kUCB = Math.sqrt(2.0);
	private double alpha = 0.666; // ]O,1[
	
	public Noeud selectionner(Noeud noeud) {
		int k = (int)(C*Math.pow(noeud.retournerNbSimulation(), alpha));	
		Noeud enfant = null;
		int best = 0;
		double min = Double.NEGATIVE_INFINITY;
		double bValeur = 0.0;
		for(int i = 0 ; i < k ; i++) {
			enfant = noeud.retournerEnfant(i);
			bValeur = ( enfant.rapportVictoireSimulation() ) * Math.pow(-1.0, enfant.getMinOrMax() );
			bValeur += kUCB * Math.sqrt( Math.log( noeud.retournerNbSimulation() ) / enfant.retournerNbSimulation());
			if ( bValeur > min ) {
				min = bValeur;
				best = i;
			}
		}
		return noeud.retournerEnfant(best);
	}

}
