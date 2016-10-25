<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/administer/admin.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#uploadBtn").click(function(){
		/* $.post("/Marble/admin/doAdvertisement",$("#writeForm").serialize(),function(data){
		alert("성공");	
		}); */
		$("#writeForm").attr( {
			"method": "post",
			"action": "/Marble/admin/doAdvertisement"
		}).submit();
	});
});

</script>
</head>
<body>
	<div class="userList">
		<form id="writeForm"name="writeForm" enctype="multipart/form-data">
				   <div>
						<div class="inline">광고 선택 : </div>
						<div class="inline"><input type="file"  name="file" id="file"/></div>
						<div class="inline"><input type="button" name="uploadBtn" id="uploadBtn" value="광고업로드"/></div>
				   </div>
				   <div>
					   	<div class="inline">광고 시작일 : </div>
						<div class="inline"><input type="date" name="contractDate" id="contractDate"/></div>
					   	<div class="inline">광고 만료일 : </div>
						<div class="inline"><input type="date" name="expirationDate"id="expirationDate"/></div>
										   </div>
				   <br>
				   <hr/>
			   <table class="grid">
					<tr>
						<td>선택</td>
						<td>번호</td>
						<td>파일이름</td>
						<td>계약일</td>
						<td>만료일</td>
						<td>파일경로</td>
					</tr>
				<c:forEach items="${advertisements}" var="advertisement">
						<div><input type="hidden" name="advertisementId" id="advertisementId" value="${advertisement.advertisementId}"/></div>
					<tr>
						<c:set var="number" value="${fn:split(advertisement.advertisementId,'-')[2]}"/>
						<fmt:parseNumber var="number" type="number" value="${number}"/>
						<td><input type="checkbox" name="checks" value="${advertisement.advertisementId}"/></td>
						
						<td>${number}</td>
						<td>${advertisement.fileName}</td>
						<td>${advertisement.contractDate }</td>
						<td>${advertisement.expirationDate}</td>
						<td><a href="/Marble/play/download?advertisementId=${advertisement.advertisementId}">${advertisement.filePath}</a></td>
					</tr>
				</c:forEach>
				</table>
				${paging}
				<div class="right">
					<select id="searchType" name="searchType">
							<option value="1" ${searchUser.searchType eq 1 ?'selected':''}>파일이름+계약일</option>
							<option value="2" ${searchUser.searchType eq 1 ?'selected':''}>파일이름+만료일</option>
							<option value="3" ${searchUser.searchType eq 2 ?'selected':''}>계약일</option>
							<option value="4" ${searchUser.searchType eq 3 ?'selected':''}>만료일</option>
					</select>
					<div class="inline"><input type="text" id="searchKeyword" name="searchKeyword"  value="${searchUser.searchKeyword}"/></div>				
					<div class="inline"><input type="button" id="adUploadBtn" value="검색" onclick="movePage(0)"/></div>
					<div class="inline"><input type="button" id="deleteBtn" value="삭제"/></div>
					<div class="clear"></div>
				</div>
			</form>
	</div>
</body>
</html>