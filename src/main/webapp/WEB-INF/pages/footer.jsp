<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />
 <style>
 footer {
    background: #3e2723;
    color: #fff;
    padding: 4vh 5vw;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    gap: 3vh;
  }
  
  footer > div {
    flex: 1 1 180px;
    min-width: 180px;
  }
  
  footer h3 {
    margin-bottom: 1vh;
    font-size: 2.3vh;
    color:white;
  }
  
  footer p, footer a {
    font-size: 2vh;
    color: #ffccbc;
    text-decoration: none;
    line-height: 1.6;
  }
  
  footer a:hover {
    text-decoration: underline;
  }
  
  .footer-bottom {
    flex: 1 1 100%;
    text-align: center;
    font-size: 1.8vh;
    color: #d7ccc8;
    margin-top: 3vh;
  }
  
  footer a {
    color: #ffccbc;
    text-decoration: none;
    font-size: 2vh;
    transition: color 0.3s ease;
  }
  
  footer a:hover {
    color: #fff;
    text-decoration: none;
  }
  
  footer a:active {
    color: #ffab91;
    text-decoration: none;
  }
  .elements{
      display: flex;
      justify-content: space-between;
  }
 </style>
</head>
<body>
 <footer>
    <div class="elements">
      <div>
        <h3>Visit Us</h3>
        <p>Sangam Marg, Maitidevi<br>Gyaneshwor, Kathmandu</p>
      </div>
      <div>
        <h3>Contact</h3>
        <p>Email: pranavscafe@gmail.com</p>
        <p>Phone: +977-9840003706</p>
      </div>
      <div>
        <h3>Follow Us</h3>
        <p><a href="#">🅾→ Instagram</a></p>
        <p><a href="#">ⓕ→ Facebook</a></p>
        <p><a href="#">𝕏→ Twitter</a></p>
      </div>
      <div>
        <h3>Quick Links</h3>
        <p><a href="#">Menu</a></p>
        <p><a href="#">Careers</a></p>
        <p><a href="#">Privacy Policy</a></p>
      </div>
      <div>
        <h3>Opening Hours</h3>
        <p>Mon - Fri: 8am - 8pm</p>
        <p>Sat - Sun: 9am - 10pm</p>
      </div>
    </div>
    <div class="footer-bottom">
      &copy; 2025 Pranav's Cafe. All rights reserved.
    </div>
  </footer>

</body>
</html>