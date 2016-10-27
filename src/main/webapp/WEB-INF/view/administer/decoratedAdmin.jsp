<!-- 

관리자 홈입니다.
관리자 홈입니다.
관리자 홈입니다.

 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/decorateAdmin.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$().ready(function(){
	
	$( "#gameList" ).load( "/Marble/admin/gameCategory" );
	
	$("#admin_home").click(function(){
		alert("관리자 페이지입니다.");
		location.href = "/Marble/admin";
	});
	
	$("#admin_title .menu").click(function(){
		var menu = $(this).val();
		if(menu=="회원관리"){
			location.href = "/Marble/admin/userInfo";
		}
		if(menu=="카테고리관리"){
			location.href = "/Marble/admin/categorySet";
		}
		if(menu=="게시판관리"){
			location.href = "/Marble/admin/boardInfo";
		}
		if(menu=="게임관리"){
			location.href = "/Marble/admin/gameList";
		}
	
		if(menu=="사용자게임"){
			location.href = "/Marble/admin/customList";
		}
		if(menu=="게임타입관리"){
			location.href = "/Marble/admin/typeList";
		}
		if(menu=="광고관리"){
			location.href = "/Marble/admin/advertisement";
		}
		if(menu=="히스토리관리"){
			location.href = "/Marble/admin/historyList";
		}
		if(menu=="이미지관리"){
			location.href = "/Marble/admin/imageSet";
		}
		if(menu=="주루마블"){
			location.href = "/Marble/play/index";
		}
	});
});
</script>
<head>
<title>주루마블 : 관리자 전용 페이지입니다.</title>
</head>
<body>

<div id="admin_wrapper">
	<div id="admin_title">
		<ul>
			<li class="member">
				<input type="button" id="admin_home" class="inline" value="Admin"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="회원관리"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="카테고리관리"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="게시판관리"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="게임관리"/>
				<div class="member-content">
				<div id= "gameList" >
			    </div>
				<input type="button" class="menu underMenu" value="사용자게임"/>
				</div>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="게임타입관리"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="광고관리"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="히스토리관리"/>
			</li>
			<li class="member">
				<input type="button" class="inline menu" value="주루마블"/>
			</li>
		</ul>
	</div>
	<div class="clear"></div>
</div>


