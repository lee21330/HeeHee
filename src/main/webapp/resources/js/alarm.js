$(function () {

	// 알림 숨겨놓기
	$(".alarm_container").hide();
	$("#alarmAll").removeClass("add");
	$("#alarmUnck").removeClass("add");

	$("#alarmDiv").on("click", alarmList); // 클릭하면 알림 보여주거나 숨기기
	$(".alarm_type").on("click", alarmClick); // 알림 종류 클릭 시 css 추가
	
});

// 클릭하면 알림 보여주거나 숨기기
function alarmList() {
// $(".alarm_container").toggle();
            
	if ($(".alarm_container").css("display") == "none") {
		$("#alarmAll").addClass("add"); // default 전체조회
		$(".alarm_container").show();
                
	} else {
		$(".alarm_container").scrollTop(0);
		$("#alarmUnck").removeClass("add"); // default 전체조회
		$(".alarm_container").hide();
	}
}

// 알림 종류 클릭 시 css 추가
function alarmClick(e) {
	if (e.target.id == "alarmAll") {
		$("#alarmAll").addClass("add");
		$("#alarmUnck").removeClass("add");
		// $(".alarm_container").removeClass("none"); // 높이 변경 확인용
		e.stopPropagation(); // 부모 요소는 실행되지 않고 자식요소만 실행
                
	} else {
		$("#alarmUnck").addClass("add");
		$("#alarmAll").removeClass("add");
		// $(".alarm_container").addClass("none"); // 높이 변경 확인용
		e.stopPropagation(); // 부모 요소는 실행되지 않고 자식요소만 실행
	}
}