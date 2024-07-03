$(function() {
	$(".content").hide();
	$(".header").on("click", show);
	$("#myQna .header").each(function() {
		var $ansStatus = $(this).find(".ansStatus");
		var $qnaAns = $(this).next(".content").find(".qnaAns");

		if ($qnaAns.text().trim() !== "") {
			$ansStatus.text("답변완료");

		} else {
			$ansStatus.text("답변대기");
			$ansStatus.css("background-color", "#ABC3FF");

		}
	});
	$("#cancel").on("click", reset);
	$("#btn_submit").on("click", insertQna);

});

function selectFileInput(number) {
	var inputId = "#input_file" + number;
	$(inputId).click();
}
function show() {
	if ($(this).next().css("display") != "none") {
		$(".content").hide();
		$(".header").removeClass("select");
	} else {
		$(".content").hide();
		$(this).next().show();
		$(".header").removeClass("select");
		$(this).addClass("select");
	}
}
function readURL(input, number) {
	var previewId = "preview" + number;
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#' + previewId).attr('src', e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		$('#' + previewId).attr('src', "");
	}
}
function reset() {
	$("#qna input, textarea").val("");
	$("input:radio[name='SEQ_QNA_OPTION']").removeAttr("checked");
}

function showQnaOptionContent(optionContent) {
	$('#qnaOptionContent').text(optionContent);
}

function insertQna() {

}
