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
$(document).ready(
		function() {

			$("#addBtn").click(function() {
				var cnt = 0;
				$(".selected").parents().each(function() {
					addGames(this);
				});
			});

			$("#games_set ul li > div").click(function() {
				selected($(this));
			});
			
			function selected(fnc){
				if (!$(fnc).hasClass("selected")) {
					$(fnc).parents().find("div").removeClass("selected");
					$(fnc).addClass("selected");
				}
			}

			$("#games_list ul li").dblclick(function() {
				addGames(this);
			});

			function addGames(fnc) {

				if (getCnt() >= 23) {
					alert("게임을 23개까지 넣을 수 있습니다.");
					return;
				}

				var index = $(fnc).index();
				var gameId = $("p:eq(" + index + ")").attr("id");
				var gameName = $("p:eq(" + index + ")").text();
				var duplicate = false;
				$(".gameId").each(function() {

					if (gameId == $(this).text()) {
						duplicate = true;
						return;
					} else {
						duplicate = false;
					}
				});

				if (duplicate) {
					var cntDiv = $(".gameCnt:eq(" + $(this).index() + ")");
					var cnt = cntDiv.text();
					cntDiv.text(parseInt(cnt) + 1);
				} else {
					$("#selected_games_list").append("<li>");
					$("#selected_games_list").append(
							"<div class='gameId' style='display:none'>"
									+ gameId + "</div>");
					$("#selected_games_list").append(
							"<div class='gameNmae' style='display:inline-block'>"
									+ gameName + "</div>");
					$("#selected_games_list").append(
							"<div class='gameCnt' style='display:inline-block'>"
									+ 1 + "</div>");
					$("#selected_games_list").append("</li>");
				}

			}
			
			function asdf(){
				alert("asdf");
			}

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

		<div id="games_list" style="display: inline-block;">
			<form class="setGmaesForm" name="setGamesForm">
				<ul>
					<c:forEach items="${games}" var="game">
						<li>
							<div class="inline" style="color: black;">
								<p id="${game.gameId}">${game.gameName}</p>
							</div>
							<div class="inline">
								<input type="button" class="info" value="?"
									style="background-color: white; border: 0;" />
							</div>
						</li>
					</c:forEach>
				</ul>
			</form>
			<div>
				<input type="button" id="addBtn" value="Add" />
			</div>
			<div>
				<input type="button" id="complateBtn" value="complate" />
			</div>
		</div>

		<div id="selected_games_list" style="display: inline-block;">
			<ul>
				<li style="display: none">
					<div class='gameId' style='display: none'></div>

					<div class='gameNmae' style='display: inline-block'></div>

					<div class='gameCnt' style='display: inline-block'></div>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>