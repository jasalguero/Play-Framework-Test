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
	
	
	/* =========================================== */
	/* 					IMAGE REPOSITORY		   */
	/* =========================================== */
	
	public static final String IMAGE_REPO_PROJECT_DIR = "projects";

    /* =========================================== */
	/* 					SOCIAL          		   */
	/* =========================================== */

    public static final String GOOGLE_PLUS_CLIENT_ID = "361469278486.apps.googleusercontent.com";
    public static final String GOOGLE_PLUS_SECRET = "HwTZygZCT7-AhSzEcD6E8pyW";
	
	
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
