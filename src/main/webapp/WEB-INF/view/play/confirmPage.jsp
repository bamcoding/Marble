<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	$(document).ready(function(){
		
	
		if("${sessionScope._GAME_SETTING_}" == "" && "${param.playInfo}" == ""){
			//$(".confirm").css("height","38%");
		}
		
		var i = 0;
		$(".confirmGames li").each(function(){
			var gameId = $(this).attr("class");
			var gameName = $(this).text();
			
			var length = $("#setGames").children(".row").length;
			
			if(length == 0){
				$("#setGames").append("<div class='row uniform'><div id='"+gameId+"' class='8u'>"+gameName+"</div><div class='gameCnt 4u'>1</div></div>");
			}else if(length > 0){
				var flag = true;
				for(var i=0; i<length; i++){
					
					if($("#"+gameId).text() == gameName){
						var cnt = $("#"+gameId).siblings(".gameCnt");
						var num = cnt.text();
						cnt.text(parseInt(num)+1);
						flag = false;
						break;
					}
				}
				
				if(flag){
					$("#setGames").append("<div class='row uniform'><div id='"+gameId+"' class='8u'>"+gameName+"</div><div class='gameCnt 4u'>1</div></div>");
				}
			}
			
			
			
		});
		
		$(".yesBtn").click(function(){
			$("#marbleBoard").load("/Marble/setMarbleBoard?random=false&playInfo=${param.playInfo}");
		});
		
		$(".randomBtn").click(function(){
			$("#marbleBoard").load("/Marble/setMarbleBoard?random=true&playInfo=${param.playInfo}");
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
		width: 60%; 
		top: 15%; 
		left: 20%; 
		text-align: center; 
		background-color: #FFFFFF;
		
		z-index: 100;
		border-radius: 14px;
		
		border:3px solid #4acaa8;
		
		
	}
	.comfirmWrapper{
		height: 100%; 
		width: 100%; 
		position:relative;
		padding: 2em;
	}
	
	.comfirmWrapper p{
		font-weight: bold;
		font-size: larger;
	}
	
	.buttons {
		bottom: 10px;
		position: absolute;
	}
	
	#setGames{
		overflow-y: auto;
		margin: 1em;
		padding-top: 2em;
		padding-left: 1em;
		max-height: 18em;
	
	}
	
	#setGames .row div{
		border:2px solid #4acaa8;
		font-weight: bold;
		font-size: larger;
		padding-top: 1em;
		padding-bottom: 1em;
	}
	
</style>

<div class="confirm">

	<div class="comfirmWrapper">
	<h3>������ Ȯ���ϼ���.</h3>
	<hr/>
	<c:choose>
		<c:when test="${empty plays }">
    	<p>������ ������ �����ϴ�. <br />
    	�������� ������ �����Ͻðڽ��ϱ�?
    	</p>
    	<hr/>
    		<div class="row uniform">
				<div class="6u">
					<input type="button" value="����" class="randomBtn" />
				</div>
				
				<div class="6u">
					<input type="button" value="�ݱ�" class="noBtn" />
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div>
			<ul class="confirmGames" style="display: none">
			<c:forEach var="play" items="${plays }" varStatus="i">
				<li class="${play.games.gameId }">${play.games.gameName }
				</li>
			</c:forEach>
			</ul>
			</div>
			
			<div id="setGames" >
			
			</div>
			<hr/>
				<div class="row uniform">
				<div class="4u">
					<input type="button" value="����" class="yesBtn" />
				</div>
				<div class="4u">
					<input type="button" value="�������� ���� ����" class="randomBtn" />
				</div>
				
				<div class="4u">
					<input type="button" value="�ݱ�" class="noBtn" />
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>