package main;

import algorithme.Mcts;
import algorithme.formule.FormuleSelection;
import algorithme.formule.Maxi;
import algorithme.formule.PWidening;
import algorithme.formule.Robuste;
import algorithme.formule.Uct;
import arbre.Etat;
import arbre.Noeud;
import config.Configuration;
import config.GameFactory;
import config.Puissance4Factory;
import config.TrapFactory;

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
		
		// on lance le jeu
		GAME.jouer(TEMPS, robusteOuMaxi);		
	}

	public static void ordijoue_mcts(Etat etat, long temps, FormuleSelection strategie) {
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
		
		System.out.println("");
		System.out.println("Itérations effectuées : " + iter);

		/* 
		 * fin de l'algorithme		
		 * On choisit la bonne strategie demandée par l'utilisateur
		 */
		racine = strategie.selectionner(racine);
		/*
		System.out.println("Action choisit : " + racine.getAction());
		for (int i = 0; i < racine.retournerNbEnfant(); i++) {
			racine.retournerEnfant(i).afficherStatistiques();
		}
		 */
		etat.jouerAction(racine.getAction());
	}

}