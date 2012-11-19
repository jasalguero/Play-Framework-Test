package models;

import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Image extends Model {

	public String name;

	public String toString() {
		return name;
	}

}
