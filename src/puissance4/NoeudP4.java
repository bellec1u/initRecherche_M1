package puissance4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;
import arbre.Noeud;

public class NoeudP4 implements Noeud {
	private int joueur = 0; // joueur qui a joué pour arriver ici
	private Action action;   // coup joué par ce joueur pour arriver ici

	private Etat etat; // etat du jeu

	private Noeud parent;
	private List<Noeud> enfants = new ArrayList<Noeud>(); // liste d'enfants : chaque enfant correspond à un coup possible
	private int nb_enfants = 0;	// Nombre de simulations passant par ce Noeud (N)

	// POUR MCTS:
	private int nb_victoires = 0;
	private int nb_simulations = 0;

	public NoeudP4(Noeud p, Action a) {
		if (p != null && a != null) {
			this.parent = p;
			this.action = a;
			Etat etatParent = this.parent.getEtat();
			etatParent.jouerCoup(a);
			this.etat = etatParent;
		} else {
			this.etat = new EtatP4();
			this.etat.init();
		}
	}

	@Override
	public boolean estTerminal() {
		return (this.etat.testFin() != FinDePartie.NON);
	}

	@Override
	public boolean resteAction() {
		Action[] a = this.etat.coups_possibles();
		int k = 0;
		while(a[k] != null) {
			k++;
		}
		return (k != this.nb_enfants);
	}

	@Override
	public boolean estRacine() {
		return (this.parent == null);
	}

	@Override
	public List<Action> actionsPossible() {
		Action[] a = this.etat.coups_possibles();
		List<Action> res = Arrays.asList(a);
		return res;
	}

	@Override
	public Noeud ajouterEnfant(Action a) {
		Noeud e = new NoeudP4(this, a) ;
		this.enfants.add(e);
		this.nb_enfants++;
		return e;
	}

	@Override
	public Noeud predecesseur() {
		return this.parent;
	}

	@Override
	public Noeud retournerEnfant(int indice) {
		return this.enfants.get(indice);
	}

	@Override
	public double resultat() {
		FinDePartie e = this.etat.testFin();
		double res = 0.5;
		switch(e) {
		case ORDI_GAGNE :
			res = 1;
			break;
		case HUMAIN_GAGNE :
			res = 0;
			break;
		case MATCHNUL :
			break;
		default :
			System.err.println("Error NoeudP4 -> resultat()");
			break;
		}
		return res;
	}

	@Override
	public double rapportVictoireSimulation() {
		return (this.nb_victoires / this.nb_simulations);
	}

	@Override
	public int retournerNbEnfant() {
		return this.nb_enfants;
	}

	@Override
	public int retournerNbSimulation() {
		return this.nb_simulations;
	}

	@Override
	public int retournerNbVictoire() {
		return this.nb_victoires;
	}

	@Override
	public int autreJoueur() {
		int res = 0;
		if (this.joueur == 0) {
			res = 1;
		}
		return res;
	}

	@Override
	public void visiter(double recompense) {
		this.parent.addSimulation();
		this.parent.setNbVictoires(recompense);
	}

	public void setEtat(Etat e) {
		this.etat = e;
	}

	public void afficherStatistiques() {
		System.out.println("Statistiques : \n");
		System.out.println("\t-Nombre de victoire(s) : " + this.nb_victoires);
		System.out.println("\t-Nombre de simulation(s) : " + this.nb_simulations);
		float pourcentage = (float)(this.nb_victoires / (float)this.nb_simulations) * 100.0f ;
		System.out.println("\t-Pourcentage : " + pourcentage);

	}

	public Noeud robuste() {
		int k = 0;
		int maxi = Integer.MIN_VALUE;
		int indice = 0;
		int value = 0;
		while(k < this.nb_enfants ) {
			boolean AMELIORATION = true;
			if( AMELIORATION ) {
				if (this.enfants.get(k).estTerminal()) {
					return this.enfants.get(k);
				}
			}

			value = this.enfants.get(k).retournerNbSimulation();
			if (value > maxi) {
				maxi = value;
				indice = k;
			}
			k++;
		}
		return this.enfants.get(indice);
	}

	public Noeud maxi() {
		int k = 0;
		float maxi = Integer.MIN_VALUE;
		int indice = 0;
		float value = 0;
		while(k < this.nb_enfants ) {
			boolean AMELIORATION = true;
			if( AMELIORATION ) {
				if (this.enfants.get(k).estTerminal()) {
					return this.enfants.get(k);
				}
			}

			value = this.enfants.get(k).retournerNbVictoire();
			if( value > maxi) {
				maxi = value;
				indice = k;
			}
			k++;
		}
		return this.enfants.get(indice);	
	}

	public Action getAction() {
		return this.action;
	}

	@Override
	public boolean testActionGagnanteOrdi(Action a) {
		if (this.etat.testActionGagnanteOrdi(a)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Etat getEtat() {
		return this.etat;
	}

	@Override
	public void addSimulation() {
		this.nb_simulations++;
	}

	@Override
	public void setNbVictoires(double recompense) {
		this.nb_victoires += recompense;
	}

}
