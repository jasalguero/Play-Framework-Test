package controllers;

import models.User;

/**
 * Created with IntelliJ IDEA.
 * user: jsalguero
 */
public class SecurityController extends Secure.Security {

    /**
     * Login the user
     * @param email
     * @param password
     * @return
     */
    public static boolean authenticate(String email, String password) {
        User user = User.connect(email,password);
        boolean result = (user != null && user.password.equals(password));

        if (result){
            session.put("username",user.email);
        }
        return (user != null);
    }

    /**
     * Logout the user, clearing the cookie and the session
     * @throws Throwable
     */
    public static void logout() throws Throwable {
        session.clear();
        response.removeCookie("rememberme");
        flash.success("secure.logout");
    }

    /**
     * Check the user has the right profile
     * @param profile
     * @return
     */
    static boolean check(String profile) {
        User user = User.find("byEmail", connected()).first();
        if ("administrator".equals(profile)) {
            //return user.admin;
            return false;
        }
        else {
            return false;
        }
    }
}
