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
	
	/*Blob photo;
	
	public void setPhoto(File file) {
        String type = "image/" + S.fileExtension(file.getName());
        photo = new Blob(file, type);
        save();
    }*/
	
	public Boolean nonProfit;
	
}
