package com.cafeapp.service;

import com.cafeapp.model.UserModel;
import com.cafeapp.config.DbConfig;  // Assuming the package is correct

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
