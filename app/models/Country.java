package models;

import play.*;
import play.modules.morphia.Model;


import java.util.*;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class Country extends Model {
	
	public String name;
}
