package com.cafeapp.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.model.OrderModel;
import com.cafeapp.model.ProductModel;
import com.cafeapp.model.UserModel;
import com.cafeapp.service.OrderService;
import com.cafeapp.service.ProductService;
import com.cafeapp.service.ProfileService;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Servlet implementation for displaying
 *         the home page, handling product search, and processing product
 *         purchases.
 * 
 *         Handles GET requests for product listing and POST requests for
 *         product orders.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home"

})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private ProfileService profileService = new ProfileService();

	/**
	 * Default constructor.
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the servlet.
	 */
	public void init() {
		productService = new ProductService();
	}

	/**
	 * Handles GET requests to the home page, displaying a list of products. If a
	 * search query is provided, it filters the products based on the query.
	 * 
	 * @param request  the HttpServletRequest object containing request parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchQuery = request.getParameter("search");
		List<ProductModel> allProducts = productService.getAllProducts(); // assuming this gets all

		if (searchQuery != null && !searchQuery.trim().isEmpty()) {
			List<ProductModel> filtered = new ArrayList<>();
			for (ProductModel product : allProducts) {
				if (product.getName().toLowerCase().contains(searchQuery.toLowerCase())
						|| product.getDescription().toLowerCase().contains(searchQuery.toLowerCase())) {
					filtered.add(product);
				}
			}
			request.setAttribute("products", filtered);
		} else {
			request.setAttribute("products", allProducts);
		}

		request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for processing product orders. When a user buys a
	 * product, an order is created and saved.
	 * 
	 * @param request  the HttpServletRequest object containing request parameters
	 * @param response the HttpServletResponse object used to send a response
	 * @throws ServletException if the request could not be handled
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		String username = (String) request.getSession().getAttribute("username");

		if (username == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try {

			int user = profileService.getUserId(username);

			OrderModel order = new OrderModel(productId, user);

			OrderService buy = new OrderService();
			buy.savePurchase(order);

			request.getSession().setAttribute("successMessage", "Product bought successfully!");
			response.sendRedirect("home");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
