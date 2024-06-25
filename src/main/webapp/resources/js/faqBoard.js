$(function() {
	faqOption(0);
});

function faqOption(option) {
	$.ajax({
		url: '/heehee/faqBoard/faqOption',
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
	} else {
		$(".answer").hide();
		$(this).next().show();
	}
}