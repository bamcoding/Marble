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
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">


$(document).ready(function () {
	
	$("#categoryId").change(function(){
		$.post( "/Marble/searchType", { "categoryId": $("#categoryId").val()}, function(data) {
			var gamesArr = data.split(",");	
			$("#games_set ul li").hide();
			$("#games_set ul li").each(function(){
				var gameName = $(this).attr("id");
				for(i=0; i<gamesArr.length; i++){
					if(gameName == gamesArr[i]){
						$(this).show();
					}
				}
			});
		});
	});
	
	$("#addBtn").click(function() {
	});

	$("#games_set ul li").click(function() {
		var value = $(this).find(".gameCnt").text();
		if(value > 0){
			$(this).addClass("selected");								
		}else{
			$(this).removeClass("selected");
		}
		
	});

	function getCnt() {
		var cnt = 0;
		$(".gameCnt").each(function() {
			var num = $(this).text();
			if (num != "") {
				cnt = cnt + parseInt(num);
			}
		});
		return cnt;
	}
	
	var gameCnt = 0;
	$(".mBtn").click(function(){
		var num = $(this).parents().children(".gameCnt").text();
		if(num != "0"){
			$(this).parents().children(".gameCnt").text(parseInt(num)-1);
			gameCnt--;
		}
	});
	
	$(".pBtn").click(function(){
		var num = $(this).parents().children(".gameCnt").text();
		if(gameCnt < 19){
			$(this).parents().children(".gameCnt").text(parseInt(num)+1);
			gameCnt++;
		}else{
			var txt = "게임을 23개 이상 정할 수 없습니다.";
			showWarning(txt);
		}
	});
	
	function showWarning(txt){
		$(".warning").text(txt).fadeIn(500).fadeOut(1200);
	}
	
	$(".info").click(function() {
		var info = $(this).parents().children(".gameInfo").val();
		alert(info);
	});
	
	$("#setBtn").click(function() {
		
		if(gameCnt < 19){
			var txt = "게임을 19개 선택하셔야합니다.";
			showWarning(txt);
			return;
		}
		
		var param = "";
		$("#games_set ul li").each(function(){
			if($(this).hasClass("selected")){
				var num = $(this).find(".gameCnt").text();
				for(i=0; i<num; i++){
					param += $(this).attr("id");
					param+=",";
				}
			}
		});
		
		$.post("/Marble/doSetGames", {games : param}, function(data){
			showWarning(data);
		});
		
	});
	
	if("${sessionScope._GAME_SETTING_ }" != ""){
		$.post("/Marble/getGameSetting", function(data){
			var plays = data.split(",");
			$("#games_list ul li").each(function(){
				var gameId = $(this).attr("id");
				for(i=0; i<plays.length; i++){
					if(gameId == plays[i]){
						var num = $(this).find(".gameCnt").text();
						$(this).find(".gameCnt").text(parseInt(num)+1);
						$(this).addClass("selected");
					}
				}
				
			});
		});
	}
	
});



		
</script>

</head>
<body>

	<div id = "gamesSet_Wrapper">
	<div>
		<select id="categoryId" name="categoryId">
			<option selected="selected">Category</option>
			<c:forEach items="${categories}" var="category">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>
	</div>
	
	<div id="games_set">
		<div id="games_list"
			style="display: inline-block; overflow-y: scroll; width: 100%; height: 500px; position: relative;">
			
			<div class="warning" style="display: none; position: absolute; top: 200px; margin: 0 auto; font-weight: bold; color: red; font-size: larger; width: 100%; text-align: center;">
				<p></p>
			</div>
			
			<form class="setGmaesForm" name="setGamesForm">
				<ul>
					<c:forEach items="${games}" var="game">
						<li id="${game.gameId}">
							<div class="inline gameName" style="color: black;">
								<p >${game.gameName}</p>
							</div>
							<div class="inline">
								<input type="button" class="info" value="?"
									style="background-color: white; border: 0;" />
								<input type="hidden" class="gameInfo" value="${game.gameInfo }">
							</div>
							<div class="inline">
								<input type="button" class="mBtn" value="-"/>
								<span class="gameCnt" style="color: black">0</span>
								<input type="button" class="pBtn" value="+"/>
							</div>
						</li>
					</c:forEach>
				</ul>
			</form>
			

		</div>
		<div>
			<input type="button" id="setBtn" value="Set" />
		</div>
		
	</div>
	
	</div>
</body>
</html>