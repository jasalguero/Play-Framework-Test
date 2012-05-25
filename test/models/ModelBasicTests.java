package models;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import play.Logger;
import play.modules.morphia.Blob;
import play.test.MorphiaFixtures;
import play.test.UnitTest;
import utils.Constants;

public class ModelBasicTests extends UnitTest {
	
	@BeforeClass
	public static void setUp() {
		Logger.info("Deleting all models");
		MorphiaFixtures.deleteAllModels();
		MorphiaFixtures.loadModels("data/countries.yml");
	}
	
	@AfterClass
	public static void destroyData(){
		Logger.info("Deleting all models");
		MorphiaFixtures.deleteAllModels();
	}

	@Test
	public void checkCountries() {
		Logger.info("Inside countries model test");
		// Retrieve the list of countries
		List<Country> countryList = Country.findAll();

		// Test size
		assertTrue(countryList.size() == 3);

		Country global = Country.find("byName","GLOBAL").first();

		// Test it exists
		assertEquals(global.name, "GLOBAL");
	}

	@Test
	public void createUser() {
		Logger.info("Inside user model test");
		// Retrieve country
		Country global = Country.find("byName","GLOBAL").first();

		// Get user list
		List<User> beforeList = User.findAll();
		
		// Create a new user and save it
		new User("test@senseship.com", "1234", "firstUserTest", global, Constants.UserType.ADMIN.getId()).save();
	

		// Retrieve the user with e-mail address test@senseship.com
		User admin = User.find("byEmail", "test@senseship.com").first();

		// Test
		assertNotNull(admin);
		assertEquals("firstUserTest", admin.username);
		assertTrue(beforeList.size() < User.findAll().size());
		
		//admin.delete();
		//assertTrue(beforeList.size() == User.findAll().size());	
	}
	
	@Test
	public void createProject(){
		Logger.info("Inside project model test");
		//Create new project
		Project project = new Project();
		
		//Initialize
		project.description = "Test description";
		project.headline = "Test headline";
		project.name = "Test name";
		project.nonProfit = true;
		project.goal = "Test goal";
		
		project.save();
		
		// Retrieve the project
		Project storedProject = Project.find("byName", "Test name").first();
		
		//Test
		assertNotNull(storedProject);
		assertEquals("Test headline", project.headline);
		assertEquals("Test description", project.description);
		assertEquals("Test goal", project.goal);
		assertTrue(project.nonProfit);
		
	}
	
	@Test
	public void createImage(){
		Logger.info("Inside image model test");
		String textDesc = "Test desc";
		String textHeadline = "Test headline";
		
		//Create new image
		Image image = new Image();
		image.description = textDesc;
		image.headline = textHeadline;
		image.isMain = true;
		
		File file = new File("/Users/jsalguero/Desktop/testImage.jpg");
		
		assertNotNull(file);
		Logger.info("image size %d", file.length());
		
		image.image = new Blob(file,file.getName());
		image.save();
		
		Image newImage = Image.find("byHeadline",textHeadline).first();
		
		//Test
		assertNotNull(newImage);
		assertEquals(newImage.description, textDesc);
		assertEquals(newImage.headline, textHeadline);
		assertNotNull(newImage.image);
	}

}
