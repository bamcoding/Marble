<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#settings").load("/Marble/myInfo");
		
		$("#menu ul li").click(function(){
			var index = $(this).index();
			console.log("메뉴목록 인덱스 : "+index);
			if(index == 0){
				$("#settings").load("/Marble/myInfo");
			}
			if(index == 1){
				$("#settings").load("/Marble/setGames");
			}
			if(index == 2){
				$("#settings").load("/Marble/addGames");
			}
		});
	});
	
	
</script>


	<div class="menu" id="menu">

		<ul>
			<li><a href="#info">내 정보</a></li>
			<li><a href="#setGame">게임 셋팅</a></li>
			<li><a href="#addGame">게임 추가</a></li>
			<li><a href="/Marble/board/list?categoryId=12">커뮤니티</a></li>
			<li><a href="/Marble/board/list?categoryId=13">문의사항</a></li>
		<c:if test="${sessionScope._USER_INFO_.userEmail eq 'admin'}">			
			<li><a href="/Marble/admin">관리자페이지</a></li>
		</c:if>	
		</ul>


	</div>
	<div class="settings" id="settings">

	</div>

