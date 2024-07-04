$(function () {

	// 알림 숨겨놓기
	$(".alarm_container").hide();
	$("#alarmAll").removeClass("add");
	$("#alarmUnck").removeClass("add");

	$("#alarmDiv").on("click", alarmList); // 클릭하면 알림 보여주거나 숨기기
	$(".alarm_type").on("click", alarmClick); // 알림 종류 클릭 시 css 추가
	
	$("#alarmAll").on("click", alarmAll); // 전체 알림 보기
	$("#alarmUnck").on("click", alarmUnck); // 미확인 알림 보기
	
	$("#alarmImg").on("click", alarmCheck); // 알림 아이콘 클릭 시 애니메이션 효과 제거
	
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
		e.stopPropagation(); // 부모 요소는 실행되지 않고 자식 요소만 실행
                
	} else {
		$("#alarmUnck").addClass("add");
		$("#alarmAll").removeClass("add");
		e.stopPropagation(); // 부모 요소는 실행되지 않고 자식 요소만 실행
	}
}

// jsp에서 알림 리스트 전체 불러와서 hidden, 버튼 클릭할때 보여주는 방법 있음

// 전체 알림 보기
function alarmAll() {
    $.ajax({
        url : "/heehee/alarm/alarmAll",
		type : "GET",
		success : function(responseData) {
            console.log(responseData);

            // 알림창 길이 원래대로
			$(".alarm_container").removeClass("none");

            // 알림별로 경로 다르게 걸어줘야 함
            var output = "<div id='allAlarm' class='alarmClick'>";

            // 알림 리스트 있는지 체크하고 html 다르게 찍어주기
            if (responseData.length === 0 || responseData[0].alDate == null) {
                $(".alarm_container").addClass("none");

                output += "<p>" + "☑️ 최근 알림이 없습니다" + "</p>";

			} else {
				// 알림 전체 리스트 반복문
				$.each(responseData, function(index, item) {

					if (item.alDate != null && item.cateNum == 1) {
						// 채팅
						// output += "<ul onclick='urlClick(\"/heehee/chatting\")'>";
						// output += "<ul onclick='urlClick(\"/heehee/chatting\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
						// output += "<ul onclick='urlClick(\"/heehee/chatting\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/chatting\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
	
					} else if (item.alDate != null && item.cateNum == 2) {
						// 판매
						// output += "<ul onclick='urlClick(\"/heehee/mypage/saledetail/" + item.reqSeq + "\")'>";
						// output += "<ul onclick='urlClick(\"/heehee/mypage/saledetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/mypage/saledetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
	
					} else if (item.alDate != null && item.cateNum == 3) {
						// 경매
						// output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'>";
						// output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/mypage/main\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>"
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
	
					} else if (item.alDate != null && item.cateNum == 4) {
						// 문의
						// output += "<ul onclick='urlClick(\"/heehee/qnaBoard/" + item.reqSeq + "\")'>";
						// output += "<ul onclick='urlClick(\"/heehee/qnaBoard\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/mypage/qnaBoard\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
	
					} else if (item.alDate != null && item.cateNum == 5) {
						// 배송
						// output += "<ul onclick='urlClick(\"/heehee/purchasedetail/" + item.reqSeq + "\")'>";
						// output += "<ul onclick='urlClick(\"/heehee/purchasedetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/mypage/purchasedetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.alDate != null && item.cateNum == 6) {
						// 계정 정지
						output += "<ul alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.alDate != null && item.cateNum == 7) {
						// 중고물품 판매중지 (경로 수정 필)
						// output += "<ul onclick='urlClick(\"/heehee/mypage/saledetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/mypage/main\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>";
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
						
					} else if (item.alDate != null && item.cateNum == 8) {
						// 경매물품 판매중지 (경로 수정 필)
						// output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						
						output += "<ul onclick='urlClick(\"/heehee/mypage/main\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
						output += "<li class='alarm_date'>" + item.alDate + "</li>";
						output += "<li>" + item.sender + "</li>"
						output += "<li>" + item.alContent + "</li>";
						output += "</ul>";
					}
				});
            }
            output += "</div>";
			$("#here").html(output);
			
			// 알림 확인
			$("#allAlarm ul").on("click", alarmRead);
			
			// 확인한 알림 색상 변경
			alarmVisited();
		},
		error : function(data) {
			alert("알림 조회 오류 입니다");
        }
    });
}

