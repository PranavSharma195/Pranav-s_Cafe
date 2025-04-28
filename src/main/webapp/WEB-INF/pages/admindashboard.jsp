<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cafeapp.model.ProductModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <meta charset="UTF-8">
    <title>Admin Dashboard - CafeApp</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/admindashboard.css" />
    <style>
        .table-container {
            background-color: #ffffff;
            padding: 3vh 2vw;
            border-radius: 1vw;
            box-shadow: 0 0.4vh 1vh rgba(0, 0, 0, 0.05);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 1vw;
        }

        th, td {
            padding: 1.2vh 1vw;
            border-bottom: 0.2vh solid #d7ccc8;
            text-align: left;
        }

        th {
            background-color: #8d6e63;
            color: #fff;
            font-weight: 600;
        }

        input[type="text"],
        input[type="number"] {
            width: 95%;
            padding: 1vh 0.5vw;
            font-size: 1vw;
            border: 0.1vh solid #ccc;
            border-radius: 0.4vw;
        }
        
        .search-input {
    padding: 0.8vh 1vw;
    font-size: 1vw;
    border: 0.1vh solid #ccc;
    border-radius: 2vw;
    width: 20vw;
    outline: none;
    transition: all 0.3s ease;
    box-shadow: 0 0.3vh 0.6vh rgba(0, 0, 0, 0.05);
}

.search-input:focus {
    border-color: #8d6e63;
    box-shadow: 0 0 0 0.3vh rgba(141, 110, 99, 0.2);
}

.search-button {
    padding: 0.8vh 2vw;
    font-size: 1vw;
    background-color: #8d6e63;
    color: #fff;
    border: none;
    border-radius: 2vw;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
    box-shadow: 0 0.3vh 0.6vh rgba(0, 0, 0, 0.1);
}

.search-button:hover {
    background-color: #6d4c41;
    transform: scale(1.03);
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
            <button class="nav-button active" style="width:23vh;">Dashboard</button>
        </a>
        <a href="${pageContext.request.contextPath}/edititems">
            <button class="nav-button" style="width:23vh;">Edit Menu Items</button>
        </a>
    </div>

    <div class="main-content">
        <div class="stats-container">
            <div class="card">
                <h3>Total Menu Items</h3>
                <p><%= ((List<ProductModel>) request.getAttribute("products")).size() %></p>
            </div>
            <div class="card">
                <h3>Total Price Of All Items</h3>
                <%
                    double totalPrice = 0;
                    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
                    if (products != null) {
                        for (ProductModel p : products) {
                            totalPrice += p.getPrice();
                        }
                    }
                %>
                <p>Rs. <%= String.format("%.2f", totalPrice) %></p>
            </div>
            <div class="card">
    <h3>Active Users</h3>
    <p><%= request.getAttribute("userCount") %></p>
</div>

            <div class="card">
                <h3>Highest Priced Item</h3>
                <%
                    ProductModel maxProduct = null;
                    for (ProductModel p : products) {
                        if (maxProduct == null || p.getPrice() > maxProduct.getPrice()) {
                            maxProduct = p;
                        }
                    }
                %>
                <p><%= maxProduct != null ? maxProduct.getName() : "N/A" %></p>
            </div>
            <div class="card">
                <h3>Lowest Priced Item</h3>
                <%
                    ProductModel minProduct = null;
                    for (ProductModel p : products) {
                        if (minProduct == null || p.getPrice() < minProduct.getPrice()) {
                            minProduct = p;
                        }
                    }
                %>
                <p><%= minProduct != null ? minProduct.getName() : "N/A" %></p>
            </div>
        </div>

        <div class="table-container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2vh;">
    <h1 style="margin: 0;">Menu Items</h1>
    <form action="admindashboard" method="get" id="searchForm" style="display: flex; align-items: center; gap: 1vw;">
        <input 
            type="text" 
            name="search" 
            id="searchInput"
            placeholder="Search By Menu Item Name"
            value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>"
            class="search-input"style="padding: 1vh 1vw; font-size: 1.1vw; border-radius: 1vw; border: 1px solid #ccc; width: 16vw;"
            
        />
        <button type="submit" class="search-button">Search</button>
    </form>
</div>




            <table>
                <thead>
                <tr>
                    <th>Item_ID</th>
                    <th>Item_Name</th>
                    <th>Item_Price</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Calories</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (products != null) {
                        for (ProductModel product : products) {
                %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getName() %></td>
                        <td>Rs.<%= String.format("%.2f", product.getPrice()) %></td>
                        <td><%= product.getCategory() != null ? product.getCategory() : "N/A" %></td>
                        <td><%= product.getDescription() != null ? product.getDescription() : "N/A" %></td>
                        <td><%= product.getCalories() %></td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div><br><br>

                <div class="graph-section">
            <h2>Line Graph Showing Price of Menu Items</h2>
            <canvas id="priceChart" style="max-width:100%; height:400px;"></canvas>
        </div>

        <script>
            const ctx = document.getElementById('priceChart').getContext('2d');
            const priceChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: [
                        <% 
                            if (products != null) {
                                for (int i = 0; i < products.size(); i++) {
                                    out.print("\"" + products.get(i).getName() + "\"");
                                    if (i < products.size() - 1) out.print(", ");
                                }
                            } 
                        %>
                    ],
                    datasets: [{
                        label: 'Price (Rs)',
                        data: [
                            <% 
                                if (products != null) {
                                    for (int i = 0; i < products.size(); i++) {
                                        out.print(products.get(i).getPrice());
                                        if (i < products.size() - 1) out.print(", ");
                                    }
                                } 
                            %>
                        ],
                        borderColor: '#8d6e63',
                        backgroundColor: 'rgba(141, 110, 99, 0.2)',
                        borderWidth: 2,
                        tension: 0.4,
                        fill: true,
                        pointBackgroundColor: '#4e342e'
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>

    </div>
</div>
<script>
    const input = document.getElementById('searchInput');
    const form = document.getElementById('searchForm');

    input.addEventListener('input', function () {
        if (input.value.trim() === "") {
            form.submit(); // Auto-submit when input is cleared
        }
    });
</script>


</body>
</html>
