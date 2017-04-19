/**
 * 
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 19, 2017
 */
public class StatistiqueDAO {

	public final static String SIMUS = "simulations";
	public final static String POS = "position";
	public final static String RECOMP = "recompenses";
	public final static String TEMP = "temps(ms)";
	
	private static StatistiqueDAO INSTANCE = null;
	private FileWriter writer;
	private final String file = "stats.csv";
	private final String separateur = ",";
	
	/*
	 * Pour donner une idée de comment récupérer les stats dans un tableur
	 * ca marche sur mon openoffice
	 */
	private StatistiqueDAO() {
		try {
			File f = new File(file);
			if (!f.exists()) {
				writer = new FileWriter(file, false);
				writer.append(TEMP);
				writer.append(separateur);
				writer.append(SIMUS);
				writer.append(separateur);
				writer.append(POS);
				writer.append(separateur);
				writer.append(RECOMP);
				writer.append(separateur);
				writer.append("\n");
				writer.flush();
			} else {
				writer = new FileWriter(file, true);
				writer.append("\n");
				writer.flush();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static StatistiqueDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StatistiqueDAO();
		}
		return INSTANCE;
	}
	
	public void ecrire(double valeur) {
		try {
			writer.append(valeur + "");
			writer.append(separateur);
			writer.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void nouvelleLigne() {
		try {
			writer.append("\n");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
