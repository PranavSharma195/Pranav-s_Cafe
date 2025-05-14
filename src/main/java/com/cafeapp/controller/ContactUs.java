package com.cafeapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Pranav_Sharma LMU ID: 23048577 Handles requests related to the
 * "Contact Us" page of the cafe application. Serves the contact information or
 * form through a JSP page.
 * URL pattern: /contact
 */
@WebServlet("/contact")
public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for the ContactUs servlet.
	 */
	public ContactUs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles GET requests by forwarding the user to the contact page.
	 *
	 * @param request  The HttpServletRequest object containing the client request.
	 * @param response The HttpServletResponse object to send the response.
	 * @throws ServletException If the request could not be handled.
	 * @throws IOException      If an input or output error occurs.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/contactus.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet, ensuring consistent rendering
	 * of the contact page.
	 *
	 * @param request  The HttpServletRequest object containing the client request.
	 * @param response The HttpServletResponse object to send the response.
	 * @throws ServletException If the request could not be handled.
	 * @throws IOException      If an input or output error occurs.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
