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

let imageFiles = []; // 이미지 파일을 저장할 배열
const MAX_IMAGE_COUNT = 3; // 최대 이미지 개수 설정

function handleFiles(event) {
	const files = event.target.files;
	const previewContainer = document.getElementById('image_preview');

	if (files && files.length > 0) {
		// 이미지 파일을 최대 MAX_IMAGE_COUNT(3)개까지만 처리
		const filesToUpload = Array.from(files).slice(0, MAX_IMAGE_COUNT);

		// 이미지 개수 초과 시 경고 창 표시
		if (files.length > MAX_IMAGE_COUNT) {
			alert(`이미지는 최대 ${MAX_IMAGE_COUNT}개까지 선택할 수 있습니다.`);
		}

		// 기존에 미리보기한 이미지 제거
		previewContainer.innerHTML = '';
		imageFiles = []; // 이미지 파일 배열 초기화

		// 각 이미지에 대해 미리보기 처리
		filesToUpload.forEach((file, index) => {
			const reader = new FileReader();

			reader.onload = function(e) {
				const imgElement = document.createElement('img');
				imgElement.src = e.target.result;
				imgElement.classList.add('img_preview');
				previewContainer.appendChild(imgElement);

				// 이미지 파일 배열에 추가
				imageFiles.push(file);

				// 미리보기 이미지에 클릭 이벤트 추가하여 삭제 기능 구현
				imgElement.onclick = function() {
					removeImage(imgElement);
				};
			}

			reader.readAsDataURL(file);
		});
	}
}

// 미리보기에서 이미지 삭제
function removeImage(imgElement) {
	const index = Array.from(imgElement.parentNode.children).indexOf(imgElement);
	if (index !== -1) {
		// 배열에서 해당 인덱스의 이미지 파일 제거
		imageFiles.splice(index, 1);
		// 미리보기 화면에서 해당 이미지 제거
		imgElement.parentNode.removeChild(imgElement);
	}
}