$(function() {
	var dNumberText = $("#dNumber").text().trim();
	var dealText = $("#deal").text().trim();
	var proStatus = $("#proStatus").text().trim();
	var sCheck = $("#sCheck").text().trim();

	/* 예약중인 상품이고 거래방식이 택배이고 송장 내역 없으면 '송장 입력하기' 버튼 보임 */
	if (dealText === '택배' && dNumberText === '' && proStatus === '예약중') {
		$("#enter_invoice").show();
	}

	/* 거래방식(deal)에 따라 progress_ing가 다르게 보임*/
	$(".progress_ing").hide();
	if (dealText === '택배') {

		// 택배
		$("#deliveryText").show();

		// 진행 상황에 따라 progress value 값 조절 
		//'결제완료(0%)': 거래내역 있을 때

		//'발송완료(25%)': 송장을 입력했을 때(송장 내역이 있을 때)
		if (dNumberText != '') {
			$("#graph").attr('value', 25);
		}


		//'거래완료(100%)': proStatus가 '거래완료'일 때
		else if (proStatus === '거래완료') {
			$("#graph").attr('value', 100);
		}

	} else {

		// 직거래 

		$("#directText").show();

		// 진행 상황에 따라 progress value 값 조절 
		//'거래완료(100%)': proStatus가 '거래완료'일 때
		if (proStatus === '거래완료') {
			$("#graph").attr('value', 100);

		}
	}

	// 판매 확정날짜가 있으면 '거래완료' 버튼 안 보임
	if (sCheck != '' || proStatus === '판매중지') {
		$("#complete").hide();
	}


});

function updateSCheck(proSeq) {
	$.ajax({
		url: '/heehee/mypage/saledetail/${saleDetail.productSeq}/updateSCheck',
		method: 'POST',
		data: { 'proSeq': proSeq }
	});
	$("#graph").attr('value', 75);
}

