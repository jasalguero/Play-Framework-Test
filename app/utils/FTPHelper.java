package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import play.Logger;

public class FTPHelper {

	private final static String IMAGE_REPO_FTP_HOSTNAME = "ftp.jasalguero.com";
	private final static String IMAGE_REPO_HOSTNAME = "http://www.jasalguero.com/";
	private final static String IMAGE_REPO_BASE_DIR = "/www";
	private final static String IMAGE_REPO_USER = "XXXX";
	private final static String IMAGE_REPO_PWD = "XXXX";

	public static FTPClient getFTPConnection() {

		FTPClient ftp = new FTPClient();
		int reply;

		try {

			Logger.info("Connecting to image repository");
			ftp.connect(IMAGE_REPO_FTP_HOSTNAME);
			ftp.login(IMAGE_REPO_USER, IMAGE_REPO_PWD);
			reply = ftp.getReplyCode();

			if (FTPReply.isPositiveCompletion(reply)) {
				ftp.changeWorkingDirectory(IMAGE_REPO_BASE_DIR);
				Logger.info("-------->>> %s", ftp.printWorkingDirectory());
				Logger.info("Connected Success");
			} else {
				Logger.error("Connection Failed");
				ftp.disconnect();
				ftp = null;
			}

		} catch (SocketException ex) {
			Logger.error("Error while trying to connect to the ImageBak Repository");
			ex.printStackTrace();
			ftp = null;
		} catch (IOException ex) {
			Logger.error("Error while trying to connect to the ImageBak Repository");
			ex.printStackTrace();
			ftp = null;
		}

		return ftp;

	}

	public static String uploadImageToHost(File image, String projectId) {
		String remoteUrl = null;

		FTPClient ftp = getFTPConnection();

		Logger.info("Uploading image %s", image.getName());
		FileInputStream in;
		try {
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			createDirectory(ftp, projectId);
			Logger.info("Target directory: %s", ftp.printWorkingDirectory());
			in = new FileInputStream(image);		
			if (ftp.storeFile(image.getName(), in)){
				remoteUrl = IMAGE_REPO_HOSTNAME + "/" + Constants.IMAGE_REPO_PROJECT_DIR + "/" + projectId + "/" + image.getName();
			}
			Logger.info("ImageBak uploaded sucessfully!");
		} catch (FileNotFoundException e1) {
			Logger.error("Error while creating the Input Stream");
			e1.printStackTrace();
		} catch (IOException e) {
			Logger.error("Error while uploading image: %s", image.getName());
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return remoteUrl;
	}

	private static void createDirectory(FTPClient ftp, String projectId) throws IOException {
		Logger.info("Creating parent directory %s ...", Constants.IMAGE_REPO_PROJECT_DIR);
		ftp.mkd(Constants.IMAGE_REPO_PROJECT_DIR);
		
		Logger.info("Navigating to parent directory");
		ftp.changeWorkingDirectory(Constants.IMAGE_REPO_PROJECT_DIR);
		
		Logger.info("Creating project directory %s ...", projectId);
		ftp.mkd(projectId);
		
		ftp.changeWorkingDirectory(projectId);
	}

	public static String uploadProjectImageToHost(InputStream data,
			String fileName, String projectId) {
		String remoteUrl = null;

		FTPClient ftp = getFTPConnection();

		Logger.info("Uploading image %s", fileName);
		try {
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			createDirectory(ftp, projectId);
			Logger.info("Target directory: %s", ftp.printWorkingDirectory());
			if (ftp.storeFile(fileName, data)){
				remoteUrl = IMAGE_REPO_HOSTNAME + "/" + Constants.IMAGE_REPO_PROJECT_DIR + "/" + projectId + "/" + fileName;
			}
			Logger.info("ImageBak uploaded sucessfully!");
		} catch (FileNotFoundException e1) {
			Logger.error("Error while creating the Input Stream");
			e1.printStackTrace();
		} catch (IOException e) {
			Logger.error("Error while uploading image: %s", fileName);
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return remoteUrl;
		
	}
}
