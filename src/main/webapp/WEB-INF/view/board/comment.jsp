<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js">
</script>
<script type="text/javascript">
$(document).ready(function(){
	 
});

$(".deleteBtn").click(function(){
	if (confirm("댓글을 삭제하시겠습니까?")) {
		var commentId = $(this).parents().parents().parents().attr("id");
		$.post("/Marble/board/doDeleteCmt",{"commentId":commentId},function(){
			$("#"+commentId).remove();
		});
		
	}
 });
 
 
 $("#writeCmtBtn").click(function(){
	$.post("/Marble/board/doWriteCmt" , $("#writeCmtForm").serialize() , function(data){
		$("#listCmtForm").prepend(makeDiv(data));
		$("#commentContent").val("");
	});
});

function deleteAction(cmtId){
	var commentId = cmtId.trim().toUpperCase();
	if (confirm("댓글을 삭제하시겠습니까?")) {
		$.post("/Marble/board/doDeleteCmt",{"commentId":commentId},function(){
			$("#"+commentId).remove();
		});
	}
}

 
function makeDiv(data){
	
	var comment = data.split(",");
	
	var str = "";
	str += '<div id="'+comment[0]+'">';
	str += '<div class="userInfo">';
	str += '<div style="display:inline-block;">닉네임 : '+comment[3] +'</div>';
	str += '<div style="display:inline-block; margin-left:15px;">작성 시간 : '+comment[2] +'</div>';
	str += '<div style="display:inline=block; margin-left:15px; float:right;"><input type="button" class="deleteBtn"  name="deleteBtn" value="삭제" onclick="deleteAction(\''+comment[0]+'\')"/></div>';
	str += '</div>';
	str += '<div class="comment">';
	str += '<div style="margin-left:50px; margin-top:10px;">'+comment[1]+'</div>';
	str += '</div>';
	str += '<hr/>';
	str += '</div>';
	
	return str;
}

</script>
<!-- 댓글 리스트 -->
	<!-- zzu -->


<div id="comment" style="border:1px solid black; height:100px;">
	<form id="writeCmtForm" name="writeCmtForm">
	<div>
		<div>
			<textarea id="commentContent" name="commentContent"></textarea>
			<input type="hidden" name="boardId" value="${boardId}" />
		</div>
		<div>
			<input type="button" id="writeCmtBtn" value="등록" />
		</div>
	</div>
	</form>
</div>

<div class="commentList" style="overflow-y: scroll;">
	<form id="listCmtForm" class="listCmtForm" name="listCmtForm">
		<c:forEach items="${comments}" var="comment">
			<div id="${comment.commentId}">
				<div class="userInfo">
				<div style="display:inline-block;">닉네임 : ${comment.userVO.userNickname }</div>
				<div style="display:inline-block; margin-left:15px;">작성 시간 : ${comment.createdDate }</div>
				<div style="display:inline=block; margin-left:15px; float:right;"><input type="button" class="deleteBtn"  name="deleteBtn" value="삭제" /></div>
				</div>
				<div class="comment">
				<div style="margin-left:50px; margin-top:10px;">${comment.commentContent }</div>
				</div>
				<hr/>
			</div>
		</c:forEach>
	</form>
</div>
