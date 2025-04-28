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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
