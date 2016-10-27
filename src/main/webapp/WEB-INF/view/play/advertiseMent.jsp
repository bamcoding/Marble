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
			//$(this).remove();
		});
	 
		
	});
	
	
	

	
</script>




				    <video width="100%" height="" 
				    	src="/Marble/play/download?advertisementId=${advertisement.advertisementId}" autoplay controls> 
				    </video>
