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
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

		$("#categoryId").change(function(){
			$.post("/Marble/searchType",{"categoryId": $("#categoryId").val()}, function(data) {
				$("#gameName").remove();
				$("#gameName").html(data); 
			});
		});
		
</script>

</head>
<body>

	<div>
		<select id="categoryId" name="categoryId">
			<option>Category</option>
			<c:forEach items="${games}" var="game">
				<option value="${game.gameId}">${game.gameName}</option>
			</c:forEach>
		</select>
	</div>
	<div id = "gameName" name="gameName"></div>
	<div></div>

</body>
</html>