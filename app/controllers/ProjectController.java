package controllers;

import java.io.File;
import java.util.List;

import models.Category;
import models.City;
import models.BlobImage;
import models.Image;
import models.Project;
import models.User;
import play.Logger;
import play.modules.morphia.Blob;
import play.mvc.Controller;
import utils.FTPHelper;

public class ProjectController extends Controller {

	/**
	 * Create a new project and display the edit project page
	 */
	public static void newProject() {
		Logger.info("Creating new project");

		Project project = new Project();

		// Retrieving user
		// TODO Get the real user when authentication is enabled
		Logger.info("Retrieving project owner %s", "admin");
		User user = User.find("byUsername", "admin").first();
		
		// TODO Fix until better solution for initial null values in select
		City city = City.get();

		if (user != null) {
			Logger.info("User %s retrieved successfully", user.getId());

			// Assign owner
			project.owner = user;
			
			// Assign city
			project.city = city;

			project.save();
			Logger.info("New project created with id %s", project.getId());
			
			redirect("ProjectController.editProject", project.getId().toString());
		} else {
			flash.error("message", "project.creation.error");
			projectList();
		}
	}

	/**
	 * Retrieve the projectId and display the edit project page
	 * 
	 * @param projectId
	 */
	public static void editProject(String idProject) {
		Logger.info("Editing project %s", idProject);

		Project project = Project.findById(idProject);

		notFoundIfNull(project);
		Logger.info("Project %s found", project.getId());

		editProject(project);
	}

	/**
	 * Retrieves the data needed to display the edit form
	 * @param project
	 */
	private static void editProject(Project project) {
		Logger.info("Retrieving images for project %s", project.getId()
				.toString());
		List<Image> images = Image.find("project", project).asList();
		Logger.info("%d images retrieved", images.size());

		Logger.info("Retrieving all categories...");
		List<Category> categories = Category.findAll();
		Logger.info("%d categories retrieved", categories.size());
		
		Logger.info("Retrieving possible cities...");
		List<City> cities = project.getPossibleCities();
		Logger.info("%d cities retrievied", cities.size());
				
		render("Project/editProject.html", project, images, categories, cities);
	}

	/**
	 * Save the project and show the edit project page
	 * 
	 * @param project
	 */
	public static void saveProject(Project project) {
		Logger.info("Updating project %s", project.getId());

		project.save();
		flash.success("project.updated.successfully");

		redirect("ProjectController.editProject", project.getId().toString());
	}

	/**
	 * Uploads an image to the db and associate it to the project
	 * 
	 * @param photo
	 */
	/*public static void saveImage(String projectId, File photo) {
		Logger.info("Saving image");
		notFoundIfNull(photo);

		Logger.info("Retrieving project %s for the image", projectId);
		Project project = Project.findById(projectId);

		if (project != null) {
			Logger.info("Project with id %s found", project.getId());

			Image image = new Image();
			image.project = project;

			Logger.info("Image size %d", photo.length());
			image.image = new Blob(photo, photo.getName());
			image.save();
			Logger.info("Image with id %s saved", image.image.length());

			flash.success("project.image.upload.success");

			editProject(project);
		} else {
			Logger.error("Project %s not found in the db!", projectId);
			render("errors/500.html");
		}
	}*/
	
	public static void saveImage(String projectId, File photo){
		Logger.info("Saving image");
		notFoundIfNull(photo);

		Logger.info("Retrieving project %s for the image", projectId);
		Project project = Project.findById(projectId);

		if (project != null) {
			
			String remoteUrl = FTPHelper.uploadImageToHost(photo, projectId);
						
			Logger.info("Project with id %s found", project.getId());

			Image image = new Image();
			image.project = project;
			image.url = remoteUrl;

			image.save();
			Logger.info("Image uploaded successfully with url: %s", image.url);

			flash.success("project.image.upload.success");

			redirect("ProjectController.editProject", project.getId().toString());
		} else {
			Logger.error("Project %s not found in the db!", projectId);
			render("errors/500.html");
		}
	}

	/**
	 * Create new project from form
	 * 
	 * @param project
	 */
	public static void createProject(Project project) {
		// Handle errors
		if (validation.hasErrors()) {
			render("Project/editProject.html", project);
		}

		// Hack because checkbox binding issue
		if (params.get("project.nonProfit") == null) {
			project.nonProfit = false;
		} else {
			project.nonProfit = true;
		}

		// Create the project
		project.save();

		// flash.success("Project successfully created");

		// Go to the project list page
		projectList();
	}

	/**
	 * Shows a list of all the created projects in the system
	 */
	public static void projectList() {
		Logger.info("Retrieving all the projects");
		List<Project> projects = Project.findAll();

		render("Project/projectList.html", projects);
	}

	/**
	 * Displays the project profile page
	 * 
	 * @param idProject
	 */
	public static void showProject(String idProject) {
		Logger.info("Showing project: ", idProject);
		Project project = Project.findById(idProject);
		// Redirect to the list if there's no project
		if (project == null) {
			projectList();
		} else {
			Logger.info("Project %s found", idProject);
			render("Project/project.html", project);
		}
	}

	/**
	 * Deletes a project based on the id
	 * 
	 * @param idProject
	 */
	public static void deleteProject(String idProject) {
		Logger.info("Deleting project: %s", idProject);
		Project project = Project.findById(idProject);
		if (project != null) {
			Logger.info("Project %s found!", idProject);
			project.delete();
			flash.success("project.deleted.successfully");
		}
		projectList();
	}
}
