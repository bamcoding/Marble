<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<link rel="stylesheet" type="text/css" href="/Marble/bamcoding_css/gamePan.css" />

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="/Marble/assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->


<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#board${param.categoryId}").addClass("active");
	});
	
</script>
</head>
<body>


<!-- Header -->
	<section id="header"> <header> 
	<span class="image avatar"><img src="/Marble/images/avatar.jpg" alt="" /></span>
	<h1 id="logo">
		<a href="#">주루마블</a>
	</h1>
	<div id="sessionBar">
	<c:choose>
		<c:when test="${empty sessionScope._USER_INFO_ }">
			<jsp:include page="/WEB-INF/view/user/signIn.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/view/play/myInfo.jsp" />
		</c:otherwise>
	</c:choose>
	</div>
	</header> <nav id="nav">
	<ul>	
		<!-- <li><a href="#start" class="active">GAME START</a></li> -->
		<c:choose>
			<c:when test="${empty sessionScope._USER_INFO_ }">
				<li><a href="#signup">SIGN UP</a></li>
			</c:when>
			<c:otherwise>
				<li><a id="board12" href="/Marble/board/list?categoryId=12">COMMUNITY</a></li>
				<li><a id="board13" href="/Marble/board/list?categoryId=13">QNA</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
	</nav> <footer>
	<ul class="icons">
		<!-- <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li> -->
		<!-- <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li> -->
		<!-- <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li> -->
		<!-- <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li> -->
		<!-- <li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li> -->
	</ul>
	</footer> </section>


	<div id="cover"></div>
	
	<div id="wrapper">