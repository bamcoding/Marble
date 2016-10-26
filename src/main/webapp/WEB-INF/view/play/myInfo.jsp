<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			 ${sessionScope._USER_INFO_.userNickname }
			</div>
			<div class="2u">
			<img src="/Marble/img/ic_monetization_on_white_24dp_1x.png">
			</div>
			<div class="2u">
			 ${sessionScope._USER_INFO_.points }
			</div>
		</div>
		

</div>
