package controllers;

import java.util.List;

import models.Country;
import models.User;
import play.mvc.*;

public class UserController extends Controller {

    public static void showForm() {
        List<Country> allCountries = Country.q().order("_id").asList();
        render("User/newUser.html", allCountries);
    }

}
