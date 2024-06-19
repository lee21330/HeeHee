$(function() {
	$("#purchaselist").hide();
	$("#jjimlist").hide();
	$("#btn_search").on("click", show);
	$(".mModal_close").on("click", hide);
	$(".menu li").on("click", changeMenu);
	$(".sub_menu p").on("click", changeStatus);
});
function show() {
	$("#search").addClass("show");
}
function hide() {
	$("#search").removeClass("show");
}
function changeMenu() {
	$(".menu li").removeClass("select");
	$(this).addClass("select");

	if ($(this).text() == "판매내역") {
		$(".list").hide();
		$("#salelist").show();
	} else if ($(this).text() == "구매내역") {
		$(".list").hide();
		$("#purchaselist").show();
	} else {
		$(".list").hide();
		$("#jjimlist").show();
	}

}
function changeStatus() {
	$(".sub_menu p").removeClass("select_sub");
	$(this).addClass("select_sub");

}
