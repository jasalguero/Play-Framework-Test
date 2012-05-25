package utils;

/**
 * Application constants used across the system
 * @author jsalguero
 */
public class Constants {
	
	/* =========================================== */
	/* 					COUNTRIES				   */
	/* =========================================== */
	
	public static final String COUNTRY_GERMANY = "static.data.germany";
	public static final String COUNTRY_GLOBAL = "static.data.global";
	public static final String COUNTRY_PORTUGAL = "static.data.portugal";
	
	
	/**
	 * Enum containing all the user types in the system
	 */
	public enum UserType {
		ADMIN(0, "user.type.admin"),
		OWNER(1, "user.type.owner");
		
		private int id;
		private String description;
		
		private UserType(int id, String description){
			this.id = id;
			this.description = description;
		}
		
		public int getId(){
			return id;
		}
		
		public String getDescription(){
			return description;
		}
		
	}

}
