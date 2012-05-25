package models;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class User extends Model {
	
	public String email;
	public String password;
	public String username;
	public int userType;

	@Reference
	public Country country;

	public User(String email, String password, String fullname, Country country, int userType) {
		this.email = email;
		this.password = password;
		this.username = fullname;
		this.country = country;
		this.userType = userType;
	}

}
