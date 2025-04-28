package com.cafeapp.service;

import com.cafeapp.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public int getUserCount() {
        int count = 0;
        try (Connection con = DbConfig.getDbConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM User");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
