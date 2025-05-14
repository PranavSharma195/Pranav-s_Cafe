package com.cafeapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cafeapp.model.UserModel;
import com.cafeapp.service.ProfileService;
import com.cafeapp.util.PasswordUtil;
import com.cafeapp.util.SessionUtil;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Servlet implementation class Profile
 * 
 *         Handles viewing and updating the user profile. The user can view
 *         their details and update their profile information such as name,
 *         email, password, and phone number.
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileService profileService;

	/**
	 * Initializes the Profile service to interact with the database.
	 */
	public void init() {
		profileService = new ProfileService();
	}

	/**
	 * Handles GET requests to display the user's profile information. If the user
	 * is logged in, their profile information is displayed. If the profile update
	 * was successful, a success message is shown. Otherwise, the user is redirected
	 * to the login page.
	 * 
	 * @param req  the HttpServletRequest object containing the request parameters
	 * @param resp the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) SessionUtil.getAttribute(req, "username");

		if (username != null) {
			UserModel user = profileService.getUserByUsername(username);
			req.setAttribute("user", user);

			if ("true".equals(req.getParameter("updated"))) {
				req.setAttribute("success", "Profile updated successfully.");
			}

			req.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	/**
	 * Handles POST requests for updating the user's profile information. This
	 * method processes the form input, encrypts the password, and updates the
	 * user's profile in the database. After a successful update, the user is
	 * redirected to the profile page with a success message.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) SessionUtil.getAttribute(request, "username");

		// If the user is not logged in, redirect to the login page
		if (username == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// Retrieve form parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String rawPassword = request.getParameter("password");
		String encryptedPassword = PasswordUtil.encrypt(username, rawPassword);
		String phone = request.getParameter("phone");

		// Create a UserModel object with updated information
		UserModel updatedUser = new UserModel();
		updatedUser.setUsername(username);
		updatedUser.setName(name);
		updatedUser.setEmail(email);
		updatedUser.setPassword(encryptedPassword); // ← now encrypted
		updatedUser.setPhoneNumber(phone);

		// Update the user profile in the database using the ProfileService
		boolean success = profileService.updateUserProfile(updatedUser);

		// If the update is successful, redirect to the profile page with a success
		// message
		if (success) {
			response.sendRedirect(request.getContextPath() + "/profile?updated=true");
		} else {

			// If the update fails, show an error message and re-display the profile page
			// with current user details
			request.setAttribute("error", "Failed to update profile.");
			request.setAttribute("user", profileService.getUserByUsername(username));
			request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
		}
	}
}
