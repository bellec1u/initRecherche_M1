package main;

import java.util.Scanner;

import algorithme.Mcts;
import algorithme.formule.FormuleSelection;
import algorithme.formule.Maxi;
import algorithme.formule.Robuste;
import algorithme.formule.Uct;
import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;
import arbre.Noeud;
import config.Configuration;
import config.GameFactory;
import config.Puissance4Factory;


/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public class Main {

	private final static GameFactory GAME = new Puissance4Factory();
	private final static long TEMPS = Configuration.getInstance().getTemps();
	
	public static void main(String[] args) {
		jouer(args);
	}

	public static void jouer(String[] args) {
		// verification des donnees en parametre
		if (args.length != 1) {
			System.err.println("#usage : ./jeu <strategie> avec strategie = r (robuste) ou m (maxi)");
			return;
		}

		// recuperation des donnees
		FormuleSelection robusteOuMaxi = (args[0].equals("r")) ? new Robuste() : new Maxi();

		Action coup;
		FinDePartie fin = FinDePartie.NON;

		// initialisation
		Etat etat = null;

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

		etat = GAME.getEtat(joueur);

		System.out.println("Temps de réflexion de l'ordinateur : " + (TEMPS / 1000) + "s");

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
				ordijoue_mcts(etat, TEMPS, robusteOuMaxi);
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

	private static void ordijoue_mcts(Etat etat, long temps, FormuleSelection strategie) {
		long tic, toc;
		// Creer l'arbre de recherche
		Noeud racine = GAME.getNoeud(etat);

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

		/* 
		 * fin de l'algorithme		
		 * On choisit la bonne strategie demandée par l'utilisateur
		 */
		racine = strategie.selectionner(racine);
		
		System.out.println("Action choisit : " + racine.getAction());
		for (int i = 0; i < racine.retournerNbEnfant(); i++) {
			racine.retournerEnfant(i).afficherStatistiques();
		}
		 
		etat.jouerAction(racine.getAction());
	}

}