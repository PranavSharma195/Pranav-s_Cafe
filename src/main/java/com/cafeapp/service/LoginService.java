package com.cafeapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cafeapp.config.DbConfig;
import com.cafeapp.model.UserModel;
import com.cafeapp.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public LoginService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("LoginService: Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Validates the user credentials against the database records.
     *
     * @param userModel the UserModel object containing user credentials
     * @return true if the user credentials are valid, false otherwise
     */
    public Boolean loginUser(UserModel userModel) {
        if (dbConn == null) {
            System.err.println("DB connection is null in LoginService.");
            return false;
        }

        String inputUsername = userModel.getUsername().trim();

        String query = "SELECT Username, Password FROM User WHERE Username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, inputUsername); // Trim input just in case
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return validatePassword(result, userModel);
            } else {
                System.out.println("Username not found in query: " + inputUsername);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during login: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Validates the password retrieved from the database.
     *
     * @param result     the ResultSet containing the username and password from DB
     * @param userModel  the UserModel object with user credentials
     * @return true if passwords match, false otherwise
     * @throws SQLException if a DB access error occurs
     */
    private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
        String dbUsername = result.getString("Username").trim();
        String dbPassword = result.getString("Password");

        String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);

        return decryptedPassword.equals(userModel.getPassword());
   }
}