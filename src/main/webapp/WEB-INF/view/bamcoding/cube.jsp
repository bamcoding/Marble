
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"  href="/Mables/bamcoding_css/cube.css"/>
<link rel="stylesheet" type="text/css"  href="/Mables/bamcoding_css/flip.css"/> 
<link rel="stylesheet" type="text/css"  href="/Mables/bamcoding_css/gamePan.css"/>

<script type="text/javascript" src="/Mables/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$().ready(function() {
	var move = "x+";
	var cellX = 9;
	var cellY = 6;
	var pointX = 0;
	var pointY = 0;
	var count = 0;
	$(".moveBtn").click(function() {
		
		var number =0;
		
		$.post("/Mables/doThrowCube" ,  function(data){
			number = parseInt(data);
			var showTime = 0;
			$(".moveBtn").hide(500);
			for(var i=0; i<number; i++){
				showTime = i*300;
				setTimeout(moveCube, showTime);
			}
			setTimeout(function(){
				$(".moveBtn").show(500);			
			}, showTime);
		});
		
		
	}); 
	
	
	function moveCube(){
		if (move == "x+") {
			pointX += 120;
			count += 1;
			if (count == cellX) {
				count = 0;
				move = "y-";
			}
			$(".object").css(
					"transform",
					"translateX(" + pointX
							+ "px) translateY(" + pointY
							+ "px)");
		} else if (move == "y-") {
			pointY -= 110;
			count += 1;
			$(".object").css(
					"transform",
					"translateX(" + pointX
							+ "px) translateY(" + pointY
							+ "px)");
			if (count == cellY) {
				count = 0;
				move = "x-";
			}

		} else if (move == "x-") {
			pointX -= 120;
			count += 1;
			$(".object").css(
					"transform",
					"translateX(" + pointX
							+ "px) translateY(" + pointY
							+ "px)");
			if (count == cellX) {
				count = 0;
				move = "y+";
			}
		} else if (move == "y+") {
			pointY += 110;
			count += 1;
			$(".object").css(
					"transform",
					"translateX(" + pointX
							+ "px) translateY(" + pointY
							+ "px)");
			if (count == cellY) {
				count = 0;
				move = "x+";
			}
		}
	}
	
	
	
	// 카드 플립부분
	var check = 0;
	$("#container").click(function(){
		if(check==0){
			$("#flipper").addClass("clickFlip");
			check =1;
		}
		else if(check==1){
			$("#flipper").removeClass("clickFlip");
			check =0;
		}
	});
	
	
	var preNum = 0;	
	//던지기 부분
	$(".cubeFrame").click(function(){
		
		$.post("/Mables/doThrowCube" ,  function(data){
		
		var showTime = 1000;
		var number = parseInt(data);
		$("#bling").addClass("bling");
		$("#cube").addClass("actionMove");
		
		setTimeout(function(){
			$("#cube").removeClass("actionMove");
			},showTime);
		
		console.log("전꺼:"+preNum +" 지금:"+number);
		$("#cube").removeClass("cube");
		if(preNum != 0){
			$("#cube").addClass("show"+number);
			$("#cube").	removeClass("show"+preNum+"");
		}else {
			$("#cube").addClass("show"+number);
		}
		preNum = number;
		
		
		for(var i=0; i<number; i++){
			showTime += 400;
			setTimeout(moveCube, showTime);
		}
		setTimeout(function(){
			$("#bling").removeClass("bling");
		}, showTime+700);
		
		});

			
			
			
			
			
			
	});
	
});


</script>
<!-- 게임 UI -->
	<div class="grimFrame">
		<img class="gamePan" src="/Mables/img/pan.png" />
	</div>
	<div class="moveBtn">MOVE</div>
	<div class="object">PLAYER</div>

<!-- 큐브 부분 -->
	<div class="cubeFrame">
		<div class="cube" id="cube">
			<div class="front side item">1</div>
			<div class="back side item">6</div>
			<div class="left side item">3</div>
			<div class="right side item">4</div>
			<div class="top side item">5</div>
			<div class="bottom side item">2</div>
		</div>
	</div>
<!-- 카드 뒤집기 부분 -->
<div id="container">
  <div id="flipper">
    <div class="flip_front">
      <span class="name">Post it</span>
    </div>
    <div class="flip_back">
      <div class="back-title">황금열쇠</div>
      <p> 고온다습 직사광선을 피해 보관하시기 바랍니다. </p>
    </div>
  </div>
</div>

<!-- 큐브 던지는 부분 -->
<div class="throwBtn">PUSH</div>

<div id="bling"></div>
