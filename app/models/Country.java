package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Country extends GenericModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id")
	public Integer id;
	
	public String name;

	public Integer getId() {
		return id;
	}
	
	@Override
	public Object _key() {
		return getId();
	}
}
