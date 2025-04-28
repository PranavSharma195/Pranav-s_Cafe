<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Contact Us - Pranav's Cafe</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contactus.css" />
  <style>
  .image-section {
    flex: 1;
    min-width: 40vw;
    background-image: url("${pageContext.request.contextPath}/resources/images/system/registerbackground.jpeg");
    background-size: cover;
  }

  .contact-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .back-home-button {
    background-color: black;
    color: white;
    padding: 0.6vh 1.5vw;
    font-size: 2vh;
    border: none;
    border-radius: 25px;
    text-decoration: none;
    cursor: pointer;
    transition: 0.3s ease;
    display: inline-block;
    height:4vh;
  }

  .back-home-button:hover {
    background-color: #333;
    transform: translateY(-1px);
  }
  </style>
</head>
<body>
  <div class="container">
    <div class="image-section"></div>
    <div class="form-section">
      <h1>Contact Us</h1>
      <form action="submitContact.jsp" method="post">
        <input type="text" name="fullname" placeholder="Full Name" required />
        <input type="email" name="email" placeholder="E-mail" required />
        <textarea name="message" rows="4" placeholder="Message" required></textarea>
        <button type="submit" class="submit-button">Contact Us</button>
      </form>
      <div class="contact-info">
        <div class="contact-header">
          <h3>Contact</h3>
          <a href="${pageContext.request.contextPath}/home" class="back-home-button">🏠︎← Back to Home</a>
        </div>
        <p>pranavscafe@gmail.com</p><br><br>
        <h3>Based in</h3>
        <p>Gyaneswor, Kathmandu</p>
        <div class="socials">
            <a href="#" title="Facebook">
                <img src="${pageContext.request.contextPath}/resources/images/system/facebook.png" alt="Facebook" style="width: auto; height: 3vh;">
            </a>
            <a href="#" title="Instagram">
                <img src="${pageContext.request.contextPath}/resources/images/system/instagram.png" alt="Instagram" style="width: auto; height: 3vh;">
            </a>
            <a href="#" title="LinkedIn">
                <img src="${pageContext.request.contextPath}/resources/images/system/linkedin.png" alt="LinkedIn" style="width: auto; height: 3vh;">
            </a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
