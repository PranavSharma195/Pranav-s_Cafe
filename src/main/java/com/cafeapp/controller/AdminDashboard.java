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
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/admindashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	 private  UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
        productService = new ProductService();
        userService=new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
