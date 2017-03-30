package algorithme.adaptateur;

import java.util.LinkedList;
import java.util.List;

import arbre.Action;
import arbre.Etat;
import arbre.Noeud;
import jeu.trapProblem.ActionTrap;

public abstract class AbstractAdaptateur implements Noeud {

	protected Noeud noeud;
	protected int echantillonage = 0;

	public AbstractAdaptateur(Noeud n, int e) {
		this.noeud = n;
		this.echantillonage = e;
	}

	public abstract List<Action> actionsPossible();
	
	public abstract boolean resteAction();
	
	public abstract Noeud ajouterEnfant(Action action);
	
	protected abstract List<Action> getListActionAlea(int min, int max);

	protected abstract List<Action> getListActionUniforme(int min, int max);
	
	public boolean estTerminal() {
		return this.noeud.estTerminal();
	}

	public boolean estRacine() {
		return this.noeud.estRacine();
	}

	public Noeud predecesseur() {
		return this.noeud.predecesseur();
	}

	public Noeud retournerEnfant(int indice) {
		return this.noeud.retournerEnfant(indice);
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
