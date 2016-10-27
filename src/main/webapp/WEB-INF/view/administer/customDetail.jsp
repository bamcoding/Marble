<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
<link rel="stylesheet" type="text/css" href="/Marble/css/pagination.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			if(confirm("\"${gameVO.gameName}.\"를 삭제하시겠습니까?")) {
				location.href="/Marble/admin/doDeleteCustomDetail?gameId=${customVO.gameId}"
			}
		});	
		
		$("#updateBtn").click(function() {
			location.href="/Marble/admin/updateCustom?gameId=${customVO.gameId}";
		});
		
		
		$("#goBackBtn").click(function() {
			location.href="/Marble/admin/customList";
		});
		
		
		
	});
</script>

<h3>사용자 게임 상세</h3>
<div id="listDiv">
<table id="listTable">
	
		<tr>
			<th>Category</th>	
			<c:set var="number" value="${fn:split(customVO.gameId,'-')[2]}"/>
			<fmt:parseNumber var="number" type="number" value="${number}"/>
			<td>${number}</td>
		</tr>
		<tr>
			<th>게임 이름</th>	
			<td>${customVO.gamesVO.gameName}</td>
		</tr>
		<tr>
			<th>회원닉네임</th>
			<td>${customVO.userVO.userNickname}</td>
		</tr>
		<tr>
			<th>게임설명</th>
			<td>${customVO.gamesVO.gameInfo}</td>
		</tr>
	</table>
	<div style="float: right;"><input type="submit" id="deleteBtn" value="삭제" onclick="movePage(0)"></div>
	<div style="float: right;"><input type="submit" id="updateBtn" value="수정" onclick="movePage(0)"></div>
	<div style="float: right;"><input type="submit" id="goBackBtn" value="목록보기" onclick="movePage(0)"></div>
</div>
</body>
</html>