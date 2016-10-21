<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/marble.css" />
<script type="text/javascript">
	$(document).ready(function() {
		var randomNumber = parseInt(Math.random()*3)+1
		console.log(randomNumber);
		
		$("#video"+randomNumber).css({"display":"block"});
		
		$("#video"+randomNumber).click(function(){
			$(this).css({"display":"none"});
		});
		
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
				    <video id="video1" width="100%" height="100%"
				    	src="/Marble/video/test1.mp4" autoplay controls> 
				    </video>
				    <video  id="video2" width="100%" height="100%" 
				    	src="/Marble/video/test2.mp4" autoplay controls> 
				    </video>
			    
				    <video  id="video3" width="100%" height="100%" 
				    	src="/Marble/video/test3.mp4" autoplay controls> 
				    </video>
	 			</div>
		</div>  
</div>