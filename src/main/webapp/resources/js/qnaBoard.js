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
	$("#btn_submit").on("click", insertQna);

});

function selectFileInput() {
	$("#input_file").click();
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
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#preview').attr('src', e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		$('#preview').attr('src', "");
	}
}

function showQnaOptionContent(optionContent) {
	$('#qnaOptionContent').text(optionContent);
}


function previewImages(event) {
	var files = event.target.files;

	// 이미 선택된 파일 수 체크
	var selectedFiles = $('#preview_container').find('.preview_item').length;

	// 최대 3개 제한
	if (selectedFiles + files.length > 3) {
		alert("최대 3개까지만 첨부할 수 있습니다.");
		return;
	}

	// 미리보기 컨테이너 초기화
	$('#preview_container').empty();

	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		var reader = new FileReader();

		reader.onload = function(e) {
			var img = $('<img>').attr('src', e.target.result);
			var removeBtn = $('<button type="button" class="remove_img">x</button>')
				.click(function() {
					$(this).parent().remove();
				});

			var preview = $('<div class="preview_item">').append(img).append(removeBtn);
			$('#preview_container').append(preview);
		};

		reader.readAsDataURL(file);
	}
}