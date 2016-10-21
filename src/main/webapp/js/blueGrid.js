// Select the main list and add the class "hasSubmenu" in each LI that contains an UL
$('#ctgr_content ul').each(function(){
  $this = $(this);
  $this.find("li").has("ul").addClass("hasSubmenu");
  $this.closest('ul').css("border-left", "1px dashed #cccccc");
});
// Find the last li in each level
$('#ctgr_content li:last-child').each(function(){
  $this = $(this);
  // Check if LI has children
  if ($this.children('ul').length === 0){
	// $this.closest('ul').css("border-left", "1px dashed #cccccc");
    // Add border-left in every UL where the last LI has not children
  } else {
    // Add the class "addBorderBefore" to create the pseudo-element :defore in the last li
    $this.closest('ul').children("li").last().children("a").addClass("addBorderBefore");
    // Add margin in the first level of the list
  };
});
// Add bold in li and levels above
$('#ctgr_content ul li').each(function(){
  $this = $(this);
  $this.mouseenter(function(){
    $( this ).children("a").css({"font-weight":"bold","color":"#336b9b"});
  });
  $this.mouseleave(function(){
    $( this ).children("a").css({"font-weight":"normal","color":"#428bca"});
  });
});
// Add button to expand and condense - Using FontAwesome
$('#ctgr_content ul li.hasSubmenu').each(function(){
  $this = $(this);
  $this.prepend("<a href='#'><i class='fa fa-minus-circle'></i><i style='display:none;' class='fa fa-plus-circle'></i></a>");
  $this.children("a").not(":last").removeClass().addClass("toogle");
});
// Actions to expand and consense
$('#ctgr_content ul li.hasSubmenu a.toogle').click(function(){
  $this = $(this);
  $this.closest("li").children("ul").toggle("slow");
  $this.children("i").toggle();
  return false;
});