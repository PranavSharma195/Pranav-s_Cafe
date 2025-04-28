package com.cafeapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafeapp.model.ProductModel;
import com.cafeapp.config.DbConfig;

@WebServlet("/edititems")
public class EditItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditItems() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                List<ProductModel> products = getAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("WEB-INF/pages/edititems.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                ProductModel product = getProductById(id);
                request.setAttribute("product", product);
                request.setAttribute("products", getAllProducts());
                request.getRequestDispatcher("WEB-INF/pages/edititems.jsp").forward(request, response);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                deleteProduct(id);
                response.sendRedirect("edititems");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/pages/edititems.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action.equals("add")) {
                ProductModel product = new ProductModel();
                product.setName(request.getParameter("name"));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                product.setCategory(request.getParameter("category"));
                product.setDescription(request.getParameter("description"));
                product.setCalories(Integer.parseInt(request.getParameter("calories")));
                addProduct(product);
            } else if (action.equals("update")) {
                ProductModel product = new ProductModel();
                product.setId(Integer.parseInt(request.getParameter("id")));
                product.setName(request.getParameter("name"));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                product.setCategory(request.getParameter("category"));
                product.setDescription(request.getParameter("description"));
                product.setCalories(Integer.parseInt(request.getParameter("calories")));
                updateProduct(product);
            }
            response.sendRedirect("edititems");
        } catch (SQLException | NumberFormatException e) {
            request.setAttribute("error", "Error processing request: " + e.getMessage());
            try {
				request.setAttribute("products", getAllProducts());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            request.getRequestDispatcher("WEB-INF/pages/edititems.jsp").forward(request, response);
        }
    }

    private List<ProductModel> getAllProducts() throws SQLException {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProductModel product = new ProductModel(
                    rs.getInt("Item_ID"),
                    rs.getString("Item_Name"),
                    rs.getDouble("Item_Price"),
                    rs.getString("Category"),
                    rs.getString("Description"),
                    rs.getInt("Calories")
                );
                products.add(product);
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return products;
    }

    private ProductModel getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE Item_ID = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductModel(
                        rs.getInt("Item_ID"),
                        rs.getString("Item_Name"),
                        rs.getDouble("Item_Price"),
                        rs.getString("Category"),
                        rs.getString("Description"),
                        rs.getInt("Calories")
                    );
                }
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    private void addProduct(ProductModel product) throws SQLException {
        String sql = "INSERT INTO Product (Item_Name, Item_Price, Category, Description, Calories) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getDescription());
            stmt.setInt(5, product.getCalories());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void updateProduct(ProductModel product) throws SQLException {
        String sql = "UPDATE Product SET Item_Name = ?, Item_Price = ?, Category = ?, Description = ?, Calories = ? WHERE Item_ID = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getDescription());
            stmt.setInt(5, product.getCalories());
            stmt.setInt(6, product.getId());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE Item_ID = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}