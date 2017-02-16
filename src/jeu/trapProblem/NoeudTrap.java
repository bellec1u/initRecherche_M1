/**
 * 
 */
package jeu.trapProblem;

import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 16, 2017
 */
public class NoeudTrap implements Noeud{

	/* (non-Javadoc)
	 * @see arbre.Noeud#estTerminal()
	 */
	public boolean estTerminal() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#resteAction()
	 */
	public boolean resteAction() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#estRacine()
	 */
	public boolean estRacine() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#actionsPossible()
	 */
	public List<Action> actionsPossible() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#ajouterEnfant(arbre.Action)
	 */
	public Noeud ajouterEnfant(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#predecesseur()
	 */
	public Noeud predecesseur() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#retournerEnfant(int)
	 */
	public Noeud retournerEnfant(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#getAction()
	 */
	public Action getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#getEtat()
	 */
	public Etat getEtat() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#resultat()
	 */
	public double resultat() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#rapportVictoireSimulation()
	 */
	public double rapportVictoireSimulation() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#retournerNbVictoire()
	 */
	public double retournerNbVictoire() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#retournerNbEnfant()
	 */
	public int retournerNbEnfant() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#retournerNbSimulation()
	 */
	public int retournerNbSimulation() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#getInitialJoueur()
	 */
	public int getInitialJoueur() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#getMinOrMax()
	 */
	public int getMinOrMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#visiter(double)
	 */
	public void visiter(double recompense) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#ajouterVisite(double)
	 */
	public void ajouterVisite(double recompense) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#setStatistique(int, double)
	 */
	public void setStatistique(int s, double v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#setInitialJoueur(int)
	 */
	public void setInitialJoueur(int joueur) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#setMinOrMax(int)
	 */
	public void setMinOrMax(int minMax) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#afficherStatistiques()
	 */
	public void afficherStatistiques() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#setAction(arbre.Action)
	 */
	public Noeud setAction(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

}
