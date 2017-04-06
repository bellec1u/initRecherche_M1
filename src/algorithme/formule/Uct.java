/**
 * 
 */
package algorithme.formule;

import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public class Uct implements FormuleSelection{

	private final double C = Math.sqrt(2.0);
	
	public Noeud selectionner(Noeud noeud) {
		double min = Double.NEGATIVE_INFINITY;
		double bValeur = 0.0;
		
		int best = 0;
		Noeud enfant = null;

		for( int i = 0 ; i < noeud.retournerNbEnfant() ; i++ ) {
			enfant = noeud.retournerEnfant(i);
			bValeur = ( enfant.rapportVictoireSimulation() ) * Math.pow(-1.0, enfant.getMinOrMax() );
			bValeur += C * Math.sqrt( Math.log( noeud.retournerNbSimulation() ) / enfant.retournerNbSimulation());
			if ( bValeur > min ) {
				min = bValeur;
				best = i;
			}
		}
		return noeud.retournerEnfant(best);
	}

}
