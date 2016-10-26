<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			if(confirm("\"${gameVO.gameName}.\"를 삭제하시겠습니까?")) {
				location.href="/Marble/admin/doDeleteDetail?gameId=${gameVO.gameId}"
			}
		});	
		
		$("#updateBtn").click(function() {
			location.href="/Marble/admin/updateGame?gameId=${gameVO.gameId}";
		});
		
		
		$("#goBackBtn").click(function() {
	
			location.href="/Marble/admin/gameList";
		});
		
		
		
	});
</script>
</head>
<body>
<h3>게임 상세</h3>
<div id="listDiv">
<table id="listTable">
	<tr>
		<th>Category</th>	
		<td>${gameVO.categoryVO.categoryName}</td>
	</tr>
	<tr>
		<th>번호</th>	
		<c:set var="number" value="${fn:split(gameVO.gameId,'-')[2]}"/>
		<fmt:parseNumber var="number" type="number" value="${number}"/>
		<td>${number}</td>
	</tr>
	<tr>
		<th>이름</th>	
		<td>${gameVO.gameName}</td>
	</tr>
	<tr>
		<th>설명</th>
		<td>${gameVO.gameInfo}</td>
	</tr>
</table>
<div style="float: right;"><input type="submit" id="deleteBtn" value="삭제" onclick="movePage(0)"></div>
<div style="float: right;"><input type="submit" id="updateBtn" value="수정" onclick="movePage(0)"></div>
<div style="float: right;"><input type="submit" id="goBackBtn" value="목록보기" onclick="movePage(0)"></div>
</div>
</body>
</html>