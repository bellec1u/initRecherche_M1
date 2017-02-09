package config;

import arbre.Etat;
import arbre.Noeud;

public interface GameFactory {
	public Etat getEtat(int joueur);
	public Noeud getNoeud(Etat etat);
}
