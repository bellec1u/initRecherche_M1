/**
 * 
 */
package jeu.trapProblem;

import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 16, 2017
 */
public class EtatTrap implements Etat {

	private int[][] plateau;
	private double posPlayer;
	// mettre le nombre de pas max
	private int nbStep;
	private final int minStep = 50, maxStep = 100; // en cm
	private int joueur;
	private List<Action> actions = null;

	public EtatTrap() {
		// [distance, recompense]
		// dans notre cas, de x = 0 à 2, la récomp. est de 3
		// tout en cm
		int[][] tab = {{200,3}, {270,-5}, {1000,5}};
		this.plateau = tab;
		this.posPlayer = 0;
		this.nbStep = 3;
	}

	/**
	 * affiche le jeu
	 */
	public void afficherJeu() {
		System.out.println("--------------------");
		System.out.println("Position du joueur : " + this.posPlayer);

		System.out.print("O \t\t");
		for (int[] tab : this.plateau) {
			System.out.print(tab[0] + "\t\t");
		}
		System.out.println("");
		for (int[] tab : this.plateau) {
			System.out.print("| " + tab[1] + "\t\t");
		}
		System.out.println("");
	}

	/**
	 * change le joueur courant
	 */
	public void setJoueur(int j) {
		this.joueur = j;
	}

	/**
	 * supprime l'action action de la liste d'actions
	 */
	public void supprimerAction(Action action) {
		this.actions.remove(action);
	}

	/**
	 * Demande a l'utilisateur l'action de son choix
	 */
	public Action demanderAction() {
		System.err.println("Pas implémenté : EtatTrap.demanderAction()");
		return null;
	}

	/* (non-Javadoc)
	 * @see arbre.Etat#coups_possibles()
	 */
	public List<Action> coups_possibles() {
		System.err.println("Pas implémenté : EtatTrap.coups_possibles()");
		return null;
	}

	/**
	 * retourne HUMAIN_GAGNE si l'on ne peut plus faire de pas
	 * 			NON sinon
	 */
	public FinDePartie testFin() {
		return this.nbStep == 0 ? FinDePartie.HUMAIN_GAGNE : FinDePartie.NON;
	}

	/* (non-Javadoc)
	 * @see arbre.Etat#jouerAction(arbre.Action)
	 */
	public boolean jouerAction(Action action) {
		if (this.nbStep > 0) {
			this.posPlayer += action.getColonne();
			this.nbStep--;
			this.joueur = ( 1 - this.joueur );
			return true;
		}
		return false;
	}

	/**
	 * retourne le joueur
	 */
	public int getJoueur() {
		return this.joueur;
	}

	/**
	 * retourne le nombre de pas fait jusqu'a cet etat.
	 */
	public int getNbCoups() {
		return this.nbStep;
	}

	/* (non-Javadoc)
	 * @see arbre.Etat#getPlateau()
	 */
	public String[][] getPlateau() {
		System.err.println("Pas implémenté : EtatTrap.getPlateau");
		return null;
	}

	public Etat cloneable() {
		Etat cpy = null;	
		try {
			cpy = (Etat) super.clone();
			cpy.setJoueur( 1 - cpy.getJoueur() );
		} catch (CloneNotSupportedException e) {
			System.err.println(e.getMessage());
		}

		return cpy;
	}

}
