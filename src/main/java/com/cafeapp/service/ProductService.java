package com.cafeapp.service;

import com.cafeapp.config.DbConfig;
import com.cafeapp.model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Service class for handling operations
 *         related to products. Provides functionality to fetch all products or
 *         search products by name.
 */
public class ProductService {

	/**
	 * Retrieves all products from the database.
	 *
	 * @return a list of ProductModel objects representing all products in the
	 *         system.
	 */
	public List<ProductModel> getAllProducts() {
		List<ProductModel> products = new ArrayList<>();

		try (Connection con = DbConfig.getDbConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {

			while (rs.next()) {
				ProductModel p = new ProductModel();
				p.setId(rs.getInt("Item_ID")); // Set ID
				p.setName(rs.getString("Item_Name")); // Set Name
				p.setPrice(rs.getDouble("Item_Price")); // Set Price
				p.setCategory(rs.getString("Category")); // Set Category
				p.setDescription(rs.getString("Description")); // Set Description
				p.setCalories(rs.getInt("Calories")); // Set Calories

				products.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}

	/**
	 * Retrieves a list of products filtered by a search term. The search is
	 * case-insensitive and checks if the product name contains the search term.
	 *
	 * @param searchTerm the text to search for in product names
	 * @return a list of matching ProductModel objects
	 */
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
