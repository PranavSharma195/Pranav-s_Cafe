<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cafeapp.model.ProductModel" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Edit Menu Items - CafeApp</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/edititems.css" />
        <style>
      
  .btn-like {
  display: inline-block;
  padding: 1vh 1vw;
  border: none;
  border-radius: 0.5vw;
  font-size: 0.9vw;
  cursor: pointer;
  background-color: #a1887f;
  color: white;
  text-decoration: none;
  text-align: center;
  transition: background-color 0.3s, transform 0.2s;
  width: 100%;
  box-sizing: border-box;
}

.btn-like:hover {
  background-color: #8d6e63;
  transform: scale(1.05);
}
  
        </style>
</head>
<body>
  <div class="dashboard-container">
    <div class="sidebar">
      <a href="${pageContext.request.contextPath}/adminprofile">
        <div class="profile-pic"></div>
      </a>
      <a href="${pageContext.request.contextPath}/admindashboard">
        <button class="nav-button" style="width:23vh;">Dashboard</button>
      </a>
      <a href="${pageContext.request.contextPath}/edititems">
        <button class="nav-button active" style="width:23vh;">Edit Menu Items</button>
      </a>
    </div>

    <div class="main-content">
      <h1>Edit Menu Items</h1>
      <% 
        String error = (String) request.getAttribute("error");
        if (error != null) {
      %>
        <p style="color: red;"><%= error %></p>
      <% } %>

      <!-- Add New Item Form -->
      <div class="add-item-form">
        
        <form action="edititems" method="post">
          <input type="hidden" name="action" value="add" />
          <table>
            <tr>
              <td><input type="text" name="name" placeholder="Item Name" required /></td>
              <td><input type="number" name="price" step="0.01" placeholder="Item Price" required /></td>
              <td><input type="text" name="category" placeholder="Category" /></td>
              <td><input type="text" name="description" placeholder="Description" /></td>
              <td><input type="number" name="calories" placeholder="Calories" /></td>
              <td><button type="submit" class="add-btn">Add Item</button></td>
            </tr>
          </table>
        </form>
      </div>

      <!-- Existing Items Table -->
      <div class="table-container">
        <table>
          <thead>
            <tr>
              <th>Item_ID</th>
              <th>Item_Name</th>
              <th>Item_Price</th>
              <th>Category</th>
              <th>Description</th>
              <th>Calories</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <%
              List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
              if (products != null) {
                for (ProductModel product : products) {
            %>
              <tr>
                <form action="edititems" method="post">
                  <input type="hidden" name="action" value="update" />
                  <input type="hidden" name="id" value="<%= product.getId() %>" />
                  <td><%= product.getId() %></td>
                  <td><input type="text" name="name" value="<%= product.getName() %>" required /></td>
                  <td><input type="number" name="price" step="0.01" value="<%= product.getPrice() %>" required /></td>
                  <td><input type="text" name="category" value="<%= product.getCategory() != null ? product.getCategory() : "" %>" /></td>
                  <td><input type="text" name="description" value="<%= product.getDescription() != null ? product.getDescription() : "" %>" /></td>
                  <td><input type="number" name="calories" value="<%= product.getCalories() %>" /></td>
                  <td class="actions">
                    <button type="submit" class="edit-btn">Update</button>
                   <a href="edititems?action=delete&id=<%= product.getId() %>" 
   class="delete-btn btn-like" 
   onclick="return confirm('Are you sure?')">Delete</a>

                  </td>
                </form>
              </tr>
            <%
                }
              }
            %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>
</html>