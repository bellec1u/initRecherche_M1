package config;

import arbre.Etat;
import puissance4.EtatP4;

public class Puissance4Factory implements GameFactory{

	public Etat getEtat(int joueur) {
		return new EtatP4(joueur);
	}

}
