package main;

import java.util.Scanner;

import config.Configuration;
import config.GameFactory;
import config.Puissance4Factory;
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

	private final static GameFactory GAME = new Puissance4Factory();
	
	public static void main(String[] args) {
		jeuOrdiOrdi(args);
		//jeuJoueurOrdi(args);
	}
	
	public static void jeuOrdiOrdi(String[] args) {
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
				Etat etat = null;
				
				etat = GAME.getEtat(1);
				
				System.out.println("Temps de r�flexion de l'ordinateur : " + Configuration.getInstance().getTemps());

				// boucle de jeu
				do {
					System.out.println(" ");
					etat.afficherJeu();
					
						
						ordijoue_mcts(etat, Configuration.getInstance().getTemps(), robusteOuMaxi);
						etat.setJoueur(1);
						etat.inverserGrille();						
				
					fin = etat.testFin();
				} while (fin == FinDePartie.NON);
				
				System.out.println(" ");
				
				etat.afficherJeu();
				
				if (fin == FinDePartie.ORDI_GAGNE) {
					System.out.println("** L'ordinateur a gagn� **");
				} else if (fin == FinDePartie.MATCHNUL) {
					System.out.println("** Match nul ! **");
				} else {
					System.out.println("** BRAVO, l'ordinateur a perdu **");
				}
	}	
	
	
	
	
	
	
	public static void jeuJoueurOrdi(String[] args) {
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
				Etat etat = null;

				// choix j1
				boolean correct = false;
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				int joueur = 0;
				do {
					System.out.println("Qui commence ? (0 : humain, 1 : ordinateur)");
					String res = sc.nextLine();
					if (res.equals("0") || res.equals("1")) {
						correct = true;
						joueur = Integer.parseInt(res);
					} else {
						System.out.println("Entr�es valides : 0 ou 1 !");
					}
				} while (!correct);
				
				etat = GAME.getEtat(joueur);
				
				System.out.println("Temps de r�flexion de l'ordinateur : " + Configuration.getInstance().getTemps());

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
					System.out.println("** L'ordinateur a gagn� **");
				} else if (fin == FinDePartie.MATCHNUL) {
					System.out.println("** Match nul ! **");
				} else {
					System.out.println("** BRAVO, l'ordinateur a perdu **");
				}
	}

	private static void ordijoue_mcts(Etat etat, int temps, boolean strategie) {
		long tic, toc;

		// Creer l'arbre de recherche
		Noeud racine = new NoeudP4(etat);

		FormuleSelection uct = new Uct();
		Mcts mcts = new Mcts( uct ); // On execute l'algorithme
		
		// pre rempli d�j� l'arbre

		// S'il y  a plusieurs fils alors on execute l'algo MCTS UCT
		int iter = 0;
		tic = System.currentTimeMillis();
		do {
			/*
		    	L'algo se decompose en 4 �tapes :
		    	- Selection � partir de l'etat du meileur fils
		    	- Developpement d'un Noeud fils choisit al�atoirement (et non d�j� d�velopp�)
		    	- Simulation de la fin de la partie avec une marche al�atoire
		    	- Mise � jours des valeurs des Noeuds dans l'arbre, on remonte la valeur de r�compense
		    	du Noeud terminal � la racine.
			 */
			racine = mcts.executer(racine);
			toc = System.currentTimeMillis();
			iter++;
		} while (toc < (tic + temps));
		System.out.println("It�rations effectu�es : " + iter);
		
		/* 
		 * fin de l'algorithme		
		 * On choisit la bonne strategie demand�e par l'utilisateur
		 */
		if ( strategie ) {
			System.out.println("(STRATEGIE ROBUSTE)");
			racine.robuste();
		} else {
			System.out.println("(STRATEGIE MAXI)");
			racine.maxi();
		}
		/*
		System.out.println("Action choisit : " + racine.getAction());
		for (int i = 0; i < racine.retournerNbEnfant(); i++) {
			racine.retournerEnfant(i).afficherStatistiques();
		}
		*/
		etat.jouerAction(racine.getAction());
	}

}