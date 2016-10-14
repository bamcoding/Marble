<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/Marble/css/grid.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
</head>
<body>
<!-- header -->
<jsp:include page="/WEB-INF/view/common/header.jsp" />

<div id ="list">
	<table class="grid">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>

		<c:forEach items="${boards}" var="board">
		<tr>
			<c:set var="number" value="${fn:split(board.boardId, '-')[2] }" />
			<fmt:parseNumber var="number" type="number" value="${number }" />
			<td>${number }</td>
			<td><a href="/Marble/board/detail?boardId=${board.boardId }&categoryId=${categoryId}">${board.boardSubject }</a></td>
			<td>${board.userVO.userNickname }</td>
			<td>${board.createdDate }</td>
			<td>${board.hitCount }</td>
			<td>${board.recommendCount }</td>
		</tr>
		</c:forEach>
	</table>
	
	<div id = "paging">
		${paging}
	</div>
	
	<form id = "searchForm" name="searchForm">
	<div style="padding-top: 10px;">
	<input type="hidden" name="categoryId" value="${param.categoryId }" />
		<div class="left">
			<a href="/Marble/board/write?categoryId=${param.categoryId}">글쓰기</a>
		</div>
		<div class="right">
			<select id="searchType" name="searchType">
				<option value="1" ${searchBoard.searchType eq 1 ? 'selected' : '' }>제목+내용</option>
				<option value="2" ${searchBoard.searchType eq 2 ? 'selected' : '' }>제목</option>
				<option value="3" ${searchBoard.searchType eq 3 ? 'selected' : '' }>내용</option>
				<option value="4" ${searchBoard.searchType eq 4 ? 'selected' : '' }>작성자</option>
			</select>
			<input type="text" id="searchKeyword" name="searchKeyword" value="${searchBoard.searchKeyword }" />
			<input type="button" id="searchBtn" value="검색"/>
			<a href="/Marble/board/list/init">검색 초기화</a>
		</div>
		<div class="clear"></div>	
	</div>
	</form>
	
</div>

<!-- footer -->
<div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</div>

</body>
</html>