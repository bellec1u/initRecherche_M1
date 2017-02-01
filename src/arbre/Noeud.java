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
	public Noeud retournerEnfant(int indice);;
	
	public double resultat();
	public double rapportVictoireSimulation();
	
	public int retournerNbEnfant();
	public int retournerNbSimulation();
	public int retournerNbVictoire();
	public int autreJoueur();
	public void addSimulation();
	
	public void visiter(double recompense);
	public boolean testActionGagnanteOrdi(Action a);
	public Etat getEtat();
	public void setNbVictoires(double recompense);
}
