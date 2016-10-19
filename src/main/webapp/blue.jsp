
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->
<!-- 게임을 테스트 하는 페이지 입니다. -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>http://www.blueb.co.kr</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
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
</head>
<body>


<div class="container">

<ul>
	<li><a href="#">1</a>
		<ul>
			<li><a href="#">1-1</a>
					<ul>
					<li><a href="#">1-1-1</a></li>
					<li><a href="#">1-1-2</a></li>
					<li><a href="#">1-1-3</a></li>
					<li><a href="#">1-1-4</a></li>
					</ul>
			</li>
			<li><a href="#">1-2</a>
				<ul>
				<li><a href="#">1-2-1</a></li>
				<li><a href="#">1-2-2</a></li>
				<li><a href="#">1-2-3</a></li>
				<li><a href="#">1-2-4</a></li>
				</ul>
			</li>
		</ul>
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


</body>
</html>