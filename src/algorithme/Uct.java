/**
 * 
 */
package algorithme;

import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public class Uct implements FormuleSelection{

	private final double parametreDExploration = Math.sqrt(2.0);
	
	@Override
	public Noeud selectionner(Noeud noeud) {
		double min = Double.MIN_VALUE;
		double bValeur = 0.0;
		
		Noeud meilleur = noeud;
		Noeud enfant = null;
		
		for( int i = 0 ; i < noeud.retournerNbEnfant() ; i++ ) {
			enfant = noeud.retournerEnfant(i);
			bValeur = ( enfant.rapportVictoireSimulation() ) * Math.pow(-1.0, enfant.autreJoueur() );
			bValeur += parametreDExploration * Math.sqrt( Math.log( noeud.retournerNbSimulation() ) / enfant.retournerNbSimulation());
			
			if ( bValeur > min ) {
				min = bValeur;
				meilleur = noeud.retournerEnfant(i);
			}
		}
		
		return meilleur;
	}

}
