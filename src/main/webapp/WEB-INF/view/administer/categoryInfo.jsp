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

<title>Insert title here</title>
</head>
<style>
ul {
    padding: 0em;
}

ul li, ul li ul li {
    position:relative;
    top:0;
    bottom:0;
    padding-bottom: 7px;

}

ul li ul {
    margin-left: 4em;
}

li {
    list-style-type: none;
}

li a {
    padding:0 0 0 10px;
    position: relative;
    top:1em;
}

li a:hover {
    text-decoration: none;
}

a.addBorderBefore:before {
    content: "";
    display: inline-block;
    width: 2px;
    height: 28px;
    position: absolute;
    left: -47px;
    top:-16px;
    border-left: 1px solid gray;
}

li:before {
    content: "";
    display: inline-block;
    width: 25px;
    height: 0;
    position: relative;
    left: 0em;
    top:1em;
    border-top: 1px solid gray;
}

ul li ul li:last-child:after, ul li:last-child:after {
    content: '';
    display: block;
    width: 1em;
    height: 1em;
    position: relative;
    background: #fff;
    top: 9px;
    left: -1px;
}
</style>

<body>
	<form id="categoryForm" name="categoryForm">
		<div class="container">
			<ul>
				<li><a href="#">Root <input type="checkbox" name="checks" /></a>
<c:forEach items="${categories }" var="category">
					<ul>
						<li>
						
	<a href="/Marble/admin/category?categoryId=${category.categoryId}&parentCategoryId=${category.parentCategoryId}">
	${category.categoryName}
	<input type="checkbox" name="checks" />
	</a>
						</li>
					</ul>
</c:forEach>
				</li>
			</ul>
		</div>	


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
// Select the main list and add the class "hasSubmenu" in each LI that contains an UL
$('ul').each(function(){
  $this = $(this);
  $this.find("li").has("ul").addClass("hasSubmenu");
});
// Find the last li in each level
$('li:last-child').each(function(){
  $this = $(this);
  // Check if LI has children
  if ($this.children('ul').length === 0){
    // Add border-left in every UL where the last LI has not children
    $this.closest('ul').css("border-left", "1px solid gray");
  } else {
    // Add border in child LI, except in the last one
    $this.closest('ul').children("li").not(":last").css("border-left","1px solid gray");
    // Add the class "addBorderBefore" to create the pseudo-element :defore in the last li
    $this.closest('ul').children("li").last().children("a").addClass("addBorderBefore");
    // Add margin in the first level of the list
    $this.closest('ul').css("margin-top","20px");
    // Add margin in other levels of the list
    $this.closest('ul').find("li").children("ul").css("margin-top","20px");
  };
});
// Add bold in li and levels above
$('ul li').each(function(){
  $this = $(this);
  $this.mouseenter(function(){
    $( this ).children("a").css({"font-weight":"bold","color":"#336b9b"});
  });
  $this.mouseleave(function(){
    $( this ).children("a").css({"font-weight":"normal","color":"#428bca"});
  });
});
// Add button to expand and condense - Using FontAwesome
$('ul li.hasSubmenu').each(function(){
  $this = $(this);
  $this.prepend("<a href='#'><i class='fa fa-minus-circle'></i><i style='display:none;' class='fa fa-plus-circle'></i></a>");
  $this.children("a").not(":last").removeClass().addClass("toogle");
});
// Actions to expand and consense
$('ul li.hasSubmenu a.toogle').click(function(){
  $this = $(this);
  $this.closest("li").children("ul").toggle("slow");
  $this.children("i").toggle();
  return false;
});
</script>		
		<div class="right">
			<div class="inline2"><input type="submit" id="searchBtn" value="검색"/></div>
			<div class="inline2"><input type="submit" id="deleteBtn" value="삭제"/></div>
			<div class="inline2"><input type="submit" id="modifyBtn" value="수정"/></div>
			<div class="clear"></div>
		</div>
	</form>
</body>
</html>