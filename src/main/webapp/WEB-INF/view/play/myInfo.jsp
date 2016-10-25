<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

</script>
<div class="userInfo">

	<ul class="alt">
		<li></li>
		<li>${sessionScope._USER_INFO_.userNickname } <input type="button" id="signUpBtn" class="button small" value="LOGOUT" onclick="window.location.href='/Marble/doLogout'"/></li>
		<li>Point : ${sessionScope._USER_INFO_.points	 }</li>
	</ul>

</div>
