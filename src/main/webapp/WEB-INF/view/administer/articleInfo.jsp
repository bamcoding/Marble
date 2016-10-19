<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/view/administer/admin.jsp" />

<div class="userList">

	<div>
	
		<table class="grid">
			<tr>
				<td><input type="checkbox"></td>
				<th>글번호</th>
				<th>제목</th>
				<th>닉네임</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>추천수</th>
			</tr>
	
			<c:if test="${empty boards}">
				<tr>
					<td colspan="6" style="text-align: center;">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
	
			<c:forEach items="${boards}" var="board">
				<tr>
					<td><input type="checkbox"></td>
					<c:set var="number" value="${fn:split(board.boardId, '-')[2] }" />
					<fmt:parseNumber var="number" type="number" value="${number }" />
					<td>${number }</td>
					<td><a
						href="/Marble/board/detail?boardId=${board.boardId }&categoryId=${categoryId}">${board.boardSubject }</a></td>
					<td>${board.userVO.userNickname }</td>
					<td>${board.createdDate }</td>
					<td>${board.hitCount }</td>
					<td>${board.recommendCount }</td>
				</tr>
			</c:forEach>
		</table>
	
		<form id="searchForm" name="searchForm">
	
			${paging}
	
			<div style="padding-top: 10px; float: right">
	
				<input type="hidden" name="categoryId" value="${param.categoryId }" />
	
				<div class="inline">
					<select id="searchType" name="searchType">
						<option value="1" ${searchBoard.searchType eq 1 ? 'selected' : '' }>제목+내용</option>
						<option value="2" ${searchBoard.searchType eq 2 ? 'selected' : '' }>제목</option>
						<option value="3" ${searchBoard.searchType eq 3 ? 'selected' : '' }>내용</option>
						<option value="4" ${searchBoard.searchType eq 4 ? 'selected' : '' }>작성자</option>
					</select>
				</div>
	
				<div class="inline">
					<input type="text" id="searchKeyword" name="searchKeyword" value="${searchBoard.searchKeyword}" />
				</div>
				
				<div class="inline">
					<input type="button" id="searchBtn" value="검색" onclick="movePage(0)" />
				</div>
				
				<div class="inline">
					<a href="/Marble/admin/list/init?categoryId=${param.categoryId}">검색초기화</a>
				</div>
				
			</div>
		</form>
	</div>
</div>
</body>
</html>