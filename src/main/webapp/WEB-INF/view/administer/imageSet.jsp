<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$().ready(function(){
	$("#sendBtn").click(function(){
		$("#imageForm").attr({
			"method":"post",
			"action":"./doImageUpLoad"
		}).submit();
	});
})
</script>
<body>
이미지 리스트 입니다.
<form id="imageForm" name="imageForm" enctype="multipart/form-data" >
<div><input type="text" id="imageTitle" name="imageTitle"/></div>
<div><textarea id="description" name="description"></textarea></div>
<div><input type="file" id="imgFile" name="imgFile" /></div>
<div><input type="button" id="sendBtn" name="sendBtn" value="보내기"/></div>
</form>
</body>
</html>