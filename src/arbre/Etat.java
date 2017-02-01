package arbre;

import arbre.Etat.FinDePartie;

public interface Etat {
	
	// Critères de fin de partie
	public enum FinDePartie {NON, MATCHNUL, ORDI_GAGNE, HUMAIN_GAGNE};

	public void init();
	public void afficherJeu();
	public Action demanderCoup();
	public FinDePartie testFin();
	public Action[] coups_possibles();
	public boolean testActionGagnanteOrdi(Action a);
	public boolean jouerCoup(Action action);
	
	public void setJoueur(int j);
	public int getJoueur();
	public String[][] getPlateau();
	
}
