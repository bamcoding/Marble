<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>
<script type="text/javascript">
	$(document).ready(function(){
		$("#deleteBtn").click(function(){
					$.post( "/Marble/doUserInfoDelete",$("#registForm").serialize(),function(data){
						alert(data);
					} );	
		});
	});
</script>
	

<title>Insert title here</title>
</head>
<body>



		<div class="userList">
			<form id="registForm">
			   <table class="grid">
					<tr>
						<td>선택</td>
						<td>번호</td>
						<td>아이디</td>
						<td>닉네임</td>
						<td>가입일</td>
						<td>포인트</td>
					</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<c:set var="number" value="${fn:split(user.userId,'-')[2]}"/>
						<fmt:parseNumber var="number" type="number" value="${number}"/>
						<td><input type="checkbox" name="checks" value="${user.userId}"/></td>
						<td>${number}</td>
						<td>${user.userEmail}</td>
						<td>${user.userNickname}</td>
						<td>${user.createdDate }</td>
						<td>${user.points}</td>
					</tr>
				</c:forEach>
				</table>
				<div class="right">
					<select id="searchType" name="searchType">
							<option value="1" ${ searchArticle.searchType eq 1 ? 'selected' : '' }>아이디+닉네임</option>
							<option value="2" ${ searchArticle.searchType eq 2 ? 'selected' : '' }>아이디</option>
							<option value="3" ${ searchArticle.searchType eq 3 ? 'selected' : '' }>닉네임</option>
					</select>
					<div class="inline"><input type="text" id="searchKeyword" name="searchKeyword"/></div>				
					<div class="inline"><input type="submit" id="searchBtn" value="검색"/></div>
					<div class="inline"><input type="submit" id="deleteBtn" value="삭제"/></div>
					<div class="inline"><input type="submit" id="modifyBtn" value="수정"/></div>
					<div class="clear"></div>
				</div>
			</form>
		</div>
</body>
</html>