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
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		moveToCenter("#sign-in");
		moveToCenter("#sign-up");
		
		$("#sign-in").load("/Marble/signIn");
		$("#sign-up").load("/Marble/signUp");

		$("#nav ul li").click(function() {
			var index = $(this).index();
			console.log("메뉴 인댁스 : "+index);
			if (index == 1) {
				$("#marbleBoard").load("/Marble/setMarbleBoard");
			}
			
			if (index == 2) {
				if (!$(this).hasClass("active")) {
					$("#menubar").fadeIn();
					$(this).addClass("active");

					$("#sign-in").slideUp();
				} else {
					$(this).parents().find("li").removeClass("active");
					$("#sign-in").slideUp();
					$("#menubar").fadeOut();
				}
			}
			if (index == 3) {
				if (!$(this).hasClass("active")) {
					$("#sign-in").slideDown();
					$(this).addClass("active");

					$("#menubar").fadeOut();
				} else {
					$(this).parents().find("li").removeClass("active");
					$("#sign-in").slideUp();
					$("#menubar").fadeOut();
				}

			}
			if (index == 4) {
				if (!$(this).hasClass("active")) {
					$("#sign-up").slideDown();
					$(this).addClass("active");

					$("#menubar").fadeOut();
				} else {
					$(this).parents().find("li").removeClass("active");
					$("#sign-up").slideUp();
					$("#menubar").fadeOut();
				}

			}
		});
	});
	
	$(window).resize(function() {
		moveToCenter("#sign-in");
		moveToCenter("#sign-up");
	});
	
	function moveToCenter(data) {
		var windowHeight = $(window).height();
		var wrapperHeight = $(data).height();
		var middlePosition = (windowHeight / 2) 
								- (parseInt(wrapperHeight) / 2);
		
		$(data).css({
			"position": "relative"
			, "top": middlePosition+ "px"
			, "margin": "auto"
		});
	}
</script>
</head>
<body>
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

	<div class="sign-in" id="sign-in">
		
	</div>
	<div class="sign-up" id="sign-up">
		
	</div>

<jsp:include page="/WEB-INF/view/play/menu.jsp" />

	<div id="wrapper">