<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/grid.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="userList">
		<form id="registForm">
		   <table class="grid">
				<tr>
					<td><input type="checkbox" name="checks" value="${categories}"/></td>
					<td>번호</td>
					<td>이름</td>
					<td>부모</td>
				</tr>
			<c:forEach items="${categories}" var="category">
				<tr>
					<td><input type="checkbox" name="checks" value="${category.categoryId}"/></td>
					<td>${category.categoryId}</td>
					<td>${category.categoryName}</td>
					<td>${category.parentCategoryId}</td>
				</tr>
			</c:forEach>
			
			</table>
			<div class="right">
				<div class="inline"><input type="submit" id="searchBtn" value="검색"/></div>
				<div class="inline"><input type="submit" id="deleteBtn" value="삭제"/></div>
				<div class="inline"><input type="submit" id="modifyBtn" value="수정"/></div>
				<div class="clear"></div>
			</div>
		</form>
	</div>
</body>
</html>