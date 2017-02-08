package puissance4;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import arbre.Action;
import arbre.Etat;

public class EtatP4 implements Etat {

	private int nbCoups = -1;
	private String[][] plateau;
	private final int HAUTEUR = 6, LARGEUR = 7, PUISSANCE = 4;
	private int joueur = -1;

	/***
	 * Constructeur d'EtatP4,
	 * ce constructeur initialise le plateau
	 * de Jeu � vide partout,
	 * et set le joueur � 'j'
	 * @param j - Le joueur
	 */
	public EtatP4(int j) {
		joueur = j;
		plateau = new String[HAUTEUR][LARGEUR];
		for (int l = 0; l < HAUTEUR; l++) {
			for (int c = 0; c < LARGEUR; c++) {
				this.plateau[l][c] = " ";
			}
		}
	}

	/**
	 * Constructeur de copie d'Etat,
	 * copie les informations de l'Etat en paramétre
	 * @param etat - Etat à copier
	 */
	public EtatP4(Etat etat) {
		plateau = new String[HAUTEUR][LARGEUR];
		String[][] cpy = etat.getPlateau();
		for(int i = 0 ; i < HAUTEUR ; i++) {
			for( int j = 0 ; j < LARGEUR ; j++) {
				this.plateau[i][j] = cpy[i][j];
			}
		}
		joueur = etat.getJoueur();
	}

	/**
	 * Afffiche sur la sortie standard 
	 * le plateau de Jeu
	 */
	public void afficherJeu() {
		System.out.print("| ");
		for (int c = 0; c < LARGEUR; c++) 
			System.out.print((c + 1) + " | ");
		System.out.println("");
		System.out.println("-----------------------------");

		for(int l = (HAUTEUR - 1); l >= 0; l--) {
			System.out.print("| ");
			for (int c = 0; c < LARGEUR; c++) 
				System.out.print(this.plateau[l][c] + " | ");
			System.out.println("");
			System.out.println("-----------------------------");
		}
	}

	/**
	 * Demande � l'utilisateur l'action de son choix
	 * et retourne ensuite cette Action
	 */
	public Action demanderAction() {
		int ligne = HAUTEUR - 1;
		int colonne = LARGEUR -1;
		boolean jouable = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (!jouable) {
			System.out.println("Quelle colonne ?");
			colonne = sc.nextInt();
			colonne--;

			// Veracit� du coup
			if (colonne < 0 || colonne >= LARGEUR) {
				// On indique que le coup n'est pas possible
				System.out.println("La colonne " + (colonne+1) + " n'existe pas !");
			} else {
				// si la case souhaité n'est pas vide alors coup d�j� jou�
				if (!this.plateau[HAUTEUR-1][colonne].equals(" ")) {
					System.out.println("La colonne " + colonne + " est pleine !");
				} else {
					try {
						while (this.plateau[ligne-1][colonne].equals(" ")) {
							ligne--;	       
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						ligne = 0;
					}
					jouable = true;
				}
			}
		}
		return (new ActionP4(ligne, colonne));
	}

	/**
	 * Indique si l'Etat this est un Etat terminal
	 * c-�-d : MATCHNUL ou ORDI_GAGNE ou HUMAIN_GAGNE
	 */
	public FinDePartie testFin() {
		// tester si un joueur a gagn�
		int i, j, k, n = 0;
		for ( i = 0; i < HAUTEUR; i++ ) {
			for( j = 0; j < LARGEUR; j++ ) {
				if (!this.plateau[i][j].equals(" ")) {
					n++;	// nb coups jou�s

					// lignes
					k = 0;
					while ( k < PUISSANCE && i+k < HAUTEUR && this.plateau[i+k][j].equals(this.plateau[i][j]) ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j].equals("O")? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

					// colonnes
					k=0;
					while ( k < PUISSANCE && j+k < LARGEUR && this.plateau[i][j+k].equals(this.plateau[i][j]) ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j].equals("O")? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

					// diagonales
					k=0;
					while ( k < PUISSANCE && i+k < HAUTEUR && j+k < LARGEUR && this.plateau[i+k][j+k].equals(this.plateau[i][j]) ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j].equals("O")? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

					k=0;
					while ( k < PUISSANCE && i+k < HAUTEUR && j-k >= 0 && this.plateau[i+k][j-k].equals(this.plateau[i][j]) ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j].equals("O")? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;		
				}
			}
		}

		// et sinon tester le match nul	
		if ( n == (LARGEUR * HAUTEUR) ) 
			return FinDePartie.MATCHNUL;

		return FinDePartie.NON;
	}

	/**
	 * Retourne le nombre d'actions possibles � partir de l'Etat etat
	 */
	public List<Action> coups_possibles() {
		List<Action> actions = new LinkedList<Action>();
		// pour chaque colonnes
		for (int i = 0; i < LARGEUR ; i++) {
			boolean trouve = false;
			int j = 0;
			// pour chaque lignes != " "
			while (!trouve && j < HAUTEUR ) {
				if (plateau[j][i].equals(" ")) {
					trouve = true;
					actions.add(new ActionP4(j,i));
				} else {
					j++;
				}
			}
		}	
		nbCoups = actions.size();		
		return actions;
	}

	/**
	 * Set le futur Joueur
	 */
	public void setJoueur(int j) {
		this.joueur = j;
	}

	/**
	 * Retourne le Joueur
	 */
	public int getJoueur() {
		return this.joueur;
	}

	/**
	 * Retourne le plateau de Jeu
	 */
	public String[][] getPlateau() {
		return plateau;
	}

	/**
	 * Retourne le nombre d'Action possibles � partir
	 * de l'Etat this
	 */
	public int getNbCoups() {
		if( nbCoups == -1 ) {
			coups_possibles();
		}
		return nbCoups;
	}

	/**
	 * Ajoute l'action action dans le plateau de jeu
	 */
	public boolean jouerAction(Action action) {
		if (this.plateau[action.getLigne()][action.getColonne()] != " ") {
			return false;
		} else {
			this.plateau[action.getLigne()][action.getColonne()] = ((this.joueur == 0) ? "X" : "O");

			// à l'autre joueur de jouer
			this.joueur = (1 - this.joueur);
			return true;
		} 
	}
	
	/***
	 * Set un Etat de Test c-�-d 3 'O' align�s
	 */
	public void setEtatTest() {
		this.plateau[0][0] = "O";
		this.plateau[0][1] = "O";
		this.plateau[0][2] = "O";
	}

	public void inverserGrille() {
		for(int i = 0 ; i < HAUTEUR ; i++) {
			for( int j = 0 ; j < LARGEUR ; j++) {
				if (plateau[i][j].equals("X")) {
					plateau[i][j] = "O";
				} else if (plateau[i][j].equals("O")) {
					plateau[i][j] = "X";
				}
			}
		}		
	}
}
