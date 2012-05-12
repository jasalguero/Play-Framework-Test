package models;

import java.io.File;

import com.google.code.morphia.annotations.Entity;

import play.modules.morphia.Blob;
import play.modules.morphia.Model;

@Entity
public class Image extends Model{
	
	public Blob image;	
	public String headline;
	public String description;
	public Boolean isMain;
	
	public void setImage(File file) {
        //String type = "image/" + S.fileExtension(file.getName());
        image = new Blob(file,file.getName());
        save();
    }

}
