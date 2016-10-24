<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
 -->
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

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

#ctgr_content a{
	text-decoration: none;
}

#ctgr_content ul, #ctgr_content li{
	list-style:none;
}

#start_tree{
	padding-left:20px;
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

#ctgr_content a{
	font-weight:normal;
	color:#428bca;
}
</style>

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$().ready(function(){
		//카테고리 아이디를 설정한다.
		$("#categoryId").val("ctgr0");
		
		//카테고리 이름을 클릭했을 때 이벤트		
		$("#ctgr_content li a").click(function(){
			//현재 태그의 부모(li)의 id를 가져온다.
			clickedText = $(this).parents().attr("id");
			
			//hidden input의 value에 id를 넣는다.
			$("#categoryId").val(clickedText);
			//hidden input의 value에 이름을 넣는다.
			$("#selected_info").val($(this).text());
			
			console.log("click한 데이터 : "+clickedText);			
 			console.log("form으로 전달할 데이터 : "+$("#selected_info").val());
			
 			//선택된 앵커 태그에 클래스를 추가한다. 			
			if (!$(this).hasClass("selected")){				
				//클릭한 앵커 태그만 클래스가 추가되어야 하기 때문에 나머지 모든 태그는 클래스를 제거한다.
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
							if(data!="false"){
								var categoryId = $("#categoryId").val();
								$("#"+categoryId).after("<ul><li id='ctgr"+data+"'><a href='javascript:addCategory("+data+");'>"+$("#ctgr_input").val()+"</a></li></ul>");
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
							if(data=="true"){
								var categoryId = $("#categoryId").val();
								$("#"+categoryId).children("a").text($("#ctgr_input").val());
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
								if(confirm("정말 삭제하시겠습니까?")){
									var categoryId = $("#categoryId").val();
									$("#"+categoryId).remove();
								}
							}
							else{
								alert("하위 파일이 있으면 삭제할 수 없습니다.");
							}
				});
			}else{
				alert("삭제할 파일을 선택해주세요.");				
			}
		});
		
		//전체 리스트에서 ul을 가지고 있는 li에 클래스를 추가한다
		//ul 왼쪽 대쉬 보더를 준다.
		$("#ctgr_content ul").each(function(){
			$(this).find("li").has("ul").addClass("hasSubmenu");
			$(this).closest("ul").css("border-left", "1px dashed #cccccc");
		});
		
		//li에 굵기를 준다.
		$("#ctgr_content ul li").each(function(){
			$(this).mouseenter(function(){
				$(this).children("a").css({"font-weight":"bold","color":"#336b9b"});
		  	});
			
			$(this).mouseleave(function(){
				$(this).children("a").css({"font-weight":"normal","color":"#428bca"});
		  	});
		});
		
		// Add button to expand and condense - Using FontAwesome
		$("#ctgr_content ul li.hasSubmenu").each(function(){
			$this = $(this);
			$this.prepend("<a href='#'><i class='fa fa-minus-circle'></i><i style='display:none;' class='fa fa-plus-circle'></i></a>");
			$this.children("a").not(":last").removeClass().addClass("toogle");
		});
		
		// Actions to expand and consense
		$("#ctgr_content ul li.hasSubmenu a.toogle").click(function(){
			$this = $(this);
			$this.closest("li").children("ul").toggle("slow");
		 	$this.children("i").toggle();
		 	return false;
		});
		
	});
	
	function addCategory(categoryId){
		
		var category = $("#ctgr"+categoryId);
		
		$("#categoryId").val("ctgr"+categoryId);
		$("#selected_info").val(category.text());
		
		console.log("click한 데이터 : "+clickedText);			
			console.log("form으로 전달할 데이터 : "+$("#selected_info").val());
		//선택된 태그에 클래스를 표시한다. 			
		if (!category.hasClass("selected")){				
			//$(this).closest("li").children("ul").slideDown();
			$("#ctgr_content a").removeClass("selected");
			category.children("a").addClass("selected");
		}	
		else {
			//$(this).closest("li").children("ul").slideUp();
			category.children("a").removeClass("selected");
		}
	}
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
	<input type="hidden" id="selected_info" name="selected_info">
	<input type="hidden" id="categoryId" name="categoryId">
	</form>
	<!-- 이전 레벨과 현재 레벨이 다를 경우 ul -->
	<div id="ctgr_content" >
	<ul id='start_tree'>
		<li id='ctgr0'><a href='javascript:void(0);'>ROOT</a>
		<c:set var='pr' value='0' />
		<c:forEach items='${categories }' var='category' >
 	
		<c:set var='nr' value='${category.level }' />
			<c:choose>
			<c:when test='${pr lt nr }'>
					<ul><li id='ctgr${category.categoryId }'><a href='javascript:void(0);'>${category.categoryName}</a>(${pr},${nr})</c:when>
			<c:when test='${pr gt nr }'>
					<c:forEach begin='1' end='${pr-nr }' step='1'>
					</li></ul>
					</c:forEach>
					<li id='ctgr${category.categoryId }'><a href='javascript:void(0);'>${category.categoryName}</a>(${pr},${nr})</c:when>
			<c:otherwise>
					</li>
					<li id='ctgr${category.categoryId }'><a href='javascript:void(0);'>${category.categoryName}</a>(${pr},${nr})</c:otherwise>
			</c:choose>
				<c:set var='pr' value='${nr}'/>
		</c:forEach>
		</li>
	</ul>
	</div>
	</div>
</body>
</html>