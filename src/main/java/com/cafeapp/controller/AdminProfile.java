package com.cafeapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.model.UserModel;
import com.cafeapp.service.AdminProfileService;
import com.cafeapp.service.ProfileService;
import com.cafeapp.util.SessionUtil;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/adminprofile")
public class AdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminProfileService adminprofileService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfile() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
        adminprofileService = new AdminProfileService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) SessionUtil.getAttribute(req, "username");

        if (username != null) {
            UserModel user = adminprofileService.getUserByUsername(username);
            req.setAttribute("user", user);
            req.getRequestDispatcher("WEB-INF/pages/adminprofile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
