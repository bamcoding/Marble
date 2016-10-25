<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	$(document).ready(function(){
		
		var gameName = "";
		var i = 0;
		$(".confirmGames li").each(function(){
			var gameId = $(this).attr("class");
			if(i == 0){
			$("."+gameId).each(function(index){
				gameName = $(this).text();
				i = index;
			});
			
			$(this).text(gameName + " * " + (i+1));
			
			}else{
				$(this).remove();
				i--;
			}			
		});
		
		
		$(".yesBtn").click(function(){
			$("#marbleBoard").load("/Marble/setMarbleBoard?random=false");
		});
		
		$(".randomBtn").click(function(){
			$("#marbleBoard").load("/Marble/setMarbleBoard?random=true");
		});
		
		$(".noBtn").click(function(){
			$(".confirm").remove();
		});
	});	

</script>

<div class="confirm"
	style="position: fixed; height: 60%; width: 60%; top: 20%; left: 20%; text-align: center; background: yellow; z-index: 100">


	<c:choose>
		<c:when test="${empty sessionScope._GAME_SETTING_ }">
    	설정된 셋팅이 없습니다. <br />
    	랜덤으로 게임을 시작하시겠습니까?
    	<div class="buttons">
    		<div style="float: left;">
				<div style="display: inline-block;">
					<input type="button" value="예" class="randomBtn" />
				</div>
				</div>
				<div style="float: right;">
				<div style="display: inline-block;">
					<input type="button" value="아니오" class="noBtn" />
				</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div>
			<ul class="confirmGames">
			<c:forEach var="play" items="${sessionScope._GAME_SETTING_ }" varStatus="i">
				<li class="${play.games.gameId }">${play.games.gameName }
				</li>
			</c:forEach>
			</ul>
			</div>
			
			<div class="buttons">
				<div style="float: left;">
				<div style="display: inline-block;">
					<input type="button" value="예" class="yesBtn" />
				</div>
				<div style="display: inline-block;">
					<input type="button" value="랜덤으로 게임 시작" class="randomBtn" />
				</div>
				</div>
				<div style="display: inline-block; float: right;">
					<input type="button" value="아니오" class="noBtn" />
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>