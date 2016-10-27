<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/file.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		//파일 css
		var fileTarget = $('.filebox .upload-hidden');

		  fileTarget.on('change', function(){  // 값이 변경되면
		    if(window.FileReader){  // modern browser
		      var filename = $(this)[0].files[0].name;
		    } 
		    else {  // old IE
		      var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		    }
		    
		    // 추출한 파일명 삽입
		    $(this).siblings('.upload-name').val(filename);
		  });
		
		
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
			<input type="hidden" name="categoryId" value="${category.categoryId}" />
			<div class="row uniform">
			<div class="12u">
			<input type="text" id="boardSubject" name="boardSubject" value="${board.boardSubject}" 
					placeholder="제목을 입력하세요" value="첫번째 게시물입니다." />
			</div>
			</div>
			<div class="row uniform">
			<div class="12u">
				<textarea id="boardContent" name="boardContent" rows="7" placeholder="내용을 입력 하세요.">${board.boardContent}</textarea>
			</div>
			</div>
			
			
			<c:if test="${not empty board.fileName }">
				<div style="padding-top: 10px; padding-bottom: 10px" >
					<input type="checkbox" id="fileDeleteBtn" name="fileDeleteBtn" value="delete" />
					<label for="fileDeleteBtn"><img src="/Marble/img/text-file-3-xxl.png" style="width:12px;'" />
					${board.fileName}</label>
				</div>
			</c:if>
			
			<div class="row uniform filebox">
					<div class="11u">
 							<input class="upload-name" type="text" value="파일선택" disabled="disabled">
 					</div>
 					<div class="1u" style="padding-left: 0px; margin-top: 2px">
 							<label for="file" class="button"><img src="/Marble/img/ic_file_upload_white_24dp_1x.png" style="margin-top: 10px"></label> 
  							<input type="file" id="file" class="upload-hidden"> 
					</div>
				</div>
			
				<div class ="row uniform">
					<div class="6u">
						<input type="button" id="goBackBtn" class="button fit" value="뒤로가기" />
					</div>
					<div class="6u">
						<input type="button" id="modifyBtn" class="button fit special" value="수정하기" /> 
					</div>
				</div>
		</form>

	</div>
</section>
</div>
