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
$(document).ready(function () {
	
	   $("select option:selected").each(function () {
		   $( "#gameName" ).load( "/Marble/searchType", { "categoryId": $("#categoryId").val()} );
			});

	     
		$("#categoryId").change(function(){
			$( "#gameName" ).load( "/Marble/searchType", { "categoryId": $("#categoryId").val()} );
		});

	});
</script>
</head>
<body>
	<div id = "gamesAdd_Wrapper">
		<div>
			<select id="categoryId" name="categoryId">
				<option selected="selected">Category</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category.categoryId}">${category.categoryName}</option>
				</c:forEach>
			</select>
		</div>
		
		<div name="gameName" id="gameName"></div>
	</div>
</body>
</html>