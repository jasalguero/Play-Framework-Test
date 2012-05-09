package models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

import play.db.jpa.GenericModel;
import play.modules.morphia.Model;

@Entity
public class User extends Model {
	
	public String email;
	public String password;
	public String username;

	@Reference
	public Country country;

	public User(String email, String password, String fullname, Country country) {
		this.email = email;
		this.password = password;
		this.username = fullname;
		this.country = country;
	}

}
