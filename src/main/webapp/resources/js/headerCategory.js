$(function () {

	// 카테고리 메뉴 숨겨놓기
	// $(".nav_title").hide();
	// $(".nav_content").hide();

	// 숨긴 카테고리 메뉴 보여주기
	$(".nav_container").mouseenter(function () {
		$(".nav_title").show();
	});

	// 카테고리 메뉴 css 추가
	$(".category_list li").mouseenter(function () {
		var categoryName = $(this).text();

		$(".nav_content .category_name p").text(categoryName);
		$(".nav_content").show();
		$(".category_list li").css({
			"background": "white",
			"color": "black"
		});

		/* 마우스 올라가면 카테고리에 css 추가 */
		$(this).css({
			"background-color": "rgb(63, 81, 161)",
			"color": "white"
		});
	});

	// 카테고리 메뉴 숨기기
	$(".nav_inner").mouseleave(function () {
		$(".category_list").scrollTop(0); /* 스크롤 위치 초기화 */
		// $(".nav_title").hide();
		// $(".nav_content").hide();
		$(".category_list li").css("background", "white"); /* css 초기화 */
		$(".category_list li").css("color", "black"); /* css 초기화 */
	})
});