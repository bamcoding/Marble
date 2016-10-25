<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

	$(document).ready(function() {

		isVisibleWriteBtn();

		var errorCode = "${param.errorCode}";
		if (errorCode == 1) {
			alert("글 저장에 실패 하였습니다.");
		}
		$("#goBackBtn").click(function() {
			location.href = "/Marble/board/list?categoryId=${param.categoryId}";
		});
		$("#boardSubject").keyup(function() {
			if ($(this).val() == "") {
				$(this).addClass("warning");
				$(this).removeClass("pass");
			} else {
				$(this).addClass("pass");
				$(this).removeClass("wraning");
			}
			isVisibleWriteBtn();
		});

		$("#boardContent").keyup(function() {
			if ($(this).val() == "") {
				$(this).addClass("warning");
				$(this).removeClass("pass");
			} else {
				$(this).addClass("pass");
				$(this).removeClass("wraning");
			}
			isVisibleWriteBtn();
		});

		$("#writeBtn").click(function() {
			$("#writeForm").attr({
				method : "post",
				action : "/Marble/doWrite"
			}).submit();
		});
		
		
			

		function isVisibleWriteBtn() {
			if ($(".pass").length == 2) {
				$("#writeBtn").show(500);
			} else {
				$("#writeBtn").hide();
			}
		}
;
	})
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
<section id="write">
	<div class="container">
		<header class="major">
			<h2>${category.categoryName }</h2>
		</header>

	<form id="writeForm" name="writeForm" enctype="multipart/form-data">
	<input type="hidden" name="categoryId" value="${param.categoryId }" />
		<div>
			<input type="text" id="boardSubject" name="boardSubject"
				placeholder="제목을 입력하세요">
		</div>
		<div>
			<textarea id="boardContent" name="boardContent"
				placeholder="내용을 입력하세요"></textarea>
		</div>
		<div style="margin-top: 5px;">
			<div class="left">
				<input type="file" id="file" name="file" />
			</div>
			<div class="right">
				<div class="inline">
					<input type="button" id="goBackBtn" value="뒤로가기" />
				</div>
			<div class="inline">
				<input type="button" id="writeBtn" value="글쓰기" />
			</div>
			</div>
			<div class="clear"></div>
			

		</div>
	</form>
</div>
</section>
</div>
