package config;

import arbre.Etat;
import arbre.Noeud;
import jeu.puissance4.EtatP4;
import jeu.puissance4.NoeudP4;

public class Puissance4Factory implements GameFactory{

	public Etat getEtat(int joueur) {
		return new EtatP4(joueur);
	}

	public Noeud getNoeud(Etat etat) {
		Noeud noeud = new NoeudP4(etat);
		noeud.setInitialJoueur(etat.getJoueur());
		return noeud;
	}

}
