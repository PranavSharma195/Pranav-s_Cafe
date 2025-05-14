package com.cafeapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.util.CookieUtil;
import com.cafeapp.util.SessionUtil;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Servlet implementation class
 *         LogoutController
 * 
 *         Handles the logout process by invalidating the session and deleting
 *         authentication cookies, then redirects the user to the login page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests for logging out.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performLogout(request, response);
	}

	/**
	 * Handles POST requests for logging out (e.g., form submissions).
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performLogout(request, response);
	}

	/**
	 * Performs the logout process: 1. Deletes all authentication cookies. 2.
	 * Invalidates the current session. 3. Redirects the user to the login page.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws IOException if an I/O error occurs
	 */
	private void performLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. Delete all authentication cookies
		CookieUtil.deleteCookie(response, "role");

		// 2. Invalidate the session
		SessionUtil.invalidateSession(request);

		// 3. Redirect to home page
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
