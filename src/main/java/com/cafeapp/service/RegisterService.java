package com.cafeapp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import com.cafeapp.config.DbConfig;
import com.cafeapp.model.UserModel;
import com.cafeapp.util.PasswordUtil;

/**
 * @author Pranav_Sharma LMU ID: 23048577 RegisterService handles the
 *         registration of new podcast users. It manages database interactions
 *         for user registration.
 */
public class RegisterService {

	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Registers a new podcast user in the database.
	 *
	 * @param userModel the user details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false;
		}
		String insertQuery = "INSERT INTO user (Name, Email, Username, Password,Phone_Number,Image_Path) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		String encryptedPassword = PasswordUtil.encrypt(userModel.getUsername(), userModel.getPassword());

		try (PreparedStatement ps = dbConn.prepareStatement(insertQuery)) {
			ps.setString(1, userModel.getName());
			ps.setString(2, userModel.getEmail());
			ps.setString(3, userModel.getUsername());
			ps.setString(4, encryptedPassword);
			ps.setString(5, userModel.getPhoneNumber());
			ps.setString(6, userModel.getImagePath());

			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			System.err.println("Error inserting user: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if a username already exists in the database.
	 *
	 * @param username the username to check
	 * @return true if taken, false otherwise
	 */
	public boolean isUsernameTaken(String username) {
		if (dbConn == null) {
			return false;
		}

		String query = "SELECT 1 FROM User WHERE Username = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(query)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if an email already exists in the database.
	 *
	 * @param email the email to check
	 * @return true if taken, false otherwise
	 */
	public boolean isEmailTaken(String email) {
		if (dbConn == null) {
			return false;
		}
		String query = "SELECT 1 FROM user WHERE Email = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(query)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if an phone already exists in the database.
	 *
	 * @param phone the phone to check
	 * @return true if taken, false otherwise
	 */
	public boolean isPhoneTaken(String phone) {
		if (dbConn == null) {
			return false;
		}
		String query = "SELECT 1 FROM user WHERE Phone_Number = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(query)) {
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
