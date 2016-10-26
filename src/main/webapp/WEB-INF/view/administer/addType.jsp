<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>
<link rel="stylesheet" type="text/css" href="/Marble/css/game.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		var errorCode = "${param.errorCode}";
		if(errorCode == "1") {
			alert("제목, 내용을 모두 입력해주세요");
			return;
		}		
		if(errorCode == "2") {
			alert("타입등록에 실패했습니다.");
			return;
		}
		if(errorCode == "3") {
			alert("타입 이름이 중복됩니다");
			return;
		}
		
		
		
		$("#goBackBtn").click(function() {
			location.href="/Marble/admin/typeList";
		});
		
		$("#writeBtn").click(function (){	

			if($("#typeName").val() == ""){
					alert("제목을 입력해주세요");
					return;
			}
			else if($("#typeInfo").val() == ""){
					alert("내용을 입력해주세요");
					return;
			}
			
			
			else{
				$.post("/Marble/IsExistTypeName", 
						{"typeName": $("#typeName").val()},
						function(data){
							if(data == "false") {
								if(confirm("타입을 등록 하시겠습니까?")) {
									$.post( "/Marble/admin/doAddType", $("#writeForm").serialize());
									alert("타입이 등록되었습니다.");
								 	location.href="/Marble/admin/typeList"; 
								}
								
							}
							else {
								alert("타입 이름이 중복됩니다.");
							}
				
			});	
				
			}
			
	});
	});
</script>
</head>
<body>
	<div class="gameList">
	
		<form id="writeForm" name="writeForm">
			<div>
				<input type="text" id="typeName" name="typeName" placeholder="타입 이름을 입력하세요." />
			</div>
			
			<div>
				<textarea id="typeInfo" name="typeInfo" placeholder="타입 설명을 입력하세요."></textarea>
			</div>
			<div style="margin-top:5px;">
				<div style="float: right;">
					<div class="inline">
						<input type="button" id="goBackBtn" value="뒤로가기" />
					</div>
					<div class="inline">
						<input type="button" id="writeBtn" value="글쓰기" />
					</div>
				</div>
			</div>
		</form>

	</div>
</body>
</html>