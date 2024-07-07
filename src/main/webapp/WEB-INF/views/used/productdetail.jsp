<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>판매 제품 상세페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%-- slick slider --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />

<link rel="stylesheet" href="/heehee/resources/css/productdetail.css">

</head>
<%@ include file="../common/header.jsp"%>
<body>
	<script src="/heehee/resources/js/product.js"></script>
	<div class="productDetail">
		<main>
			<div class="product-container">
				<c:if test="${userId == info.id && info.proStatus == '판매중'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<img class="product_img"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${userId == info.id && info.proStatus == '판매중지'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">판매 중지된 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${userId == info.id && info.proStatus == '판매보류'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">판매보류된 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if
					test="${userId == info.id && info.proStatus == '예약중'  && info.deal == '직거래'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">예약중인 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if
					test="${userId == info.id && info.proStatus == '예약중'  && info.deal == '택배'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">결제가 완료된 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${userId != info.id && info.proStatus == '판매중'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${userId != info.id && info.proStatus == '판매중지'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">판매 중지된 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${userId != info.id && info.proStatus == '판매보류'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">판매 보류된 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if
					test="${userId != info.id && info.proStatus == '예약중' && info.deal == '택배'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">결제가 완료된 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if
					test="${userId != info.id && info.proStatus == '예약중' && info.deal == '직거래'}">
					<div class="product_slider">
						<c:forEach var="product" items="${prodImgList}">
							<div class="product_item">
								<div id="overlay">
									<p id="postpone">예약중인 상품입니다.</p>
								</div>
								<img class="product_img"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<div class="product-details">
					<div class="title-container">
						<p id="product_category">${info.category}>
							${info.detailCategory} (${info.prodName})</p>
						<img id="url_copy"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/linkcopy.png"
							alt="Copy URL" style="cursor: pointer">
					</div>

					<div class="title-container">

						<p id="product_name">${info.articleTitle}</p>
						<!-- <a href="#" class="price-check-link">시세조회</a> -->

					</div>
					<p id="product_price">
						<fmt:formatNumber value="${info.productPrice}" pattern="#,###" />
						원
					</p>
					<p id="product_etc">
						<fmt:formatDate value="${info.createDate}" pattern="yyyy/MM/dd"
							type="date" />
						· 조회 ${info.viewCnt} · 찜 <span id="jjim_Cnt">${info.jjimCnt}</span>
						<c:if test="${userId == info.id}">
							<span id="fullHeart" style="display: none">❤️</span>
							<span id="emptyHeart" style="display: none">🤍</span>
						</c:if>
						<c:if test="${userId == 'admin'}">
							<span id="fullHeart" style="display: none">❤️</span>
							<span id="emptyHeart" style="display: none">🤍</span>
						</c:if>
						<c:if
							test="${userId != 'admin' && userId != info.id && info.specifiedJjimCnt >= 1}">
							<span id="fullHeart">❤️</span>
							<span id="emptyHeart" style="display: none">🤍</span>
						</c:if>
						<c:if
							test="${userId != 'admin' && userId != info.id && info.specifiedJjimCnt == 0}">
							<span id="fullHeart" style="display: none">❤️</span>
							<span id="emptyHeart">🤍</span>
						</c:if>
					</p>
					<ul id="product_state">
						<li>제품 상태: ${info.condition}</li>
						<li>거래 방식: ${info.deal}</li>

						<c:if test="${info.deal == '택배'}">
							<li>배송비: <fmt:formatNumber value="${info.DCharge}"
									pattern="#,###" />원
							</li>
						</c:if>

					</ul>
					<c:if test="${userId == info.id && info.proStatus == '판매중'}">
						<div class="button-container">
							<button
								onclick="location.href='${path}/sell/productmodify/${info.productSeq}'"
								id="gochat" style="cursor: pointer">물품정보 수정</button>
							<button id="gobuy" style="cursor: pointer">판매상태 수정</button>
							<%@include file="/WEB-INF/views/used/proStatusmodify.jsp"%>
						</div>
					</c:if>
					<c:if test="${userId == info.id && info.proStatus == '판매중지'}">
						<div class="text-container">
							<p id="reserve_explanation">※ 판매 중지된 물품은 수정할 수 없습니다.</p>
							<p id="banReason">판매중지 사유</p>
							<p id="paused_explanation">${info.productBanReason}</p>

						</div>
					</c:if>
					<c:if test="${userId == info.id && info.proStatus == '판매보류'}">
						<div class="button-container">
							<button
								onclick="location.href='${path}/sell/productmodify/${info.productSeq}'"
								id="gochat" style="cursor: pointer">물품정보 수정</button>
							<button id="gobuy" style="cursor: pointer">판매상태 수정</button>
							<%@include file="/WEB-INF/views/used/proStatusmodify.jsp"%>
						</div>
					</c:if>
					<c:if test="${userId == info.id && info.proStatus == '예약중'}">
						<div class="button-container">
							<p id="reserve_explanation">※ 예약중인 물품은 수정할 수 없습니다.</p>
							<%@include file="/WEB-INF/views/used/proStatusmodify.jsp"%>
						</div>
					</c:if>
					<c:if
						test="${userId != info.id && info.proStatus == '예약중' && info.deal == '택배'}">
						<div class="button-container">
							<p id="reserve_explanation">※ 이미 거래가 완료된 상품입니다.</p>
						</div>
					</c:if>
					<c:if
						test="${userId != info.id && info.proStatus == '예약중' && info.deal == '직거래'}">
						<div class="button-container">
							<button loginUserId="${userId}" sellerId="${info.id}"
								sellSeq="${info.productSeq}" id="gochat" class="seller-chat"
								style="cursor: pointer">판매자와 채팅하기</button>
						</div>
					</c:if>
					<c:if
						test="${userId != info.id && info.proStatus == '판매중' && info.deal == '택배'}">
						<div class="button-container">
							<button loginUserId="${userId}" sellerId="${info.id}"
								sellSeq="${info.productSeq}" id="gochat" class="seller-chat"
								style="cursor: pointer">판매자와 채팅하기</button>
							<button id="gobuy" style="cursor: pointer"
								prodSeq="${info.productSeq}" alarmId="${info.id}"
								onclick='payForSell("${info.prodName}",${info.productPrice},${info.productSeq}, "${info.id}")'>즉시구매</button>
						</div>
					</c:if>
					<c:if
						test="${userId != info.id && info.proStatus == '판매중' && info.deal == '직거래'}">
						<div class="button-container">
							<button loginUserId="${userId}" sellerId="${info.id}"
								sellSeq="${info.productSeq}" id="gochat" class="seller-chat"
								style="cursor: pointer">판매자와 채팅하기</button>
							<button id="disabled_btn" disabled>즉시구매</button>
						</div>
					</c:if>
					<c:if test="${userId != info.id && info.proStatus == '판매중지'}">
						<div class="button-container">
							<p id="reserve_explanation">※ 판매 중지된 물품은 구매할 수 없습니다.</p>
						</div>
					</c:if>
					<c:if test="${userId != info.id && info.proStatus == '판매보류'}">
						<div class="button-container">
							<p id="reserve_explanation">※ 판매 보류된 물품은 구매할 수 없습니다.</p>
						</div>
					</c:if>

				</div>
				<div id="plusArea">
					<c:if test="${userId != 'admin'}">
						<p id="">최근 본 상품</p>
						<div id="recentArea">

							<c:forEach var="recent" items="${recentlyList}">
								<img class="recentimg"
									onclick="location.href='${path}/sell/productdetail/${recent.productSeq}'"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${recent.imgName}"
									style="cursor: pointer">
							</c:forEach>

						</div>
					</c:if>
					<div id="plus_container">
						<!-- <img id="top_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/logo_favicon.png"> -->
						<p id="gotop">TOP</p>
					</div>
				</div>
			</div>
			<div class="info-container">
				<div class="product-info-container">
					<p class="info_title">물품 정보</p>
					<hr>
					<p id="info">${info.introduce}</p>
				</div>
				<div class="seller-info-container">
					<p class="info_title">판매자 정보</p>
					<hr>
					<div id="seller_score">
						<img id="sellerimg"
							onclick="location.href='${path}/sell/sellerProfile/${info.id}'"
							style="cursor: pointer"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${info.profileImg}"
							onerror="this.src='https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg'">
						<div>
							<img class="star"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
						</div>
					</div>

					<div id="seller_info">
						<a class="seller" href="${path}/sell/sellerProfile/${info.id}">${info.nickName}</a>
						<p class="seller_comm">${info.userIntroduce}</p>
					</div>
				</div>
			</div>
			<p id="recommand_title">추천 제품</p>
			<div id="recommand">
				<c:forEach var="prodReco" items="${prodRecoList}">
					<img class="reco"
						onclick="location.href='${path}/sell/productdetail/${prodReco.productSeq}'"
						style="cursor: pointer"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${prodReco.imgName}">
				</c:forEach>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

	<script>
	$(function () {
		
		$("#emptyHeart").on("click", addJjim);
		$("#fullHeart").on("click", deleteJjim);
		
		var productSeq = ${info.productSeq};
        var id = "${userId}";

        
        
    	/* $.ajax({
            url: '/heehee/sell/selectJjim',
            method: 'GET',
            contentType: 'application/json',
            data: JSON.stringify({ "productSeq": productSeq, "id": id }),
            success: function (data, status, xhr) {
                console.log(data);
               
            },
            error: function (data, status, err) {
                console.log(err);
            }
        }); */
	});
	
	document.addEventListener('DOMContentLoaded', function () {
        var userRating = ${info.userRating}; // EL문법때문에 js파일로 따로 못뺌
        var stars = document.querySelectorAll('#seller_score .star');

        for (var i = 0; i < userRating; i++) {
            stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
        }
    });
	
	
    function addJjim() {
    	$('#emptyHeart').hide();
        $('#fullHeart').show();
        
        var productSeq = ${info.productSeq};
        var id = "${userId}";
    	$.ajax({
            url: '/heehee/sell/insertJjim',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ "productSeq": productSeq, "id": id }),
            success: function (data, status, xhr) {
                console.log(data);
                if(data.success == true) {
                    showTost(data.message);
                    $('#product_etc').val('${info.jjimCnt}');
                    updateProduct();
                } else {
                	showTost(data.message);
                }
            },
            error: function (data, status, err) {
                console.log(err);
            }
        });
    	
    	
    }
        
    function deleteJjim() {
    	$('#fullHeart').hide();
        $('#emptyHeart').show();
        
        var productSeq = ${info.productSeq};
        var id = "${userId}";
    	$.ajax({
            url: '/heehee/sell/deleteJjim',
            method: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify({ "productSeq": productSeq, "id": id }),
            success: function (data, status, xhr) {
                console.log(data);
                if(data.success == true) {
                    showTost(data.message);
                    $('#product_etc').val('${info.jjimCnt}');
                    updateProduct();
                } else {
                	showTost(data.message);
                }
            },
            error: function (data, status, err) {
                console.log(err);
            }
        });
    }
    
    function updateProduct() {
        var productSeq = ${info.productSeq};  // 현재 제품의 고유 번호

        // AJAX 요청을 통해 서버로부터 최신 데이터 가져오기
        $.ajax({
            url: '/heehee/sell/LatestJjimCnt',
            method: 'GET',
            data: { "productSeq": productSeq },
            success: function(data) {
            	console.log(data);
                if (data.success) {
                    $('#jjim_Cnt').text(data.jjimCnt);
                    console.log(data.jjimCnt);
                } else {
                    alert(data.message);
                }
            },
            error: function(xhr, status, err) {
                console.error('Error fetching latest jjim count:', err);
            }
        });
    }

    
    
	</script>
</body>
</html>

