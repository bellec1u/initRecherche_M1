package algorithme.adaptateur;

import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Noeud;

public abstract class AbstractAdaptateur implements Noeud {

	protected Noeud noeud;
	protected int echantillonage;
	protected List<Action> espace;

	public AbstractAdaptateur(Noeud n, int e) {
		this.noeud = n;
		this.echantillonage = e;
		this.espace = null;
	}

	public abstract List<Action> actionsPossible();
		
	public abstract Noeud ajouterEnfant(Action action);
	
	protected abstract List<Action> getListActionAlea(int min, int max);

	protected abstract List<Action> getListActionUniforme(int min, int max);
	
	public abstract Noeud predecesseur();
	
	public abstract Noeud retournerEnfant(int indice);
	
	public boolean estTerminal() {
		return this.noeud.estTerminal();
	}

	public boolean estRacine() {
		return this.noeud.estRacine();
	}

	public Action getAction() {
		return this.noeud.getAction();
	}

	public Etat getEtat() {
		return this.noeud.getEtat();
	}

	public double resultat() {
		return this.noeud.resultat();
	}

	public double rapportVictoireSimulation() {
		return this.noeud.rapportVictoireSimulation();
	}

	public double retournerNbVictoire() {
		return this.noeud.retournerNbVictoire();
	}

	public int retournerNbEnfant() {
		return this.noeud.retournerNbEnfant();
	}

	public int retournerNbSimulation() {
		return this.noeud.retournerNbSimulation();
	}

	public int getInitialJoueur() {
		return this.noeud.getInitialJoueur();
	}

	public int getMinOrMax() {
		return this.noeud.getMinOrMax();
	}

	public void visiter(double recompense) {
		this.noeud.visiter(recompense);
	}

	public boolean resteAction() {
		if (espace == null) {
			return true;
		}
		return ( (espace.size() != 0) );
	}
	
	public void setStatistique(int s, double v) {
		this.noeud.setStatistique(s, v);
	}

	public void setInitialJoueur(int joueur) {
		this.noeud.setInitialJoueur(joueur);
	}

	public void setMinOrMax(int minMax) {
		this.noeud.setMinOrMax(minMax);
	}

	public void afficherStatistiques() {
		this.noeud.afficherStatistiques();
	}

	public void supprimerEnfants() {
		this.noeud.supprimerEnfants();
	}

	public Noeud setAction(Action action) {
		return this.noeud.setAction(action);
	}



}
