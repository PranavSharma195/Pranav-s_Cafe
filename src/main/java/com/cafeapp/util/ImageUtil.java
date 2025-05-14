package com.cafeapp.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Utility class for handling image file
 *         uploads. This class provides methods for extracting the file name
 *         from a {@link Part} object and uploading the image file to a
 *         specified directory on the server.
 */
public class ImageUtil {

	/**
	 * Extracts the file name from the given {@link Part} object based on the
	 * "content-disposition" header.
	 * 
	 * This method parses the "content-disposition" header to retrieve the file name
	 * of the uploaded image. If the file name cannot be determined, a default name
	 * "download.png" is returned.
	 * 
	 * @param part the {@link Part} object representing the uploaded file.
	 * @return the extracted file name. If no filename is found, returns a default
	 *         name "download.png".
	 */
	public String getImageNameFromPart(Part part) {
		// Retrieve the content-disposition header from the part
		String contentDisp = part.getHeader("content-disposition");

		// Split the header by semicolons to isolate key-value pairs
		String[] items = contentDisp.split(";");

		// Initialize imageName variable to store the extracted file name
		String imageName = null;

		// Iterate through the items to find the filename
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// Extract the file name from the header value
				imageName = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}

		// Check if the filename was not found or is empty
		if (imageName == null || imageName.isEmpty()) {
			// Assign a default file name if none was provided
			imageName = "download.png";
		}

		// Return the extracted or default file name
		return imageName;
	}

	/**
	 * Uploads the image file from the given {@link Part} object to a specified
	 * directory on the server.
	 * 
	 * This method ensures that the directory where the file will be saved exists
	 * and creates it if necessary. It writes the uploaded file to the server's file
	 * system. Returns {@code true} if the upload is successful, and {@code false}
	 * otherwise.
	 * 
	 * @param part the {@link Part} object representing the uploaded image file.
	 * @return {@code true} if the file was successfully uploaded, {@code false}
	 *         otherwise.
	 */
	public boolean uploadImage(Part part, String rootPath, String saveFolder) {
		String savePath = getSavePath(saveFolder);
		File fileSaveDir = new File(savePath);

		// Ensure the directory exists
		if (!fileSaveDir.exists()) {
			if (!fileSaveDir.mkdir()) {
				return false; // Failed to create the directory
			}
		}
		try {
			// Get the image name
			String imageName = getImageNameFromPart(part);
			// Create the file path
			String filePath = savePath + "/" + imageName;
			// Write the file to the server
			part.write(filePath);
			return true; // Upload successful
		} catch (IOException e) {
			e.printStackTrace(); // Log the exception
			return false; // Upload failed
		}
	}

	/**
	 * Constructs and returns the absolute path to a specific folder inside the web
	 * application's resources directory.
	 *
	 * @param saveFolder the name of the subfolder (e.g., "/images", "/uploads") to
	 *                   append
	 * @return the full absolute file path for saving resources within the specified
	 *         folder
	 */
	public String getSavePath(String saveFolder) {
		return "/Users/pranavsharma/eclipse-workspace/CafeApp/src/main/webapp/resources" + saveFolder + "/";
	}
}
