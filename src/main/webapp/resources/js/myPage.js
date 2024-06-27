$(function() {
	changeStatus('all');
	showUserInfo();
	$("#btn_search").on("click", show);
	$(".mModal_close").on("click", hide);
	$(".menu li").on("click", changeMenu);
	$(".sub_menu li").on("click", changeSubMenu);

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

	//포인트 값 가독성 높이기
	var userPoint = parseInt($('#userPoint').text(), 10);
	$('#userPoint').text(userPoint.toLocaleString());
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
					var detailUrl = sale.proStatus === '판매중' ? `/heehee/productdetail/${sale.productSeq}` : `/heehee/mypage/saledetail/${sale.productSeq}`;
					output += `							
                            <div class="product" onclick="location.href='${detailUrl}'">
                                <div class="product_slider">
                                    <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
                                </div>
                                <p>${sale.articleTitle}</p>
                                <p>${sale.productPrice}</p>
                                <p id="s_statusVal">${sale.proStatus}</p>
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
                            <div class="product" onclick="location.href='/heehee/purchasedetail/${purchase.productSeq}'">
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
			var output = '<input type="submit" value="삭제" class="btn">';
			if (data.length === 0) {
				output = "<p class='message'>찜 내역이 없습니다.</p>";
			} else {

				data.forEach(function(jjim) {
					output += `
					<input type="checkbox" name="checkBno" value=""> 
                            <div class="product"
							onclick="location.href='/heehee/productdetail/${jjim.productSeq}'">
							<img class="product_img"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${jjim.imgName}">
							<p>${jjim.articleTitle}</p>
							<p>${jjim.productPrice}</p>
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

