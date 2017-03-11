package jeu.puissance4;

import arbre.Action;
import arbre.Etat;
import arbre.Noeud;
import arbre.Etat.FinDePartie;


public class ActionP4 implements Action {

	/**
	 * Une Action est un couple (ligne, colonne)
	 * dans le plateau de Jeu
	 **/
	private int ligne;
	private int colonne;

	/**
	 * Constructeur d'une Action
	 * @param lig - ligne de l'Action
	 * @param col - colonne de l'Action
	 */
	public ActionP4(int lig, int col) {
		ligne = lig;
		colonne = col;
	}
	
	/**
	 * Indique si l'Action tis est considérée comme gagnante 
	 * dans l'Etat etat
	 */
	public boolean estGagnante(Noeud noeud) {
		/*Etat e = etat.cloneable();
		e.jouerAction(this);*/
		Etat e = new EtatP4( noeud.getEtat() );
		e.jouerAction(this);
		if( noeud.getInitialJoueur() == Etat.HUMAIN ) {
			return (e.testFin() == FinDePartie.HUMAIN_GAGNE);			
		} else {
			return (e.testFin() == FinDePartie.ORDI_GAGNE);			
		}
	}
	
	/**
	 * Retourne la ligne de l'Action
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Retourne la colonne de l'Action
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Test si 2 Actions sont égales
	 */
	@Override
	public boolean equals(Object obj) {
		ActionP4 a = (ActionP4)obj;
		return (ligne == a.getLigne() && colonne == a.getColonne());
	}
	
	/**
	 * Retourne sous forme de String une Action 
	 */
	@Override
	public String toString() {
		return "(" + (ligne + 1) + ", " + (colonne + 1) + ")";
	}	
}
