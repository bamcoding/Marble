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
</script>
<title>Insert title here</title>
</head>
<body>



		<div class="userList">
		   <table class="grid">
				<tr>
					<td>번호</td>
					<td>아이디</td>
					<td>작성자</td>
					<td>가입일</td>
					<td>포인트</td>
				</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<c:set var="number" value="${fn:split(user.userId,'-')[2]}"/>
					<fmt:parseNumber var="number" type="number" value="${number}"/>
					<td>${number}</td>
					<td>${user.userEmail}</td>
					<td>${user.userNickname}</td>
					<td></td>
					<td>${user.points}</td>
				</tr>
			</c:forEach>
			</table>
		</div>
</body>
</html>