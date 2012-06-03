package controllers;

import java.util.List;

import models.Image;
import play.Logger;
import play.i18n.Lang;
import play.mvc.Controller;

public class Application extends Controller {

	public static void changeLang(String lang, String url) {
		if (lang != null) {
			Lang.change(lang);
		}

		redirect(url);
	}

	/**
	 * Shows a list of all the created projects in the system
	 */
	public static void showImageList() {
		List<Image> images = Image.findAll();

		render("Application/imageList.html", images);
	}

	/**
	 * Displays an image stores in the DB
	 * 
	 * @param id
	 */
	public static void displayImage(String idImage) {
		Logger.info("Displaying image with id %s", idImage);
		final Image image = Image.findById(idImage);
		notFoundIfNull(image);
		response.setContentTypeIfNotSet("image/jpg");
		renderBinary(image.image.get());
	}

	public static void homepage(){
		render("homepage.html");
	}
	
}
