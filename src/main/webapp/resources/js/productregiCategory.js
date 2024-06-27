$(document).ready(function() {
    $('.content_list1 p').click(function(event) {
        event.preventDefault();
        $('.content_list1 p').removeClass('selected');
        $(this).addClass('selected');
    });
});

$(function () {
	// 카테고리 메뉴 css 추가
	$(".category_list1 li").mouseenter(function () {
		var categoryName1 = $(this).text();

		$(".nav_content1 .category_name1 p").text(categoryName1);
		$(".category_list1 li").css({
			"background": "white",
			"color": "black"
		});

		/* 마우스 올라가면 카테고리에 css 추가 */
		$(this).css({
			"background-color": "rgb(63, 81, 161)",
			"color": "white"
		});
	});
	
});