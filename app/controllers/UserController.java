package controllers;

import models.Country;
import models.User;
import play.Logger;
import play.mvc.Controller;
import play.data.validation.*;

import java.util.List;

public class UserController extends Controller {

    /**
     * Display the registration form
     */
    public static void showForm() {
        List<Country> allCountries = Country.q().order("_id").asList();
        render("user/newUser.html", allCountries);
    }
    
    public static void createUser(@Valid User user){
        if (Validation.hasErrors()){
            Logger.info("Found errors");
            Validation.keep();
            params.flash();
            showForm();
        }else{
            Logger.info("Creating new user...");
            user.save();
            session.put("username",user.email);
            ProjectController.projectList();
        }

    }
}
