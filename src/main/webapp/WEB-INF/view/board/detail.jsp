<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/Marble/css/grid.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<jsp:include page="/WEB-INF/view/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$("#deleteBtn").click(function() {
		if (confirm("\"${board.boardSubject }\"를 삭제하시겠습니까?")) {
			location.href = "/Marble/doDelete?boardId=${board.boardId}&categoryId=${categoryId}";
		}
	});

	$("#recommendBtn").click(function() {
		if (confirm("\"${board.boardSubject }\"를 추천하시겠습니까?")) {
			$.post("/Marble/doUpdateRecommend",{boardId : "${board.boardId}"},function(data) {
				if (data == "true") {
					var count = parseInt($("#recommendCount").text());
					$("#recommendCount").text(count + 1);
				} else {
					alert("추천 중 오류가 발생하였습니다.")
				}
			});

		}
	});
	$("#listBtn").click(function() {
		location.href = "/Marble/goToList?categoryId=${categoryId}";
	});

});
</script>
<div id="article">
	<div id="articleHeader">
		<p>${board.boardSubject }</p>
		<div id="articleInfo">
			<span>${board.userVO.userNickname}</span> <span>${board.createdDate}</span>
			<span><img src= "/Marble/img/eye-icon.png" />${board.hitCount}</span>
			<span><img src="/Marble/img/heart-24-128.png" /><span
				id="recommendCount">${board.recommendCount}</span></span>
		</div>
		<c:if test="${not empty board.fileName }">
			<div id="attachedFile">
				<span><img src="/Board/img/text-file-3-xxl.png" /><a
					href="/Marble/doDownload?boardId=${board.boardId}">${board.fileName }</a></span>
			</div>
		</c:if>
	</div>
	<hr />
	<div id="boardBody">${board.boardContent}</div>
</div>
<div id="boardFooter">
	<a href="javascript:void(0);" id="recommendBtn">추천</a>

	<c:if test="${sessionScope._USER_INFO_.userId eq board.userId || sessionScope._USER_INFO_.userEmail eq 'admin' }">
		<a href="javascript:void(0);"  id="deleteBtn">삭제</a>
		<a href="/Marble/board/modify?boardId=${board.boardId}&categoryId=${categoryId}">수정</a>
    	<a href="javascript:void(0);"  id="listBtn">목록보기</a>
	</c:if>

</div>
</body>
</html>