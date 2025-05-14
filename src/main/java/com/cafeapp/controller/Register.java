package com.cafeapp.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cafeapp.model.UserModel;
import com.cafeapp.service.RegisterService;
import com.cafeapp.util.ImageUtil;
import com.cafeapp.util.ValidationUtil;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Servlet implementation class Register
 * 
 *         Handles the user registration functionality. This servlet validates
 *         the input form, processes image uploads, and interacts with the
 *         RegisterService to add a new user to the database.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register", "/" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegisterService registerService = new RegisterService();

	/**
	 * Default constructor for the Register servlet.
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles the HTTP GET request. It forwards the request to the registration
	 * page for user input.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send the response
	 * @throws ServletException if an error occurs during the request handling
	 * @throws IOException      if an input-output error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * Handles the HTTP POST request. It processes the form data, validates it,
	 * handles image upload, and either forwards the request back to the
	 * registration page with errors or attempts to register the user.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send the response
	 * @throws ServletException if an error occurs during the request handling
	 * @throws IOException      if an input-output error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		validateRegistrationForm(request, response);
	}

	/**
	 * Validates the registration form data. This method performs validation for
	 * various fields such as name, username, phone number, email, and password. It
	 * also handles file upload for profile images. If validation fails, it
	 * repopulates the form and redirects the user back to the registration page. If
	 * validation is successful, it attempts to register the user.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send the response
	 * @throws ServletException if an error occurs during the request handling
	 * @throws IOException      if an input-output error occurs
	 */
	public void validateRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract all parameters
		String fullName = request.getParameter("name");
		String userName = request.getParameter("username");
		String phoneNumber = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		// Handle image file upload
		Part imagePart = request.getPart("imagePath"); // Get the image part from the form
		String imageFileName = null;
		boolean imageUploaded = false;

		if (imagePart != null && imagePart.getSize() > 0) {
			ImageUtil imageUtil = new ImageUtil();
			String rootPath = request.getServletContext().getRealPath("/");
			String saveFolder = "profile_images"; // Folder name to store the images

			imageUploaded = imageUtil.uploadImage(imagePart, rootPath, saveFolder);
			if (imageUploaded) {
				// Get the uploaded image name
				imageFileName = imageUtil.getImageNameFromPart(imagePart);
			}
		}

		// Validate full name
		if (ValidationUtil.isNullOrEmpty(fullName)) {
			request.setAttribute("nameError", "Full name is required.");
		} else if (!ValidationUtil.isAlphabetic(fullName)) {
			request.setAttribute("nameError", "Full name must not contain numbers or special characters.");
		}

		// Validate username
		if (ValidationUtil.isNullOrEmpty(userName)) {
			request.setAttribute("usernameError", "User name is required.");
		} else if (!ValidationUtil.isAlphanumericStartingWithLetter(userName)) {
			request.setAttribute("usernameError", "User name should start with a letter and can contain numbers.");
		}

		// Validate phone number
		if (ValidationUtil.isNullOrEmpty(phoneNumber)) {
			request.setAttribute("phoneError", "Phone number is required.");
		} else if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
			request.setAttribute("phoneError", "Phone number must start with '98' and be 10 digits long.");
		}

		// Validate email
		if (ValidationUtil.isNullOrEmpty(email)) {
			request.setAttribute("emailError", "Email is required.");
		} else if (!ValidationUtil.isValidEmail(email)) {
			request.setAttribute("emailError", "Invalid email format.");
		}

		// Validate password
		if (ValidationUtil.isNullOrEmpty(password)) {
			request.setAttribute("passwordError", "Password is required.");
		} else if (!ValidationUtil.isValidPassword(password)) {
			request.setAttribute("passwordError",
					"Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.");
		}

		// Confirm password
		if (ValidationUtil.isNullOrEmpty(confirmPassword)) {
			request.setAttribute("confirmPasswordError", "Please retype the password.");
		} else if (!ValidationUtil.doPasswordsMatch(password, confirmPassword)) {
			request.setAttribute("confirmPasswordError", "Passwords do not match.");
		}

		if (!ValidationUtil.isNullOrEmpty(userName) && registerService.isUsernameTaken(userName)) {
			request.setAttribute("usernameError", "This username is already taken.");
		}

		if (!ValidationUtil.isNullOrEmpty(email) && registerService.isEmailTaken(email)) {
			request.setAttribute("emailError", "This email is already registered.");
		}

		if (!ValidationUtil.isNullOrEmpty(phoneNumber) && registerService.isPhoneTaken(phoneNumber)) {
			request.setAttribute("phoneError", "This phone number is already registered.");
		}

		// Check for any error flags
		boolean hasErrors = false;
		Enumeration<String> attrNames = request.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String attr = attrNames.nextElement();
			if (attr.endsWith("Error")) {
				hasErrors = true;
				break;
			}
		}

		if (hasErrors) {
			// Repopulate form fields on error
			request.setAttribute("nameValue", fullName);
			request.setAttribute("usernameValue", userName);
			request.setAttribute("phoneValue", phoneNumber);
			request.setAttribute("emailValue", email);

			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}
		// --- IF VALID, ADD TO DATABASE ---
		UserModel user = new UserModel(fullName, email, userName, password, phoneNumber, imageFileName);

		boolean success = registerService.addUser(user);
		if (success) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.setAttribute("formError", "Something went wrong while registering. Please try again.");
			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
		}
	}

}
