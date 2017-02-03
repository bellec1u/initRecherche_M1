package main;

import java.util.Scanner;

import config.Configuration;

import puissance4.EtatP4;
import puissance4.NoeudP4;
import algorithme.FormuleSelection;
import algorithme.Mcts;
import algorithme.Uct;
import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;
import arbre.Noeud;


/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public class Main {

	public static void main(String[] args) {

		// verification des donnees en parametre
		if (args.length != 1) {
			System.err.println("#usage : ./jeu <strategie> avec strategie = r (robuste) ou m (maxi)");
			return;
		}

		// recuperation des donnees
		boolean robusteOuMaxi = (args[0].equals("r")) ? true : false;

		Action coup;
		FinDePartie fin = FinDePartie.NON;

		// initialisation
		Etat etat = new EtatP4();

		// choix j1
		boolean correct = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Qui commence ? (0 : humain, 1 : ordinateur)");
			String res = sc.nextLine();
			if (res.equals("0") || res.equals("1")) {
				correct = true;
				etat.setJoueur(Integer.parseInt(res));
			} else {
				System.out.println("Entrées valides : 0 ou 1 !");
			}
		} while (!correct);

		System.out.println("Temps de réflexion de l'ordinateur : " + Configuration.getInstance().getTemps());

		// boucle de jeu
		do {
			System.out.println(" ");
			etat.afficherJeu();
			if (etat.getJoueur() == 0) {
				// tour de l'humain
				do {
					coup = etat.demanderAction();
				} while(!etat.jouerAction(coup));
			} else {
				// tour de l'ordinateur
				ordijoue_mcts(etat, Configuration.getInstance().getTemps(), robusteOuMaxi);
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

	private static void ordijoue_mcts(Etat etat, int temps, boolean strategie) {
		long tic, toc;

		// Créer l'arbre de recherche
		Noeud racine = new NoeudP4(etat);

		FormuleSelection uct = new Uct();
		Mcts mcts = new Mcts( uct ); // On execute l'algorithme
		
		// pre rempli déjà l'arbre

		// S'il y  a plusieurs fils alors on execute l'algo MCTS UCT
		int iter = 0;
		tic = System.currentTimeMillis();
		do {
			/*
		    	L'algo se decompose en 4 étapes :
		    	- Selection à partir de l'etat du meileur fils
		    	- Developpement d'un Noeud fils choisit aléatoirement (et non déjà développé)
		    	- Simulation de la fin de la partie avec une marche aléatoire
		    	- Mise à jours des valeurs des Noeuds dans l'arbre, on remonte la valeur de récompense
		    	du Noeud terminal à la racine.
			 */
			racine = mcts.executer(racine);
			toc = System.currentTimeMillis();
			iter++;
		} while (toc < (tic + temps));
		System.out.println("Itérations effectuées : " + iter);
		
		// fin de l'algorithme
		racine.afficherStatistiques();
		
		// On choisit la bonne strategie demandée par l'utilisateur
		/*if (strategie) {
			// robuste
			racine = (NoeudP4) racine.robuste();
		} else {
			// maxi
			racine = (NoeudP4) racine.maxi();
		}*/
		racine.robuste();
		// Jouer le meilleur premier coup
		Noeud best = uct.selectionner(racine);
		for (int i = 0; i < racine.retournerNbEnfant(); i++) {
			System.out.println(racine.retournerEnfant(i).getAction().getColonne());
			racine.retournerEnfant(i).afficherStatistiques();
		}
		etat.jouerAction(best.getAction());
	}

}