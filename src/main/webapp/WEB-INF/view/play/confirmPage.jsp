<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	$(document).ready(function(){
		
		var flag = true;
		var cntGame = 0;
		var gameName = "";
		var i = 0;
		$(".confirmGames li").each(function(){
			cntGame++;
			
			var result ="";
			var idx = $(this).index();
			gameName = $(this).text();
			//alert(idx + " " + gameName);
			var gameId = $(this).attr("class");
			if(i == 0){
			$("."+gameId).each(function(index){
				gameName = $(this).text();
				i = index;
			});
			
			if(flag){
				result += "";
			}
			
			result += "<div class='row uniform'><div class='3u'>"+gameName + "</div><div class='3u'> X " + (i+1)+"</div></div>";
			
			if(!flag){
				result += "";
			}
			
			$(this).html(result);
			flag = !flag;
			
			}else{
				$(this).remove();
				i--;
			}			
			
			
		});
		
		$(".cntGame").html(cntGame);
		
		
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

<style>
	.confirmGames{
		list-style:none;
	}
	.confirm{
		position: fixed; 
		height: 80%; 
		width: 60%; 
		top: 10%; 
		left: 20%; 
		text-align: center; 
		background-color: #FFFFFF;
		
		z-index: 100;
		border-radius: 14px;
		
	}
	.comfirmWrapper{
		height: 100%; 
		width: 100%; 
		position:relative;
		border:2px solid #00FF00;
	}
	
	.buttons {
		bottom: 10px;
		position: absolute;
	}
	
</style>

<div class="confirm">

	<div class="comfirmWrapper">
	<div class="cntGame">235353</div>
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
			
			<div class="buttons 12u">
				<div class="row uniform">
				<div class="4u">
					<input type="button" value="예" class="yesBtn" />
				</div>
				<div class="4u">
					<input type="button" value="랜덤으로 게임 시작" class="randomBtn" />
				</div>
				
				<div class="4u">
					<input type="button" value="아니오" class="noBtn" />
				</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>