package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.GenericModel;

@Entity
public class User extends GenericModel {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	public String email;
	public String password;
	public String username;

	@ManyToOne()
	@JoinColumn(name="country_id")
	public Country country;

	public User(String email, String password, String fullname, Country country) {
		this.email = email;
		this.password = password;
		this.username = fullname;
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Object _key() {
		return getId();
	}
}
