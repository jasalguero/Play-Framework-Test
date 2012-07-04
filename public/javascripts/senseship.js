/******************************************************/
/*				BASIC FUNCTIONALITY					  */
/******************************************************/
function bindProjectEvent() {
	//Add event for expand and collapse post content
	$(".eventPreview").mouseenter(function() {
		jQuery(".infoOverlay",this).fadeIn("slow");
	/*	$("#overlay").offset($(this).offset());
		$("#overlay").width($(this).width());
		$("#overlay").height($(this).height());*/
	});

	$(".eventPreview").mouseleave(function() {
		console.log("mouse out");
		jQuery(".infoOverlay",this).fadeOut("slow");
		/*$("#overlay").css("top",-5000);
		$("#overlay").width(1);
		$("#overlay").height(1);*/
	});

}