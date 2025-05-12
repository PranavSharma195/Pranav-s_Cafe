package com.cafeapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cafeapp.model.UserModel;
import com.cafeapp.service.ProfileService;
import com.cafeapp.util.PasswordUtil;
import com.cafeapp.util.SessionUtil;

@WebServlet("/profile")
public class Profile extends HttpServlet {
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

			req.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(req, resp);
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
		String rawPassword = request.getParameter("password");
		String encryptedPassword = PasswordUtil.encrypt(username, rawPassword);
		String phone = request.getParameter("phone");

		UserModel updatedUser = new UserModel();
		updatedUser.setUsername(username);
		updatedUser.setName(name);
		updatedUser.setEmail(email);
		updatedUser.setPassword(encryptedPassword); // ← now encrypted
		updatedUser.setPhoneNumber(phone);

		boolean success = profileService.updateUserProfile(updatedUser);

		if (success) {
			response.sendRedirect(request.getContextPath() + "/profile?updated=true");
		} else {
			request.setAttribute("error", "Failed to update profile.");
			request.setAttribute("user", profileService.getUserByUsername(username));
			request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
		}
	}
}
