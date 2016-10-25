<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>
<link rel="stylesheet" type="text/css" href="/Marble/css/game.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			if(confirm("\"${typeVO.typeName}.\"를 삭제하시겠습니까?")) {
				location.href="/Marble/admin/deleteTypeDetail?typeId=${typeVO.typeId}"
			}
		});	
		
		$("#updateBtn").click(function() {
			location.href="/Marble/admin/updateType?typeId=${typeVO.typeId}";
		});
		
		
		$("#goBackBtn").click(function() {
	
			location.href="/Marble/admin/typeList";
		});
		
		
		
	});
</script>
</head>
<body>
	<div class="gameList">
	<table class="grid">
		<tr>
			<td>타입번호</td>	
			<td>${typeVO.typeId}</td>
		</tr>
		<tr>
			<td>타입이름</td>	
			<td>${typeVO.typeName}</td>
		</tr>
		<tr>
			<td>타입설명</td>
			<td>${typeVO.typeInfo}</td>
		</tr>
	</table>
	<div style="float: right;"><input type="submit" id="deleteBtn" value="삭제" onclick="movePage(0)"></div>
	<div style="float: right;"><input type="submit" id="updateBtn" value="수정" onclick="movePage(0)"></div>
	<div style="float: right;"><input type="submit" id="goBackBtn" value="목록보기" onclick="movePage(0)"></div>
	</div>
</body>
</html>