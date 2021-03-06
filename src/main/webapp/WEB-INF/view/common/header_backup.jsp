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

<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#marbleBoard").load("/Marble/play/video");
		$("#nav ul li").click(function() {
			var index = $(this).index();
			console.log("메뉴 인댁스 : "+index);
			//게임 스타트
			if ( index == 0 ){
				location.href="/Marble/play/index";	
			}
			if (index == 1) {
				/* $("#marbleBoard").load("/Marble/setMarbleBoard"); */
				$("#marbleBoard").load("../confirmPlays");
			}
			//메뉴 목록
			if (index == 2) {
				if (!$(this).hasClass("active")) {
					$(this).parents().find("li").removeClass("active");
					$(this).addClass("active");
					$("#menubar").fadeIn();
					$("#sign-in").fadeOut();
					$("#sign-up").fadeOut();
					
				} else {
					$(this).parents().find("li").removeClass("active");
					$("#menubar").fadeOut();
					$("#sign-in").fadeOut();
					$("#sign-up").fadeOut();
				}
			}
			//로그인
			if (index == 3) {
				if (!$(this).hasClass("active")) {
					$(this).parents().find("li").removeClass("active");
					$(this).addClass("active");
					$("#menubar").fadeOut();
					$("#sign-in").fadeIn();
					$("#sign-up").fadeOut();
				} else {
					$(this).parents().find("li").removeClass("active");
					$("#menubar").fadeOut();
					$("#sign-in").fadeOut();
					$("#sign-up").fadeOut();
				}
			}
			//회원가입
			if (index == 4) {
				if (!$(this).hasClass("active")) {
					$(this).parents().find("li").removeClass("active");
					$(this).addClass("active");
					$("#menubar").fadeOut();
					$("#sign-in").fadeOut();
					$("#sign-up").fadeIn();
				} else {
					$(this).parents().find("li").removeClass("active");
					$("#menubar").fadeOut();
					$("#sign-in").fadeOut();
					$("#sign-up").fadeOut();
				}
			}
		});
	});
	
</script>
</head>
<body>

			<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	<div class="nav" id="nav">
		<ul>
			<li><label>주루마블</label></li>
			<li><label>Game Start</label></li>

			<li><label><i class="material-icons">subject</i></label></li>
			
			<c:if test="${empty sessionScope._USER_INFO_}">
				<li><label>sign-in</label></li>
				<li><label>sign-up</label></li>			
			</c:if>
			<c:if test="${not empty sessionScope._USER_INFO_}">
				 <li><li>
				 <li><label>${sessionScope._USER_INFO_.userNickname}</label><li>
				 <li><a href="/Marble/doLogout" ><label>log-out</label></a><li>
			</c:if>

		</ul>


	</div>

	<div id="cover"></div>
	
	<!-- 로그인 -->
	<div class="sign-in" id="sign-in">
		<jsp:include page="/WEB-INF/view/user/signIn.jsp" />
	</div>
	
	<!-- 회원가입 -->
	<div class="sign-up" id="sign-up">
		<jsp:include page="/WEB-INF/view/user/signUp.jsp" />		
	</div>
	
	<!-- 메뉴목록 -->
	<div class="menubar" id="menubar">
		<jsp:include page="/WEB-INF/view/play/menu.jsp" />
	</div>
	
	<div id="wrapper">