// 미확인 알림 보기
function alarmUnck() {
    $.ajax({
        url : "/heehee/alarm/alarmUnck",
        type : "GET",
        success : function(responseData) {
        	console.log(responseData);

            // 알림창 길이 원래대로
            $(".alarm_container").removeClass("none");

            // 알림별로 경로 다르게 걸어줘야 함
            var output = "<div id='allAlarm' class='alarmClick'>";

            // 알림 리스트 있는지 체크하고 html 다르게 찍어주기
            if (responseData.length === 0 || responseData[0].alDate == null) {
                $(".alarm_container").addClass("none");

                output += "<p>" + "☑️ 최근 알림이 없습니다" + "</p>";

            } else {
            	// 알림 확인 시 상태값 N => Y, null 되므로 모두 확인하면 html 다르게 찍어주기 위해 모든 요소로 확인
                var check = responseData.every(data => data.alCheck == null);
                // console.log(check);
                
                if (check == true) {
                        $(".alarm_container").addClass("none");

                        output += "<p>" + "☑️ 최근 알림이 없습니다" + "</p>";

                    } else {
                    	// 알림 전체 리스트 반복문
                    	$.each(responseData, function(index, item) {

                        if (item.alDate != null && item.cateNum == 1 && item.alCheck == "N") {
                            // 채팅
                            // output += "<ul onclick='urlClick(\"/heehee/chatting\")'>";
                            // output += "<ul onclick='urlClick(\"/heehee/chatting\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
                            // output += "<ul onclick='urlClick(\"/heehee/chatting\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            
                            output += "<ul onclick='urlClick(\"/heehee/chatting\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            output += "<li class='alarm_date'>" + item.alDate + "</li>";
                            output += "<li>" + item.sender + "</li>";
                            output += "<li>" + item.alContent + "</li>";
                            output += "</ul>";

                        } else if (item.alDate != null && item.cateNum == 2 && item.alCheck == "N") {
                            // 판매
                            // output += "<ul onclick='urlClick(\"/heehee/saledetail/" + item.reqSeq + "\")'>";
                            // output += "<ul onclick='urlClick(\"/heehee/saledetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
                            
                            output += "<ul onclick='urlClick(\"/heehee/mypage/saledetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            output += "<li class='alarm_date'>" + item.alDate + "</li>";
                            output += "<li>" + item.sender + "</li>";
                            output += "<li>" + item.alContent + "</li>";
                            output += "</ul>";

                        } else if (item.alDate != null && item.cateNum == 3 && item.alCheck == "N") {
                            // 경매
                            // output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'>";
                            // output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
                            
                            output += "<ul onclick='urlClick(\"/heehee/mypage/main\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            output += "<li class='alarm_date'>" + item.alDate + "</li>";
                            output += "<li>" + item.sender + "</li>"
                            output += "<li>" + item.alContent + "</li>";
                            output += "</ul>";

                        } else if (item.alDate != null && item.cateNum == 4 && item.alCheck == "N") {
                            // 문의
                            // output += "<ul onclick='urlClick(\"/heehee/qnaBoard\")'>";
                            // output += "<ul onclick='urlClick(\"/heehee/qnaBoard/" + item.reqSeq + "\")'>";
                            // output += "<ul onclick='urlClick(\"/heehee/qnaBoard\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
                            
                            output += "<ul onclick='urlClick(\"/heehee/mypage/qnaBoard\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            output += "<li class='alarm_date'>" + item.alDate + "</li>";
                            output += "<li>" + item.sender + "</li>";
                            output += "<li>" + item.alContent + "</li>";
                            output += "</ul>";

                        } else if (item.alDate != null && item.cateNum == 5 && item.alCheck == "N") {
                            // 배송
                            // output += "<ul onclick='urlClick(\"/heehee/purchasedetail/" + item.reqSeq + "\")'>";
                            // output += "<ul onclick='urlClick(\"/heehee/purchasedetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + ">";
                            
                            output += "<ul onclick='urlClick(\"/heehee/mypage/purchasedetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            output += "<li class='alarm_date'>" + item.alDate + "</li>";
                            output += "<li>" + item.sender + "</li>";
                            output += "<li>" + item.alContent + "</li>";
                            output += "</ul>";
                            
                        } else if (item.alDate != null && item.cateNum == 6) {
                        	// 계정 정지
                        	output += "<ul alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                        	output += "<li class='alarm_date'>" + item.alDate + "</li>";
                        	output += "<li>" + item.sender + "</li>";
                        	output += "<li>" + item.alContent + "</li>";
                        	output += "</ul>";
							
                        } else if (item.alDate != null && item.cateNum == 7) {
                        	// 중고물품 판매중지 (경로 수정 필)
                        	// output += "<ul onclick='urlClick(\"/heehee/mypage/saledetail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                        	
                        	output += "<ul onclick='urlClick(\"/heehee/mypage/main\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                        	output += "<li class='alarm_date'>" + item.alDate + "</li>";
                        	output += "<li>" + item.sender + "</li>";
                        	output += "<li>" + item.alContent + "</li>";
                        	output += "</ul>";
							
                        } else if (item.alDate != null && item.cateNum == 8) {
                        	// 경매물품 판매중지 (경로 수정 필)
                        	// output += "<ul onclick='urlClick(\"/heehee/auc/detail\/" + item.reqSeq + "\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                        	
                        	output += "<ul onclick='urlClick(\"/heehee/mypage/main\")'" + " alNum=" + item.alNum + " alCheck=" + item.alCheck + ">";
                            output += "<li class='alarm_date'>" + item.alDate + "</li>";
                            output += "<li>" + item.sender + "</li>"
                            output += "<li>" + item.alContent + "</li>";
                            output += "</ul>";
                        }
                    });
                }
            }
            output += "</div>";
            $("#here").html(output);
            
            // 알림 확인
            $("#allAlarm ul").on("click", alarmRead);
            
            // 확인한 알림 색상 변경
            alarmVisited();
        },
        error : function(data) {
            alert("알림 조회 오류 입니다");
        }
    });
}

// 알림 확인
function alarmRead() {
	// var li = event.target.parentElement;
	
	var alNum = $(this).attr("alNum");
	console.log(alNum);
	
	$.ajax({
		url : "/heehee/alarm/alarmUpdate/" + alNum,
		type : "POST",
		success : function(responseData) {
			if (responseData == "1") {
				console.log("알림 확인");
				
			} else {
				console.log("이미 확인");
			}
		},
		error : function(data) {
			alert("알림 확인 오류 입니다");
		}
	});
}

// 알림 별 페이지 이동
function urlClick(url) {
	location.href = url;
}

// 확인한 알림 색상 변경
function alarmVisited() {
	var beforeCheck = $("#allAlarm").children("ul");

    for (let i = 0; i < beforeCheck.length; i++) {
    	// console.log("몇번타? : " + i);
    	
        if (beforeCheck.eq(i).attr("alCheck") == "Y") {
        	// console.log(i);
            beforeCheck.eq(i).addClass("visited");
        }
    }
}

// 알림 아이콘 클릭 시 애니메이션 효과 제거
function alarmCheck() {
	$("#alarmImg").removeClass("alarmImg");
	$("#alarmImg").attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_alarm_X.png");
}