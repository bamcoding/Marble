<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<title>Insert title here</title>
</head>
<body style="background:black;">
테스트 페이지 입니다.
<html id="exit">
<style>

#exit *,
#exit *:before,
#exit *:after {
  box-sizing: border-box;
}
#exit nav,
#exit nav ul {
  position: fixed;
  width: 100%;
  height: 100%;
}
#exit nav {
  box-shadow: inset 0 0 0 1px rgba(255,255,255,0.3);
  opacity: 0;
  -webkit-transform: scale(0.3);
          transform: scale(0.3);
  -webkit-transition: all 0.25s;
  transition: all 0.25s;
}
#exit nav ul li {
  display: table;
  width: 100%;
  height: calc(100% /4);
  border-bottom: 1px solid #ccc;
  background: gray;
  opacity:0.8;
  -webkit-perspective: 0px;
          perspective: 0px;
  -webkit-transform: rotateX(-90deg) scale(0.5);
          transform: rotateX(-90deg) scale(0.5);
  -webkit-transition: all 0.3s ease 0.1s;
  transition: all 0.3s ease 0.1s;
}
#exit nav ul li a {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  text-decoration: none;
  font-size: 2.5em;
  letter-spacing: 0.2em;
  color: white;
  font-family: 'Helvetica', sans-serif;
  font-weight: 100;
}
#exit nav ul li a:hover {
  background: rgba(255,255,255,0.1);
}
#exit #toggle {
  display: none;
}
#exit #toggle + #toggle-btn,
#exit .nav-icon {
  position: absolute;
  top: 15px;
  right: 15px;
  border-radius: 3px;
  cursor: pointer;
}
#exit #toggle + #toggle-btn {
  height: 40px;
  width: 40px;
  z-index: 1000;
  background: rgba(0,0,0,0.05);
}
#exit .nav-icon,
#exit .nav-icon:before,
#exit .nav-icon:after {
  position: absolute;
  height: 4px;
  width: 40px;
  margin: 18px 0;
  z-index: 900;
  border-radius: 3px;
  background: #fff;
  -webkit-transition: all 0.25s;
  transition: all 0.25s;
}
#exit .nav-icon:before {
  content: "";
  margin-top: -18px;
}
#exit .nav-icon:after {
  content: "";
  margin-top: 18px;
}
#exit #toggle:checked ~ nav {
  opacity: 1;
  -webkit-transform: scale(0.8);
          transform: scale(0.8);
}
#exit #toggle:checked ~ .nav-icon {
  background: rgba(255,0,0,0);
}
#exit #toggle:checked ~ .nav-icon:before {
  -webkit-transform: rotate(-225deg);
          transform: rotate(-225deg);
  margin-top: 0;
}
#exit #toggle:checked ~ .nav-icon:after {
  -webkit-transform: rotate(225deg);
          transform: rotate(225deg);
  margin-top: 0;
}
#exit #toggle:checked ~ nav ul li {
  -webkit-transform: rotateY(0deg) scale(1);
          transform: rotateY(0deg) scale(1);
}
</style>

<body>
<header>
  <input type="checkbox" id="toggle"/>
  <label for="toggle" id="toggle-btn"></label>
  <div class="nav-icon"></div>
  <nav data-state="close">
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Services</a></li>
      <li><a href="#">Contact</a></li>
    </ul>
  </nav>
</header>

</body>
</html>