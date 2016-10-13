<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#menu ul li").click(function(){
			var index = $(this).index();
			if(index == 0){
				$("#settings").load("/Marble/myInfo");
				
			}
		});
		
		
	});
</script>

<div class="menubar" id="menubar" style="display: none">
	<div class="menu" id="menu">

		<ul>
			<li><a href="#info">내 정보</a></li>
			<li><a href="#setGame">게임 셋팅</a></li>
			<li><a href="#addGame">게임 추가</a></li>
			<li><a href="#community">커뮤니티</a></li>
			<li><a href="#fna">문의사항</a></li>
		</ul>


	</div>
	<div class="settings" id="settings">
		<p>asdfasdf</p>
		<i class="material-icons">accessible</i> fdfd<br /> fdfd<br /> fdfd<br />
		fdfd<br /> fdfd<br /> v <i class="material-icons">accessible</i> fdfd<br />
		fdfd<br /> fdfd<br /> fdfd<br /> fdfd<br /> fdfd<br /> v <i
			class="material-icons">accessible</i> fdfd<br /> fdfd<br /> fdfd<br />
		fdfd<br /> fdfd<br /> v v fdfd<br /> fdfd<br /> fdfd<br /> fdfd<br />
		v fdfd<br /> v fdfd<br /> v fdfd<br /> vfdfd<br />

	</div>
</div>
