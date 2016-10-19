<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css"/>
<link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/Marble/css/blueGrid.css">
<script type="text/javascript" src="/Marble/js/blueGrid.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>

<style>
#ctgr_view{
	border:1px solid black;
	width:500px;
	margin:10px;
	border-radius: 10px;
}
#ctgr_title{
	height:50px;
	font-weight: bold;
	background:#eeeeee;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}
#ctgr_title .left{
	margin:0 20px 0 20px;
	line-height: 50px;
}
#ctgr_title .right{
	margin:10px 20px 10px 20px;
	height:50px;
}

#ctgr_content{
	margin:10px 0 10px 0;
}
</style>

<body>
	<form id="categoryForm" name="categoryForm">
	
	<div id="ctgr_view">
	<div id="ctgr_title">
		<div class="left inline2">카테고리 미리보기</div>
		<div class="right inline2">
			<input type="submit" id="addBtn" value="추가"/>
			<input type="submit" id="modifyBtn" value="수정"/>
			<input type="submit" id="deleteBtn" value="삭제"/>
		</div>
		<div class="clear"></div>
	</div>
	<!-- 이전 레벨과 현재 레벨이 다를 경우 ul -->
	<div id="ctgr_content">
	<div class="container">
	<ul><li>전체보기
		<c:set var="pr" value="0" />
		<c:forEach items="${categories }" var="category" >
		<c:set var="nr" value="${category.level }" />
			<c:choose>
				<c:when test="${pr lt nr }">
					<ul>
					<li><a href="#">${category.categoryName}(${pr},${nr})</a>
					<c:set var="pr" value="${nr}"/>
				</c:when>
				<c:when test="${pr gt nr }">
					
					<c:forEach begin="1" end="${pr-nr }" step="1">
						</ul></li>
					</c:forEach>
					<li><a href="#">${category.categoryName}(${pr},${nr})</a></li>
					<c:set var="pr" value="${nr}"/>
				</c:when>
				
				<c:otherwise>
				<li><a href="#">${category.categoryName}(${pr},${nr})</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</li>
	</ul>		
	</div>
	
	</div>
	</div>
	</form>
</body>
</html>