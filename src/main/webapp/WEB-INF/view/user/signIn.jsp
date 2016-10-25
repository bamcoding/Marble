<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#signInBtn").click(
						function() {
							$.post("/Marble/doSignIn", $("#signInForm")
									.serialize(), function(data) {
								if (data == "true") {
									location.href = "/Marble/play/index";
								} else if (data == "false") {
									$(".small").html("<p>로그인에 실패하였습니다.</p>");
								} else if (data == "EmailNull") {
									$(".small").html("<p>아이디를 입력하세요.</p>");
								} else if (data == "PasswordNull") {
									$(".small").html("<p>패스워드를 입력하세요.</p>");
								}
							});

						});

				$("#signUserEmail").blur(function() {
					$("#signUserEmail").keyup();
				});

				$("#signUserEmail").keyup(function() {
					if ($(this).val() == "") {
						$(this).addClass("warning");
						$(this).removeClass("pass");
					} else {
						$(this).addClass("pass");
						$(this).removeClass("warning");
					}
				});

				$("#signUserPassword").blur(function() {
					$("#signUserPassword").keyup();
				});
				$("#signUserPassword").keyup(function() {
					if ($(this).val() == "") {
						$(this).addClass("warning");
						$(this).removeClass("pass");
					} else {
						$(this).addClass("pass");
						$(this).removeClass("warning");
					}
				});
				
				$("#signUpBtn").click(function(){
					$("#sign-up").fadeIn();
				});
				
			});
</script>
<div>
	<form id="signInForm" name="signInForm">
		
			<input type="text" id="signUserEmail" class="12u" name="userEmail" placeholder="Email 또는 ID를 적어주세요" />
			<input type="password" id="signUserPassword" class="12u" name="userPassword" placeholder="Password를 적어주세요" />
			<input type="button" id="signInBtn" class="button fit 12u" value="SIGN IN" />
		
	</form>
</div>

