package com.cafeapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cafeapp.config.DbConfig;
import com.cafeapp.model.OrderModel;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Service class responsible for handling
 *         operations related to orders. This class provides a method to save a
 *         purchase/order made by a user.
 */
public class OrderService {

	/**
	 * Saves a user's order/purchase into the database.
	 * 
	 * @param order an instance of OrderModel containing the order details (user ID,
	 *              product ID)
	 * @throws Exception if there is an error executing the SQL query or interacting
	 *                   with the database
	 */
	public void savePurchase(OrderModel order) throws Exception {
		// Get database connection
		Connection conn = DbConfig.getDbConnection();

		// Use backticks to escape the reserved keyword "Order"
		String sql = "INSERT INTO `Order` (User_ID, Item_ID) VALUES (?, ?)";

		// Prepare the statement with the SQL query
		PreparedStatement stmt = conn.prepareStatement(sql);

		// Set the parameters from the OrderModel object
		stmt.setInt(1, order.getUserid());
		stmt.setInt(2, order.getProductid());

		// Execute the update to insert the record
		stmt.executeUpdate();

		// Close the statement and connection
		stmt.close();
		conn.close();
	}
}
