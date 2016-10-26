<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Marble/css/pagination.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	var errorCode = "${param.errorCode}";
	if(errorCode == "1") {
		alert("파일, 광고시작일, 만료일을 모두 입력해주세요");
		return;
	}		
	if(errorCode == "2") {
		alert("광고 업로드에 실패했습니다.");
		return;
	}
	
	$("#uploadBtn").click(function(){
		if(confirm("광고를 업로드 하시겠습니까?")){
			$("#writeForm").attr( {
				"method": "post",
				"action": "/Marble/admin/doAdvertisement"
			}).submit();
		}
	});
	
	$("#deleteBtn").click(function(){
		if(confirm("광고를 삭제 하시 겠습니까?")){
			$.post("/Marble/admin/doDeleteAdvertisement",$("#writeForm").serialize(),function(data){
				 alert( "" + data );
			});
		}
	});
});

</script>
</head>
<body>
<h3>광고관리</h3>
	<div id="listDiv">
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
			   <table id="listTable">
					<tr>
						<th>선택</th>
						<th>번호</th>
						<th>파일이름</th>
						<th>계약일</th>
						<th>만료일</th>
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
					</tr>
				</c:forEach>
				</table>
			</form>
			<form id = "searchForm" name="searchForm">
				${paging}
				<div class="right">
					<select id="searchType" name="searchType">
							<option value="1" ${searchAdvertisement.searchType eq 1 ?'selected':''}>파일이름</option>
<%-- 							<option value="2" ${searchAdvertisement.searchType eq 2 ?'selected':''}>파일이름+만료일</option>
							<option value="3" ${searchAdvertisement.searchType eq 3 ?'selected':''}>계약일</option>
							<option value="4" ${searchAdvertisement.searchType eq 4 ?'selected':''}>만료일</option> --%>
					</select>
					<div class="inline"><input type="text" id="searchKeyword" name="searchKeyword"  value="${searchAdvertisement.searchKeyword}"/></div>				
					<div class="inline"><input type="button" id="searchBtn" value="검색" onclick="movePage(0)"/></div>
					<div class="inline"><input type="button" id="deleteBtn" value="삭제"/></div>
					<div class="clear"></div>
				</div>
			</form>
	</div>
</body>
</html>