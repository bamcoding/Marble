<!--

관리자 게시판입니다. 
관리자 게시판입니다. 
관리자 게시판입니다. 
  
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/Marble/css/decorateAdmin.css" />
<link rel="stylesheet" type="text/css" href="/Marble/css/pagination.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>

<script type="text/javascript">
$().ready(function(){
	$("div .divTd").click(function(){
		if($(this).text()=="게시판"){
		 	location.href="/Marble/board/list?categoryId=12"
		}
		if($(this).text()=="문의사항"){
		 	location.href="/Marble/board/list?categoryId=13"
		}
	});
	
})
</script>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"></jsp:include>

<h3>게시판관리</h3>
<div class="divTable">
<div class="divTr tableHead">
	<div class="inline divTd">선택</div>
	<div class="inline divTd">그룹</div>
	<div class="inline divTd">TABLE</div>
</div>
<div class="divTr">
	<div class="inline divTd"><input type="checkbox"/></div>
	<div class="inline divTd">커뮤니티</div>
	<div class="inline divTd"><a href="javascript:void(0);">게시판</a></div>
</div>
<div class="divTr">
	<div class="inline divTd"><input type="checkbox"/></div>
	<div class="inline divTd">커뮤니티</div>
	<div class="inline divTd"><a href="javascript:void(0);">문의사항</a></div>
</div>
</div>
</body>
