<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
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

	$("#commentList").load("/Marble/board/listCmt?boardId=${board.boardId}");
	
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
<section id="article">
	<div class="container">
		<header class="major">
			<h2>${category.categoryName }</h2>
		</header>

<div id="article">
	<div id="articleHeader">
		<div class="row uniform">
			<div class="9u">
				<h2>${board.boardSubject }</h2>
			</div>
			<div class="3u">
				<span style="margin: 10px"><img src= "/Marble/img/eye-icon.png" />${board.hitCount}</span>
				<span style="margin: 10px">
					<img src="/Marble/img/heart-24-128.png" />
					<span id="recommendCount">${board.recommendCount}</span>
				</span>
			</div>
			
		</div>
		
		<div id="articleInfo" class="12u" style="text-align: right;">
			<span >${board.userVO.userNickname}</span> <span>${board.createdDate}</span>
			
		</div>
		<div class="row uniform">
			<div class="10u">
		<c:if test="${not empty board.fileName }">
				<span><img src="/Marble/img/text-file-3-xxl.png" /><a
					href="/Marble/doDownload?boardId=${board.boardId}">${board.fileName }</a></span>
		</c:if>
			</div>
			<div class="2u" style="text-align: right;">
				<a href="javascript:void(0);" class="button small" id="recommendBtn"><img src= "/Marble/img/ic_thumb_up_white_24dp_1x.png" style="margin-top: 8px"/></a>
			</div>
		</div>
		
		
	</div>
	<hr />
	<div id="boardBody" style="padding: 20px">${board.boardContent}</div>
	<hr />
</div>
<div id="boardFooter" class="row uniform">
	<div class="10u">
	
	<c:if test="${sessionScope._USER_INFO_.userId eq board.userId || sessionScope._USER_INFO_.userEmail eq 'admin' }">
		<a href="javascript:void(0);"  id="deleteBtn" class="button small"><img src= "/Marble/img/ic_delete_white_24dp_1x.png" style="margin-top: 8px"/></a>
		<a href="/Marble/board/modify?boardId=${board.boardId}&categoryId=${categoryId}" class="button small"><img src= "/Marble/img/ic_create_white_24dp_1x.png" style="margin-top: 8px"/></a>
    	<!-- <a href="javascript:void(0);"  id="listBtn">목록보기</a> -->
	</c:if>
	</div>
	<div class="2u" style="text-align: right;">
	<a href="/Marble/board/list?categoryId=${categoryId}" class="button small"><img src= "/Marble/img/ic_list_white_24dp_1x.png" style="margin-top: 8px"/></a>
	</div>
</div>

<div id="commentList"></div>

</div>
</section>
</div>

