package controllers;

import models.Country;
import play.mvc.Controller;

import java.util.List;

public class UserController extends Controller {

    public static void showForm() {
        List<Country> allCountries = Country.q().order("_id").asList();
        render("User/newUser.html", allCountries);
    }

}
