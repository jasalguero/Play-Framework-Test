package controllers;

import models.Country;
import models.User;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class UserController extends Controller {

    /**
     * Display the registration form
     */
    public static void showForm() {
        List<Country> allCountries = Country.q().order("_id").asList();
        render("User/newUser.html", allCountries);
    }

    public static void createUser(User user){

        if (userExists(user)){
            Logger.info("User already exists, writing error");
            flash.error("views.user.error.exists");
            showForm();
        }else{
            Logger.info("Creating new user...");
            user.save();
            session.put("username",user.email);
            ProjectController.projectList();
        }

    }

    /**
     *
     * @param user
     */
    private static boolean userExists(User user) {
        User oldUser = User.find("byEmail", user.email).first();
        if (oldUser != null){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

}
