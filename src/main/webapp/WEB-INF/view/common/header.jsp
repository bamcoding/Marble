<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#sign-in").load("/Marble/signIn");

		$("#nav ul li").click(function() {
			var index = $(this).index();
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
			
			

		});

	});
</script>
</head>
<body>

	<div class="nav" id="nav">
		<ul>
			<li><label>주루마블</label></li>
			<li><label>Game Start</label></li>

			<li><label><i class="material-icons">subject</i></label></li>
			<li><label>sign-in</label></li>
			<li><label>sign-up</label></li>

		</ul>


	</div>

	<div class="sign-in" id="sign-in" style="display: none">
		
	</div>

<jsp:include page="/WEB-INF/view/play/menu.jsp" />

	<div id="wrapper">