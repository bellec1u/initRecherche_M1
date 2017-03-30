package algorithme.adaptateur;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import arbre.Action;
import arbre.Noeud;
import jeu.trapProblem.ActionTrap;
import jeu.trapProblem.NoeudTrap;

public class AdaptateurContinue extends AbstractAdaptateur {

	private int nbActions;
	
	public AdaptateurContinue(Noeud n) {
		super(n, 100);
		this.nbActions = this.echantillonage;
	}

	public List<Action> actionsPossible() {
		// on prend l'intervalle des actions
		// element 1 = borne min
		// element 2 = borne max
		List<Action> la = this.noeud.actionsPossible();
		int min = (int) ((ActionTrap) la.get(0)).getStep();
		int max = (int) ((ActionTrap) la.get(1)).getStep();

		//la = this.getListActionAlea(min, max);
		la = this.getListActionUniforme(min, max);
				
		return la;
	}
	
	public boolean resteAction() {
		return (/*(this.noeud.getEtat().getNbCoups() != 0) &&*/ (this.nbActions != 0) );
	}
	
	public Noeud ajouterEnfant(Action action) {
		this.nbActions--;
		return new AdaptateurContinue( this.noeud.ajouterEnfant(action) );
	}

	/**
	 * alea qui suit la courbe de gausse (loi normale)
	 */
	protected List<Action> getListActionAlea(int min, int max) {
		// params de la loi normale
		double mu = (max + min) / 2;
		double sigma = 7.0;

		// retourne une liste d'actions choisi aléatoirement dans [minStep; maxStep]
		List<Action> actions = new LinkedList<Action>();
		// nb actions que l'ont prend
		Random r = new Random();
		for ( int i = 0; i < this.echantillonage; i++ ) {
			//double step = min + (max - min) * r.nextGaussian();
			double step = r.nextGaussian() * sigma + mu;
			actions.add( new ActionTrap(step) );
		}

		return actions;
	}

	protected List<Action> getListActionUniforme(int min, int max) {
		// retourne une liste d'actions choisi de maniere uniforme
		List<Action> actions = new LinkedList<Action>();
		// on ne prend pas le pas minimal mais comme ca on prend le + grand
		double i = min + ((max - min + 0.0) / this.echantillonage);
		while (i < max) {
			actions.add( new ActionTrap(i) );
			i = i + ((max - min + 0.0) / this.echantillonage);
		}

		return actions;
	}

}
