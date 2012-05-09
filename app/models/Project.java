package models;

import play.*;
import play.data.validation.Required;
import play.modules.morphia.Model;

import java.util.*;

import com.google.code.morphia.annotations.Entity;

@Entity
public class Project extends Model {
 
	@Required
	public String name;
	
	@Required
	public String headline;
	
	@Required
	public String description;
	
	@Required
	public String goal;
	
	public Boolean nonProfit;
	
	//Contact
	
	//String photoUrl;
}
