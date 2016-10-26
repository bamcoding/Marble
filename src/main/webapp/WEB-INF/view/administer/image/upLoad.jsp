<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"></jsp:include>

<h3>이미지를 올리는 중입니다.</h3>
<div id="listDiv">
<form id="imageForm" name="imageForm" enctype="multipart/form-data" >
<div><input type="text" id="imageTitle" name="imageTitle"/></div>
<div><textarea id="description" name="description"></textarea></div>
<div><input type="file" id="imgFile" name="imgFile" /></div>
<div><input type="button" id="sendBtn" name="sendBtn" value="보내기"/></div>
</form>
</div>
</body>
</html>