package models;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;

@Entity
public class Category extends Model {

	public String name;

	public String toString() {
		return name;
	}

}
