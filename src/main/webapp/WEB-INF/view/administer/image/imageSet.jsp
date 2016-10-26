<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/administer/decoratedAdmin.jsp"></jsp:include>


<script type="text/javascript" src="/Marble/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$().ready(function(){
	$("#deleteBtn").click(function(){
		if(confirm("정말로 이미지를 삭제하시겠습니까?")){
			$.post(".doDeleteImage", $("#imageForm").serialize() , function(data){
				if(data!=true){
					alert("선택할 이미지를 선택해 주세요");
				}	
			});
		}
	});
})

</script>

<h3>이미지관리</h3>
<div id="listDiv">
<table>
	<tr>
		<th></th>
		<th>파일이름</th>
<form>
	</tr>
	<c:forEach var="image" items="${images}">	
	<tr>
		<td><input type="checkbox" id="check" value="${image.imageId}"></td>
		<td><a href="#">${image.imageName}</a></td>
	</tr>
	</c:forEach>
</table>
<input type="button" onclick="location.href='./imageUpLoad'" value="이미지 올리기"/> 
<input type="button"  id="deleteBtn" value="이미지 삭제하기"/> 
</form>
</div>
</body>
</html>