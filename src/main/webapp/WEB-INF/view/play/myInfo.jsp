<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
    	$(document).ready(function() {
    	});
    </script>

<div class="userInfo">

	<div class="userID" style="display: inline-block;">
		${sessionScope._USER_INFO_.userNickname }
	</div>
	<div class="userPoint" style="display: inline-block;">
		Point : ${sessionScope._USER_INFO_.points	 }
	</div>
</div>

<div class="history">
	
	<c:forEach var="his" items="${history }">
		<div id="${his.playInfo }">${his.playDate }</div>
	</c:forEach>
		
</div>