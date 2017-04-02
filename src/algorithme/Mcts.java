/**
 * 
 */
package algorithme;

import java.util.List;
import java.util.Random;

import algorithme.formule.FormuleSelection;
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
		Noeud terminal = simuler(meilleurChoix);

		/* 4. On mets a jour le meilleur enfant */
		meilleurChoix = mettreAJour(meilleurChoix, terminal.resultat());

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
		/*	J'ai commenté pour éviter que l'algo parcourt
		 * ce truc tant que l'erreur n'est pas résolue
		 * for (Action a : actions) {
			if ( a.estGagnante( noeud ) ) {
				return noeud.ajouterEnfant(a);
			}
		}*/
		/*
		 * Si tu décommentes tu verras qu'on à 2 comme résultats
		 * ce qui est absurde 
		 * un Noeud dans notre algo est un NoeudTrap et pas un adaptateurContinue
		 * System.out.println("actions possibles : " + actions.size());
		 */
		return noeud.ajouterEnfant(actions.get( random.nextInt( actions.size() ) ));
	}

	/* 3. on stock plus simu*/
	private Noeud simuler(Noeud noeud) {
		Noeud simulation = noeud;
		
		while( !simulation.estTerminal() ) {
			simulation = developper(simulation);

		}
		noeud.supprimerEnfants();
		return simulation;
	}

	/* 4. */
	private Noeud mettreAJour(Noeud noeud, double recompense) {
		
		while( !noeud.estRacine() ) {
			noeud.visiter(recompense);
			noeud = noeud.predecesseur();
		}
		noeud.visiter(recompense);
		return noeud;
	}

}
