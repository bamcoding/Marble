<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	
	$("#gameName").keyup(function(){
		$.post("/Marble/IsExistGameName", 
				{"gameName": $("#gameName").val()},
				function(data){
					if(data == "false") {
						$("#gameName").addClass("pass");
						$("#gameName").removeClass("warning");
					}
					else {
						$("#gameName").removeClass("pass");
						$("#gameName").addClass("warning");
					}
		});
	});
	
		$("#addBtn").click(function(){				
				if($("#gameName").val() == "") {
					alert("제목을 입력해주세요");
					return;
				}
				else if($("#gameInfo").val() == "") {
					alert("내용을 입력해주세요");
					return;
				}
				else {
					$.post("/Marble/IsExistGameName", 
							{"gameName": $("#gameName").val()},
							function(data){
								if(data == "false") {
									$.post( "/Marble/doAddGames", $( "#addGamesForm" ).serialize());
									alert("게임이 등록되었습니다.");
								}
								else {
									alert("게임 이름이 중복됩니다.");
								}
					
				});	
				}
		});

});	

</script>
</head>
<body>
	<div id = "gamesAdd_Wrapper">
		<form id="addGamesForm" name="addGamesForm" >
			<div class="row uniform">
				<div class="12u">
					<input type="text" id="gameName" name="gameName" placeholder="게임 이름을 작성하세요." />
				</div>
			</div>
			
				<div class="row uniform">
			<div class="12u">
				<textarea id="gameInfo" name="gameInfo" placeholder="게임 설명을 작성하세요." rows="7"></textarea>
			</div>
			</div>
			
			<div class="12u">
				<input type="button" id="addBtn" class="button fit special" value="ADD" />
			</div>
		</form>
		
		
	</div>
</body>
</html>