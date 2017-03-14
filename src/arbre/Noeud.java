/**
 * 
 */
package arbre;

import java.util.List;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public interface Noeud {

	public boolean estTerminal();
	public boolean resteAction();
	public boolean estRacine();
	
	public List<Action> actionsPossible();
	
	public Noeud ajouterEnfant(Action action);
	public Noeud predecesseur();
	public Noeud retournerEnfant(int indice);
	
	public Action getAction();
	
	public Etat getEtat();
	
	public double resultat();
	public double rapportVictoireSimulation();
	public double retournerNbVictoire();
	
	public int retournerNbEnfant();
	public int retournerNbSimulation();
	public int getInitialJoueur();
	public int getMinOrMax();
	
	public void visiter(double recompense);
	public void setStatistique(int s, double v);
	public void setInitialJoueur(int joueur);
	public void setMinOrMax(int minMax);
	public void afficherStatistiques();
	public void supprimerEnfants();

	public Noeud setAction(Action action);
	
}
