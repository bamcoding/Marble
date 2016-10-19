<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Marble/css/interface.css" />
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	var delId = 0;
	function delGame(index) {
		alert(index);
	}

	$(document)
			.ready(
					function() {
						
						$("#addBtn").click(function() {
						});

						$("#games_set ul li").click(function() {
							var value = $(this).find(".gameCnt").val();
							if(value > 0){
								$(this).addClass("selected");								
							}else{
								$(this).removeClass("selected");
							}
							
						});
						
						$(":input[type=number]").change(function(e) {
							var value = $(this).val();
							var index = $(this).index();
							if(value <= 0){
								$(this).val(0);
							}
						});

						function getCnt() {
							var cnt = 0;
							$(".gameCnt").each(function() {
								var num = $(this).text();
								if (num != "") {
									cnt = cnt + parseInt(num);
								}
							});

							return cnt;
						}
					});
</script>
</head>
<body>
	<div id="games_set">
		<div id="games_list"
			style="display: inline-block; overflow: scroll; width: 500px; height: 500px;">
			<form class="setGmaesForm" name="setGamesForm">
				<ul>
					<c:forEach items="${games}" var="game">
						<li>
							<div class="inline gameName" style="color: black;">
								<p id="${game.gameId}">${game.gameName}</p>
							</div>
							<div class="inline">
								<input type="button" class="info" value="?"
									style="background-color: white; border: 0;" />
							</div>
							<div class="inline">
								<input type="number" class="gameCnt" value="0" style="width: 50px;" />
							</div>

						</li>
					</c:forEach>
				</ul>
			</form>

		</div>
		<div>
			<input type="button" id="addBtn" value="Add" />
		</div>
		<div>
			<input type="button" id="complateBtn" value="complate" />
		</div>
	</div>
</body>
</html>