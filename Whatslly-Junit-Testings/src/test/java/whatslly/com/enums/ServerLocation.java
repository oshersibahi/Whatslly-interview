package whatslly.com.enums;

/**
 * Enum contains country names and matched labels. 
 * @author Osher Sibahi
 */
public enum ServerLocation {
	
	US("United States","us") , BRAZIL("Brazil","br");
	
	public final String name;
	public final String label;
	
	ServerLocation(String name, String label) {
		this.name = name;
		this.label = label;
	} 
	
	
};