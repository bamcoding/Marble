<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/game.css"/>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>
<script type="text/javascript">

	$().ready(function () {
		


		$("#addBtn").click(function(){	
			location.href="/Marble/admin/addType";
	
			
		});

		
		$("#deleteBtn").click(function(){	
			if(confirm("선택한 게임을 삭제하시겠습니까?")) {
				$.post( "/Marble/admin/deleteType", $("#checkType").serialize());
			}
			
			
		});
		
	});
</script>
</head>
<body>
	
	<div class="gameList">
		
	<div id="game" >
	<form id="checkType" name="checkType">
	<table class="grid">
	<tr>
		<td>선택</td>
		<td>타입번호</td>
		<td>타입이름</td>
		<td>타입내용</td>
	</tr>
	
	<c:forEach items="${types}" var="type">
	<tr id="gemes"  >
		<td><input type="checkbox" id="checkbox" name="checkbox" value="${type.typeId}"></td>
		<td>${type.typeId}</td>
		<td><a href="/Marble/admin/typeDetail?typeId=${type.typeId}" >${type.typeName}</a></td>
		<td>${type.typeInfo}</td>
	</tr>
	</c:forEach>
	</table>
	
	</form>
	<div style="float: right;"><input type="submit" id="addBtn" value="타입추가"></div>
	<div style="float: right;"><input type="submit" id="deleteBtn" value="선택삭제" onclick="movePage(0)"></div>
	
	<form id="searchForm" name="searchForm">
	${paging}	
			<div style="padding-top: 5px;">
			<div class="left">
			
						<select id="searchType" name="searchType">
						//단항 조건문 
							<option value="2" ${ searchGame.searchType eq 2 ? 'selected' : '' }>타입이름+내용</option>
							<option value="3" ${ searchGame.searchType eq 3 ? 'selected' : '' }>타입이름</option>
							<option value="4" ${ searchGame.searchType eq 4 ? 'selected' : '' }>내용</option>
				
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword"  value="${searchGame.searchKeyword}"/>
						<input type="button" id="searchBtn" value="전체검색" onclick="movePage(0)"/>
						<a href="/Marble/admin/searchTypeInit">검색 초기화</a>
				</div>
			
				<div class="clear"></div>
			</div>
	</form>
			
	</div>
	</div>
</body>
</html>