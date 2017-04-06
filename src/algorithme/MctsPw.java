package algorithme;

import java.util.List;
import java.util.Random;

import algorithme.formule.FormuleSelection;
import arbre.Action;
import arbre.Noeud;

public class MctsPw implements Algorithme {

	private FormuleSelection formule;

	public MctsPw(FormuleSelection form) {
		formule = form;
	}

	public Noeud executer(Noeud noeud) {
		/* 3. On simule la fin de partie avec une démarche aléatoire */
		Noeud terminal = simuler(noeud);

		/* 4. On mets a jour le meilleur enfant */
		Noeud meilleurChoix = mettreAJour(noeud, terminal.resultat());

		// On retourne ensuite le noeud initial
		return meilleurChoix;
	}

	/* 2. */
	private Noeud developper(Noeud noeud) {
		return formule.selectionner(noeud);
	}

	/* 3. on stock plus simu*/
	private Noeud simuler(Noeud noeud) {
		Noeud simulation = noeud;
		
		while( !simulation.estTerminal() ) {
			simulation = developper(simulation);

		}
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
