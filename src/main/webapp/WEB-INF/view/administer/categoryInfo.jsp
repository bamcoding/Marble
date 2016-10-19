<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Marble/css/login.css"/>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<script>
getCategoryTree();

function getCategoryTree(){
	var array = '${categories}';
	var size = array.length;
	console.log(size);
}

</script>
<title>Insert title here</title>
</head>

<body>
	<form id="categoryForm" name="categoryForm">
		<div class="container">
			<ul>
				<li>루트
					<ul><li>카테고리2
						<ul><li>카테고리2-1
					</li></ul>
					</li></ul>
					<ul><li>카테고리3</li></ul>
<c:forEach items="${categories }" var="category">
					<ul>
						<li>
						
	<a href="/Marble/admin/category?categoryId=${category.categoryId}&parentCategoryId=${category.parentCategoryId}">
	${category.categoryName} ;
	<input type="checkbox" name="checks" />
	</a>
						</li>
					</ul>
</c:forEach>
				</li>
			</ul>
		</div>	
	
		<div class="right">
			<div class="inline2"><input type="submit" id="searchBtn" value="검색"/></div>
			<div class="inline2"><input type="submit" id="deleteBtn" value="삭제"/></div>
			<div class="inline2"><input type="submit" id="modifyBtn" value="수정"/></div>
			<div class="clear"></div>
		</div>
	</form>
</body>
</html>