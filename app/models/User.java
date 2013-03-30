package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

import play.data.validation.CheckWith;
import play.data.validation.Required;
import play.modules.morphia.Model;

import controllers.validators.*;

@Entity
public class User extends Model {
	
	@Required
	@CheckWith(value = UserExist.class, message = "views.user.error.exists")
	public String email;
	@Required
	public String password;
	public String username;
	public int userType;

//	@Reference
//	public Country country;

	public User(String email, String password, String fullname, int userType) {
		this.email = email;
		this.password = password;
		this.username = fullname;
//		this.country = country;
		this.userType = userType;
	}

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

}
