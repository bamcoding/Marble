<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
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
			location.href="/Marble/admin/typeDetail?typeId=${typeVO.typeId}";
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
								
								if(confirm("수정하시겠습니까?")) {
									$.post( "/Marble/admin/doUpdateType", $("#writeForm").serialize());
									alert("타입이 수정되었습니다.");
								 	location.href="/Marble/admin/typeList"; 
								}
							
							}
							else if( $("#typeName").val() == "${typeVO.typeName}" ) {
								
								if(confirm("수정하시겠습니까?")) {
									$.post( "/Marble/admin/doUpdateType", $("#writeForm").serialize());
									alert("타입이 수정되었습니다.");
									location.href="/Marble/admin/typeList";
								}
							}
							else {
								alert("타입이름이 중복됩니다.");
									
							}
				
			});	
				
			}
			
	});
	});
</script>
</head>
<body>
	<div id="listDiv">
		<form id="writeForm" name="writeForm">
		<input type="hidden" name="typeId" value="${typeVO.typeId }"/>

			<div>
				<input type="text" id="typeName" name="typeName" placeholder="타입 제목을 입력하세요." value="${typeVO.typeName}" />
			</div>
			
			<div>
				<textarea id="typeInfo" name="typeInfo" placeholder="타입 설명을 입력하세요."  value="">${typeVO.typeInfo}</textarea>
			</div>
			<div style="margin-top:5px;">
				<div style="float: right;">
					<div class="inline">
						<input type="button" id="goBackBtn" value="뒤로가기" />
					</div>
					<div class="inline">
						<input type="button" id="writeBtn" value="수정하기" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>