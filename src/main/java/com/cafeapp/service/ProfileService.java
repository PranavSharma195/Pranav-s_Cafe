package com.cafeapp.service;

import com.cafeapp.model.UserModel;
import com.cafeapp.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Service class responsible for user
 *         profile-related operations, such as fetching and updating user
 *         information.
 */
public class ProfileService {

	/**
	 * Retrieves user profile details from the database based on username.
	 *
	 * @param username the username of the user whose profile is to be fetched
	 * @return a {@link UserModel} object populated with the user's profile data, or
	 *         null if the user is not found
	 */
	public UserModel getUserByUsername(String username) {
		UserModel user = null;
		String query = "SELECT Name, Email, Phone_Number, Image_Path FROM User WHERE Username = ?";

		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new UserModel(rs.getString("Name"), rs.getString("Email"), rs.getString("Phone_Number"),
						rs.getString("Image_Path"));
				user.setUsername(username);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Updates a user's profile information in the database.
	 *
	 * @param user a {@link UserModel} object containing updated user data
	 * @return true if the update was successful; false otherwise
	 */
	public boolean updateUserProfile(UserModel user) {
		boolean updated = false;
		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "UPDATE User SET Name = ?, Email = ?, Password = ?, Phone_Number = ? WHERE Username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getPhoneNumber());
			stmt.setString(5, user.getUsername());

			updated = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	/**
	 * Retrieves the database ID of a user based on their username.
	 *
	 * @param username the username to look up
	 * @return the user ID if found, otherwise 0
	 */
	public static int getUserId(String username) {
		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "SELECT User_ID FROM User WHERE Username=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("User_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
}
