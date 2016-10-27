<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/Marble/css/marble.css" />
<link rel="stylesheet" type="text/css"	href="/Marble/bamcoding_css/cube.css" />
<link rel="stylesheet" type="text/css"	href="/Marble/bamcoding_css/flip.css" />
<link rel="stylesheet" type="text/css"	href="/Marble/bamcoding_css/gamePan.css" />
<link rel="stylesheet" type="text/css"	href="/Marble/bamcoding_css/carousel.css" />

<script type="text/javascript">
	$(document).ready( function() {
		//상수
		var move = "x+";
		//게임판 셀의 수
		var cellX = 6;
		var cellY = 6;
		//한번에 이동하는 거리
		var pxX = 146;
		var pxY = 102;
		//현재까지 이동한 거리
		var pointX = 0;
		var pointY = 0;
		//
		var count = 0;

		function moveUnit() {
			if (move == "x+") {
				pointX += pxX;
				count += 1;
				if (count == cellX) {
					count = 0;
					move = "y-";
				}
				$(".object").css("transform","translateX(" + pointX + 
							"px) translateY("+ pointY + "px)");
			} else if (move == "y-") {
				pointY -= pxY;
				count += 1;
				$(".object").css("transform","translateX(" + pointX + 
							"px) translateY(" + pointY + "px)");
				if (count == cellY) {
					count = 0;
					move = "x-";
				}
			} else if (move == "x-") {
				pointX -= pxX;
				count += 1;
				$(".object").css("transform","translateX(" + pointX +
							"px) translateY("+ pointY + "px)");
				if (count == cellX) {
					count = 0;
					move = "y+";
				}
			} else if (move == "y+") {
				pointY += pxY;
				count += 1;
				$(".object").css("transform","translateX(" + pointX + 
							"px) translateY("+ pointY + "px)");
				if (count == cellY) {
					count = 0;
					move = "x+";
				}
			}
		}
		//던지기 부분
		$(".cubeFrame").click(function() {
			var showTime = 1000;
			var randomNum = parseInt(Math.random()*6) + 1;
			
			//큐브 애니메이션을 실행한다.
			$("#cube").addClass("throwAction");

			//큐브가 떨어지면 실행
			setTimeout(function() {
				$("#cube").removeClass("throwAction");
				$("#cube").removeClass("cube");
				$("#cube").addClass("show" + randomNum);
				$("#blingEffect").addClass("blingEffect");
			}, showTime);
			
			for (var i = 0; i < randomNum; i++) {
				showTime += 400;
				setTimeout(moveUnit, showTime);
			}
			showTime += 400
			setTimeout(function() {
				//블링이 끝나면 모든 기능을 제거
				$("#cube").addClass("cube");
				$("#cube").removeClass("show" + randomNum);
				$("#blingEffect").removeClass("blingEffect");
			}, showTime);	
			
			setTimeout(function() {
				getPenalty();
			}, showTime);
		});
				
		function getPenalty(){
			var x = pointX/pxX;
			var y = pointY/pxY;
			var positionIndex = 0;
			if(y == 0){
				positionIndex = x;
			}else if(x == 6){
				positionIndex = x - y;
			}else if(y == -6){
				positionIndex = cellX + (cellX- x) - y;
			}else if(x == 0){
				positionIndex = (2 * cellX) + cellY + (cellY + y);					
			}
				
			//벌칙들
			var cellDiv = $("#cell"+positionIndex);
			if(cellDiv.hasClass("golden-card")){
				$.post("/Marble/getCard", function(data){
					$(".flip_back").html(data);
					actionSpinGoldKey();
				});	
			}else if(cellDiv.hasClass("island")){
				$("#gameInfoBox").html("한번 쉬어가세요~");
				$("#gameInfoBox").show();
			}else if(false){
				
			}else{
				var gameId = cellDiv.children(".gameId").text();
				if(gameId != ""){
					
					$.post("/Marble/checkImg", {"type":"detail", "gameId" : gameId}, function(data){
						
						var str = "";
						
						if(data == "true"){
							$("#gameInfoBox").html("<img src='/Marble/admin/doDownloadDetailImg?gameId="+gameId+"' style='width:100%;height:100%;'/>");
							
						}else{
							var str= "<h2>"+cellDiv.children(".gameName").text()+"</h2>";
							str += "<div>" + cellDiv.children(".gameInfo").text()+"</div>";
							$("#gameInfoBox").html(str);
						}
						
						$("#gameInfoBox").show();
					});
				}
				
				
				
			}
		}
		
		$("#gameInfoBox").click(function(){
			$("#gameInfoBox").html("");
			$("#gameInfoBox").hide();
		});

		var randomNum = 0;
		function actionSpinGoldKey(){
			$("#rollCardGroup").css("display","block");
			$("#disabledEffect").css("display","block");
			var showTime = 0;
			var extraDeg =0;
			randomNum = parseInt(Math.random()*6)+1;
				
			// 카드 돌리기 애니메이션 시작
			$("#cardGroup").addClass("actionSpinGoldKey");
			showTime += 1500;
				
			//애니메이션이 끝날 때까지 기다리다가 카드를 랜덤 하게 보여주는 부분
			setTimeout(function(){
				$("#cardGroup").removeClass("actionSpinGoldKey");
				$("#cardGroup").css({
					"transform":"rotateY(-"+((randomNum-1)*60)+"deg)"
				});
				// pick 클래스로 카드를 하나 뽑는다.	
				$("#keyCard").removeClass("keyCard");
				$("#keyCard").addClass("pick" + randomNum);
				
				// 뒤집을 수 있는 이미지와 교체한다.
				$("#selectedCard").css("display","block");					
				$(".card"+randomNum).css("display","none");	
				
			}, showTime);
		}			
		
		// 카드 플립부분
		$("#selectedCard").click(function() {
				
			if($("#flipper").hasClass("clickFlip")){
				$("#flipper").removeClass("clickFlip");
				
				$(".card"+randomNum).css("display","block");					
				$("#selectedCard").css("display","none");	
				$("#rollCardGroup").css("display","none");
				$("#disabledEffect").css("display","none");
			}else{
				$("#flipper").addClass("clickFlip");						
			}
		});
		
		
		$("#gamePan td").each(function(){

			var gameId = $(this).children(".gameId").text();
			var cellId = $(this).attr("id");
			if(gameId != ""){
				
				$.post("/Marble/checkImg", {"type":"cell", "gameId" : gameId}, function(data){
					if(data == "true"){
						$("#"+cellId).children(".gameName").html("<img src='/Marble/admin/doDownloadCellImg?gameId="+gameId+"' style='height:100%;width:100%;'/>");
					}
				});
			}
			
		});
	});
