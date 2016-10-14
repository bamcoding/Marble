<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	     
		$("#addBtn").click(function(){
			$.post( "/Marble/doAddGames", $( "#addGamesForm" ).serialize()) });
		

	});
</script>
</head>
<body>
	<div id = "gamesAdd_Wrapper">
		<form id="addGamesForm" name="addGamesForm" >
			<div>
				<input type="text" id="gameName" name="gameName" placeholder="GameName" />
			</div>
			<div>
				<textarea id="gameInfo" name="gameInfo" placeholder="Explanation"></textarea>
			</div>
			<div class="right">
				<input type="button" id="addBtn" value="Add" />
			</div>
		</form>
		
		
	</div>
</body>
</html>