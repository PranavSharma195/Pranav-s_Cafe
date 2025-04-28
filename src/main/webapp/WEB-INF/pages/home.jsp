<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cafeapp.model.ProductModel" %>
<%@ page import="java.util.List" %>
<%
    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
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

    main h1 {
      font-size: 4.5vh;
      margin-bottom: 1vh;
      color: #4e342e;
      font-weight: 600;
    }

    .slogan {
      font-size: 2.3vh;
      color: #6d4c41;
      font-weight: 300;
      margin-bottom: 5vh;
    }

    .menu-container {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
      gap: 4vh 3vw;
      padding: 0 2vw;
    }

    .menu-item {
      background-color: #fffaf5;
      border-left: 6px solid #a1887f;
      padding: 3vh 2vw;
      border-radius: 1.5vh;
      box-shadow: 0 0.5vh 1vh rgba(0, 0, 0, 0.05);
      transition: transform 0.3s ease;
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

    .product-emoji {
      font-size: 5vh;
    }

    .product-name {
      font-size: 2vh;
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
  <h1>Our Menu</h1>
  <p class="slogan">Explore our delicious offerings crafted with love and the finest ingredients.</p>

  <div class="menu-container">
    <%
      if (products != null && !products.isEmpty()) {
        for (ProductModel product : products) {
          String fullName = product.getName(); // e.g. "☕Espresso"
          String emoji = fullName.substring(0, 2); // First 2 chars as emoji
          
    %>
      <div class="menu-item">
        <div class="product-header">
          
          <span class="product-name"><%= fullName %></span>
        </div>
        <p class="product-description"><%= product.getDescription() %></p>
        <p class="product-price">₹ <%= product.getPrice() %></p>
        <form action="buyNow" method="post">
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
