package com.cafeapp.service;

import com.cafeapp.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Service class responsible for
 *         user-related utility operations.
 */
public class UserService {

	/**
	 * Retrieves the total number of users in the system.
	 *
	 * @return the total count of users from the "User" table
	 */
	public int getUserCount() {
		int count = 0;
		try (Connection con = DbConfig.getDbConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM User");
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt(1); // Get the count from the result set
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
