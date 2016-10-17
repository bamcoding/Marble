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
<link rel="stylesheet" type="text/css" href="/Marble/css/grid.css" />
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#nav ul li").click(function(){
			var index = $(this).index();
			if( index == 0){
				$("#member-info").mousedown(function(){
					$("#playList").addClass("display-block")
					$("#playList").removeClass("display-none")
					//$("#playList").load("/Marble/admin/userInfo");
					
				});
				$("#member-info").mouseup(function(){
					$("#playList").addClass("display-none")
					$("#playList").removeClass("display-block")
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
			  	
			  		<a href="/Marble/admin" class=memberBtn>Home</a>
			  </li>
			  <li class="member">
			  	  <a href="javascript:void(0);" class="memberBtn">회원관리 </a>
			  	  <div class="member-content">
			  	  	<a href="/Marble/admin/userInfo" id="member-info">회원정보</a>
			  	  </div>
			  </li>
			  <li class="member">
			  		<a href="" class=memberBtn>게시판관리</a>
			  		<div class="member-content">
			  			<a href="">글 검색</a>
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
			  <div style="float:right">
			  	<li style="color:white; padding:12px 16px">안녕하세요 관리자님</li>
			  </div>
			</ul>
		</div>
		<div class="clear" style="width:100%; height:300px; background-color: lightblue;"></div>
		<div class="printList" id="playList"></div>
	</div>

</body>
</html>



