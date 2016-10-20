<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/layout.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js">
</script>
<script>
$(document).ready(function(){
	 $(".deleteBtn").click(function(){
		if (confirm("댓글을 삭제하시겠습니까?")) {
			var commentId = $(this).attr("id");
			$.post("/Marble/board/doDeleteCmt",{"commentId":commentId});
			
		}
	 });
});

</script>
</head>
<body>
<!-- 댓글 리스트 -->
	<form id="listCmtForm" class="listCmtForm" name="listCmtForm">
		<c:forEach items="${comments}" var="comment">
			<div>
				<input type="hidden" id="commentId" value=${comment.commentId} />
				<div style="display:inline-block;">닉네임 : ${comment.userVO.userNickname }</div>
				<div style="display:inline-block; margin-left:15px;">작성 시간 : ${comment.createdDate }</div>
				<div style="display:inline=block; float:right;"><input type="button" id="mdfyBtn" value="수정" /></div>
				<div style="display:inline=block; margin-left:15px; float:right;"><input type="button" class="deleteBtn" id="${comment.commentId}" name="deleteBtn" value="삭제" /></div>
			</div>
			<div>
				<div style="margin-left:50px; margin-top:10px;">${comment.commentContent }</div>
			</div>
			<hr/>
		</c:forEach>
	</form>

</body>
</html>