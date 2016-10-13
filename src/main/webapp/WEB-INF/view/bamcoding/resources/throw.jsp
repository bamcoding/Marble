<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Mables/js/jquery-3.1.1.js"></script>

</head>
<body>

<script type="text/javascript">
	$().ready(function(){
		$(".push").click(function(){
			$(this).mousedown(function(){
				$(".ball").addClass("actionMove");
			});	
			$(this).mouseleave(function(){
				$(".ball").removeClass("actionMove");
			});
		});
	});
</script>
<style>
.push {
  display: block;
  position: absolute;
  background: #ccc;
  width: 250px;
  height: 200px;
  line-height: 200px;
  font-size: 5em;
  text-align: center;
  color: #FFF;
  opacity: 0.9;
  border-radius: 10px;
  box-shadow: 5px 10px 0 #999;
  transition: box-shadow 0.1s, top 0.1s;
}

.push:active {
	left:10px;
	top: 15px;
	box-shadow: 0 1px 0 #999;
}
.ball {
	top:300px;
	left:0px;
  	display: block;
  	position: absolute;
  	background: #ccc;
  	width: 100px;
  	height: 100px;
  	line-height: 100px;
  	font-size: 2em;
  	text-align: center;
  	color: #FFF;
  	border-radius: 25px;
  	animation : spin 0.6s infinite linear;
}
@keyframes spin {
	from { transform: rotateZ(0); }
	to { transform: rotateZ(360deg); }
}

.actionMove{
    -webkit-animation-name: example; /* Chrome, Safari, Opera */
    -webkit-animation-duration: 4s; /* Chrome, Safari, Opera */
    animation-name: example;
    animation-duration: 4s;
}

/* Chrome, Safari, Opera */
@-webkit-keyframes example {
    0%   { left:0px; top:300px; transform: rotateZ(0);}
    5%   { left:50px; top:600px; transform: rotateZ(180deg);}
    20%  { left:150px; top:400px; transform: rotateZ(300deg);}    
    23%  { left:200px; top:600px; transform: rotateZ(360deg);}    
    38%  { left:250px; top:470px; transform: rotateZ(420deg);}    
    41%  { left:300px; top:600px; transform: rotateZ(480deg);}
    56%  { left:350px; top:530px; transform: rotateZ(540deg);}    
    59%  { left:400px; top:600px; transform: rotateZ(600deg);}
    100% { left:500px; top:600px; transform: rotateZ(660deg);}
}



/* Standard syntax */
@keyframes example {
    0%   { left:0px; top:300px; transform: rotateZ(0);}
    5%   { left:50px; top:600px; transform: rotateZ(180deg);}
    20%  { left:150px; top:400px; transform: rotateZ(300deg);}    
    23%  { left:200px; top:600px; transform: rotateZ(360deg);}    
    38%  { left:250px; top:470px; transform: rotateZ(420deg);}    
    41%  { left:300px; top:600px; transform: rotateZ(480deg);}
    56%  { left:350px; top:530px; transform: rotateZ(540deg);}    
    59%  { left:400px; top:600px; transform: rotateZ(600deg);}
    100% { left:500px; top:600px; transform: rotateZ(660deg);}
}
</style>

<div class="push">PUSH</div>


<div class="ball">CUBE</div>


</body>
</html>