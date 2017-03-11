/**
 * 
 */
package jeu.trapProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
	private int nbStepTest = 1000;
	private final int minStep = 70, maxStep = 100; // en cm
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

	public List<Action> coups_possibles() {
		if (this.actions == null) {
			this.actions = new LinkedList<Action>();
			// nb actions que l'ont prend
			Random r = new Random();
			for ( int i = 0; i < this.nbStepTest; i++ ) {
				double step = this.minStep + (this.maxStep - this.minStep) * r.nextDouble();
				this.actions.add( new ActionTrap(step) );
			}
		}
		return this.actions;
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
			System.err.println("Error : EtatTrap.jouerAction()");
			System.exit(0);
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
	
	/**
	 * fait bouger la position du joueur de x
	 * simule un coup de vent par exemple
	 * @param i
	 */
	public void ajouterBruit(double x) {
		double lastPosPlayer = this.posPlayer;		
		this.posPlayer += x;
		
		int lastX = 0, newX = 0;
		boolean findLastX = false, findNewX = false;
		for (Integer[] i : this.plateau) {
			if (lastPosPlayer <= i[0] && !findLastX) {
				lastX = i[1];
				findLastX = true;
			}
			if (this.posPlayer <= i[0] && !findNewX) {
				newX = i[1];
				findNewX = true;
			}
		}
		
//		System.out.println("#######################################");
//		System.out.println("x : "+x);
//		System.out.println("lastPos : "+lastPosPlayer);
//		System.out.println("newPos : "+this.posPlayer);
//		System.out.println("lastScore : "+this.score);
//		System.out.println("lastX : " + lastX);
//		System.out.println("newX : "+newX);
		
		// score = ancienScore - scoreDernPos + scoreNouvPos
		this.score = this.score - lastX + newX;
		
//		System.out.println("Newscore : "+this.score);
//		System.out.println("#######################################");
	}

}
