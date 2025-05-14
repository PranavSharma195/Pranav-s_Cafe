package com.cafeapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Handles HTTP requests for the "About
 *         Us" page of the cafe application. Forwards the request to the
 *         corresponding JSP page for rendering the content. This servlet
 *         supports both GET and POST requests, with POST delegating to GET.
 */
@WebServlet("/aboutus")
public class AboutUs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for the AboutUs servlet.
	 */
	public AboutUs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles GET requests by forwarding the request to the About Us JSP page.
	 *
	 * @param request  The HttpServletRequest object that contains the request the
	 *                 client has made.
	 * @param response The HttpServletResponse object that contains the response the
	 *                 servlet sends.
	 * @throws ServletException If the request could not be handled.
	 * @throws IOException      If an input or output error occurs while handling
	 *                          the request.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/aboutus.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating them to the doGet() method. This ensures
	 * both GET and POST access result in rendering the same content.
	 *
	 * @param request  The HttpServletRequest object that contains the request the
	 *                 client has made.
	 * @param response The HttpServletResponse object that contains the response the
	 *                 servlet sends.
	 * @throws ServletException If the request could not be handled.
	 * @throws IOException      If an input or output error occurs while handling
	 *                          the request.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
