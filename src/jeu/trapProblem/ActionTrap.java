/**
 * 
 */
package jeu.trapProblem;

import arbre.Action;
import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 16, 2017
 */
public class ActionTrap implements Action{

	private int lengthStep; // en cm
	
	public ActionTrap(int ls) {
		this.lengthStep = ls;
	}
	
	/* (non-Javadoc)
	 * @see arbre.Action#getLigne()
	 */
	public int getLigne() {
		System.err.println("Ne doit pas passer par : ActionTrap.getLigne()");
		System.exit(0);
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Action#getColonne()
	 */
	public int getColonne() {
		return this.lengthStep;
	}

	/* (non-Javadoc)
	 * @see arbre.Action#estGagnante(arbre.Noeud)
	 */
	public boolean estGagnante(Noeud noeud) {

		//System.out.println("ici. "+noeud.getEtat().getNbCoups());
		return (noeud.getEtat().getNbCoups() == 0);
	}

}
