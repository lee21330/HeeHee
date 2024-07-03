$(function() {
	changeStatus('all');
	changeStatus_auc('all');
	showUserInfo();
	$("#deal_auc").hide();
	$("#btn_search").on("click", show);
	$(".mModal_close").on("click", hide);
	$(".menu li").on("click", changeMenu);
	$(".sub_menu li").on("click", changeSubMenu);
	$("#btn_charge").on("click", chargePoint);
});
function show() {
	$("#search").addClass("show");
}
function hide() {
	$("#search").removeClass("show");
}
function showUserInfo() {
	//평점 별로 표현
	var userRating = parseInt($('#myRating').text(), 10);
	var stars = $('.star');

	for (var i = 0; i < userRating; i++) {
		stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
	}
}
function changeMenu() {
	$(".menu li").removeClass("select");
	$(this).addClass("select");
}
function changeSubMenu() {
	$(".sub_menu li").removeClass("select_sub");
	$(this).addClass("select_sub");
}

function changeStatus(status) {
	$(".list").empty();
	$(".sub_menu").show();
	$.ajax({
		url: '/heehee/mypage/main/searchSaleStatus',
		method: 'GET',
		data: { 'status': status },
		success: function(data) {
			var salelist = $('#salelist');
			console.log(data);
			var output = '';
			if (data.length === 0) {
				output = "<p class='message'>해당 판매 내역이 없습니다.</p>";
			} else {
				data.forEach(function(sale) {
					var detailUrl = '';

					// 판매상태에 따라 페이지 다르게
					if (sale.proStatus === '판매중') {
						detailUrl = "/heehee/sell/productdetail/" + sale.productSeq;
					} else if (sale.proStatus === '판매보류') {
						detailUrl = 'javascript:void(0);'; // 판매보류일 경우, 클릭 이벤트를 비활성화
					} else {
						detailUrl = "/heehee/mypage/saledetail/" + sale.productSeq;
					}


					var statusChangeMenu = '';
					if (sale.proStatus === '판매중') {
						statusChangeMenu = ` <img class="photo_status" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png" 
                                	onclick="dropdown()">
                        					<ul id="dropdown">
                                				<li onclick="location.href='/heehee/sell/productmodify/${sale.productSeq}'">상품수정</li>
                                				<li onclick="sub_dropdown()">상태변경 </li>
                                				<li onclick="deleteSale(${sale.productSeq})">상품삭제</li>
                            				</ul>
                            				<ul id="sub_dropdown">
                            					<li onclick="hideSubDropdown()">취소</li>
                            					<li onclick="updateStatus(${sale.productSeq},'${sale.proStatus}')" id="statusDropdown">판매보류</li>
					                        </ul>`;
					} else if (sale.proStatus === '판매보류') {
						statusChangeMenu = ` <img class="photo_status" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png" 
                                	onclick="dropdown()">
                        					<ul id="dropdown">
                                				<li onclick="sub_dropdown()">상태변경</li>
                                				<li onclick="deleteSale(${sale.productSeq})">상품삭제</li>
                            				</ul>
                            				<ul id="sub_dropdown">
                            					<li onclick="hideSubDropdown()">취소</li>
                            					<li onclick="updateStatus(${sale.productSeq},'${sale.proStatus}')" id="statusDropdown">판매중</li>
					                        </ul>`;
					}

					output += `				
                            <div class="product">
                                <div>${statusChangeMenu}</div>
                                <div onclick="location.href='${detailUrl}'">
                                	<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}" >
                                	<p>${sale.articleTitle}</p>
                                	<p>${sale.productPrice}</p>
                                	<p id="s_statusVal">${sale.proStatus}</p>
                                </div>
                            </div>`;
				});
			}
			salelist.html(output);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}

function dropdown() {
	$("#dropdown").show();
}

function sub_dropdown() {
	$("#sub_dropdown").show();
}
function hideSubDropdown() {
	$("#sub_dropdown").hide();
}

function deleteSale(productSeq) {
	$.ajax({
		url: '/heehee/mypage/main/deleteSale',
		method: 'POST',
		data: { 'productSeq': productSeq },
		success: function(data, status, xhr) {
			if (data.success) {
				showTost(data.message); // 성공 메시지를 표시
			} else {
				showTost(data.message); // 실패 메시지를 표시
			}

		},
		error: function(xhr, status, error) {
			console.error('판매 삭제 오류:', error);

		}
	});
}
function updateStatus(productSeq, proStatus) {
	$.ajax({
		url: '/heehee/mypage/main/updateStatus',
		method: 'POST',
		data: { 'productSeq': productSeq, 'proStatus': proStatus },
		success: function(data, status, xhr) {
			if (data.success) {
				showTost(data.message); // 성공 메시지를 표시
			} else {
				showTost(data.message); // 실패 메시지를 표시
			}

		},
		error: function(xhr, status, error) {
			console.error('판매상태 업데이트 오류:', error);

		}
	});
}
function showPurchaseList() {
	$(".list").empty();
	$(".sub_menu").hide();
	$.ajax({
		url: '/heehee/mypage/main/purchaselist',
		method: 'POST',
		success: function(data) {
			var purchaselist = $('#purchaselist');
			console.log(data);
			var output = '';
			if (data.length === 0) {
				output = "<p class='message'>해당 구매 내역이 없습니다.</p>";
			} else {

				data.forEach(function(purchase) {
					output += `
                            <div class="product" onclick="location.href='/heehee/mypage/purchasedetail/${purchase.productSeq}'">
								<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${purchase.imgName}">
								<p>${purchase.articleTitle}</p>
								<p>${purchase.productPrice}</p>
							</div>`;
				});
			}

			purchaselist.html(output);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}

function showJjimList() {
	$(".list").empty();
	$(".sub_menu").hide();
	$.ajax({
		url: '/heehee/mypage/main/jjimlist',
		method: 'POST',
		success: function(data) {
			var jjimlist = $('#jjimlist');
			console.log(data);
			var output = '<div id="deleteJjim"><input type="button" value="삭제" class="btn" onclick="deleteJjim()"></div>';
			if (data.length === 0) {
				output = "<p class='message'>찜 내역이 없습니다.</p>";
			} else {

				data.forEach(function(jjim) {
					output += `
                            <div class="product">
								<input type="checkbox" name="checkBno" value="${jjim.productSeq}"> 
								<div onclick="location.href='/heehee/sell/productdetail/${jjim.productSeq}'">
									<img class="product_img"
										src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${jjim.imgName}">
									<p>${jjim.articleTitle}</p>
									<p>${jjim.productPrice}</p>
								</div>
							</div>`;
				});
			}
			jjimlist.html(output);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}
function deleteJjim() {
	var seqList = [];
	$('input[name="checkBno"]:checked').each(function() {
		seqList.push($(this).val()); // 선택된 체크박스의 값(proSeq)을 배열에 추가
	});

	if (seqList.length === 0) {
		alert('삭제할 상품을 선택해주세요.');
		return;
	}

	$.ajax({
		url: '/heehee/mypage/main/deleteJjim',
		method: 'POST',
		traditional: true, // jQuery에서 배열 파라미터를 전송할 때 필요한 옵션
		data: { seq: seqList },
		success: function(response) {
			alert('선택한 상품을 삭제했습니다.');
			showJjimList(); // 삭제 후 목록을 다시 불러옴
		},
		error: function(xhr, status, error) {
			alert('삭제 중 오류가 발생했습니다.');
		}
	});
}
function changeDeal(deal) {
	if (deal === '중고물품') {
		$("#deal_sell").show();
		$("#deal_auc").hide();
	} else {
		$("#deal_auc").show();
		$("#deal_sell").hide();
	}
}



//경매페이지
function changeStatus_auc(status) {
	$(".list").empty();
	$(".sub_menu").show();
	$.ajax({
		url: '/heehee/mypage/main/searchSaleStatusAuc',
		method: 'GET',
		data: { 'status': status },
		success: function(data) {
			var salelist = $('#salelist_acu');
			console.log(data);
			var output = '';
			if (data.length === 0) {
				output = "<p class='message'>해당 판매 내역이 없습니다.</p>";
			} else {

				data.forEach(function(sale) {
					var detailUrl = sale.proStatus === '입찰' ? '/heehee/auc/detail/' + sale.productSeq : '/heehee/mypage/saledetailAuc/' + sale.productSeq;
					output += `							
                            <div class="product" onclick="location.href='${detailUrl}'">
                                <div class="product_slider">
                                    <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${sale.imgName}">
                                </div>
                                <p>${sale.auctionTitle}</p>
                                <p>${sale.aucPrice}</p>
                                <p id="s_statusVal">${sale.aucStatus}</p>
                            </div>`;
				});
			}
			salelist.html(output);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}


function showPurchaseList_auc() {
	$(".list").empty();
	$(".sub_menu").hide();
	$.ajax({
		url: '/heehee/mypage/main/purchaselistAuc',
		method: 'POST',
		success: function(data) {
			var purchaselist = $('#purchaselist_acu');
			console.log(data);
			var output = '';
			if (data.length === 0) {
				output = "<p class='message'>해당 구매 내역이 없습니다.</p>";
			} else {

				data.forEach(function(purchase) {
					output += `
                            <div class="product" onclick="location.href='/heehee/mypage/purchasedetail/${purchase.productSeq}'">
								<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${purchase.imgName}">
								<p>${purchase.articleTitle}</p>
								<p>${purchase.productPrice}</p>
							</div>`;
				});
			}

			purchaselist.html(output);
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
}



// 충전하기 모달
function chargePoint(point) {
	var amount = $("#charge").val();
	payForPoint("포인트 충전", amount, point);
}

function payForPoint(payName, amount, point) {
	$.ajax({
		url: '/heehee/pay/before',
		method: 'POST',
		data: {
			"payName": payName,
			"amount": amount
		},
		success: function(data, status, xhr) {
			payment(payName,amount, data, point);
			return true;
		},
		error: function(data, status, err) {
			console.log(err);
		}
	});
}

function payment(payName, newPoint, payInfo, point) {
	IMP.init("imp22447463");
	IMP.request_pay(
		{
			pg: "html5_inicis.INIpayTest", //테스트 시 html5_inicis.INIpayTest 기재
			pay_method: "card",
			merchant_uid: payInfo.paySeq, //상점에서 생성한 고유 주문번호
			name: payName,
			amount: 1,
			buyer_email: payInfo.buyerEmail,
			buyer_name: payInfo.buyerId,
			buyer_tel: payInfo.buyerTel, //필수 파라미터 입니다.
			buyer_addr: payInfo.buyerTel,
			buyer_postcode: "123-456",
			m_redirect_url: "{모바일에서 결제 완료 후 리디렉션 될 URL}",
			escrow: true, //에스크로 결제인 경우 설정
			vbank_due: "20240725",
			bypass: {
				acceptmethod: "noeasypay", // 간편결제 버튼을 통합결제창에서 제외(PC)
				P_RESERVED: "noeasypay=Y", // 간편결제 버튼을 통합결제창에서 제외(모바일)
			},
			period: {
				from: "20240101", //YYYYMMDD
				to: "20241231", //YYYYMMDD
			},
		}, function(rsp) {
			if (rsp.success) {
				completePayment(payInfo.paySeq, newPoint, point);
				return true;
			}
		}
	);
}

function completePayment(paySeq,newPoint, point) {
	$.ajax({
        url: '/heehee/pay/complete',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ "paySeq": paySeq }),
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);
            fetch("/heehee/mypage/chargePoint",{
                method : "PUT",
                headers : {"Content-Type": "application/json"},
                body : JSON.stringify({
                "point" : newPoint,
                "userPoint" : point
                })
            })
            .then(resp => resp.text())
            .then(result => console.log(result))
            .catch(err => console.log(err));
            return true;
        },
        error: function (data, status, err) {
            console.log(err);
        }
    });
    window.location.reload();
}