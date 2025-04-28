<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Pranav's Cafe - Login</title>

  <!-- Use context path to load CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
  <style>
  .image-section {
    flex: 0.6;
    background-image: url("${pageContext.request.contextPath}/resources/images/system/registerbackground.jpeg");
    background-size: cover;
    background-position: center;
    border-top-left-radius: 1vw;
    border-bottom-left-radius: 1vw;
  }
  </style>
 
</head>
<body>
  <div class="container">
    <div class="form-section">
      <h1 class="logo">PRANAV'S CAFE</h1>
      <p class="tagline">EAT HEALTHY • EAT FRESH</p>
      <h2>User Login</h2>

      <!-- Add action and method to handle form -->
      <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required />

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required />
        
        <div class="error">${error}</div>

        <div class="buttons">
          <!-- Link to register page -->
          <a href="${pageContext.request.contextPath}/register" class="btn">🔄 Register</a>
          <button type="submit" class="btn primary">⏎ Login</button>
          <span class="forgot">Forgot Password?</span>
        </div>
      </form>

      <div class="nutrition">
        <strong>32%</strong>
        <p>A 100 g serving of blackberries provides 32% of the daily value of manganese.</p>
      </div>
    </div>

    <div class="image-section">
      <!-- You can style this via CSS using a background-image -->
    </div>
  </div>
</body>
</html>
