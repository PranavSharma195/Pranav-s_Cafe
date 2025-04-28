package com.cafeapp.controller;

import com.cafeapp.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/buyNow")
public class BuyNow extends HttpServlet {

    private final OrderService orderService = new OrderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            if (orderService.hasUserOrdered(userId)) {
                request.setAttribute("message", "You have already placed an order.");
            } else {
                boolean success = orderService.placeOrder(userId, productId);
                if (success) {
                    request.setAttribute("message", "Order placed successfully!");
                } else {
                    request.setAttribute("message", "Failed to place the order.");
                }
            }
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error processing your order.");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
