package config;

import java.util.Scanner;

import main.Main;

import algorithme.formule.FormuleSelection;
import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;
import arbre.Noeud;
import jeu.puissance4.EtatP4;
import jeu.puissance4.NoeudP4;

public class Puissance4Factory implements GameFactory{

	public Etat getEtat(int joueur) {
		return new EtatP4(joueur);
	}

	public Noeud getNoeud(Etat etat) {
		Noeud noeud = new NoeudP4(etat);
		noeud.setInitialJoueur(etat.getJoueur());
		return noeud;
	}

	public void jouer(long temps, FormuleSelection strategie) {
		FinDePartie fin = FinDePartie.NON;

		// initialisation
		Etat etat = null;
		Action coup = null;
		// choix j1
		boolean correct = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int joueur = Etat.HUMAIN;
		do {
			System.out.println("Qui commence ? (0 : humain, 1 : ordinateur)");
			String res = sc.nextLine();
			if (res.equals("0") || res.equals("1")) {
				correct = true;
				joueur = Integer.parseInt(res);
			} else {
				System.out.println("Entrées valides : 0 ou 1 !");
			}
		} while (!correct);

		etat = getEtat(joueur);
		
		System.out.println("Temps de réflexion de l'ordinateur : " + (temps / 1000) + "s");
		

		// boucle de jeu
		do {
			System.out.println(" ");
			etat.afficherJeu();
			if (etat.getJoueur() == Etat.HUMAIN) {
				// tour de l'humain
				do {
					coup = etat.demanderAction();
				} while(!etat.jouerAction(coup));
				//ordijoue_mcts(etat, TEMPS, robusteOuMaxi);
			} else {
				// tour de l'ordinateur
				Main.ordijoue_mcts(etat, temps, strategie);
			}
			fin = etat.testFin();
		} while (fin == FinDePartie.NON);

		System.out.println(" ");

		etat.afficherJeu();

		if (fin == FinDePartie.ORDI_GAGNE) {
			System.out.println("** L'ordinateur a gagné **");
		} else if (fin == FinDePartie.MATCHNUL) {
			System.out.println("** Match nul ! **");
		} else {
			System.out.println("** BRAVO, l'ordinateur a perdu **");
		}
	}

}
