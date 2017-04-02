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

	public boolean estGagnante(Noeud noeud) {
		/*
		 * Le jeu n'a pas de critère d'arret
		 * telle qu'une victoire ou non
		 * return (noeud.getEtat().getNbCoups() == 0);
		 */
		return false;
	}

	public void ajouterBruit(double x) {
		this.lengthStep += x;
	}

	@Override
	public boolean equals(Object obj) {
		ActionTrap trap = (ActionTrap)obj;
		return (trap.lengthStep == lengthStep);
	}

	@Override
	public String toString() {
		return "pas = " + lengthStep;
	}

	
}
