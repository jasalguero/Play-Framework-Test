package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import play.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonHelper {

	public static JsonObject getImageUploadResponse(File[] files){
		Logger.info("Generating json response for %s files", files.length);
		JsonObject response = new JsonObject();
		JsonArray elements = new JsonArray();
		
		for(File file : files){
			JsonObject singleResponse = new JsonObject();
			singleResponse.addProperty("name", file.getName());
			singleResponse.addProperty("size", file.length());
			singleResponse.addProperty("url", "http://placehold.it/200x200");
			singleResponse.addProperty("thumbnail_url", "http://placehold.it/80x80");
			singleResponse.addProperty("delete_url", "http://deleteme");
			singleResponse.addProperty("delete_type", "DELETE");
			
			elements.add(singleResponse);
			
		}
		
		response.add("", elements);
		return response;
	}
	
	public static List<FileUploadResponse> getFileUploadResponse(File[] files){
		List<FileUploadResponse> response = new ArrayList<FileUploadResponse>();
		for(File file : files){
			FileUploadResponse element = new FileUploadResponse();
			element.name = file.getName();
			element.size = file.length();
			element.url = "http://placehold.it/200x200";
			element.thumbnail_url = "http://placehold.it/80x80";
			element.delete_url = "http://deleteme";
			element.delete_type ="DELETE";
			
			response.add(element);
		}
		
		return response;
	}
}
