package models;

import com.google.code.morphia.annotations.Entity;
import play.modules.morphia.Model;

@Entity
public class Category extends Model {

	public String name;

	public String toString() {
		return name;
	}

}
