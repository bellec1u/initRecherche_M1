package config;

import arbre.Etat;

public interface GameFactory {
	public Etat getEtat(int joueur);
}
