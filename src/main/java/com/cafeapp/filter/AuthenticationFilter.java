package com.cafeapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.util.CookieUtil;
import com.cafeapp.util.SessionUtil;

/**
 * @author Pranav_Sharma LMU ID: 23048577 AuthenticationFilter is a filter that
 *         intercepts all requests to the application and checks whether the
 *         user is logged in and has the appropriate role for the requested
 *         page. It controls access to pages based on user roles (admin,
 *         customer).
 * 
 *         If the user is not logged in or does not have the appropriate role,
 *         the filter redirects them to the login page.
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

	// Constants for the URL patterns
	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String ABOUT = "/aboutus";
	private static final String CONTACT = "/contact";
	private static final String PROFILE = "/profile";

	private static final String ADMIN_DASHBOARD = "/admindashboard";
	private static final String EDIT_ITEMS = "/edititems";
	private static final String ADMIN_PROFILE = "/adminprofile";

	private static final String ROOT = "/";

	/**
	 * This method is called to filter the incoming requests and check
	 * authentication and authorization. It checks the requested URI and validates
	 * whether the user has the required role (admin/customer) to access the
	 * requested page. If not, the user is redirected to the login page.
	 * 
	 * @param request  the ServletRequest object that contains the request details
	 * @param response the ServletResponse object that is used to send the response
	 * @param chain    the FilterChain object used to pass the request and response
	 *                 to the next filter or resource
	 * @throws IOException      if an input-output error occurs during the filtering
	 *                          process
	 * @throws ServletException if a servlet-related error occurs during the
	 *                          filtering process
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		// Allow static resources to be accessed freely
		if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css") || uri.endsWith(".js")
				|| uri.endsWith(".jpeg")) {
			chain.doFilter(request, response);
			return;
		}

		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		Cookie roleCookie = CookieUtil.getCookie(req, "role");
		String role = (roleCookie != null) ? roleCookie.getValue() : null;

		// Pages open to everyone
		if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(ABOUT)
				|| uri.endsWith(CONTACT) || uri.endsWith("/logout") || uri.equals(req.getContextPath() + ROOT)) {
			chain.doFilter(request, response);
			return;
		}

		// Admin-only pages
		if (uri.endsWith(ADMIN_DASHBOARD) || uri.endsWith(EDIT_ITEMS) || uri.endsWith(ADMIN_PROFILE)) {
			if (isLoggedIn && "admin".equals(role)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN);
			}
			return;
		}

		// User-only pages
		if (uri.endsWith(PROFILE)) {
			if (isLoggedIn && "customer".equals(role)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN);
			}
			return;
		}

		// All other paths redirect to login
		res.sendRedirect(req.getContextPath() + LOGIN);
	}

}
