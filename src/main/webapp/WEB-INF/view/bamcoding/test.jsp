<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
 $().ready(function(){
	 
 setTimeout(function(){
	$("#a").addClass("zoomIt"); 
 },300)

 setTimeout(function(){
	$("#b").addClass("zoomIt"); 
 },600)
	 
 })
 

</script>

<style>

.zoomIt{
	animation: zome 1s infinite linear;
}

@keyframe zome{
	0%	{ transform:scale(1);}
	100% { transform:scale(1.5);}
}
</style>

<body>
<div id="a" style="font-size:2em;font-weight:bold;">
이근재 
</div>
<div id="b" style="font-size:2em;font-weight:bold;">
성정한
</div>
</body>
</html>