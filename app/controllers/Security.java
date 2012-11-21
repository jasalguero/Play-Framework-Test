package controllers;

import models.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jsalguero
 */
public class Security extends Secure.Security {

    static boolean authenticate(String username, String password) {
        User user = User.connect(username,password);
        return (user != null);
    }
}
