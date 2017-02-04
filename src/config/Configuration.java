package config;

public class Configuration {

	public static Configuration INSTANCE = new Configuration();
	
	private final int TEMPS = 1000; // 1000 ms
	
	private Configuration() {
	}
	
	public static Configuration getInstance() {
		return INSTANCE;
	}
	
	public int getTemps() {
		return TEMPS;
	}
	
}
