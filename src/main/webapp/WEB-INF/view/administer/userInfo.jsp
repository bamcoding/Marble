<!-- 

관리자 회원정보입니다.
관리자 회원정보입니다.
관리자 회원정보입니다.

-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="/Marble/css/decorateAdmin.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#deleteBtn").click(function(){
			if(confirm("delete하시겠습니까?")){
				
				$.post( "/Marble/doUserInfoDelete",$("#registForm").serialize(),function(data){
					if ( data == "true"){
						location.href=("/Marble/admin/userInfo");
					}
					else if( data == "false" ){
						alert("delete할 유저를 한명이상을 고르시오");
					}
				} );	
				
			}
				
		});
		
		$("#passwordResetBtn").click(function(){
			if(confirm("패스워드를 초기화 하시겠습니까?")){
				$.post("/Marble/admin/doPasswordReset",$("#registForm").serialize(),function(data){
					if ( data == "false"){
						alert("초기화할 유저를 1명 이상 골라주세요");
					}
					else if( data == "true"){
						alert("비밀번호가 초기화 되었습니다.");
					}
				});
			}
		});
	});
</script>

<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"></jsp:include>

	<h3>회원관리</h3>
	<div id="listDiv">
		<form id="registForm">
			<table id="listTable">
				<tr>
					<th>선택</th>
					<th>번호</th>
					<th>아이디</th>
					<th>닉네임</th>
					<th>포인트</th>
					<th>가입일</th>
				</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<c:set var="number" value="${fn:split(user.userId,'-')[2]}"/>
					<fmt:parseNumber var="number" type="number" value="${number}"/>
					<td><input type="checkbox" name="checks" value="${user.userId}"/></td>
					<td>${number}</td>
					<td>${user.userEmail}</td>
					<td>${user.userNickname}</td>
					<td>${user.points}</td>
					<td>${user.createdDate }</td>
				</tr>
			</c:forEach>
			</table>
			${paging}
		<div class="right">
			<select id="searchType" name="searchType">
				<option value="1" ${searchUser.searchType eq 1 ?'selected':''}>아이디+닉네임</option>
				<option value="2" ${searchUser.searchType eq 2 ?'selected':''}>아이디</option>
				<option value="3" ${searchUser.searchType eq 3 ?'selected':''}>닉네임</option>
			</select>
			<div class="inline"><input type="text" id="searchKeyword" name="searchKeyword"  value="${searchUser.searchKeyword}"/></div>				
			<div class="inline"><input type="button" id="searchBtn" value="검색" onclick="movePage(0)"/></div>
			<div class="inline"><input type="button" id="deleteBtn" value="삭제"/></div>
			<div class="inline"><input type="button" id="passwordResetBtn" value="비밀번호 초기화"/></div>
		</div>
		<div class="clear"></div>
	</form>
	</div>
</body>
</html>