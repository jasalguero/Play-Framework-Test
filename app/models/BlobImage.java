package models;

import java.io.File;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

import play.modules.morphia.Blob;
import play.modules.morphia.Model;
import utils.S;

@Entity
public class BlobImage extends Model{
	
	public Blob image;	
	public String headline;
	public String description;
	public Boolean isMain;
	
	@Reference(ignoreMissing=true)
	public Project project;
	
	public void setImage(File file) {
        String type = "image/" + S.fileExtension(file.getName());
        image = new Blob(file,type);
        save();
    }

}
