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
				location.href="/Marble/admin/doDeleteHistoryDetail?playInfoId=${gameSetVO.historyVO.playInfoId}"
			}
		});	
		
		
		$("#goBackBtn").click(function() {
	
			location.href="/Marble/admin/historyList";
		});
		
		
		
	});
</script>
</head>
<body>
<h3>플레이 상세</h3>
<div id="listDiv" style="overflow:auto;">
<table id="listTable">
	<tr>
		<th>ID</th>	
		<c:set var="number" value="${fn:split(gameSetVO.historyVO.playInfoId,'-')[2]}"/>
		<fmt:parseNumber var="number" type="number" value="${number}"/>
		<td colspan="2">${number}</td>
	</tr>
	<tr>
		<th>시간</th>	
		<td colspan="2">${gameSetVO.historyVO.playTime}</td>
	</tr>

	<tr>
		<th>회원ID</th>	
		<td colspan="2">${gameSetVO.userVO.userId}</td>
	</tr>
	<tr>
		<th>닉네임</th>
		<td colspan="2">${gameSetVO.userVO.userNickname}</td>
	</tr>

	<tr>
			<th>게임</th>
				<td><b>아이디</br></b></b><c:forEach items="${gameSetList}" var="gameSet">
				${gameSet.gamesVO.gameId}</br>
				</c:forEach></td>
				<td><b>이름</br></b><c:forEach items="${gameSetList}" var="gameSet">
				${gameSet.gamesVO.gameName}</br>
				</c:forEach></td>
				
	</tr>

	
</table>
<div style="float: right;"><input type="submit" id="deleteBtn" value="삭제" onclick="movePage(0)"></div>
<div style="float: right;"><input type="submit" id="goBackBtn" value="목록보기" onclick="movePage(0)"></div>
</div>
</body>
</html>