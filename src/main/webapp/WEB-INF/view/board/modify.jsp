<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#goBackBtn").click(function() {
			location.herf = "/Marble/board/detail?boardId=${board.boardId}";
		})
		var errorCode = "${param.errorCode}";
		if ( errorCode == 1 ) {
			alert("글 수정에 실패 하였습니다.");
		}
		$("#goBackBtn").click(function(){
			location.href = "/Marble/board/detail?boardId=${board.boardId}&categoryId=${param.categoryId}";
			/* window.history.back(); */
		})
		
		$("#boardSubject").keyup(function(){
			if($(this).val() == "") {
				$(this).addClass("warning");
				$(this).removeClass("pass");
			}
			else {
				$(this).addClass("pass");
				$(this).removeClass("warning");
			}
			isVisibleWriteBtn();
		});
		
		$("#boardContent").keyup(function() {
			if($(this).val == ""){
				$(this).addClass("warning");
				$(this).removeClass("pass");
			}
			else {
				$(this).addClass("pass");
				$(this).removeClass("warning");
			}
			isVisibleWriteBtn();
		});
		
		$("#modifyBtn").click(function() {
			$("#modifyForm").attr({
				method : "post",
				action : "/Marble/doModify"
			}).submit();
		});
	});
</script>

<jsp:include page="/WEB-INF/view/common/header.jsp" />

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




	<div class="clear"></div>


<section id="modify">
	<div class="container">
		<header class="major">
			<h2>${category.categoryName }</h2>
		</header>
		
<form id="modifyForm" name="modyfyForm" enctype="multipart/form-data">
	<input type="hidden" name="boardId" value="${board.boardId}" />
	<input type="hidden" name="categoryId" value="${param.categoryId}" />
	<div>
		<input type="text" id="boardSubject" name="boardSubject" value="${board.boardSubject}" 
					placeholder="제목을 입력하세요" value="첫번째 게시물입니다." />
	</div>
	<div>
		<textarea id="boardContent" name="boardContent" placeholder="내용을 입력 하세요.">${board.boardContent}</textarea>
	</div>
	<c:if test="${not empty board.fileName }">
	<div style="padding-top: 10px; padding-bottom: 10px" >
		<input type="checkbox" id="fileDeleteBtn" name="fileDeleteBtn" value="delete" />
		<img src="/Marble/img/text-file-3-xxl.png" style="width:12px;'" />
		${board.fileName}
	</div>
	</c:if>
	<div>
		<div class="left">
			<input type="file" id="file" name="file" />
		</div>
		<div class ="right">
			<div class="inline">
				<input type="button" id="goBackBtn" value="뒤로가기" />
			</div>
			<div class="inline">
				<input type="button" id="modifyBtn" value="수정하기" /> 
			</div>
		</div>
		<div class="clear"></div>
	</div>
</form>

</div>
</section>
</div>
