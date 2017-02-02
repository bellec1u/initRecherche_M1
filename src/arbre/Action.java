/**
 * 
 */
package arbre;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Jan 26, 2017
 */
public interface Action {

	public int getLigne();
	public int getColonne();
	public boolean estGagnante(Etat etat);
	
}
