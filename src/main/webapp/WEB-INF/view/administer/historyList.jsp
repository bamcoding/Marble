<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/game.css"/>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
<script type="text/javascript">

	$().ready(function () {
		
		$("#deleteBtn").click(function(){	
			if(confirm("선택한 게임을 삭제하시겠습니까?")) {
				$.post( "/Marble/admin/deleteHistory", $("#checkGame").serialize(), function( data ) {
					  alert( "" + data );
				});
			}
			
			
		});
		
	});
</script>
	
<h3>히스토리관리</h3>
<div id="listDiv">
	<form id="checkGame" name="checkGame">
	<table id="listTable">
	<tr>
		<th>선택</th>
		<th>플레이정보ID</th>
		<th>플레이시간</th>
		<th>회원닉네임</th>
	</tr>
	
	<c:forEach items="${histories}" var="history">
	<tr id="gemes"  >
		<td><input type="checkbox" id="checkbox" name="checkbox" value="${history.playInfoId}"></td>

		<c:set var="number" value="${fn:split(history.playInfoId,'-')[2]}"/>
		<fmt:parseNumber var="number" type="number" value="${number}"/>
		<td><a href="/Marble/admin/historyDetail?playInfoId=${history.playInfoId}" >${number}</a></td>
		<td>${history.playTime}</td>
		<td>${history.userVO.userNickname}</td>
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
							<option value="1" ${ searchGame.searchType eq 1 ? 'selected' : '' }>플레이아이디</option>
							<option value="2" ${ searchGame.searchType eq 2 ? 'selected' : '' }>회원닉네임</option>
							
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword"  value="${searchGame.searchKeyword}"/>
						<input type="button" id="searchBtn" value="전체검색" onclick="movePage(0)"/>
						<a href="/Marble/admin/searchHistoryInit">검색 초기화</a>
				</div>
			
				<div class="clear"></div>
			</div>
	</form>
</div>

</body>
</html>