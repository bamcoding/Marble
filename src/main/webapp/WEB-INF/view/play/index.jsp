<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#settingPage").load("/Marble/setGames");
		$("#addGamePage").load("/Marble/addGames");
		$("#historyPage").load("/Marble/myInfo")
	});
</script>

<style>
	.container:not(:last-child){
		height: 100%
	}
</style>

<jsp:include page="/WEB-INF/view/common/header.jsp" />

<!-- Main -->
		<div id="main">
			<section id="one">
			</section>	

			<!-- One -->
			<!-- <section id="start">
			<div class="container">
				<header class="major">
				<h2>Read Only</h2>
				<p>
					Just an incredibly simple responsive site<br /> template freebie
					by <a href="http://html5up.net">HTML5 UP</a>.
				</p>
				</header>
			</div>
			</section> -->
			
			
			<c:choose>
			<c:when test="${empty sessionScope._USER_INFO_ }">
				
			<section id="signup">
			<div class="container">
				<header class="major">
				<h2>SIGN UP</h2>
				
				</header>
				<jsp:include page="/WEB-INF/view/user/signUp.jsp" />
				
			</div>
			</section>

			
			
			</c:when>
			<c:otherwise>
				
				<!-- Two -->
			<section id="setting">
			<div class="container">
				<header class="major">
				<h2>Setting</h2>
				<p>19개의 게임을 선택해주세요.</p>
				</header>
				<div id="settingPage">
				</div>
				
			</div>
			</section>
			
			
			<!-- Three -->
			<section id="add">
			<div class="container">
				<header class="major">
				<h2>ADD GAME</h2>
				<p>자신만의 게임을 추가해보세요.</p>
				</header>
				<div id="addGamePage"></div>
				
			</div>
			</section>
			
			
			<section id="history">
			<div class="container">
				<header class="major">
				<h2>HISTORY</h2>
				<p>지난 게임을 선택해서 플레이 해보세요.</p>
				</header>
				<div id="historyPage"></div>
				
			</div>
			</section>

			
				
			</c:otherwise>
		</c:choose>
	
</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp" />