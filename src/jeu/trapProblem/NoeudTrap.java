/**
 * 
 */
package jeu.trapProblem;

import java.util.LinkedList;
import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;
import arbre.Noeud;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 16, 2017
 */
public class NoeudTrap implements Noeud{
	
	private Noeud parent = null;
	private Action action = null;

	private EtatTrap etat;
	private List<Noeud> enfants;

	private int minOrMax = 1; // min a la racine
	private int simulations = 0;
	private double victoires = 0.0;
	
	private static int JOUEUR_INIT = Etat.ORDI;
	
	public NoeudTrap(Etat etat) {
		this.etat = new EtatTrap(etat);
		this.enfants = new LinkedList<Noeud> ();
	}

	public NoeudTrap(Noeud p, Action a) {
		parent = p;
		enfants = new LinkedList<Noeud> ();
		action = a;
		etat = new EtatTrap(parent.getEtat());
		etat.jouerAction(a);
	}

	/**
	 * retourne true si le noeud est terminal
	 * 			false sinon
	 */
	public boolean estTerminal() {
		return this.etat.testFin() != FinDePartie.NON;
	}

	/**
	 * retourne true si il reste des pas a faire
	 * 			false sinon
	 */
	public boolean resteAction() {
		return (this.etat.getNbCoups() != 0) && (this.etat.coups_possibles().size() != 0);
	}

	/**
	 * retourne true si le noeud est la racine
	 * 			false sinon
	 */
	public boolean estRacine() {
		return this.parent == null;
	}

	/**
	 * retourne une liste d'actions possibles
	 */
	public List<Action> actionsPossible() {
		return this.etat.coups_possibles();
	}

	/**
	 * crée un enfant avec une action et l'ajoute a ce noeud
	 */
	public Noeud ajouterEnfant(Action action) {
		Noeud enfant = new NoeudTrap(this, action);
		enfant.setMinOrMax( 1 - this.getMinOrMax() );
		this.etat.supprimerAction(action);
		this.enfants.add(enfant);
		return enfant;
	}

	/**
	 * retourne le parent du noeud
	 */
	public Noeud predecesseur() {
		return this.parent;
	}

	/**
	 * retourne le noeud enfand num. "indice"
	 */
	public Noeud retournerEnfant(int indice) {
		return this.enfants.get(indice);
	}

	/**
	 * retourne l'action du noeud
	 */
	public Action getAction() {
		return this.action;
	}

	/**
	 * retourne l'etat du noeud
	 */
	public Etat getEtat() {
		return this.etat;
	}

	/* (non-Javadoc)
	 * @see arbre.Noeud#resultat()
	 */
	public double resultat() {
		for (Integer[] i : (Integer[][])this.etat.getPlateau()) {
			if (this.etat.getPosJoueur() <= i[0]) {
				return i[1];
			}
		}
		return this.etat.getPlateau()[this.etat.getPlateau().length-1][1];
	}

	/**
	 * Retourne le rapport Victoire / Simulation
	 */
	public double rapportVictoireSimulation() {
		return (double)( victoires / simulations );
	}

	/**
	 * retourne le nombre de victoires
	 */
	public double retournerNbVictoire() {
		return this.victoires;
	}

	/**
	 * retourne le nombre d'enfants du noeud
	 */
	public int retournerNbEnfant() {
		return this.enfants.size();
	}

	/**
	 * retourne le nombre de simulations du noeud
	 */
	public int retournerNbSimulation() {
		return this.simulations;
	}

	/**
	 * retourne le joueur initial
	 */
	public int getInitialJoueur() {
		return JOUEUR_INIT;
	}

	/**
	 * retourne 1 si le noeud est un noeud min
	 * 		    0 sinon
	 */
	public int getMinOrMax() {
		return this.minOrMax;
	}

	/**
	 * Ajoute a victoires la valeur de recompense
	 * et increment le nombre de simulations
	 */
	public void visiter(double recompense) {
		this.victoires += recompense;
		this.simulations++;
	}

	/**
	 * Fixe le nombre de simulations et de victoires
	 */
	public void setStatistique(int s, double v) {
		this.simulations = s;
		this.victoires = v;
	}

	/**
	 * Affecte une valeur de départ de JOUEUR
	 */
	public void setInitialJoueur(int joueur) {
		this.JOUEUR_INIT = joueur;
	}

	public void setMinOrMax(int minMax) {
		this.minOrMax = minMax;
	}

	/**
	 * Affiche sur la sortie standard 
	 * les statistiques du Noeud
	 */
	public void afficherStatistiques() {
		System.out.println("Statistiques : ");
		if( action != null ) {
			System.out.println("\t-Action : " + action);
		} else {
			System.out.println("\t-Action : none");
		}
		System.out.println("\t-Nombre de victoire(s) : " + victoires);
		System.out.println("\t-Nombre de simulation(s) : " + simulations);
		System.out.println("\t-Nombre d'enfant(s) : " + enfants.size());
		double pourcentage = (victoires / simulations) * 100.0 ;
		System.out.println("\t-Pourcentage : " + pourcentage + "\n");
	}
	
	public Noeud setAction(Action action) {
		this.action = action;
		return this;
	}

	public void supprimerEnfants() {
		enfants.clear();
	}

}
