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
			var div = $("#cell"+positionIndex+".gameType").text();
			alert(div);
			if(div == "GOLD_KEY"){
				actionSpinGoldKey();
			}
		}

		function actionSpinGoldKey(){
			$("#rollCardGroup").css("display","block");
			$("#disabledEffect").css("display","block");
			var showTime = 0;
			var randomNum = 0;
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
<<<<<<< HEAD
				// pick 클래스로 카드를 하나 뽑는다.	
<<<<<<< HEAD
				$("#keyCard").removeClass("keyCard");
				$("#keyCard").addClass("pick" + randomNum);
				
				// 뒤집을 수 있는 이미지와 교체한다.
				$("#container").css("display","block");					
				$(".card"+randomNum).css("display","none");	
				
				//확대할 때 쓰던 계산식
				var degNum = (randomNum - 1)*60;
=======
				
				console.log("random_value : "+randomNum);
				// 동시에 그 카드를 안보이게 한다.
				$(".card"+randomNum).css("display","none");	
>>>>>>> 50ef3bf7e8263ab1b3ab97cbc0eed9247ae863ab
=======
				console.log("random_value : "+randomNum);
				// 동시에 그 카드를 안보이게 한다.
				$(".card"+randomNum).css("display","none");	
>>>>>>> 321
				
				// 동시에 뒤집는 카드를 보이게 한다.
				$("#selectedCard").css("display","block");					
				
				$("#selectedCard").dblclick(function(){
					$(".card"+randomNum).css("display","block");					
					$(this).css("display","none");	
					$("#rollCardGroup").css("display","none");
					$("#disabledEffect").css("display","none");
				});
				}, showTime);
		}
				// 카드 플립부분
		var check = 0;
		$("#selectedCard").click(function() {
			if (check == 0) {
				$("#flipper").addClass("clickFlip");
				check = 1;
			} else if (check == 1) {
				$("#flipper").removeClass("clickFlip");
				check = 0;
			}
		});
	});
