package config;

import jeu.trapProblem.EtatTrap;
import jeu.trapProblem.NoeudTrap;
import algorithme.formule.FormuleSelection;
import arbre.Etat;
import arbre.Noeud;

public class TrapFactory implements GameFactory {

	public Etat getEtat(int joueur) {
		return new EtatTrap();
	}

	public Noeud getNoeud(Etat etat) {
		Noeud noeud = new NoeudTrap(etat);
		noeud.setInitialJoueur(etat.getJoueur());
		return noeud;
	}

	public void jouer(long temps, FormuleSelection strategie) {
		// TODO Auto-generated method stub
		
	}

}
