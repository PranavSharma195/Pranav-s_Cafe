<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
 <style>
 header {
    background: #3e2723;
    padding: 2vh 4vw;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #fff;
  }

  header h1 {
    font-size: 3vh;
    font-weight: 600;
  }

  nav ul {
    list-style: none;
    display: flex;
    gap: 3vw;
    align-items: center;
  }

  nav a {
    color: #fff;
    text-decoration: none;
    font-size: 2.2vh;
    transition: color 0.3s ease;
    font-weight: 400;
  }

  nav a:hover {
    color: #ffccbc;
  }

  .profile-icon {
    width: 5vh;
    height: 5vh;
    border-radius: 50%;
    background-color: #795548;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
  }
 </style>
</head>
<body>
<header>
    <h1>Pranav's Cafe</h1>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
        <li><a href="${pageContext.request.contextPath}/aboutus">About Us</a></li>
        <li>
          <div class="profile-icon">
            <a href="${pageContext.request.contextPath}/profile"><span style="color:white; font-size: 2.5vh;">👤</span></a>
          </div>
        </li>
      </ul>
    </nav>
  </header>

</body>
</html>