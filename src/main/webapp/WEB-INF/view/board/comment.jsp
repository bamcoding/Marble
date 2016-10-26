<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="/Marble/css/comment.css" />

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js">
</script>
<script type="text/javascript">
$(document).ready(function(){
	 
});

$(".deleteBtn").click(function(){
	if (confirm("댓글을 삭제하시겠습니까?")) {
		var commentId = $(this).parents().parents().attr("id");
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
	str += '<div id="'+comment[0]+'" class="commentWrapper">';
	str += '<div class="row uniform">';
	str += '<div class="userInfo 12u">';
	str += '<div style="margin-bottom: -35px">'+comment[2] +'</div>';
	str += '</div>';
	str += '</div>';
	str += '<div class="row uniform">';
	str += '<div class="2u">'+comment[3] +'</div>';
	str += '<div class="comment 8u">'+comment[1]+'</div>';
	str += '<div class="commentDelete"><a href="javascript:deleteAction(\''+comment[0]+'\');"  id="deleteBtn" class="deleteBtn button small"><img src= "/Marble/img/ic_delete_white_24dp_1x.png" style="margin-top: 8px"/></a></div>';
	str += '</div>';
	str += '</div>';
	

	return str;
}

</script>
<!-- 댓글 리스트 -->
	<!-- zzu -->


<div id="comment" style="">
	<form id="writeCmtForm" name="writeCmtForm">
	<div class="row uniform">
		<div class="10u">
			<textarea id="commentContent" name="commentContent"></textarea>
			<input type="hidden" name="boardId" value="${boardId}" />
		</div>
		<div class="2u">
			<a href="#" id="writeCmtBtn" class="button fit big" style="height: 90px"><img src= "/Marble/img/ic_reply_white_24dp_2x.png" style="margin-top: 18px; margin-left: -8px"/></a>
		</div>
	</div>
	</form>
</div>

<div class="commentList" style="overflow-y: scroll; max-height: 400px">
	<form id="listCmtForm" class="listCmtForm" name="listCmtForm">
		<c:forEach items="${comments}" var="comment">
			<div id="${comment.commentId}" class="commentWrapper">
				<div class="row uniform" >
					<div class="userInfo 12u">
					<div style="margin-bottom: -35px">${comment.createdDate }</div>
					</div>
				</div>
				<div class="row uniform">
					<div class="2u">${comment.userVO.userNickname }</div>
					<div class="comment 8u">
					${comment.commentContent }
					</div>
				</div>
				<div class="commentDelete"><a href="javascript:void(0);"  id="deleteBtn" class="deleteBtn button small"><img src= "/Marble/img/ic_delete_white_24dp_1x.png" style="margin-top: 8px"/></a></div>
			</div>
		</c:forEach>
	</form>
</div>
