$(function() {
	changeStatus('all');
	showUserInfo();
	$("#deleteJjim").hide();

	$("#deal_auc").hide();

	$("#btn_search").on("click", show);
	$(".mModal_close").on("click", hide);

	$("#btn_charge").on("click", chargePoint);
});
function changeDeal(deal) {
	if (deal === '중고물품') {
		$("#deal_sell").show();
		$("#deal_auc").hide();

		$("#sell").addClass("select_deal");
		$("#auction").removeClass("select_deal");

		changeStatus('all');
		$("#menu li").removeClass("select");
		$("#menu li").eq(0).addClass("select");
		$('#selectMenu').val("all");



	} else {
		$("#deal_auc").show();
		$("#deal_sell").hide();

		$("#auction").addClass("select_deal");
		$("#sell").removeClass("select_deal");

		changeStatus_auc('all');
		$("#menu_auc li").removeClass("select");
		$("#menu_auc li").eq(0).addClass("select");
		$('#selectMenuAuc').val("all");

	}
}
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

function changeMenu(event) {
	$("#menu li").removeClass("select");
	$(event.target).addClass("select");
}
function changeMenuAuc(event) {
	$("#menu_auc li").removeClass("select");
	$(event.target).addClass("select");
}

function changeStatus(status) {
	$(".list").empty();
	$(".sub_menu").show();
	$("#deleteJjim").hide();
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
				data.forEach(function(sale, index) {
					var detailUrl = '';
					if (sale.proStatus === '판매중' || sale.proStatus === '판매보류' || sale.proStatus === '판매중지') {
						detailUrl = "/heehee/sell/productdetail/" + sale.productSeq;
					} else {
						detailUrl = "/heehee/mypage/saledetail/" + sale.productSeq;
					}
					var dropdownId = 'dropdown_' + index;
					var subDropdownId = 'sub_dropdown_' + index;
					var statusChangeMenu = '';
					var productBanReason = '';
					if (sale.proStatus === '판매중') {
						statusChangeMenu = `
                            <img class="photo_status" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png" 
                                onclick="dropdown('${dropdownId}', this)">
                            <ul id="${dropdownId}" class="dropdown dropdown-menu" style="display: none;">
                                <li onclick="location.href='/heehee/sell/productmodify/${sale.productSeq}'">상품수정</li>
                                <li onclick="sub_dropdown('${subDropdownId}')">상태변경</li>
                                <li onclick="deleteSale(${sale.productSeq})">상품삭제</li>
                            </ul>
                            <ul id="${subDropdownId}" class="sub_dropdown sub-dropdown-menu" style="display: none;">
                                <li onclick="hideSubDropdown('${subDropdownId}')">취소</li>
                                <li onclick="updateStatus(${sale.productSeq},'판매중')" id="statusDropdown">판매보류</li>
                            </ul>`;
					} else if (sale.proStatus === '판매보류') {
						statusChangeMenu = `
                            <img class="photo_status" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png" 
                                onclick="dropdown('${dropdownId}', this)">
                            <ul id="${dropdownId}" class="dropdown dropdown-menu" style="display: none;">
                                <li onclick="sub_dropdown('${subDropdownId}')">상태변경</li>
                                <li onclick="deleteSale(${sale.productSeq})">상품삭제</li>
                            </ul>
                            <ul id="${subDropdownId}" class="sub_dropdown sub-dropdown-menu" style="display: none;">
                                <li onclick="hideSubDropdown('${subDropdownId}')">취소</li>
                                <li onclick="updateStatus(${sale.productSeq},'판매보류')" id="statusDropdown">판매중</li>
                            </ul>`;
					} else if (sale.proStatus === '판매중지') {
						productBanReason = `<div class="productBanReason"><p>판매중지</p></div>`;
					}

					// "판매중지" 상태일 때의 배경색 지정
					var productStyle = sale.proStatus === '판매중지' ? 'background-color: orangered;' : '';
					var imgClass = sale.proStatus === '판매중지' ? 'gray-scale' : '';
					
					var formattedPrice = new Intl.NumberFormat('ko-KR').format(sale.productPrice) + '원';

					output += `             
                        <div class="product">
                            <div>${statusChangeMenu}</div>
                            <div class="product_img_div" onclick="location.href='${detailUrl}'">
                                <div class="product_img ${imgClass}">
                                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
                                    ${productBanReason}
                                </div>              
                            </div>
                            <p>${sale.articleTitle}</p>
                            <p class="rankProdIntro">${sale.introduce}</p>
                            <p id="productPrice">${formattedPrice}</p>
                            <span id="s_statusVal_div"><p id="s_statusVal" style="${productStyle}">${sale.proStatus}</p></span>
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

function dropdown(dropdownId, imgElement) {
	var dropdownMenu = $("#" + dropdownId);
	var isVisible = dropdownMenu.is(":visible");

	$(".dropdown-menu").hide(); 
	$(".sub-dropdown-menu").hide();
	$(".photo_status").attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-status.png");

	if (!isVisible) {
		dropdownMenu.show();
		$(imgElement).attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-close.png");
	}
}

function sub_dropdown(subDropdownId) {
	$(".sub-dropdown-menu").hide(); 
	$("#" + subDropdownId).toggle();
}

function hideSubDropdown(subDropdownId) {
	$("#" + subDropdownId).hide();
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
			window.location.reload();
		},
		error: function(xhr, status, error) {
			console.error('판매상태 업데이트 오류:', error);
		}
	});
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
function showPurchaseList() {
	$(".list").empty();
	$(".sub_menu").hide();
	$("#deleteJjim").hide();
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
					var formattedPrice = new Intl.NumberFormat('ko-KR').format(purchase.productPrice) + '원';
					output += `
							<div class="product">
								<div class="product_img_div">
		                            <div class="product_img" onclick="location.href='/heehee/mypage/purchasedetail/${purchase.productSeq}'">
		                                <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${purchase.imgName}"> 
		                            </div>
		                        </div>
	                            	<p>${purchase.articleTitle}</p>
	                                <p class="rankProdIntro">${purchase.introduce}</p>
	                                <p id="productPrice">${formattedPrice}</p>
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
	$("#deleteJjim").show();
	$.ajax({
		url: '/heehee/mypage/main/jjimlist',
		method: 'POST',
		success: function(data) {
			var jjimlist = $('#jjimlist');
			console.log(data);
			var output = '';
			if (data.length === 0) {
				output = "<p class='message'>찜 내역이 없습니다.</p>";
			} else {
				data.forEach(function(jjim) {
					var formattedPrice = new Intl.NumberFormat('ko-KR').format(jjim.productPrice) + '원';
					output += `
                            <div class="product">
                            	<div class="product_img_div">
                               		<input type="checkbox" name="checkBno" value="${jjim.productSeq}"> 
                                	<div class="product_img" onclick="location.href='/heehee/sell/productdetail/${jjim.productSeq}'">
                                    	<img class="product_img"
                                        src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${jjim.imgName}">
                                    </div>
                                </div>   
                                    <p>${jjim.articleTitle}</p>
                                    <p class="rankProdIntro">${jjim.introduce}</p>
                                    <p id="productPrice">${formattedPrice}</p>
                                
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
			showTost('선택한 상품을 삭제했습니다.');
			showJjimList(); // 삭제 후 목록을 다시 불러옴
		},
		error: function(xhr, status, error) {
			alert('삭제 중 오류가 발생했습니다.');
		}
	});
}
//경매페이지
function changeStatus_auc(status) {
	$(".list").empty();
	$(".sub_menu").show();
	$("#deleteJjim").hide();
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
					var formattedPrice = new Intl.NumberFormat('ko-KR').format(sale.aucPrice) + '원';
					var productStyle = sale.aucStatus === '판매중지' ? 'background-color: orangered;' : '';
					var imgClass = sale.aucStatus === '판매중지' ? 'gray-scale' : '';
					var detailUrl = (sale.aucStatus === '낙찰' || sale.aucStatus === '거래완료')? '/heehee/mypage/saledetailAuc/' + sale.productSeq : '/heehee/auc/detail/' + sale.productSeq;
					// 가격 표시를 조건부로 설정
					var priceDisplay = (sale.aucStatus === '낙찰' || sale.aucStatus === '거래 완료') ? `<p id="productPrice">${formattedPrice}</p>` : '';
					var productBanReason = sale.aucStatus === '판매중지' ? `<div class="productBanReason"><p>판매중지</p></div>` : '';
					output += `         
					 		 <div class="product">                
                           		 <div class="product_img_div" onclick="location.href='${detailUrl}'">
                                	<div class="product_img ${imgClass}">
                                   		 <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${sale.imgName}">
                                   		 ${productBanReason}
                                	</div>     
                                </div>
                                <p>${sale.auctionTitle}</p>
                                <p class="rankProdIntro">${sale.introduce}</p>
                                ${priceDisplay}
                                <span id="s_statusVal_div"><p id="s_statusVal" style="${productStyle}">${sale.aucStatus}</p></span>
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
	$("#deleteJjim").hide();
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
					var formattedPrice = new Intl.NumberFormat('ko-KR').format(purchase.aucPrice) + '원';
					output += `
							<div class="product">   
                           		 <div class="product_img_div" onclick="location.href='/heehee/mypage/purchasedetailAuc/${purchase.productSeq}'">
                            		<div class="product_img">
                                		<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${purchase.imgName}">
                                	</div>
                               	 </div>
                                <p>${purchase.auctionTitle}</p>
                                 <p class="rankProdIntro">${purchase.introduce}</p>
                                 <p id="productPrice">${formattedPrice}</p>
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

