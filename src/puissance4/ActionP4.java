package puissance4;

import arbre.Action;
import arbre.Etat;
import arbre.Etat.FinDePartie;

public class ActionP4 implements Action {

	private int ligne;
	private int colonne;

	public ActionP4(int lig, int col) {
		ligne = lig;
		colonne = col;
	}
	
	public boolean estGagnante(Etat etat) {
		return (etat.testFin() == FinDePartie.ORDI_GAGNE);
	}
	
	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}



}
