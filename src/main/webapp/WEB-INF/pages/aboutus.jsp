<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>About Us – Pranav's Cafe</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutus.css" />
  <style>
  .owner-photo {
    width: 12vw;
    height: 12vw;
    background-image: url("${pageContext.request.contextPath}/resources/images/system/student.png");
    background-size: cover;
    background-position: center;
    border-radius: 50%;
    box-shadow: 0 0.5vh 1vh rgba(0, 0, 0, 0.1);
    margin-bottom: 1vh;
  }
  
  body{
  background-image: url("${pageContext.request.contextPath}/resources/images/system/aboutus.jpeg");
  background-size: contain;
  }
  .contact-header {
    display: flex;
    justify-content: center;
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
    <div class="mission">
    
      <h3>Our Mission</h3>
      <h1>To create a warm, welcoming café experience where every dish and drink reflects freshness, community, and comfort.</h1>
      <div class="contact-header"> 
      <a href="${pageContext.request.contextPath}/home" class="back-home-button">🏠︎← Back to Home</a>
        </div>
    </div>

    <div class="about">
      <div>
        <h3>About Us</h3>
        <p>
          Nestled in the bustling yet peaceful neighborhood of Gyaneswor, Kathmandu, Pranav’s Cafe stands as a haven for those who appreciate good food, good vibes, and even better company. From the moment you walk in, you’re greeted by a blend of rustic charm and modern elegance — a place where wooden textures meet greenery, and soft jazz hums in the background.
        </p>
        <p>
          Our café isn’t just about food — it's about connection. Every cup of coffee brewed, every pastry baked, and every salad tossed tells a story of local love, seasonal freshness, and sustainability. We prioritize health-conscious yet hearty recipes and offer plenty of vegan and gluten-free options without compromising on taste.
        </p>
        <p>
          Whether you’re looking for a quiet place to work, planning a meet-up with friends, or just dropping by for your daily espresso shot, Pranav’s Cafe welcomes you with open arms. Our loyal community has made us more than just a spot on the map — we're a feeling, a ritual, and a second home for many.
        </p>
        <p>
          What sets us apart is our commitment to community. Being located in the culturally rich and bustling area of Gyaneswor, we’ve grown alongside our neighborhood — welcoming students, artists, professionals, and families alike. We regularly host open-mic nights, art displays, and sustainable initiatives to keep the creative pulse alive. Our vision is rooted in sustainability, inclusivity, and joy — and every element of the cafe reflects that, from our eco-conscious packaging to our diverse menu options for every dietary need.
        </p>
      </div>

      <div class="owner-box">
        <div class="owner-photo"></div>
        <div class="owner-name">Owned by Pranav Sharma</div>
        <div class="social-icons">
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
