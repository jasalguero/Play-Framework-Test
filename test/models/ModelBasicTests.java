package models;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class ModelBasicTests extends UnitTest {
	
	@Before
	public void setUp() {
	    Fixtures.deleteAllModels();
	    Fixtures.loadModels("data/countries.yml");
	}

	@Test
	public void checkCountries() {

		// Retrieve the list of countries
		List<Country> countryList = Country.findAll();

		// Test size
		assertTrue(countryList.size() == 3);

		Country global = Country.find("byName","GLOBAL").first();

		// Test it exists
		assertEquals(global.name, "GLOBAL");
	}

	@Test
	public void checkCreateAndRetrieveUser() {
		
		// Retrieve country
		Country global = Country.find("byName","GLOBAL").first();

		// Get user list
		List<User> beforeList = User.findAll();
		
		// Create a new user and save it
		new User("test@senseship.com", "1234", "firstUserTest", global).save();

		// Retrieve the user with e-mail address test@senseship.com
		User admin = User.find("byEmail", "test@senseship.com").first();

		// Test
		assertNotNull(admin);
		assertEquals("firstUserTest", admin.username);
		assertTrue(beforeList.size() < User.findAll().size());
		
		admin.delete();
		assertTrue(beforeList.size() == User.findAll().size());	
	}

}
