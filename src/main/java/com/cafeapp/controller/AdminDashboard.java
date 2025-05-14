package com.cafeapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.model.ProductModel;
import com.cafeapp.service.ProductService;
import com.cafeapp.service.UserService;

/**
 * Author: Pranav_Sharma LMU ID: 23048577 Handles requests for the admin
 * dashboard of the cafe application. This servlet retrieves product information
 * and user count to display on the admin interface. It supports product search
 * functionality by item name and displays all products if no search term is
 * provided. Accessible via the "/admindashboard" URL pattern.
 */
@WebServlet("/admindashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private UserService userService = new UserService();

	/**
	 * Default constructor for AdminDashboard servlet
	 */
	public AdminDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the servlet by creating instances of ProductService and
	 * UserService. This ensures fresh instances in case the servlet container calls
	 * init().
	 */
	public void init() {
		productService = new ProductService();
		userService = new UserService();
	}

	/**
	 * Handles GET requests by retrieving product and user data. If a search term is
	 * provided, it filters the products by name (case-insensitive). Otherwise, it
	 * retrieves all products. It also fetches the total number of users.
	 * 
	 * The retrieved data is forwarded to the "admindashboard.jsp" page for
	 * rendering.
	 *
	 * @param request  The HttpServletRequest object containing the request from the
	 *                 client.
	 * @param response The HttpServletResponse object containing the response from
	 *                 the servlet.
	 * @throws ServletException If the request cannot be handled.
	 * @throws IOException      If an input or output error occurs.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchTerm = request.getParameter("search");
		List<ProductModel> productList;

		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			// Filter products by item name (case-insensitive)
			productList = productService.getProductsByName(searchTerm);
		} else {
			productList = productService.getAllProducts();
		}

		request.setAttribute("products", productList);

		int userCount = userService.getUserCount();

		request.setAttribute("userCount", userCount);

		request.getRequestDispatcher("WEB-INF/pages/admindashboard.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating them to the doGet() method. This ensures
	 * consistent behavior for both GET and POST requests.
	 *
	 * @param request  The HttpServletRequest object containing the request from the
	 *                 client.
	 * @param response The HttpServletResponse object containing the response from
	 *                 the servlet.
	 * @throws ServletException If the request cannot be handled.
	 * @throws IOException      If an input or output error occurs.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
