package puissance4;

import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Noeud;

public class NoeudP4 implements Noeud {
	private int joueur; // joueur qui a joué pour arriver ici
	private Action action;   // coup joué par ce joueur pour arriver ici

	private Etat etat; // etat du jeu

	private Noeud parent;
	private List<Noeud> enfants; // liste d'enfants : chaque enfant correspond à un coup possible
	private int nb_enfants;	// Nombre de simulations passant par ce Noeud (N)

	// POUR MCTS:
	private int nb_victoires;
	private int nb_simulations;
	
	public NoeudP4(Noeud p, Action a) {
		this.parent = p;
		this.action = a;
	}
	
	@Override
	public boolean estTerminal() {
		return (this.etat.testFin() != Etat.FinDePartie.NON);
	}
	
	@Override
	public boolean resteAction() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean estRacine() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Action> actionsPossible() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Noeud ajouterEnfant(Action action) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Noeud predecesseur() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Noeud retournerEnfant(int indice) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double resultat() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double rapportVictoireSimulation() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void visiter(double recompense) {
		// TODO Auto-generated method stub
		
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

}
