<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/pagination.css" />
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
<script type="text/javascript">
	$().ready(function () {
		$("#deleteBtn").click(function(){	
			if(confirm("삭제하시겠습니까?")) {
				$.post( "/Marble/admin/deleteCustom", $("#registForm").serialize(), function( data ) {
					  alert( "" + data );
				});
			}
			
			
		});
		
	});
</script>
</head>
<body>	
	<h3>사용자 게임 관리</h3>
	<div id="listDiv">

	<form id="registForm" name="registForm">
	<table id="listTable">
	<tr>
		<th>선택</th>
		<th>게임번호</th>
		<th>게임이름</th>
		<th>회원아이디</th>
	</tr>
	
	<c:forEach items="${customs}" var="custom">
	<tr id="gemes"  >
		<td><input type="checkbox" id="checkbox" name="checkbox" value="${custom.gameId}"></td>
		<c:set var="number" value="${fn:split(custom.gameId,'-')[2]}"/>
		<fmt:parseNumber var="number" type="number" value="${number}"/>
		<td>${number}</td>
		<td><a href="/Marble/admin/customDetail?gameId=${custom.gameId}" >${custom.gamesVO.gameName}</a></td>
		<td>${custom.userVO.userNickname}</td>
	</tr>
	</c:forEach>
	</table>
	
	</form>
	<div style="float: right;"><input type="submit" id="deleteBtn" value="선택삭제" onclick="movePage(0)"></div>
	
	<form id="searchForm" name="searchForm">
	${paging}	
			<div style="padding-top: 5px;">	
			<div class="left">
		
						<select id="searchType" name="searchType">
						//단항 조건문 
							<option value="1" ${ searchGame.searchType eq 1 ? 'selected' : '' }>회원닉네임</option>
							<option value="2" ${ searchGame.searchType eq 2 ? 'selected' : '' }>게임이름+내용</option>
							<option value="3" ${ searchGame.searchType eq 3 ? 'selected' : '' }>게임이름</option>
							<option value="4" ${ searchGame.searchType eq 4 ? 'selected' : '' }>내용</option>
				
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword"  value="${searchGame.searchKeyword}"/>
						<input type="button" id="searchBtn" value="검색" onclick="movePage(0)"/>
						<a href="/Marble/admin/searchCustomInit">검색 초기화</a>
				</div>
				<div class="clear"></div>
			</div>
	</form>
			
	</div>

</body>
</html>