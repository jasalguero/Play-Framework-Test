package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

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
