<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/admin.css"/>
<link rel="stylesheet" type="text/css" href="/Marble/css/grid.css" />

<title>Insert title here</title>
</head>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$( "#gameList" ).load( "/Marble/admin/gameCategory" );
		
		$("#nav ul li").click(function(){
			var index = $(this).index();
			if( index == 1){
				$("#member-info").mousedown(function(){
					$("#playList").addClass("display-block")
					$("#playList").removeClass("display-none")
					
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
		<div id="admin">
		<div id="nav" style="width:100%; height:49px;">
			<ul>
			  <li class="member">
			  	
			  		<a href="/Marble/play/index" class=memberBtn>Home</a>
			  </li>
			  <li class="member">
			  	  <a href="javascript:void(0);" class="memberBtn">회원관리 </a>
			  	  <div class="member-content">
			  	  	<a href="/Marble/admin/userInfo" id="member-info" class="underBtn">회원정보</a>
			  	  </div>
			  </li>
			  <li class="member">
			  		<a href="javascript:void(0);"  class="articleBtn">게시판관리</a>
			  		<div class="member-content">
			  			<a href="/Marble/admin/articleInfo?categoryId=12"  id="article-info" class="underBtn">커뮤니티</a>
			  			<a href="/Marble/admin/articleInfo?categoryId=13" class="underBtn">문의사항</a>
			  		</div>
	  		 </li>
			  <li class="member">
			  	<a href="">게임관리</a>
			  		<div class="member-content">
			  			<a href="/Marble/admin/gameList" class="underBtn">게임전체</a>
			  			<div id= "gameList" >
			  			</div>
			  			<a href="/Marble/admin/customList" class="underBtn">사용자 게임</a>

			  		</div>
			  			
			  </li>
			  <li class="member">
			  	<a href="/Marble/admin/categorySet" class="categoryBtn">카테고리 설정</a>
			  </li>
			  <li class="member">
			  	<a href="javascript:void(0);"  class="categoryBtn">게임타입 관리</a>
			  		<div class="member-content">
			  			<a href="/Marble/admin/typeList" id="category-info" class="underBtn">게임타입</a>
			  		</div>
			  </li>
			    <li class="member">
			  	<a href="javascript:void(0);"  class="advertisemnetBtn">광고관리</a>
			  		<div class="member-content">
			  			<a href="/Marble/admin/advertisement" id="category-info" class="underBtn">광고 설정</a>
			  		</div>
			  </li>
	   	    <li class="member">
			  	<a href="javascript:void(0);"  class="soundTrackBtn">음악관리</a>
			  		<div class="member-content">
			  			<a href="/Marble/admin/soundTrack" id="category-info" class="underBtn">음악 설정</a>
			  		</div>
			  </li>
			    <li class="member">
			  	<a href="javascript:void(0);"  class="historyBtn">히스토리 관리</a>
			  		<div class="member-content">
			  			<a href="/Marble/admin/historyList" id="category-info" class="underBtn">히스토리 설정</a>
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

	</div>

</body>
</html>
