package com.cafeapp.service;

import com.cafeapp.model.UserModel;
import com.cafeapp.config.DbConfig;  // Assuming the package is correct

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pranav_Sharma LMU ID: 23048577
 * Service class responsible for handling admin-related user profile operations.
 * This class provides methods to fetch user details for admin purposes.
 */
public class AdminProfileService {

    /**
     * Retrieves a user's profile by their username.
     * 
     * @param username the username of the user whose profile is to be fetched
     * @return a UserModel object containing the user's profile details, or null if no user is found
     */
    public UserModel getUserByUsername(String username) {
        UserModel user = null;
        String query = "SELECT Name, Email, Phone_Number, Image_Path FROM User WHERE Username = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // If a record is found, map it to a UserModel object
            if (rs.next()) {
                user = new UserModel(
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Phone_Number"),
                        rs.getString("Image_Path")
                );
                user.setUsername(username);
            }

        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
        }

        return user;
    }
}