</script>
<div id="marble">
		<div id="disabledEffect"></div>
	<div id="gamePan">
		<table>
			<tr>
				<td id="cell18">
				<div class="gameId">${plays[15].games.gameId }</div>
				<div class="gameName">${plays[15].games.gameName }</div>
				<div class="gameInfo">${plays[15].games.gameInfo }</div>
				<div class="gameType">${plays[15].games.typeId }</div>
				</td>
				<td id="cell17">
				<div class="gameId">${plays[14].games.gameId }</div>
				<div class="gameName">${plays[14].games.gameName }</div>
				<div class="gameInfo">${plays[14].games.gameInfo }</div>
				<div class="gameType">${plays[14].games.typeId }</div>
				</td>
				<td id="cell16">
				<div class="gameId">${plays[13].games.gameId }</div>
				<div class="gameName">${plays[13].games.gameName }</div>
				<div class="gameInfo">${plays[13].games.gameInfo }</div>
				<div class="gameType">${plays[13].games.typeId }</div>
				</td>
				<td id="cell15" class="golden-card">
				<div class="gameName"><img src="/Marble/img/usedImage/황금열쇠.jpg" style="height:100%;width:100%;"/></div>
				<div class="gameInfo"></div>
				<div class="gameType">5</div>
				</td>
				<td id="cell14">
				<div class="gameId">${plays[12].games.gameId }</div>
				<div class="gameName">${plays[12].games.gameName }</div>
				<div class="gameInfo">${plays[12].games.gameInfo }</div>
				<div class="gameType">${plays[12].games.typeId }</div>
				</td>
				<td id="cell13">
				<div class="gameId">${plays[11].games.gameId }</div>
				<div class="gameName">${plays[11].games.gameName }</div>
				<div class="gameInfo">${plays[11].games.gameInfo }</div>
				<div class="gameType">${plays[11].games.typeId }</div>
				</td>
				<td id="cell12">
				<div class="gameId">${plays[10].games.gameId }</div>
				<div class="gameName">${plays[10].games.gameName }</div>
				<div class="gameInfo">${plays[10].games.gameInfo }</div>
				<div class="gameType">${plays[10].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell19">
				<div class="gameId">${plays[16].games.gameId }</div>
				<div class="gameName">${plays[16].games.gameName }</div>
				<div class="gameInfo">${plays[16].games.gameInfo }</div>
				<div class="gameType">${plays[16].games.typeId }</div>
				</td>
				<th id="goodPlace" colspan="5" rowspan="5"></th>
				<td id="cell11">
				<div class="gameId">${plays[9].games.gameId }</div>
				<div class="gameName">${plays[9].games.gameName }</div>
				<div class="gameInfo">${plays[9].games.gameInfo }</div>
				<div class="gameType">${plays[9].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell20">
				<div class="gameId">${plays[17].games.gameId }</div>
				<div class="gameName">${plays[17].games.gameName }</div>
				<div class="gameInfo">${plays[17].games.gameInfo }</div>
				<div class="gameType">${plays[17].games.typeId }</div>
				</td>
				<td id="cell10">
				<div class="gameId">${plays[8].games.gameId }</div>
				<div class="gameName">${plays[8].games.gameName }</div>
				<div class="gameInfo">${plays[8].games.gameInfo }</div>
				<div class="gameType">${plays[8].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell21" class="golden-card">
				<div class="gameName"><img src="/Marble/img/usedImage/황금열쇠.jpg" style="height:100%;width:100%;"/></div>
				<div class="gameInfo"></div>
				<div class="gameType">5</div>
				</td>
				<td id="cell9" class="golden-card">
				<div class="gameName"><img src="/Marble/img/usedImage/황금열쇠.jpg" style="height:100%;width:100%;"/></div>
				<div class="gameInfo"></div>
				<div class="gameType">5</div>
				</td>
			</tr>
			<tr>
				<td id="cell22">
				<div class="gameId">${plays[18].games.gameId }</div>
				<div class="gameName">${plays[18].games.gameName }</div>
				<div class="gameInfo">${plays[18].games.gameInfo }</div>
				<div class="gameType">${plays[18].games.typeId }</div>
				</td>
				<td id="cell8">
				<div class="gameId">${plays[7].games.gameId }</div>
				<div class="gameName">${plays[7].games.gameName }</div>
				<div class="gameInfo">${plays[7].games.gameInfo }</div>
				<div class="gameType">${plays[7].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell23">
				<div class="gameId">${plays[19].games.gameId }</div>
				<div class="gameName">${plays[19].games.gameName }</div>
				<div class="gameInfo">${plays[19].games.gameInfo }</div>
				<div class="gameType">${plays[19].games.typeId }</div>
				</td>
				<td id="cell7">
				<div class="gameId">${plays[16].games.gameId }</div>
				<div class="gameName">${plays[6].games.gameName }</div>
				<div class="gameInfo">${plays[6].games.gameInfo }</div>
				<div class="gameType">${plays[6].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell0" class="start">
				<div class="gameName"><img src="/Marble/img/usedImage/Start.jpg" style="height:100%;width:100%;"/></div>
				<div class="gameInfo">벌주 면제권 획득!!</div>
				<div class="gameType">${start.games.typeId }</div>
				</td>
				<td id="cell1">
				<div class="gameId">${plays[0].games.gameId }</div>
				<div class="gameName">${plays[0].games.gameName }</div>
				<div class="gameInfo">${plays[0].games.gameInfo }</div>
				<div class="gameType">${plays[0].games.typeId }</div>
				</td>
				<td id="cell2">
				<div class="gameId">${plays[1].games.gameId }</div>
				<div class="gameName">${plays[1].games.gameName }</div>
				<div class="gameInfo">${plays[1].games.gameInfo }</div>
				<div class="gameType">${plays[1].games.typeId }</div>
				</td>
				<td id="cell3">
				<div class="gameId">${plays[2].games.gameId }</div>
				<div class="gameName">${plays[2].games.gameName }</div>
				<div class="gameInfo">${plays[2].games.gameInfo }</div>
				<div class="gameType">${plays[2].games.typeId }</div>
				</td>
				<td id="cell4">
				<div class="gameId">${plays[4].games.gameId }</div>
				<div class="gameName">${plays[4].games.gameName }</div>
				<div class="gameInfo">${plays[4].games.gameInfo }</div>
				<div class="gameType">${plays[4].games.typeId }</div>
				</td>
				<td id="cell5">
				<div class="gameId">${plays[5].games.gameId }</div>
				<div class="gameName">${plays[5].games.gameName }</div>
				<div class="gameInfo">${plays[5].games.gameInfo }</div>
				<div class="gameType">${plays[5].games.typeId }</div>
				</td>
				<td id="cell6" class="island">
				<div class="gameName"><img src="/Marble/img/usedImage/무인도.jpg" style="height:100%;width:100%;"/></div>
				<div class="gameInfo">한번 쉬어가세요~</div>
				<div class="gameType">6</div>
				</td>
			</tr>
		</table>

		<div class="object"><img src="/Marble/img/usedImage/horse.png" style="height:100%;width:100%;"/></div>

		<!-- 큐브 부분 -->
		<div class="cubeFrame">
			<div class="cube" id="cube">
				<div class="front side"></div>
				<div class="back side"></div>
				<div class="left side"></div>
				<div class="right side"></div>
				<div class="top side"></div>
				<div class="bottom side"></div>
			</div>
		</div>
		<div id="blingEffect"></div>
		<div id="disabledEffect"></div>
		
		<div id="gameInfoBox"></div>
		
		<!-- 황금 열쇠 ( 1.연출 부분 ) -->
		<div id="keyWrapper" style="top:35%;left:40%;position:absolute;">
		<div id="rollCardGroup">
		  <div id="cardGroup">
		    <div class="card1 item"><img src="/Marble/img/key.png"/></div>
		    <div class="card2 item"><img src="/Marble/img/key.png"/></div>
		    <div class="card3 item"><img src="/Marble/img/key.png"/></div>
		    <div class="card4 item"><img src="/Marble/img/key.png"/></div>
		    <div class="card5 item"><img src="/Marble/img/key.png"/></div>
		    <div class="card6 item"><img src="/Marble/img/key.png"/></div>
		  </div>
		</div>
		<!--  황금 열쇠 ( 2.카드 뒤집기 부분 ) -->
		<div id="selectedCard">
			<div id="flipper">
				<div class="flip_front">
					<img src="/Marble/img/key.png"/>
					<span class="name">Post it</span>
				</div>
				<div class="flip_back">
				</div>
			</div>
		</div></div>
		<div id="viewInfo"></div>
		<div id="goldenCard"></div>
		<div id="drink"></div>
	</div>

</div>


