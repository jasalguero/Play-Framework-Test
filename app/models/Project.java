package models;

import play.*;
import play.data.validation.Required;
import play.modules.morphia.Blob;
import play.modules.morphia.Model;

import java.io.File;
import java.util.*;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

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
	
	@Reference
	public User owner;
	
}
