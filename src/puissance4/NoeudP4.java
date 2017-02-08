package puissance4;

import java.util.LinkedList;
import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Noeud;
import arbre.Etat.FinDePartie;

public class NoeudP4 implements Noeud {

	private Noeud parent = null;
	private Action action = null;

	private Etat etat;
	private List<Noeud> enfants;

	private int simulations = 0;
	private double victoires = 0.0;

	/**
	 * Constructeur de NoeudP4,
	 * initialise l'Etat du Noeud, et sa liste d'enfant
	 * @param e - Etat du noeud
	 */
	public NoeudP4(Etat e) {
		etat = new EtatP4(e);
		enfants = new LinkedList<Noeud> ();
	}

	/**
	 * Constructeur de NoeudP4,
	 * Affecte comme parent du Noeud le Noeud en param�tre p
	 * et affecte l'Action a comme étant l'Action du Noeud
	 * @param p - Noeud parent
	 * @param a - Action a affecté au Noeud
	 */
	public NoeudP4(Noeud p, Action a) {
		parent = p;
		enfants = new LinkedList<Noeud> ();
		action = a;
		etat = new EtatP4(parent.getEtat());
		etat.jouerAction(a);		
	}

	/**
	 * Indique si le Noeud est terminal ou non
	 */
	public boolean estTerminal() {
		return (etat.testFin() != FinDePartie.NON);
	}

	/**
	 * Indique s'il reste des actions possibles à effectuées
	 * à partir du Noeud this
	 */
	public boolean resteAction() {
		return ( enfants.size() != etat.getNbCoups() );
	}

	/**
	 * Indique si le Noeud est racine
	 */
	public boolean estRacine() {
		return (parent == null);
	}

	/**
	 * Retourne le nombre d'actions possibles à partir de l'Etat etat
	 */
	public List<Action> actionsPossible() {
		List<Action> all = etat.coups_possibles();
		for(int i = 0 ; i < enfants.size() ; i++) {
			Noeud enfant = enfants.get(i);
			all.remove(enfant.getAction());
		}
		return all;
	}

	/**
	 * Ajoute un Noeud enfant au Noeud this,
	 * avec l'Action action
	 */
	public Noeud ajouterEnfant(Action action) {
		Noeud enfant = new NoeudP4(this, action);
		enfants.add(enfant);
		return enfant;
	}

	/**
	 * Retourne le Noeud parent
	 */
	public Noeud predecesseur() {
		return parent;
	}

	/**
	 * Retourne le Noeud Enfant 'indice'
	 */
	public Noeud retournerEnfant(int indice) {
		return enfants.get(indice);
	}

	/**
	 * Retourne l'Action liée au Noeud
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Retourne l'Etat du Noeud
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * Indique la recompense du Noeud, 
	 * si c'est un Noeud HUMAIN_GAGNANT, ORDI_GAGNANT ETC...
	 */
	public double resultat() {
		FinDePartie res = etat.testFin();
		double recomp = 0.0;
		switch (res) {
		case ORDI_GAGNE :
			recomp = 1.0;
			break;
		case HUMAIN_GAGNE :
			recomp = 0.0;
			break;
		case MATCHNUL :
			recomp = 0.5;
			break;
		default:
			System.err.println("Oops ! (NoeudP4).resultat()");
			break;
		}
		return recomp;
	}

	/**
	 * Retourne le rapport Victoire / Simulation
	 */
	public double rapportVictoireSimulation() {		
		return (double)( victoires / simulations );
	}

	/**
	 * Retourne le nombre de Noeud enfant
	 */
	public int retournerNbEnfant() {
		return enfants.size();
	}

	/**
	 * Retourne le nombre de simulation du Noeud
	 */
	public int retournerNbSimulation() {
		return simulations;
	}

	/**
	 * Retourne le nombre de victoire du Noeud
	 */
	public double retournerNbVictoire() {
		return victoires;
	}


	/**
	 * Ajoute a victoires la valeur de recompense
	 * et increment le nombre de simulations
	 */
	public void visiter(double recompense) {
		victoires+= recompense;
		simulations++;
	}

	/**
	 * Fixe le nombre de simulations et de victoires
	 */
	public void setStatistique(int s, double v) {
		simulations = s;
		victoires = v;
	}
	/**
	 * Incremente le nombre de simulation
	 * et victoire
	 */
	public void ajouterVisite(double recompense) {
		simulations++;
		victoires += recompense;
	}

	/**
	 * Afffiche sur la sortie standard 
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

	/**
	 * Choisit un Noeud enfant en respectant la 
	 * stratégie robuste, on retournera donc le 
	 * Noeud enfant avec le plus grand nombre de simulations
	 */
	public void robuste() {
		int k = 0;
		int maxi = Integer.MIN_VALUE;
		int indice = 0;
		int value = 0;
		while(k < enfants.size() ) {
			value = this.enfants.get(k).retournerNbSimulation();
			if (value > maxi) {
				maxi = value;
				indice = k;
			}
			k++;
		}
		action = enfants.get(indice).getAction();
	}

	/**
	 * Choisit un Noeud enfant en respectant la 
	 * stratégie maxi, on retournera donc le 
	 * Noeud enfant avec le plus grand nombre de victoires
	 */
	public void maxi() {
		int k = 0;
		double maxi = Integer.MIN_VALUE;
		int indice = 0;
		double value = 0;
		while(k < enfants.size() ) {
			value = this.enfants.get(k).retournerNbVictoire();
			if( value > maxi) {
				maxi = value;
				indice = k;
			}
			k++;
		}
		action = enfants.get(indice).getAction();
	}
}
