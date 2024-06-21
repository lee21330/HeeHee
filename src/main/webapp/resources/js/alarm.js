$(function () {

	// 알림 숨겨놓기
	$(".alarm_container").hide();
	$("#alarmAll").removeClass("add");
	$("#alarmUnck").removeClass("add");

	$("#alarmDiv").on("click", alarmList); // 클릭하면 알림 보여주거나 숨기기
	$(".alarm_type").on("click", alarmClick); // 알림 종류 클릭 시 css 추가
	
	$("#alarmAll").on("click", alarmAll); // 전체 알림 보기
	$("#alarmUnck").on("click", alarmUnck); // 미확인 알림 보기
	
});

// 클릭하면 알림 보여주거나 숨기기
function alarmList() {
// $(".alarm_container").toggle();
    
	if ($(".alarm_container").css("display") == "none") {
		$("#alarmUnck").addClass("add"); // default 미확인 알림
		$(".alarm_container").show();
		
		alarmUnck();
                
	} else {
		$(".alarm_container").scrollTop(0);
		$("#alarmAll").removeClass("add"); // default 미확인 알림
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

// 전체 알림 보기
function alarmAll() {
	$.ajax({
		url : "/heehee/alarm/alarmAll",
		type : "get",
		success : function(responseData) {
			// alert("성공");
			console.log(responseData);
			
			// 알림창 길이 원래대로
			$(".alarm_container").removeClass("none");
				
			// 알림별로 경로 다르게 걸어줘야 함
			var output = "<div>";
				
			// 알림 리스트 있는지 체크하고 html 다르게 찍어주기
			if (responseData.length === 0) {
				$(".alarm_container").addClass("none");
			
				var output = "<div>";
				output += "<p>" + "☑️ 최근 알림이 없습니다" + "</p>";
			
			} else {
				// 알림 전체 리스트 반복문
				$.each(responseData, function(index, item) {
						
					if (item.cateNum == 1) {
						// 채팅
						output += "<ul onclick='urlClick(\"/heehee/chatting\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
							
					} else if (item.cateNum == 2) {
						// 판매
						output += "<ul onclick='urlClick(\"/heehee/saledetail/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.cateNum == 3) {
						// 경매
						output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.cateNum == 4) {
						// 문의
						// output += "<ul onclick='urlClick(\"/heehee/qnaBoard\")'>";
							
						output += "<ul onclick='urlClick(\"/heehee/qnaBoard/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.cateNum == 5) {
						// 배송
						output += "<ul onclick='urlClick(\"/heehee/purchasedetail/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else {
						alert("전체 알림 조회 오류");
					}
				});
					
				output += "</div>";
				$("#here").html(output);
			} 
		},
		error : function(data) {
			alert("실패");
		}
	});
}

// 미확인 알림 보기
function alarmUnck() {
	$.ajax({
		url : "/heehee/alarm/alarmUnck",
		type : "get",
		success : function(responseData) {
			// alert("성공");
			console.log(responseData);
			
			// 알림창 길이 원래대로
			$(".alarm_container").removeClass("none");
				
			// 알림별로 경로 다르게 걸어줘야 함
			var output = "<div>";
				
			// 알림 리스트 있는지 체크하고 html 다르게 찍어주기
			if (responseData.length === 0) {
				$(".alarm_container").addClass("none");
			
				var output = "<div>";
				output += "<p>" + "☑️ 최근 알림이 없습니다" + "</p>";
			
			} else {
				// 알림 전체 리스트 반복문
				$.each(responseData, function(index, item) {
					if (item.cateNum == 1) {
						// 채팅
						output += "<ul onclick='urlClick(\"/heehee/chatting\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
							
					} else if (item.cateNum == 2) {
						// 판매
						output += "<ul onclick='urlClick(\"/heehee/saledetail/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.cateNum == 3) {
						// 경매
						output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.cateNum == 4) {
						// 문의
						// output += "<ul onclick='urlClick(\"/heehee/qnaBoard\")'>";
							
						output += "<ul onclick='urlClick(\"/heehee/qnaBoard/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.cateNum == 5) {
						// 배송
						output += "<ul onclick='urlClick(\"/heehee/purchasedetail/" + item.reqSeq + "\")'>";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} 
			
					// 예외처리 해야되는데 에러...		
					/*
					else {
						$(".alarm_container").addClass("none");
			
						var output = "<div>";
						output += "<p>" + "☑️ 최근 알림이 없습니다" + "</p>";
					
					}
					*/
					
					output += "</div>";
					$("#here").html(output);
					
				});
			} 
		},
		error : function(data) {
			alert("실패");
		}
	});
}

// 알림 별 페이지 이동
function urlClick(url) {
	location.href = url;
}

// 확인한 알림은 색상 변경해야 함 (방문했던 링크는 색상 변경되도록)