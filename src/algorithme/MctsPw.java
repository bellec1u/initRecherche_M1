package algorithme;

import algorithme.formule.FormuleSelection;
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

	/* 3. */
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
			noeud.setStatistique(noeud.retournerNbSimulation(), 
					noeud.retournerNbVictoire() + recompense);
			noeud = noeud.predecesseur();
		}
		noeud.setStatistique(noeud.retournerNbSimulation(), 
				noeud.retournerNbVictoire() + recompense);
		return noeud;
	}

}
