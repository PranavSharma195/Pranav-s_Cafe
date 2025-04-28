package com.cafeapp.service;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cafeapp.config.DbConfig;

public class OrderService {

    public boolean hasUserOrdered(int userId) throws Exception {
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM Order WHERE User_ID = ?")) {
        	
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if order exists
        }
    }

    public boolean placeOrder(int userId, int productId) throws Exception {
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Order (User_ID, Product_ID, Quantity) VALUES (?, ?, 1)")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
}
