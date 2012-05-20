package utils;

import models.Country;
import models.User;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import play.test.MorphiaFixtures;

@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
    	Logger.info("Checking database for bootstrap");
        // Check if the database is empty
    	if(User.find("byUsername", "admin").first() == null){
    		Logger.info("Proceeding to load initial data");
    		MorphiaFixtures.loadModels("initial-data.yml");
    	}
    }
 
}