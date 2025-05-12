<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cafeapp.model.ProductModel" %>
<%@ page import="java.util.List" %>
<%
    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
    String searchQuery = request.getParameter("search");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Pranav's Cafe</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />
  <style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Poppins', sans-serif;
        background: linear-gradient(to right, #fdfcfb, #e2d1c3);
        color: #333;
        line-height: 1.6;
    }

    main {
        padding: 6vh 5vw;
        text-align: center;
    }

    .header-section {
        position: relative;
        margin-bottom: 2vh;
        height: 6vh;
        background-color: linear-gradient(to right, #fdfcfb, #e2d1c3);
        border-radius: 1.5vh;
        padding: 1vh 2vw;
    }

    .header-section h1 {
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        font-size: 4.5vh;
        font-weight: 600;
    }

    .search-form {
        position: absolute;
        right: 0;
        top: 50%;
        transform: translateY(-50%);
        display: flex;
        gap: 1.5vw;
        align-items: center;
    }

    .search-form input[type="text"] {
        padding: 1.2vh 2.5vw;
        border-radius: 2vh;
        border: 1px solid #ccc;
        font-size: 2vh;
        transition: border 0.3s ease, box-shadow 0.3s ease;
        width: 20vw;
    }

    .search-form input[type="text"]:focus {
        border-color: #6d4c41;
        box-shadow: 0 0 5px rgba(109, 76, 65, 0.5);
        outline: none;
    }

    .search-form button {
        padding: 1.2vh 2.5vw;
        border-radius: 2vh;
        background-color: #6d4c41;
        color: white;
        border: none;
        font-size: 2vh;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    .search-form button:hover {
        background-color: #5d4037;
        transform: translateY(-2px);
    }

    .search-form button:active {
        transform: translateY(0);
    }

    .slogan {
        font-size: 2.3vh;
        color: #6d4c41;
        font-weight: 300;
        margin-bottom: 5vh;
    }

    .menu-container {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
        gap: 4vh 3vw;
        padding: 0 2vw;
    }

    .centered-results {
        justify-content: center;
    }

    .menu-item {
        background-color: #fffaf5;
        border-left: 6px solid #a1887f;
        padding: 3vh 2vw;
        border-radius: 1.5vh;
        box-shadow: 0 0.5vh 1vh rgba(0, 0, 0, 0.05);
        transition: transform 0.3s ease;
        width: 100%;
    }

    .menu-item:hover {
        transform: translateY(-1vh);
    }

    .product-header {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 1vw;
        margin-bottom: 1vh;
    }

    .product-name {
        font-size: 2.2vh;
        font-weight: bold;
        color: #4e342e;
    }

    .product-description {
        color: #6d4c41;
        font-size: 1.8vh;
        margin-bottom: 1vh;
    }

    .product-price {
        font-size: 2.2vh;
        color: #3e2723;
        font-weight: 600;
        margin-bottom: 2vh;
    }

    .buy-now-button {
        background-color: #6d4c41;
        color: #fff;
        border: none;
        padding: 1vh 2vw;
        border-radius: 0.5vh;
        font-size: 2vh;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .buy-now-button:hover {
        background-color: #5d4037;
    }
  </style>
</head>

<body>
<jsp:include page="header.jsp"/>

<main>
  <!-- Title + Search -->
  <div class="header-section">
    <h1>Our Menu</h1>
    <form class="search-form" method="get" action="home">
      <input type="text" name="search" placeholder="Search menu..." value="<%= searchQuery != null ? searchQuery : "" %>"/>
      <button type="submit">Search</button>
    </form>
  </div>
 <%
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
%>
    <div style="background: linear-gradient(to right, #a1887f, #6d4c41); color: #fff; padding: 1.5vh 2vw; border-radius: 1vh; margin-bottom: 3vh; font-weight: bold; box-shadow: 0 0.5vh 1vh rgba(0, 0, 0, 0.1);">
        <%= successMessage %>
    </div>
<%
        session.removeAttribute("successMessage");
    }
%>


  

  <p class="slogan">Explore our delicious offerings crafted with love and the finest ingredients.</p>

  <div class="menu-container <%= (searchQuery != null && !searchQuery.isEmpty()) ? "centered-results" : "" %>">
    <%
      if (products != null && !products.isEmpty()) {
        for (ProductModel product : products) {
    %>
      <div class="menu-item">
        <div class="product-header">
          <span class="product-name"><%= product.getName() %></span>
        </div>
        <p class="product-description"><%= product.getDescription() %></p>
        <p class="product-price">₹ <%= product.getPrice() %></p>
        <form action="home" method="post">
          <input type="hidden" name="productId" value="<%= product.getId() %>" />
          <button type="submit" class="buy-now-button">Buy Now</button>
        </form>
      </div>
    <%
        }
      } else {
    %>
      <p>No products found.</p>
    <%
      }
    %>
  </div>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>
