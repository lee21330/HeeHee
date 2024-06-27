$(function() {
	faqOption(0);
	$("#submit").on("click", search);

	//엔터 키 누르면 검색
	$("#searchInput").keydown(function(event) {
		if (event.keyCode === 13) { // 엔터 키의 keyCode: 13
			search(); 
		}
	});
});
function search() {
	var keyword = $('#searchInput').val();
	$.each($(".title"), function(index, item) {
		var text = $(item).text();
		if (!text.includes(keyword))
			$(this).parent().addClass("searchStyle");
		else
			$(this).parent().removeClass("searchStyle");

		$("#faqTable").removeClass("searchStyle");
	});

}
function faqOption(option) {
	$("#searchInput").val("");
	$.ajax({
		url: '/heehee/mypage/faqBoard/faqOption',
		method: 'GET',
		data: { 'option': option },
		success: function(data) {
			var faqList = $('#faqList');
			var output = '';
			if (data.length === 0) {
				$('#message').html("<p class='message'>해당 유형의 FAQ가 없습니다.</p>");
			} else {

				data.forEach(function(fa) {
					$('#message').empty();
					output += `							
                            <tr class="question">
								<td class="type">${fa.qnaOption}</td>
								<td class="title">${fa.faqContent}</td>
							</tr>
							<tr class="answer">
								<td class="type">답변</td>
								<td class="title">${fa.faqAns}</td>
							</tr>`;
				});
			}
			faqList.html(output);
			$(".answer").hide();
			$(".question").on("click", show);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}
function show() {
	if ($(this).next().css("display") != "none") {
		$(".answer").hide();
		$(".question").removeClass("select");
	} else {
		$(".answer").hide();
		$(this).next().show();
		$(".question").removeClass("select");
		$(this).addClass("select");
	}
}