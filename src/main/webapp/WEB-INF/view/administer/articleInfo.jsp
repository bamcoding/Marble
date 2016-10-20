<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/view/administer/admin.jsp" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$("#deleteBtn").click(function() {
		
		$.post( "/Marble/doArticleInfoDelete",$("#searchForm").serialize(),function(data){
			if( data == "true" ){
				location.href = ("/Marble/admin/articleInfo?categoryId=${categoryId}");
			}
			else if( data == "false" ){
				alert("삭제할 게시물을 선택하세요");
			}
		});
	});
});

</script>
<div class="userList">

	<div>
	
		<form id="searchForm" name="searchForm">
		<table class="grid">
			<tr>
				<th>선택</th>
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
					<c:set var="number" value="${fn:split(board.boardId, '-')[2] }" />
					<fmt:parseNumber var="number" type="number" value="${number }" />
					<td><input type="checkbox" name="checks" value="${board.boardId}" /></td>
					<td>${number}</td>
					<td><a
						href="/Marble/board/detail?boardId=${board.boardId }&categoryId=${categoryId}">${board.boardSubject }</a></td>
					<td>${board.userVO.userNickname }</td>
					<td>${board.createdDate }</td>
					<td>${board.hitCount }</td>
					<td>${board.recommendCount }</td>
				</tr>
			</c:forEach>
		</table>
	
	
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
				
				<div class="inline"><input type="button" id="deleteBtn" value="삭제"/></div>
				<div class="inline">
					<a href="/Marble/admin/list/init?categoryId=${param.categoryId}">검색초기화</a>
				</div>
				
			</div>
		</form>
	</div>
</div>
</body>
</html>