<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

</script>
<div class="userInfo">

		<div class="12u">
		<input type="button" id="signUpBtn" class="button fit small" value="LOGOUT" onclick="window.location.href='/Marble/doLogout'"/>
		
		</div>

		<div class="row uniform" style="margin-bottom: 30px">
			<div class="2u">
			<img src="/Marble/img/ic_account_circle_white_24dp_1x.png">
			</div>
			<div class="5u">
			<c:choose>
				<c:when test="${sessionScope._USER_INFO_.userEmail eq 'admin'}">			
					<a href="/Marble/admin">관리자</a>
				</c:when>
				<c:otherwise>
					${sessionScope._USER_INFO_.userNickname }
				</c:otherwise>
			</c:choose>
			</div>
			<div class="2u">
			<img src="/Marble/img/ic_monetization_on_white_24dp_1x.png">
			</div>
			<div class="2u">
			 ${sessionScope._USER_INFO_.points }
			</div>
		</div>
		

</div>
