$(function() {
	$("#purchaselist").hide();
	$("#jjimlist").hide();
	$("#btn_search").on("click", show);
	$(".mModal_close").on("click", hide);
	$(".menu li").on("click", change);
});
function show() {
	$("#search").addClass("show");
}
function hide() {
	$("#search").removeClass("show");
}
function change() {
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