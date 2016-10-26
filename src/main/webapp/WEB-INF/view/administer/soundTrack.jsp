<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	var errorCode = "${param.errorCode}";
	if(errorCode == "1") {
		alert("파일을 업로드해주세요");
		return;
	}		
	if(errorCode == "2") {
		alert("음악 업로드에 실패했습니다.");
		return;
	}
	
	$("#uploadBtn").click(function(){
		if(confirm("음악을 업로드 하시겠습니까?")){
			$("#writeForm").attr( {
				"method": "post",
				"action": "/Marble/admin/doSoundTrack"
			}).submit();
		}
	});
	
	$("#deleteBtn").click(function(){
		if(confirm("음악을 삭제 하시 겠습니까?")){
			$.post("/Marble/admin/doDeleteSoundTrack",$("#writeForm").serialize(),function(data){
				 alert( "" + data );
			});
			location.href = "/Marble/admin/soundTrack"
		}
	});
});

</script>

<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"></jsp:include>

<h3>음악관리</h3>

	<div id="listDiv">
		<form id="writeForm"name="writeForm" enctype="multipart/form-data">
				   <div>
						<div class="inline">음악 선택 : </div>
						<div class="inline"><input type="file"  name="file" id="file"/></div>
						<div class="inline"><input type="button" name="uploadBtn" id="uploadBtn" value="음악업로드"/></div>
				   </div>
				   <br>
				   <hr/>
			   <table id="listTable">
					<tr>
						<th>선택</th>
						<th>번호</th>
						<th>파일이름</th>
						<th>음악정보</th>
					</tr>
				<c:forEach items="${soundTracks}" var="soundTrack">
						<div><input type="hidden" name="soundTrackId" id="soundTrackId" value="${soundTrack.soundTrackId}"/></div>
					<tr>
						<c:set var="number" value="${fn:split(soundTrack.soundTrackId,'-')[2]}"/>
						<fmt:parseNumber var="number" type="number" value="${number}"/>
						<td><input type="checkbox" name="checks" value="${soundTrack.soundTrackId}"/></td>
						
						<td>${number}</td>
						<td>${soundTrack.fileName}</td>
						<td>${soundTrack.soundInfo}</td>
					</tr>
				</c:forEach>
				</table>
			</form>
			<form id = "searchForm" name="searchForm">
				${paging}
				<div class="right">
					<select id="searchType" name="searchType">
							<option value="1" ${searchSoundTrack.searchType eq 1 ?'selected':''}>파일이름</option>
<%-- 							<option value="2" ${searchAdvertisement.searchType eq 2 ?'selected':''}>파일이름+만료일</option>
							<option value="3" ${searchAdvertisement.searchType eq 3 ?'selected':''}>계약일</option>
							<option value="4" ${searchAdvertisement.searchType eq 4 ?'selected':''}>만료일</option> --%>
					</select>
					<div class="inline"><input type="text" id="searchKeyword" name="searchKeyword"  value="${searchSoundTrack.searchKeyword}"/></div>				
					<div class="inline"><input type="button" id="searchBtn" value="검색" onclick="movePage(0)"/></div>
					<div class="inline"><input type="button" id="deleteBtn" value="삭제"/></div>
					<div class="clear"></div>
				</div>
			</form>
	</div>
</body>
</html>