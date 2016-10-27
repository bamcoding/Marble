<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="/Marble/css/pagination.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<!-- header -->
<script type="text/javascript">
	$().ready(function() {
		$("#searchType").change(function() {
			//	alert($(this).val());
			
		});
		
	});

</script>

<jsp:include page="/WEB-INF/view/common/header.jsp" />


<!-- Main -->
<div id="main">
	<section id="one"></section>

	<!-- One -->
	<!-- <section id="start">
			<div class="container">
				<header class="major">
				<h2>Read Only</h2>
				<p>
					Just an incredibly simple responsive site<br /> template freebie
					by <a href="http://html5up.net">HTML5 UP</a>.
				</p>
				</header>
			</div>
			</section> -->






<section id="board">
	<div class="container">
		<header class="major">
			<h2>${category.categoryName }</h2>
		</header>
		<form id="searchForm" name="searchForm">
		
		
		<div>
			<a href="/Marble/board/write?categoryId=${category.categoryId}"
							class="button fit big">글쓰기</a>
		</div>
		
			<div id="list" class="table-wrapper">
				<table class="">
					<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>닉네임</th>
						<th>작성일</th>
						<th><span><img src= "/Marble/img/eye-icon.png" /></span></th>
						<th><span><img src="/Marble/img/heart-24-128.png" /></span></th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${empty boards}">
						<tr>
							<td colspan="6" style="text-align: center;">등록된 게시물이 없습니다.</td>
						</tr>
					</c:if>

					<c:forEach items="${boards}" var="board">
						<tr>
							<c:set var="number" value="${fn:split(board.boardId, '-')[2] }" />
							<fmt:parseNumber var="number" type="number" value="${number }" />
							<td>${number }</td>
							<td>
							<%-- <a href="javascript:toDetail('${board.boardId}','${category.categoryId}');">${board.boardSubject}</a> --%>
							<a href="/Marble/board/detail?boardId=${board.boardId }&categoryId=${category.categoryId }">${board.boardSubject }</a>
							</td>
							<td>${board.userVO.userNickname }</td>
							<td>${board.createdDate }</td>
							<td>${board.hitCount }</td>
							<td>${board.recommendCount }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				
					<div
						style="text-align: center; margin-top: 10px; margin-bottom: 10px;">
						
						${paging}
						
					</div>

					<div class="12u" style="text-align: right; margin-bottom: -10">
					  <a
					href="/Marble/board/list/init?categoryId=${category.categoryId}">검색어
					초기화</a>
					</div>
					
					<div style="padding-top: ;">
			<input type="hidden" name="categoryId"
				value="${category.categoryId }" /> <input type="hidden"
				name="boardId" value="${board.boardId }" />
				<div class="row uniform" style="margin-bottom: 20px">
			<div class="3u">
				
				<select id="searchType" name="searchType">
					<option value="1" ${searchBoard.searchType eq 1 ? 'selected' : '' }>제목+내용</option>
					<option value="2" ${searchBoard.searchType eq 2 ? 'selected' : '' }>제목</option>
					<option value="3" ${searchBoard.searchType eq 3 ? 'selected' : '' }>내용</option>
					<option value="4" ${searchBoard.searchType eq 4 ? 'selected' : '' }>작성자</option>
				</select> 
				</div>

				<div class="7u">
				<input type="text" id="searchKeyword" name="searchKeyword"	value="${searchBoard.searchKeyword}" />
					
					</div>
					<div class="2u">
					
					<input type="button"
					id="searchBtn" value="검색" onclick="movePage(0)" />
					</div>
					
					</div>

					
				
			</div>
		</div>
		</form>
	</div>
</section>
</div>



<jsp:include page="/WEB-INF/view/common/footer.jsp" />
