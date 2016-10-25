<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
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
    
<div class="history" style=" overflow-y: scroll; height: 300px">
	<ul class="alt">
	<c:forEach var="his" items="${history }">
		<li id="${his.playInfo }"><h3>${his.playDate }</h3></li>
	</c:forEach>
	</ul>	
</div>