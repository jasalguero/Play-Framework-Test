package utils;

import models.Country;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import play.test.MorphiaFixtures;

@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
    	
        // Check if the database is empty
        if(Country.count() == 0) {
        	MorphiaFixtures.loadModels("initial-data.yml");
        }
    }
 
}