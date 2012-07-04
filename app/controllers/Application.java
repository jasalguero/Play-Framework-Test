package controllers;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import models.BlobImage;
import play.Logger;
import play.i18n.Lang;
import play.mvc.Controller;
import utils.FTPHelper;
import utils.JsonHelper;

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
		List<BlobImage> images = BlobImage.findAll();

		render("Application/imageList.html", images);
	}

	/**
	 * Displays an image stores in the DB
	 * 
	 * @param id
	 */
	public static void displayImage(String idImage) {
		Logger.info("Displaying image with id %s", idImage);
		final BlobImage image = BlobImage.findById(idImage);
		notFoundIfNull(image);
		response.setContentTypeIfNotSet("image/jpg");
		renderBinary(image.image.get());
	}

	public static void homepage() {
		render("homepage.html");
	}
	
	public static void imageUploader(){
		render("imageUploadTest.html");
	}
	
	public static void uploadFiles(File[] files, String projectId){
		if (request.isNew){
			Logger.info("Uploading files...");
		}
		
		renderJSON(JsonHelper.getFileUploadResponse(files));
	}
	

	//REMOVE?
	public static void upload(String qqfile, String projectId) {
		if (request.isNew) {
			Logger.info("Name of the file %s", qqfile);
			// Another way I used to grab the name of the file
			String fileName = request.headers.get("x-file-name").value();

			try {
				InputStream data = request.body;
				//File targetFile = new File("upload/" + qqfile);
				//FTPHelper.uploadImageToHost(targetFile, projectId);

				FTPHelper.uploadProjectImageToHost(data, fileName, projectId);

			} catch (Exception ex) {

				// catch file exception
				// catch IO Exception later on
				renderJSON("{success: false}");
			}

		}

		renderJSON("{success: true}");
	}
	
	public static void upload(File file){
		Logger.info(file.getName());
	}

}
