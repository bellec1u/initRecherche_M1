/**
 * 
 */
package algorithme;

import java.util.List;
import java.util.Random;

import arbre.Action;
import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public class Mcts implements Algorithme{

	private FormuleSelection formule;
	
	private final Random random = new Random();

	public Mcts(FormuleSelection form) {
		formule = form;
	}

	public Noeud executer(Noeud noeud) {
		Noeud meilleurChoix = selectionner(noeud);

		/* 3. On simule la fin de partie avec une démarche aléatoire */
		meilleurChoix = simuler(meilleurChoix);

		/* 4. On mets a jour le meilleur enfant */
		meilleurChoix = mettreAJour(meilleurChoix);

		// On retourne ensuite le noeud initial
		return meilleurChoix;
	}

	/* 1. */
	private Noeud selectionner(Noeud noeud) {
		Noeud selection = noeud;

		while( !selection.estTerminal() ) {
			if(selection.resteAction()) {
				return developper(selection);
			} else {
				selection = formule.selectionner(selection);
			}
		}
		return selection;
	}

	/* 2. */
	private Noeud developper(Noeud noeud) {
		List<Action> actions = noeud.actionsPossible();
		for (Action a : actions) {
			if ( a.estGagnante( noeud.getEtat() ) ) {
				return noeud.ajouterEnfant(a);
			}
		}
		return noeud.ajouterEnfant(actions.get( random.nextInt( actions.size() ) ));
	}

	/* 3. */
	private Noeud simuler(Noeud noeud) {
		Noeud simulation = noeud;
		
		while( !simulation.estTerminal() ) {
			simulation = developper(simulation);
		}

		return simulation;
	}

	/* 4. */
	private Noeud mettreAJour(Noeud noeud) {
		double recompense = noeud.resultat();
		
		while( !noeud.estRacine() ) {
			noeud.visiter(recompense);
			noeud = noeud.predecesseur();
		}
		noeud.visiter(recompense);
		return noeud;
	}

}
