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

	private double lengthStep; // en cm

	public ActionTrap(double ls) {
		this.lengthStep = ls;
	}

	public double getStep() {
		return this.lengthStep;
	}

	/* (non-Javadoc)
	 * @see arbre.Action#estGagnante(arbre.Noeud)
	 */
	public boolean estGagnante(Noeud noeud) {
		//System.out.println("ici. "+noeud.getEtat().getNbCoups());
		return (noeud.getEtat().getNbCoups() == 0);
	}

	public void ajouterBruit(double x) {
		this.lengthStep += x;
	}

}
