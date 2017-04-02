/**
 * 
 */
package jeu.trapProblem;

import java.util.ArrayList;
import java.util.List;

import arbre.Action;
import arbre.Etat;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 16, 2017
 */
public class EtatTrap implements Etat {

	private Integer[][] plateau = {{100,70}, {170,0}, {1000,100}};
	private double posPlayer;
	// mettre le nombre de pas max
	private int nbStep = 2;
	private final int minStep = 0, maxStep = 100; 
	private int joueur;
	private List<Action> actions = null;

	private int score;

	public EtatTrap() {
		// [distance, recompense]
		// dans notre cas, de x = 0 à 2, la récomp. est de 3
		// tout en cm
		this.posPlayer = 0;
		this.score = 0;
	}

	public EtatTrap(Etat etat) {
		this.posPlayer = ((EtatTrap) etat).getPosJoueur();
		this.nbStep = etat.getNbCoups();
		this.score = ((EtatTrap)etat).getScore();
	}

	public double getPosJoueur() {
		return this.posPlayer;
	}
	/**
	 * affiche le jeu
	 */
	public void afficherJeu() {
		System.out.println("----------------------------------------------------");
		System.out.println("Position du joueur : " + this.posPlayer);
		System.out.println("Score : " + this.score);

		System.out.print("O \t\t");
		for (Integer[] tab : this.plateau) {
			System.out.print(tab[0] + "\t\t");
		}
		System.out.println("");
		for (Integer[] tab : this.plateau) {
			System.out.print("| " + tab[1] + "\t\t");
		}
		System.out.println("");
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
		System.exit(0);
		return null;
	}

	/**
	 * retourne les bornes d'actions possibles
	 */
	public List<Action> coups_possibles() {
		List<Action> la = new ArrayList<Action>();
		la.add( new ActionTrap(this.minStep) );
		la.add( new ActionTrap(this.maxStep) );
		return la;
	}

	/**
	 * retourne HUMAIN_GAGNE si l'on ne peut plus faire de pas
	 * 			NON sinon
	 */
	public FinDePartie testFin() {
		return this.nbStep == 0 ? FinDePartie.HUMAIN_GAGNE : FinDePartie.NON;
	}

	public boolean jouerAction(Action action) {
		if (this.nbStep > 0) {
			this.posPlayer += ((ActionTrap) action).getStep();
			this.nbStep--;
			this.joueur = ( 1 - this.joueur );

			for (Integer[] i : this.plateau) {
				if (this.posPlayer <= i[0]) {
					this.score += i[1];
					return true;
				}
			}
			this.score += this.plateau[this.plateau.length-1][1];
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

	public Integer[][] getPlateau() {
		return this.plateau;
	}

	public int getScore() {
		return this.score;
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
