<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="/Marble/css/marble.css" />
<script type="text/javascript">
	$(document).ready(function() {
		
		
		
	 	$("#advertisementVideo").click(function(){
			$(this).remove();
		});
	 
		
	});
	
	
	

	
</script>

<style>
	video{
	display: block;
		}
</style>

<div>
	 	<div id="marble" >
	 			<div id="gamePan">
				    <video id="advertisementVideo" width="100%" height="100%" 
				    	src="/Marble/play/download?advertisementId=${advertisement.advertisementId}" autoplay controls> 
				    </video>
	 			</div>
		</div>  
</div>