<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css"/>
<script type="text/javascript">
	$(document).ready(function() {
	 	$("#joinBtn").click(function(){
	 		$.post("/Marble/doSignUp",
	 				$( "#registForm" ).serialize(), function(data){
	 			if( data == "success"){	 				
	 				location.href="/Marble/play/index";
	 			}
	 			else{
	 				alert(data);
	 			}	
	 		});
		}); 
	 	
	 	$("#cancleBtn").click(function(){
	 		location.href="/Marble/play/index";		
		}); 
		
		
		$("#userEmail").blur(function(){
			$("#userEmail").keyup();
		});
		
		$("#userEmail").keyup(function(){
			
			if($(this).val() == ""){
				$(this).addClass("warning");
				$(this).removeClass("pass")
			}
			else {
			$.post("/Marble/duplicateUserEmail",
				{"userEmail":$("#userEmail").val()},
				function(data){
					if( data=="false"){
						$("#userEmail").addClass("pass");
						$("#userEmail").removeClass("warning");
					}
					else{
						$("#userEmail").addClass("warning");
						$("#userEmail").removeClass("pass");
					}
				});
			}
		});
		
		$("#userNickname").blur(function(){
			$("#userNickname").keyup();
		});
		
		$("#userNickname").keyup(function(){
			if($(this).val() == ""){
				$(this).addClass("warning");
				$(this).removeClass("pass")
			}
			else{
			$.post("/Marble/duplicateUserNickname",
				{"userNickname":$("#userNickname").val()},
				function(data){
					if ( data == "false"){
						$("#userNickname").addClass("pass");
						$("#userNickname").removeClass("warning");
					}
					else{
						$("#userNickname").addClass("warning");
						$("#userNickname").removeClass("pass");
					}
				});
			}
		});
		
		$("#userPassword1").blur(function(){
			$("#userPassword1").keyup();
		});
	
		$("#userPassword1").keyup(function(){
			if($(this).val() != $("#userPassword2").val() ){
				$(this).addClass("warning");
				$(this).removeClass("pass")
				$("#userPassword2").addClass("warning");
				$("#userPassword2").removeClass("pass")
			}
			else if($("#userPassword1").val() == "") {
				$(this).addClass("warning");
				$(this).removeClass("pass")
				$("#userPassword2").addClass("warning");
				$("#userPassword2").removeClass("pass")
			}
			else {
				$("#userPassword1").addClass("pass");
				$("#userPassword1").removeClass("warning")
				$("#userPassword2").addClass("pass");
				$("#userPassword2").removeClass("warning")
			}
		});
		
		$("#userPassword2").blur(function(){
			$("#userPassword2").keyup();
		});
		
		$("#userPassword2").keyup(function(){
			if($("#userPassword2").val() != $("#userPassword1").val() ){
				$("#userPassword2").addClass("warning");
				$("#userPassword2").removeClass("pass")
				$("#userPassword1").addClass("warning");
				$("#userPassword1").removeClass("pass")
			}
			else if($(this).val() == ""){
				$(this).addClass("warning");
				$(this).removeClass("pass")
				$("#userPassword1").addClass("warning");
				$("#userPassword1").removeClass("pass")
			}
			else {
				$(this).addClass("pass");
				$(this).removeClass("warning")
				$("#userPassword1").addClass("pass");
				$("#userPassword1").removeClass("warning")
			}
		});
	});
</script>

<body>
	<form id="registForm">
		<div class="sign_head inline2">주루마블에 회원가입하기</div>
		<div class="inline2 right"><input type="button" id="cancleBtn" value="X"/></div>
		<div class="clear"></div>
		<div id="signContent">
		<div class="sign-title2">회원가입</div>
		<div><input type="text" id="userEmail" name="userEmail" placeholder="Email 또는 ID를 적어주세요"> </div>
		<div><input type="text" id="userNickname" name="userNickname" placeholder="닉네임을 적어주세요"/>  </div>
		<div><input type="password" id="userPassword1" name="userPassword1" placeholder="Password를 적어주세요"/></div>
		<div><input type="password" id="userPassword2" name="userPassword2" placeholder="Password를 한번더 적어주세요"/></div>
		<div><input type="button" id="joinBtn" value="JOIN"/></div>
		</div>
		<div class="sign_footer">회원가입 페이지입니다.</div>
	</form>
</body>
</html>