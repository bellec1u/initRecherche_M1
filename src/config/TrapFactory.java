package config;


import main.Main;
import jeu.trapProblem.EtatTrap;
import jeu.trapProblem.NoeudTrap;
import algorithme.formule.FormuleSelection;
import arbre.Etat;
import arbre.Noeud;
import arbre.Etat.FinDePartie;

public class TrapFactory implements GameFactory {

	public Etat getEtat(int joueur) {
		return new EtatTrap();
	}

	public Noeud getNoeud(Etat etat) {
		Noeud noeud = new NoeudTrap(etat);
		noeud.setInitialJoueur(etat.getJoueur());
		return noeud;
	}

	public void jouer(long temps, FormuleSelection strategie) {
		FinDePartie fin = FinDePartie.NON;

		// initialisation
		Etat etat = null;
		
		int joueur = Etat.HUMAIN;
		etat = getEtat(joueur);
		
		System.out.println("Temps de réflexion de l'ordinateur : " + (temps / 1000.0) + "s");
		

		// boucle de jeu
		do {
			etat.afficherJeu();
			if (etat.getJoueur() == Etat.HUMAIN) {
				// tour de l'humain
				Main.ordijoue_mcts(etat, temps, strategie);
			} else {
				// tour de l'ordinateur
				Main.ordijoue_mcts(etat, temps, strategie);
			}
			fin = etat.testFin();
			//System.out.println("TrapFactory do while");
		} while (fin == FinDePartie.NON);

		etat.afficherJeu();

		System.out.println("");
		System.out.println("");
		System.out.println("Fin de partie.");
		
//		if (fin == FinDePartie.ORDI_GAGNE) {
//			System.out.println("** L'ordinateur a gagné **");
//		} else if (fin == FinDePartie.MATCHNUL) {
//			System.out.println("** Match nul ! **");
//		} else {
//			System.out.println("** BRAVO, l'ordinateur a perdu **");
//		}
	}

}
