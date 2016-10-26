<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			if(confirm("\"${typeVO.typeName}.\"를 삭제하시겠습니까?")) {
				location.href="/Marble/admin/deleteTypeDetail?typeId=${typeVO.typeId}"
			}
		});	
		
		$("#updateBtn").click(function() {
			location.href="/Marble/admin/updateType?typeId=${typeVO.typeId}";
		});
		
		
		$("#goBackBtn").click(function() {
	
			location.href="/Marble/admin/typeList";
		});
		
		
		
	});
</script>
</head>
<body>
<h3>타입 상세</h3>
	<div id="listDiv">
	<table id="listTable">
		<tr>
			<th>ID</th>	
			<td>${typeVO.typeId}</td>
		</tr>
		<tr>
			<th>이름</th>	
			<td>${typeVO.typeName}</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>${typeVO.typeInfo}</td>
		</tr>
	</table>
	<div style="float: right;"><input type="submit" id="deleteBtn" value="삭제" onclick="movePage(0)"></div>
	<div style="float: right;"><input type="submit" id="updateBtn" value="수정" onclick="movePage(0)"></div>
	<div style="float: right;"><input type="submit" id="goBackBtn" value="목록보기" onclick="movePage(0)"></div>
	</div>
</body>
</html>