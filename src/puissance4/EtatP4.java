package puissance4;

import java.util.Scanner;

import arbre.Action;
import arbre.Etat;

public class EtatP4 implements Etat {

	private String[][] plateau;
	private int joueur;

	public final static int HAUTEUR = 6, LARGEUR = 7, TEMPS = 1, PUISSANCE = 4;

	public void init() {
		this.plateau = new String[HAUTEUR][LARGEUR];
		for (int l = 0; l < HAUTEUR; l++)
			for (int c = 0; c < LARGEUR; c++)
				this.plateau[l][c] = " ";
	}

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

	public boolean jouerCoup(Action action) {
		/* par exemple : */
		if (this.plateau[action.getLigne()][action.getColonne()] != " ") {
			return false;
		} else {
			this.plateau[action.getLigne()][action.getColonne()] = ((this.joueur == 0) ? "O" : "X");

			// à l'autre joueur de jouer
			this.changerJoueur(); 	
			return true;
		}	
	}

	public void changerJoueur() {
		if (this.joueur == 0) {
			this.joueur = 1;
		} else {
			this.joueur = 0;
		}
	}

	public void setJoueur(int j) {
		this.joueur = j;
	}

	public int getJoueur() {
		return this.joueur;
	}

	public ActionCoupP4 demanderCoup() {
		int ligne = HAUTEUR - 1;
		int colonne = HAUTEUR -1;
		boolean jouable = false;
		Scanner sc = new Scanner(System.in);

		while (!jouable) {
			System.out.println("Quelle colonne ?");
			colonne = sc.nextInt();
			colonne--;

			// Veracité du coup
			if (colonne < 0 || colonne >= LARGEUR) {
				// On indique que le coup n'est pas possible
				System.out.println("La colonne " + (colonne+1) + " n'existe pas !");
			} else {
				// si la case souhaité n'est pas vide alors coup déjà joué
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
		return (new ActionCoupP4(ligne, colonne));
	}

	@Override
	public FinDePartie testFin() {
		// tester si un joueur a gagné
		int i, j, k, n = 0;
		for ( i = 0; i < HAUTEUR; i++ ) {
			for( j = 0; j < LARGEUR; j++ ) {
				if (!this.plateau[i][j].equals(" ")) {
					n++;	// nb coups joués

					// lignes
					k = 0;
					while ( k < PUISSANCE && i+k < HAUTEUR && this.plateau[i+k][j] == this.plateau[i][j] ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j] == "O"? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

					// colonnes
					k=0;
					while ( k < PUISSANCE && j+k < LARGEUR && this.plateau[i][j+k] == this.plateau[i][j] ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j] == "O"? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

					// diagonales
					k=0;
					while ( k < PUISSANCE && i+k < HAUTEUR && j+k < LARGEUR && this.plateau[i+k][j+k] == this.plateau[i][j] ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j] == "O"? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;

					k=0;
					while ( k < PUISSANCE && i+k < HAUTEUR && j-k >= 0 && this.plateau[i+k][j-k] == this.plateau[i][j] ) 
						k++;
					if ( k == PUISSANCE ) 
						return this.plateau[i][j] == "O"? FinDePartie.ORDI_GAGNE : FinDePartie.HUMAIN_GAGNE;		
				}
			}
		}

		// et sinon tester le match nul	
		if ( n == (LARGEUR * HAUTEUR) ) 
			return FinDePartie.MATCHNUL;

		return FinDePartie.NON;
	}

}
