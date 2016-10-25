<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/marble.css" />
<script type="text/javascript">
	$(document).ready(function() {
		
		var randomNumber = parseInt(Math.random()*3)+1
		
		
		
	/* 	$("#test"+randomNumber).click(function(){
		$(this).remove();
		$("#test"+randomNumber).css({"display":"block"});
		$("#test"+randomNumber).attr({"autoplay":"autoplay"});			
			 */
		
		
		
	});
	
	
	

	
</script>

<style>
	#video1{
	display: none;
		}
	#video2{
	display: none;
		}
	#video3{
		display: none;
	}
</style>

<div>
	 	<div id="marble" >
	 			<div id="gamePan">
	 			<c:forEach items="${advertisements}" var="advertisement">
				    <video id="${advertisement.fileName}" width="100%" height="100%" 
				    	src="/Marble/play/download?advertisementId=${advertisement.advertisementId}" autoplay controls> 
				    </video>
	 			</c:forEach>
	 			</div>
		</div>  
</div>