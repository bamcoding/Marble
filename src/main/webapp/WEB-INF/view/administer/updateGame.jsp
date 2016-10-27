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
			alert("카테고리, 제목, 내용을 모두 입력해주세요");
			return;
		}		
		if(errorCode == "2") {
			alert("게임등록에 실패했습니다.");
			return;
		}
		if(errorCode == "3") {
			alert("게임 이름이 중복됩니다");
			return;
		}
		
		
		
		$("#goBackBtn").click(function() {
			location.href="/Marble/admin/gameDetail?gameId=${gamesVO.gameId}";
		});
		
		$("#writeBtn").click(function (){	
			
			if($("#categoryId").val() == "카테고리를 선택해주세요"){
				alert("카테고리를 선택해주세요");
				return;
			}
			else if($("#gameName").val() == ""){
					alert("제목을 입력해주세요");
					return;
			}
			else if($("#gameInfo").val() == ""){
					alert("내용을 입력해주세요");
					return;
			}
			
			
			else{
				$.post("/Marble/IsExistGameName", 
						{"gameName": $("#gameName").val()},
						function(data){
							if(data == "false") {
								
								if(confirm("수정하시겠습니까?")) {
									$("#writeForm").attr( {
										"method": "post",
										"action": "/Marble/admin/doUpdateGame?gameId=${gamesVO.gameId}"
									}).submit();
									return;
								}
							
							}
							else if( $("#gameName").val() == "${gamesVO.gameName}" ) {
								
								if(confirm("수정하시겠습니까?")) {
									$("#writeForm").attr( {
										"method": "post",
										"action": "/Marble/admin/doUpdateGame?gameId=${gamesVO.gameId}"
									}).submit();
									return;
								}
							}
							else {
								alert("게임 이름이 중복됩니다.");
									
							}
				
			});	
				
			}
			
	});
	});
</script>
</head>
<body>
	<div id="listDiv">
		<form id="writeForm" name="writeForm" enctype="multipart/form-data">
		<input type="hidden" name="gameId" value="${gamesVO.gameId }"/>
			<div>
				<select class="categoryId" id="categoryId" name="categoryId" >
				<option selected="selected" value="${gamesVO.categoryId}">${gamesVO.categoryVO.categoryName}</option>
				<c:forEach items="${categories}" var="category">
				<option value="${category.categoryId}">${category.categoryName}</option>
				</c:forEach>
				</select>
			</div>
			<div>
				<input type="text" id="gameName" name="gameName" placeholder="게임 제목을 입력하세요." value="${gamesVO.gameName}" />
			</div>
			
			<div>
				<textarea id="gameInfo" name="gameInfo" placeholder="게임 설명을 입력하세요."  value="">${gamesVO.gameInfo}</textarea>
			</div>
			
			<c:if test="${not empty gamesVO.detailImage}">
			<div style="padding-top: 10px; padding-bottom: 10px;">
				<input type="checkbox" id="detailDeleteBtn" name="detailDeleteBtn" value="delete" />
				<img src="/Marble/admin/doDownloadDetailImg?gameName=${gamesVO.gameName}" style="width: 12px;" /> ${gamesVO.detailImage}
			</div>
			</c:if>
			
			<c:if test="${not empty gamesVO.cellImage}">
			<div style="padding-top: 10px; padding-bottom: 10px;">
				<input type="checkbox" id="cellDeleteBtn" name="cellDeleteBtn" value="delete" />
				<img src="/Marble/admin/doDownloadCellImg?gameName=${gamesVO.gameName}" style="width: 12px;" /> ${gamesVO.cellImage}
			</div>
			</c:if>
			
			<div style="margin-top:5px;">
				<div class="left">
					<div><input type="file" id="detailImage" name="detailImage" /></div>
					<div><input type="file" id="cellImage" name="cellImage" /></div>
				</div>
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