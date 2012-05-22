package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Model;

@Entity
public class City extends Model{

	public String name;
	
	@Reference
	public Country country;
	
}
