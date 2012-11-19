package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import play.modules.morphia.Model;

@Entity
public class Image extends Model {

	public String url;
    public String headline;
    public String description;
    public Boolean isMain;


    @Reference
    private Project project;
}
