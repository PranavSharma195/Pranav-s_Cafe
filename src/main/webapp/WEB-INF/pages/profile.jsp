<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    com.cafeapp.model.UserModel user = (com.cafeapp.model.UserModel) request.getAttribute("user");
    String successMessage = (String) request.getAttribute("success");
    String errorMessage = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>User Profile</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/profile.css">
  <style>
  * {
    box-sizing: border-box;
  }

  body {
    margin: 0;
    padding: 0;
    font-family: 'Segoe UI', sans-serif;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: url('${pageContext.request.contextPath}/resources/images/system/profile.jpg') no-repeat center center fixed;
    background-size: cover;
  }

  .profile-wrapper {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    gap: 3vw;
    padding: 4vh 2vw;
    max-width: 1300px;
    width: 100%;
    flex-wrap: wrap;
  }

  .box {
    background: linear-gradient(
      145deg,
      rgba(38, 28, 20, 0.92),
      rgba(22, 15, 10, 0.92)
    );
    border: 1px solid rgba(255, 225, 180, 0.08);
    border-radius: 2vh;
    box-shadow:
      0 10px 30px rgba(0, 0, 0, 0.6),
      inset 0 0 1px rgba(255, 200, 150, 0.05);
    padding: 4vh 4vw;
    width: 47%;
    color: #fdf7f2;
    transition: all 0.3s ease;
  }

  .box:hover {
    transform: translateY(-6px);
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.6), 0 0 20px rgba(255, 183, 128, 0.15);
  }

  .profile-info-box h2,
  .form-box h2 {
    font-size: 3vh;
    color: #fff;
    margin-bottom: 2vh;
    text-align: center;
  }

  .profile-icon {
    width: 16vh;
    height: 16vh;
    border-radius: 50%;
    
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 8vh;
    color: #fff;
    margin: 0 auto 3vh auto;
    box-shadow: 0 0.7vh 2vh rgba(255, 120, 150, 0.5);
  }

  form {
    display: flex;
    flex-direction: column;
  }

  .input-group {
    margin-bottom: 2.5vh;
  }

  label {
    display: block;
    font-weight: 500;
    margin-bottom: 1vh;
    color: #ddd;
  }

  input {
    width: 100%;
    padding: 1.5vh 1vw;
    border: 1px solid rgba(255, 255, 255, 0.2);
    border-radius: 0.7vh;
    font-size: 2.2vh;
    background-color: rgba(255, 255, 255, 0.1);
    color: #fff;
  }

  input:focus {
    outline: none;
    border-color: #ff7eb3;
    box-shadow: 0 0 0 2px rgba(255, 126, 179, 0.4);
  }

  input::placeholder {
    color: #aaa;
  }

  .btn-group {
    margin-top: 3vh;
    display: flex;
    justify-content: space-between;
    gap: 2vw;
  }

  button {
    width: 100%;
    padding: 1.7vh 0;
    border: none;
    border-radius: 1vh;
    font-size: 2.2vh;
    cursor: pointer;
    transition: all 0.3s ease;
  }
  
  img{
  border-radius:50%;
  height:16vh;
  }

  .update-btn {
    background: linear-gradient(135deg, #ff758c, #ff7eb3);
    color: white;
    box-shadow: 0 4px 12px rgba(255, 126, 179, 0.4);
  }

  .update-btn:hover {
    background: linear-gradient(135deg, #ff4f8b, #ff6fa6);
    box-shadow: 0 6px 16px rgba(255, 100, 150, 0.6);
  }

  .back-btn {
    background-color: rgba(255, 255, 255, 0.2);
    color: #fff;
    border: 1px solid rgba(255, 255, 255, 0.2);
  }

  .back-btn:hover {
    background-color: rgba(255, 255, 255, 0.3);
  }
  .logout-btn {
    background: linear-gradient(135deg, #ff758c, #ff7eb3);
    color: white;
    box-shadow: 0 4px 12px rgba(255, 126, 179, 0.4);
  }

  .logout-btn:hover {
    background: linear-gradient(135deg, #ff4f8b, #ff6fa6);
    box-shadow: 0 6px 16px rgba(255, 100, 150, 0.6);
  }
  

  .profile-details p {
    display: flex;
    gap: 1vw;
    font-size: 2.3vh;
    margin: 1.5vh 0;
    color: #f0f0f0;
    align-items: center;
  }

  .profile-details .label {
    font-weight: 600;
    color: #fff;
    min-width: 100px;
  }
</style>
</head>
<body>

  <div class="profile-wrapper">

    <!-- Left Profile Box -->
    <div class="box profile-info-box">
      <div class="profile-icon">
        <img src="<%= request.getContextPath() + "/resources/images/system/" + user.getImagePath() %>" alt="Profile Image" />
      </div>
      <h2>Profile Information</h2>
      <div class="profile-details">
        <p><strong>Name:</strong> <%= user.getName() %></p>
        <p><strong>Email:</strong> <%= user.getEmail() %></p>
        <p><strong>Password:</strong> ********</p>
        <p><strong>Phone:</strong> <%= user.getPhoneNumber() %></p>
        <form action="<%= request.getContextPath() %>/logout" method="post">
          <button type="submit" class="logout-btn">Logout</button>
        </form>
      </div>
    </div>

    <!-- Right Editable Form -->
    <div class="box form-box">
      <h2>Edit Profile</h2>

      <% if (successMessage != null) { %>
        <p style="color:lightgreen; text-align:center;"><%= successMessage %></p>
      <% } %>
      <% if (errorMessage != null) { %>
        <p style="color:salmon; text-align:center;"><%= errorMessage %></p>
      <% } %>

      <form action="<%= request.getContextPath() %>/profile" method="post">
        <div class="input-group">
          <label for="name">Name</label>
          <input type="text" id="name" name="name" value="<%= user.getName() %>" required>
        </div>
        <div class="input-group">
          <label for="email">Email</label>
          <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
        </div>
        <div class="input-group">
          <label for="password">Password</label>
          <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>
        </div>
        <div class="input-group">
          <label for="phone">Phone Number</label>
          <input type="tel" id="phone" name="phone" value="<%= user.getPhoneNumber() %>" required>
        </div>
        <div class="btn-group">
          <a href="<%= request.getContextPath() %>/home" style="width: 100%;">
            <button type="button" class="back-btn">🏠︎← Back to Home</button>
          </a>
          <button type="submit" class="update-btn">Update Profile</button>
        </div>
      </form>
    </div>
  </div>

</body>
</html>
