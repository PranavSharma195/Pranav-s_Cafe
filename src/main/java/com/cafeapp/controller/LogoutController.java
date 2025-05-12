package com.cafeapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.util.CookieUtil;
import com.cafeapp.util.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        performLogout(request, response);
    }


    // Handle POST requests (form submissions)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        performLogout(request, response);
    }

    
    private void performLogout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // 1. Delete all authentication cookies
        CookieUtil.deleteCookie(response, "role");
        

        // 2. Invalidate the session
        SessionUtil.invalidateSession(request);

        // 3. Redirect to home page
        response.sendRedirect(request.getContextPath() + "/login");
    }
}