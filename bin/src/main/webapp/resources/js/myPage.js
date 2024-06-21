$(function() {
	$("#purchaselist").hide();
	$("#jjimlist").hide();
	$("#btn_search").on("click", show);
	$(".mModal_close").on("click", hide);
	$(".menu li").on("click", changeMenu);

});
function show() {
	$("#search").addClass("show");
}
function hide() {
	$("#search").removeClass("show");
}
function changeMenu() {
	$(".menu li").removeClass("select");
	$(this).addClass("select");

	if ($(this).text() == "판매내역") {
		$(".list").hide();
		$(".sub_menu").show();
		$("#salelist").show();
	} else if ($(this).text() == "구매내역") {
		$(".list").hide();
		$(".sub_menu").hide();
		$("#purchaselist").show();
	} else {
		$(".list").hide();
		$("#jjimlist").show();
	}

}

function changeStatus(id,status) {
	$(".sub_menu li").removeClass("select_sub");
	$(this).addClass("select_sub");

		$.ajax({
		url: '${path}/myPage/' + id +'searchSaleStatus',
		method: 'GET',
		data: { 'status': status,
				'id': id},
		success: function(data) {
			var salelist = $('#salelist');
			data.forEach(function(sale) {
				if (data.length === 0) {
					salelist.html="<p class='message'>최근 판매 내역이 없습니다.</p>";
				} else {
					const detailUrl = sale.proStatus === '판매중' ? `${path}/productdetail/${sale.productSeq}` : `${path}/saledetail/${sale.productSeq}`;
					salelist.html=`
                            <div class="product" onclick="location.href='${detailUrl}'">
                                <div class="product_slider">
                                    <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
                                </div>
                                <p>${sale.articleTitle}</p>
                                <p>${sale.productPrice}</p>
                                <p id="s_statusVal">${sale.proStatus}</p>
                            </div>`;
				}
			});
		},
		error: function(xhr, status, error) {
			alert('데이터를 가져오는 중 오류가 발생했습니다.');
		}
	});
	
	
}