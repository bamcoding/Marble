<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">


$(document).ready(function () {
	
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
	
	
	$(".mBtn").click(function(){
		var num = $(this).siblings(".gameCnt").text();
		if(num != "0"){
			$(this).parents().children(".gameCnt").text(parseInt(num)-1);
			
		}
	});
	
	$(".pBtn").click(function(){
		var num = $(this).siblings(".gameCnt").text();
		if(getCnt() < 19){
			$(this).siblings(".gameCnt").text(parseInt(num)+1);
		}else{
			var txt = "게임을 19개 이상 정할 수 없습니다.";
			showWarning(txt);
		}
	});
	
	function showWarning(txt){
		$(".warningText").text(txt).fadeIn(500).fadeOut(1200);
	}
	
	$(".info").click(function() {
		var info = $(this).parents().children(".gameInfo").val();
		var text = "</br><span style='font-size:2em;border-bottom:1px solid white;'>게임설명</span>"
		$("#infoBoard").html(text+"</br></br>"+info);
		$("#infoBoard").addClass("show");
		$("#infoBoard").fadeIn();
		
		//alert(info);
	});
	
	$("#infoBoard").click(function(){
		if($("#infoBoard").hasClass("show")){
			$("#infoBoard").fadeOut();
		}	
	});
	
	$("#setBtn").click(function() {
		
		if(getCnt() < 19){
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
	
	
	
});



		
</script>

	<div id = "gamesSet_Wrapper" class="row uniform">
	<div class="12u">
	<div class=" select-wrapper">
		<select id="categoryId" name="categoryId">
			<option selected="selected">Category</option>
			<c:forEach items="${categories}" var="category">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>
	</div>
	</div>
	
	<div id="games_set" class="12u">
		<div id="games_list"
			style="overflow-y: scroll; height: 500px; position: relative;">
			
			<div class="warningText" style="display: none; position: absolute; top: 200px; margin: 0 auto; font-weight: bold; color: red; font-size: larger; width: 100%; text-align: center;">
				<p></p>
			</div>
			
			<form class="setGmaesForm" name="setGamesForm">
				<ul class="alt">
					<c:forEach items="${games}" var="game">
						<li id="${game.gameId}">
							<div class="row uniform">
							<div class="gameName 7u" style="color: black;">
								<p >${game.gameName}</p>
							</div>
							<div class="2u">
								<input type="button" class="info button small" value="?"
									style="background-color: ; border: 0;" />
								<input type="hidden" class="gameInfo" value="${game.gameInfo }">
							</div>
							<div class="3u">
								<input type="button" class="mBtn button small" value="-"/>
								<span class="gameCnt" style="color: black; padding: 20px">0</span>
								<input type="button" class="pBtn button small" value="+"/>
							</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</form>
			

		</div>
		<div>
			<input type="button" id="setBtn" value="Set" class="button special big fit"/>
		</div>
		
	</div>
	
	</div>
