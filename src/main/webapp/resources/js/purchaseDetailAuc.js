$(function() {
	var dNumberText = $("#dNumber").text().trim();
	var proStatus = $("#proStatus").text().trim();
	var pCheck = $("#pCheck").text().trim();
	var dStatus = $("#dStatus").text().trim();
	var rateSeq = $("#rateSeq").text().trim();




	// 진행 상황에 따라 progress value 값 조절 
	// '결제완료(0%)': 거래내역 있을 때

	//'거래완료(100%)': proStatus가 '거래완료'일 때
	if (proStatus === '거래완료') {
		$("#graph").attr('value', 100);
	}

	// '배송완료(75%)': 택배 배송이 완료되었을 때 + 구매 확정날짜가 있을 때
	else if (pCheck != '' || dStatus === '배송완료') {
		$("#graph").attr('value', 75);
	}
	//'배송완료(50%)': dStatus가 '배송중'일때 
	else if (dStatus === '배송중') {
		$("#graph").attr('value', 50);
	}
	//'발송완료(25%)': 송장을 입력했을 때(송장 내역이 있을 때)
	else if (dNumberText != '') {
		$("#graph").attr('value', 25);
	}



	// 판매 확정날짜가 있으면 '거래완료' 버튼 안 보임
	if (pCheck != '' || proStatus === '판매중지') {
		$("#complete").hide();
	}
	if (proStatus === '거래완료' && rateSeq === '') {
		$("#accuracy").show();
	}



	$(".auc-chat").on("click", function() {
		if (checkNick == '') { openLogin(); return false; }
		const loginUserId = $(".auc-chat").attr("loginUserId");
		const sellerId = $(".auc-chat").attr("sellerId");
		const sellSeq = $(".auc-chat").attr("sellSeq");

		aucChat(loginUserId, sellerId, sellSeq);
	});


});

function aucChat(loginUserId, sellerId, sellSeq) {
alert(loginUserId);
alert(sellerId);
alert(sellSeq);
	fetch("/heehee/chatting/auction", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({
			"loginUserId": loginUserId,
			"sellerId": sellerId,
			"aucSeq": sellSeq
		})
	})
		.then(resp => resp.text())
		.then(result => {
			console.log(result);
			if (result > 0) {
				window.location.href = "/heehee/chatting";
			}
		})
		.catch(err => console.log(err));
}

function updatePCheck(proSeq) {
	$.ajax({
		url: '/heehee/mypage/purchasedetail/updatePCheckAuc',
		method: 'POST',
		data: { proSeq: proSeq },
		success: function(data) {
			console.log(data);
			if (data.success == true) {
				showTost(data.message);

			} else {
				showTost(data.message);
			}
			setTimeout(() => window.location.reload(), 1000);
		}, error: function(data, status, err) {
			console.log(err);
			showTost(data.message);
		}
	});

}

