<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Pranav's Cafe Management System - Signup</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/register.css" />
  <style>
    .image-section {
      flex: 1;
      background-image: url("${pageContext.request.contextPath}/resources/images/system/registerbackground.jpeg");
      background-size: cover;
      background-position: center;
    }

    
  </style>
</head>
<body>
  <div class="container">
    <div class="form-section">
      <h1 class="logo">PRANAV'S CAFE</h1>
      <p class="tagline">EAT HEALTHY • EAT FRESH</p>
      <h2>Register</h2>

      <form action="register" method="post" enctype="multipart/form-data">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Enter your name" value="${nameValue}" required />
        <div class="error">${nameError}</div>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Enter your email" value="${emailValue}" required />
        <div class="error">${emailError}</div>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" value="${usernameValue}" required />
        <div class="error">${usernameError}</div>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter password" required />
        <div class="error">${passwordError}</div>

        <label for="confirmpassword">Confirm Password</label>
        <input type="password" id="confirmpassword" name="confirmPassword" placeholder="Confirm password" required />
        <div class="error">${confirmPasswordError}</div>

        <label for="mobile">Mobile Number</label>
        <input type="text" id="phone" name="phone" placeholder="Enter mobile number" value="${phoneValue}" required />
        <div class="error">${phoneError}</div>

        <label for="upload">Upload</label>
        <input type="file" id="imagePath" name="imagePath" accept="image/*" required />

        <div class="buttons">
          <button type="submit" class="btn primary">Signup</button>
          <a href="${pageContext.request.contextPath}/login" class="btn">Already have an account? <b style="text-decoration: underline;">Login</b></a>
        </div>
      </form>

      
    </div>
    <div class="image-section">
      <!-- Background image goes here -->
    </div>
  </div>
</body>
</html>
