<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style>


#ctgr_view{
	border:1px solid black;
	width:500px;
	margin:10px;
	border-radius: 10px;
}
#ctgr_title{
	height:50px;
	font-weight: bold;
	background:#eeeeee;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}
#ctgr_title .left{
	margin:0 20px 0 20px;
	line-height: 50px;
}
#ctgr_title .right, #selected_info{
	margin:13px 20px 13px 20px;
	height:50px;
}

#ctgr_content{
	margin:10px 0 10px 0;
}

#ctgr_title > *{
	display:inline-block;
}

#ctgr_title .right #ctgr_input{
	width: 100px;
}

.displayBlock{
	display : block;
}

.selected{
	background : #cccccc;
}

</style>

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$().ready(function(){
		var clickedText =null;
		$("#ctgr_content li a").click(function(){
			//인덱스는 기대할 수 없음.
			clickedText = $(this).parents().attr("id");
			$("#categoryId").val(clickedText);
			$("#selected_info").val($(this).text());
			
			console.log("click한 데이터 : "+clickedText);			
 			console.log("form으로 전달할 데이터 : "+$("#selected_info").val());
			//선택된 태그에 클래스를 표시한다. 			
			if (!$(this).hasClass("selected")){				
				$("#ctgr_content a").removeClass("selected");
				$(this).addClass("selected");
			}	
			else {
				$(this).removeClass("selected");
			}
		});
		
		$("#ctgr_title #addBtn").click(function(){
			if($("#ctgr_input").val() != ""){
				$.post( "/Marble/admin/doAddCtgr"
						,$("#categoryForm").serialize()
						,function(data){
							if(data=="true"){
								alert("카테고리를 추가하였습니다.");
								var categoryId = $("#categoryId").val();
								$("#"+categoryId).after("<ul><li><a href='#'>"+$("#ctgr_input").val()+"</a></li></ul>");
							}
							else{
								alert("중복되는 이름은 사용할 수 없습니다.");
							}
				});
			}else{
				alert("카테고리 이름을 입력해주세요.");				
			}
		});

		$("#ctgr_title #modifyBtn").click(function(){
			
			if($("#ctgr_input").val() != ""){
				$.post( "/Marble/admin/doModifyCtgr"
						,$("#categoryForm").serialize()
						,function(data){
							alert(data);
							if(data=="true"){
								alert(data);
								
								$("a .selected").text($("ctgr_input").val());
								console.log($("a .selected").text($("ctgr_input").val()));
							}
							else{
								alert("중복되는 이름은 사용할 수 없습니다.");
							}
						});
			}
			else if($("#selected_info").val()==""){
				alert("수정할 파일을 선택해주세요.");
			}
			else{
				alert("수정할 이름을 입력해주세요.");				
			}
		});
		
		$("#ctgr_title #deleteBtn").click(function(){
			if($("#selected_info").val() != ""){
				$.post( "/Marble/admin/doDeleteCtgr"
						,$("#categoryForm").serialize()
						,function(data){
							if(data=="true"){
								alert("삭제하였습니다.");
								var categoryId = $("#categoryId").val();
								$("#"+categoryId).remove();
							}
							else{
								alert("하위 파일이 있으면 삭제할 수 없습니다.");
							}
				});
			}else{
				alert("삭제할 파일을 선택해주세요.");				
			}
		});
	});
</script>

<body>
	<form id="categoryForm" name="categoryForm">
	
	<div id="ctgr_view">
	<div id="ctgr_title">
		<div class="left">카테고리 미리보기</div>
		<div class="right">
			<input type="text" id="ctgr_input" name="ctgr_input">
			<input type="button" id="addBtn" value="추가"/>
			<input type="button" id="modifyBtn" value="수정"/>
			<input type="button" id="deleteBtn" value="삭제"/>
		</div>
		<div class="clear"></div>
	</div>
	
	</div>
	<input type="hidden" id="selected_info" name="selected_info">
	<input type="hideen" id="categoryId" name="categoryId">
	</div>
	</form>
	<!-- 이전 레벨과 현재 레벨이 다를 경우 ul -->
	<div id="ctgr_content" >
	<ul>
		<li><a href="#">전체보기</a></li>
		
		<c:set var="pr" value="0" />
		<c:forEach items="${categories }" var="category" >
 	
		<c:set var="nr" value="${category.level }" />
			<c:choose>
				<c:when test="${pr lt nr }">
					<ul>
					<li id="ctgr${category.categoryId }"><a href="#">${category.categoryName}</a>(${pr},${nr})</li>
					<c:set var="pr" value="${nr}"/>
				</c:when>
				<c:when test="${pr gt nr }">
					
					<c:forEach begin="1" end="${pr-nr }" step="1">
						</ul>
					</c:forEach>
					
					<li id="ctgr${category.categoryId }"><a href="#">${category.categoryName}</a>(${pr},${nr})</li>
					<c:set var="pr" value="${nr}"/>
				</c:when>
				
				<c:otherwise>
					<li id="ctgr${category.categoryId }"><a href="#">${category.categoryName}</a>(${pr},${nr})</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	
	
</body>
</html>