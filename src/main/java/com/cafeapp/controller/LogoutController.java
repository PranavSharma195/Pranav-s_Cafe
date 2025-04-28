package com.cafeapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.util.CookieUtil;
import com.cafeapp.util.SessionUtil;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieUtil.deleteCookie(response, "role"); // ✅ deletes role cookie
        SessionUtil.invalidateSession(request);    // ✅ invalidates session
        response.sendRedirect(request.getContextPath() + "/login"); // ✅ redirects to login
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login"); // Optional, redirect GET to login
    }
}
