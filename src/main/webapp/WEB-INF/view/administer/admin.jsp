<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/admin.css"/>

<title>Insert title here</title>
</head>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#nav ul li").click(function(){
			var index = $(this).index();
			console.log(index);
			if( index == 0){
				$("#member-info").click(function(){
					$("#playList").load("/Marble/admin/userInfo");
				});
			}
			if( index == 1){
				$("#article-info").click(function(){
					$("#playList").load("/Marble/admin/articleInfo");
				});
			}
			if( index == 3){
				$("#category-info").click(function(){
					$("#playList").load("/Marble/admin/category");
				});
			}
		});
	});
</script>
<body>
	
	<div class="wrapper">
		<div id="nav" style="width:100%; height:49px;">
			<ul>
			  <li class="member">
			  	  <a href="javascript:void(0);" class="memberBtn">회원관리 </a>
			  	  <div class="member-content">
			  	  	<a href="javascript:void(0);" id="member-info">회원정보</a>
			  	  </div>
			  </li>
			  <li class="member">
			  		<a href="javascript:void(0);"  class="articleBtn">게시판관리</a>
			  		<div class="member-content">
			  			<a href="javascript:void(0);"  id="article-info">게시판 정보</a>
			  			<a href="">글 삭제</a>
			  		</div>
	  		 </li>
			  <li class="member">
			  	<a href="">게임관리</a>
			  		<div class="member-content">
			  			<a href="">게임 목록</a>
			  			<a href="">게임 삭제</a>
			  		</div>
			  </li>
			  <li class="member">
			  	<a href="javascript:void(0);"  class="categoryBtn">카테고리관리</a>
			  		<div class="member-content">
			  			<a href="javascript:void(0);" id="category-info">카테고리 목록</a>
			  			<a href="">카테고리 삭제</a>
			  		</div>
			  </li>
			  <div style="float:right">
			  	<li style="color:white; padding:12px 16px">안녕하세요 성정한님</li>
			  </div>
			</ul>
		</div>
		<div class="clear" style="width:100%; height:300px; background-color: lightblue;"></div>
		<div class="printList" id="playList"></div>
	</div>

</body>
</html>