</script>
<div id="marble">
		<div id="disabledEffect"></div>
	<div id="gamePan">
		<table border="1">
			<tr>
				<td id="cell18">
				<div class="gameName">${plays[17].games.gameName }</div>
				<div class="gameInfo">${plays[17].games.gameInfo }</div>
				<div class="gameType">${plays[17].games.typeId }</div>
				</td>
				<td id="cell17">
				<div class="gameName">${plays[16].games.gameName }</div>
				<div class="gameInfo">${plays[16].games.gameInfo }</div>
				<div class="gameType">${plays[16].games.typeId }</div>
				</td>
				<td id="cell16">
				<div class="gameName">${plays[15].games.gameName }</div>
				<div class="gameInfo">${plays[15].games.gameInfo }</div>
				<div class="gameType">${plays[15].games.typeId }</div>
				</td>
				<td id="cell15">
				<div class="gameName">${plays[14].games.gameName }</div>
				<div class="gameInfo">${plays[14].games.gameInfo }</div>
				<div class="gameType">${plays[14].games.typeId }</div>
				</td>
				<td id="cell14">
				<div class="gameName">${plays[13].games.gameName }</div>
				<div class="gameInfo">${plays[13].games.gameInfo }</div>
				<div class="gameType">${plays[13].games.typeId }</div>
				</td>
				<td id="cell13">
				<div class="gameName">${plays[12].games.gameName }</div>
				<div class="gameInfo">${plays[12].games.gameInfo }</div>
				<div class="gameType">${plays[12].games.typeId }</div>
				</td>
				<td id="cell12">
				<div class="gameName">${plays[11].games.gameName }</div>
				<div class="gameInfo">${plays[11].games.gameInfo }</div>
				<div class="gameType">${plays[11].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell19">
				<div class="gameName">${plays[18].games.gameName }</div>
				<div class="gameInfo">${plays[18].games.gameInfo }</div>
				<div class="gameType">${plays[18].games.typeId }</div>
				</td>
				<th id="goodPlace" colspan="5" rowspan="5"></th>
				<td id="cell11">
				<div class="gameName">${plays[17].games.gameName }</div>
				<div class="gameInfo">${plays[17].games.gameInfo }</div>
				<div class="gameType">${plays[17].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell20">
				<div class="gameName">${plays[19].games.gameName }</div>
				<div class="gameInfo">${plays[19].games.gameInfo }</div>
				<div class="gameType">${plays[19].games.typeId }</div>
				</td>
				<td id="cell10">
				<div class="gameName">${plays[9].games.gameName }</div>
				<div class="gameInfo">${plays[9].games.gameInfo }</div>
				<div class="gameType">${plays[9].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell21">
				<div class="gameName">${plays[20].games.gameName }</div>
				<div class="gameInfo">${plays[20].games.gameInfo }</div>
				<div class="gameType">${plays[20].games.typeId }</div>
				</td>
				<td id="cell9">
				<div class="gameName">${plays[8].games.gameName }</div>
				<div class="gameInfo">${plays[8].games.gameInfo }</div>
				<div class="gameType">${plays[8].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell22">
				<div class="gameName">${plays[21].games.gameName }</div>
				<div class="gameInfo">${plays[21].games.gameInfo }</div>
				<div class="gameType">${plays[21].games.typeId }</div>
				</td>
				<td id="cell8">
				<div class="gameName">${plays[7].games.gameName }</div>
				<div class="gameInfo">${plays[7].games.gameInfo }</div>
				<div class="gameType">${plays[7].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell23">
				<div class="gameName">${plays[22].games.gameName }</div>
				<div class="gameInfo">${plays[22].games.gameInfo }</div>
				<div class="gameType">${plays[22].games.typeId }</div>
				</td>
				<td id="cell7">
				<div class="gameName">${plays[6].games.gameName }</div>
				<div class="gameInfo">${plays[6].games.gameInfo }</div>
				<div class="gameType">${plays[6].games.typeId }</div>
				</td>
			</tr>
			<tr>
				<td id="cell0">
				<div class="gameName">${start.games.gameName }</div>
				<div class="gameInfo">${start.games.gameInfo }</div>
				<div class="gameType">${start.games.typeId }</div>
				</td>
				<td id="cell1">
				<div class="gameName">${plays[0].games.gameName }</div>
				<div class="gameInfo">${plays[0].games.gameInfo }</div>
				<div class="gameType">${plays[0].games.typeId }</div>
				</td>
				<td id="cell2">
				<div class="gameName">${plays[1].games.gameName }</div>
				<div class="gameInfo">${plays[1].games.gameInfo }</div>
				<div class="gameType">${plays[1].games.typeId }</div>
				</td>
				<td id="cell3">
				<div class="gameName">${plays[2].games.gameName }</div>
				<div class="gameInfo">${plays[2].games.gameInfo }</div>
				<div class="gameType">${plays[2].games.typeId }</div>
				</td>
				<td id="cell4">
				<div class="gameName">${plays[4].games.gameName }</div>
				<div class="gameInfo">${plays[4].games.gameInfo }</div>
				<div class="gameType">${plays[4].games.typeId }</div>
				</td>
				<td id="cell5">
				<div class="gameName">${plays[5].games.gameName }</div>
				<div class="gameInfo">${plays[5].games.gameInfo }</div>
				<div class="gameType">${plays[5].games.typeId }</div>
				</td>
				<td id="cell6">
				<div class="gameName">${plays[6].games.gameName }</div>
				<div class="gameInfo">${plays[6].games.gameInfo }</div>
				<div class="gameType">${plays[6].games.typeId }</div>
				</td>
			</tr>


		</table>

		<div class="object">PLAYER</div>

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
					<div class="back-title">황금열쇠</div>
					<p>고온다습 직사광선을 피해 보관하시기 바랍니다.</p>
				</div>
			</div>
		</div></div>
		<div id="viewInfo"></div>
		<div id="goldenCard"></div>
		<div id="drink"></div>
	</div>

</div>


