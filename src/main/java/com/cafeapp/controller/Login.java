package com.cafeapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.model.UserModel;
import com.cafeapp.service.LoginService;
import com.cafeapp.util.CookieUtil;
import com.cafeapp.util.SessionUtil;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Servlet implementation class Login
 * 
 *         Handles the login process for users. Verifies user credentials and
 *         redirects to either the admin dashboard or the home page based on
 *         user roles.
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles GET requests for the login page.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to process user login. Validates user credentials, sets
	 * the session, and redirects based on user role.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserModel userModel = new UserModel(username, password);

		LoginService loginService = new LoginService();
		Boolean loginResult = loginService.loginUser(userModel);

		if (loginResult == null) {
			// Handle database connection error
			request.setAttribute("error", "Database connection error.");
			request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
		} else if (loginResult) {
			// Set session attribute for the logged-in user
			SessionUtil.setAttribute(request, "username", username);

			// Check if user is admin or regular user and set cookie accordingly
			if ("Pranav".equals(username)) {
				CookieUtil.addCookie(response, "role", "admin", 5 * 30); // Expires in 150 seconds
				response.sendRedirect(request.getContextPath() + "/admindashboard"); // Redirect to admin dashboard
			} else {
				CookieUtil.addCookie(response, "role", "customer", 5 * 30); // Expires in 150 seconds
				response.sendRedirect(request.getContextPath() + "/home"); // Redirect to user home
			}
		} else {
			// Handle failed login attempt
			request.setAttribute("error", "Invalid username or password.");
			request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
		}

	}

}
