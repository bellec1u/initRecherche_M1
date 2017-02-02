package arbre;

import java.util.List;

public interface Etat {
	
	// Critères de fin de partie
	public enum FinDePartie {NON, MATCHNUL, ORDI_GAGNE, HUMAIN_GAGNE};

	public void afficherJeu();
	public Action demanderAction();
	public FinDePartie testFin();
	public List<Action> coups_possibles();
	public boolean jouerAction(Action action);
	
	public void setJoueur(int j);
	
	public int getJoueur();
	public String[][] getPlateau();
	public int getNbCoups();
	
}
