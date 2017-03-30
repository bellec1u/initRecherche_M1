package config;


import java.util.Random;

import jeu.trapProblem.ActionTrap;
import jeu.trapProblem.EtatTrap;
import jeu.trapProblem.NoeudTrap;
import main.Main;
import algorithme.adaptateur.AdaptateurContinue;
import algorithme.formule.FormuleSelection;
import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;
import arbre.Noeud;

public class TrapFactory implements GameFactory {

	public Etat getEtat(int joueur) {
		return new EtatTrap();
	}

	public Noeud getNoeud(Etat etat) {
		Noeud noeud = new AdaptateurContinue( new NoeudTrap(etat) );
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
		etat.afficherJeu();
		do {
			// tour de l'humain
			System.out.println("####################################################");
			
			Main.ordijoue_mcts(etat, temps, strategie);
			etat.afficherJeu();

			etat.setJoueur(1 - etat.getJoueur());

			fin = etat.testFin();
			//System.out.println("TrapFactory do while");
		} while (fin == FinDePartie.NON);

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
