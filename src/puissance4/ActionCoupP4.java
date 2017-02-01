package puissance4;

import arbre.Action;

public class ActionCoupP4 implements Action {

	private int ligne, colonne;
	
	public ActionCoupP4(int l, int c) {
		this.ligne = l;
		this.colonne = c;
	}

	@Override
	public int getLigne() {
		return this.ligne;
	}

	@Override
	public int getColonne() {
		return this.colonne;
	}
	
}
