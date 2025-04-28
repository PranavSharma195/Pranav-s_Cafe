package com.cafeapp.service;

import com.cafeapp.model.UserModel;
import com.cafeapp.config.DbConfig;  // Assuming the package is correct

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileService {

    public UserModel getUserByUsername(String username) {
        UserModel user = null;
        String query = "SELECT Name, Email, Phone_Number, Image_Path FROM User WHERE Username = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

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
            e.printStackTrace();
        }

        return user;
    }
}
