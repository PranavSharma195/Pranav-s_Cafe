package com.cafeapp.service;

import com.cafeapp.config.DbConfig;

import com.cafeapp.model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

	public List<ProductModel> getAllProducts() {
	    List<ProductModel> products = new ArrayList<>();
	    try (Connection con = DbConfig.getDbConnection();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {

	        while (rs.next()) {
	            ProductModel p = new ProductModel();
	            p.setId(rs.getInt("Item_ID")); // Set ID
	            p.setName(rs.getString("Item_Name"));
	            p.setPrice(rs.getDouble("Item_Price"));
	            p.setCategory(rs.getString("Category")); // Set Category
	            p.setDescription(rs.getString("Description"));
	            p.setCalories(rs.getInt("Calories")); // Set Calories

	            products.add(p);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return products;
	}
	public List<ProductModel> getProductsByName(String searchTerm) {
	    List<ProductModel> allProducts = getAllProducts();
	    List<ProductModel> filtered = new ArrayList<>();
	    for (ProductModel product : allProducts) {
	        if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
	            filtered.add(product);
	        }
	    }
	    return filtered;
	}

}
