package controllers;

import play.*;
import play.i18n.Lang;
import play.mvc.*;
import play.mvc.Http.Header;

import java.net.URL;
import java.util.*;

import models.*;

public class Application extends Controller {

	public static void changeLang(String lang, String url) {
		if (lang != null) {
			Lang.change(lang);
		}
		
		redirect(url);
	}

}
