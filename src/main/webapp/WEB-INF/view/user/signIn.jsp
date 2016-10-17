<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css"/>
<script type="text/javascript">
	$(document).ready(function(){

		$("#signInBtn").click(function(){
			$.post("/Marble/doSignIn",
			$("#signInForm").serialize(),
			function(data){
				if ( data == "true" ){
					location.href="/Marble/play/index";
				}
				else if( data == "false"){
					alert("로그인이 실패 하였습니다.");
				}
				else{
					alert(data);
				}
			}
			);
			
		});
		
		$("#signUpBtn").click(function(){
			$("#wrapper").load("/Marble/signUp", function(data){
			});
		});
		
		$("#cancleBtn").click(function(){
			location.href="/Marble/play/index";
		});
		
		
		$("#signUserEmail").blur(function(){
			$("#signUserEmail").keyup();
		});
		
		$("#signUserEmail").keyup(function(){
			if( $(this).val()=="" ){
				$(this).addClass("warning");
				$(this).removeClass("pass");
			}
			else{
				$(this).addClass("pass");
				$(this).removeClass("warning");
			}
		});
		
		$("#signUserPassword").blur(function(){
			$("#signUserPassword").keyup();
		});
		$("#signUserPassword").keyup(function(){
			if( $(this).val()=="" ){
				$(this).addClass("warning");
				$(this).removeClass("pass");
			}
			else{
				$(this).addClass("pass");
				$(this).removeClass("warning");
			}
		});
	});
	
</script>
	<form id="signInForm" name="signInForm">
		<div class="sign-title">LOGIN</div>
		<div>
			<div><input type="text" id="signUserEmail" name="userEmail" placeholder="Email 또는 ID를 적어주세요"> </div>
			<div><input type="password" id="signUserPassword" name="userPassword" placeholder="Password를 적어주세요"/></div>
		</div>
		<div>
			<div class="inline"><input type="button" id="signInBtn" value="Sign In"/></div>
			<div class="inline"><input type="button" id="signUpBtn" value="Sign Up"/></div>
			<div class="inline"><input type="button" id="cancleBtn" value="Cancle"/></div>
		</div>
	</form>

