package com.cafeapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cafeapp.model.UserModel;
import com.cafeapp.service.ProfileService;
import com.cafeapp.util.PasswordUtil;
import com.cafeapp.util.SessionUtil;

@WebServlet("/adminprofile")
public class AdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileService profileService;

	public void init() {
		profileService = new ProfileService();
	}

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
