<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
    	$(document).ready(function() {
    	});
    	
    	$(".history ul li").hover(function(){
    		$(this).addClass("selected");
    	})
    	
    	$(".history ul li").mouseleave(function(){
    		$(this).removeClass("selected");
    	});
    	
    	$(".history ul li").click(function(){
    		playInfo = $(this).attr("id");
    		$("#marbleBoard").load("../confirmPlays?playInfo="+playInfo);
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

<div class="history" style="position:absolute; height: 80%; width:60%; bottom: 0; overflow-y: scroll;">
	<ul>
	<c:forEach var="his" items="${history }">
		<li id="${his.playInfo }"><label>${his.playDate }</label></li>
	</c:forEach>
	</ul>	
</div>