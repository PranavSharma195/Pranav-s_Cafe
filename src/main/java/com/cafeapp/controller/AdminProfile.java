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
 * Author: Pranav_Sharma LMU ID: 23048577 Handles the admin profile page
 * functionality in the cafe application. Supports displaying current admin
 * profile information and updating profile details.
 * 
 * Accessible via the "/adminprofile" URL pattern.
 * 
 * 
 */
@WebServlet("/adminprofile")
public class AdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileService profileService;

	/**
	 * Initializes the servlet and instantiates the ProfileService used for fetching
	 * and updating user data.
	 */
	public void init() {
		profileService = new ProfileService();
	}

	/**
	 * Handles GET requests to display the admin's profile. Retrieves the current
	 * user's details from the session and fetches user data from the database. Also
	 * handles success message display after a profile update.
	 * 
	 * @param req  The HttpServletRequest object that contains the request from the
	 *             client.
	 * @param resp The HttpServletResponse object that contains the response to be
	 *             sent to the client.
	 * @throws ServletException If the request could not be handled.
	 * @throws IOException      If an input or output error occurs.
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

			req.getRequestDispatcher("WEB-INF/pages/adminprofile.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	/**
	 * Handles POST requests to update the admin's profile information. Retrieves
	 * form data, encrypts the password, updates the user in the database, and
	 * redirects or displays errors based on the result.
	 * 
	 * @param request  The HttpServletRequest object that contains the request from
	 *                 the client.
	 * @param response The HttpServletResponse object that contains the response to
	 *                 be sent to the client.
	 * @throws ServletException If the request could not be handled.
	 * @throws IOException      If an input or output error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) SessionUtil.getAttribute(request, "username");

		if (username == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		// Encrypt password
		String encryptedPassword = PasswordUtil.encrypt(username, password);

		UserModel updatedUser = new UserModel();
		updatedUser.setUsername(username);
		updatedUser.setName(name);
		updatedUser.setEmail(email);
		updatedUser.setPassword(encryptedPassword);
		updatedUser.setPhoneNumber(phone);

		boolean success = profileService.updateUserProfile(updatedUser);

		if (success) {
			response.sendRedirect(request.getContextPath() + "/adminprofile?updated=true");
		} else {
			request.setAttribute("error", "Failed to update profile.");
			request.setAttribute("user", profileService.getUserByUsername(username));
			request.getRequestDispatcher("WEB-INF/pages/adminprofile.jsp").forward(request, response);
		}
	}
}
