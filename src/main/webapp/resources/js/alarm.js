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

	$.ajax({
			url : "/heehee/alarm/alarmAll",
			type : "get",
			success : function(responseData) {
				// alert("성공");
				console.log(responseData);
				
				// 알림별로 경로 다르게 걸어줘야 함
				var output = "<a href='/heehee/chatting'>";
				
				// 리스트 비어있는지 확인 후 html 다르게 찍어주기 (전체라서 굳이?)
				// if (responseData.length != 0) {
				
					// 알림 리스트 반복문
					$.each(responseData, function(index, item) {
						output += "<ul>";
						output += "<li class='alarm_date'>" + item.sendTime + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
					});
					
					output += "</a>";
					
					$("#here").html(output);
				
				// }
			},
			error : function(data) {
				alert("실패");
			}
		});
            
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
		e.stopPropagation(); // 부모 요소는 실행되지 않고 자식 요소만 실행
                
	} else {
		$("#alarmUnck").addClass("add");
		$("#alarmAll").removeClass("add");
		// $(".alarm_container").addClass("none"); // 높이 변경 확인용
		e.stopPropagation(); // 부모 요소는 실행되지 않고 자식 요소만 실행
	}
}