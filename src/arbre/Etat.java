package arbre;

import java.util.List;

public interface Etat {
	
	// Critéres de fin de partie
	public enum FinDePartie {NON, MATCHNUL, ORDI_GAGNE, HUMAIN_GAGNE};
	public static int HUMAIN = 0;
	public static int ORDI = 1;
	
	public void afficherJeu();
	public void setJoueur(int j);
	public void supprimerAction(Action action);
	
	public Action demanderAction();
	
	public List<Action> coups_possibles();
	public FinDePartie testFin();
	
	public boolean jouerAction(Action action);
	
	public int getJoueur();
	public int getNbCoups();
	
	public Object[][] getPlateau();
	
	public Etat cloneable();
	
}
