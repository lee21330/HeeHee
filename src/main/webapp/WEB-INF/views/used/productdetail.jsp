<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ㅎㅇ</title>
<<<<<<< HEAD
<link rel="stylesheet" href="/heehee/resources/css/productdetail.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/product.js"></script>
=======
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%-- slick slider --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
>>>>>>> branch 'feat/sondonghee' of https://github.com/Sh03Team05/HeeHee.git

<link rel="stylesheet" href="/heehee/resources/css/productdetail.css">

</head>
<%@ include file="../common/header.jsp" %>
<body>
	
	<script src="/heehee/resources/js/product.js"></script>
	
	
	
	<div class="productDetail">
		<main>
			<div class="product-container">
				<div class="product_slider">
				<div id="here"></div>
					<c:forEach var="product" items="${prodImg}">
						<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/${product.imgName}">
					</c:forEach>
				</div>
				<div class="product-details">
					<div class="title-container">
<<<<<<< HEAD
						<p id="product_category">장난감 > 인형</p>
=======
						<p id="product_category">${info.category} > ${info.detailCategory}</p>
>>>>>>> branch 'feat/sondonghee' of https://github.com/Sh03Team05/HeeHee.git
						<img id="url_copy" src="/heehee/resources/images/linkcopy.png" alt="Copy URL" style="cursor: pointer">
					</div>

					<div class="title-container">

						<p id="product_name">${info.prodName}</p>
						<a href="#" class="price-check-link">시세조회</a>

					</div>
					<p id="product_price">${info.productPrice}원</p>
					<p id="product_etc">
					<fmt:formatDate value="${info.createDate}" pattern="yyyy/MM/dd" type="date"/>
					 · 
					 조회 1.5만 
					 · 
					 찜 2.1천 ❤️</p>
					<ul id="product_state">
						<li>제품 상태: ${info.condition}</li>
						<li>거래 방식: ${info.deal}</li>
						<li>배송비: ${info.DCharge}</li>
					</ul>
					<div class="button-container">
						<button onclick="location.href='#'" id="gochat" style="cursor: pointer">판매자와 채팅</button>
						<button onclick="location.href='#'" id="gobuy" style="cursor: pointer">즉시 구매</button>
					</div>

				</div>
				<div id="plusArea">
					<p>최근 본 상품</p>
					<div id="recentArea">
						<img class="recentimg" src="/heehee/resources/images/11.png" style="cursor: pointer">
						<img class="recentimg" src="/heehee/resources/images/11.png" style="cursor: pointer">
					</div>
					<p id="gotop" style="cursor: pointer">TOP</p>
				</div>
			</div>
			<div class="info-container">
				<div class="product-info-container">
					<p class="info_title">물품 정보</p>
					<hr>
					<p id="info">
					상태 깨끗한 너부리입니다. 마스코트인형이랑 비교하면 크기가 크고 2023년에 구매했으며 정품이에요 환불은 불가합니다.
					상태 깨끗한 너부리입니다. 마스코트인형이랑 비교하면 크기가 크고 2023년에 구매했으며 정품이에요 환불은 불가합니다.
					상태 깨끗한 너부리입니다. 마스코트인형이랑 비교하면 크기가 크고 2023년에 구매했으며 정품이에요 환불은 불가합니다.
					상태 깨끗한 너부리입니다. 마스코트인형이랑 비교하면 크기가 크고 2023년에 구매했으며 정품이에요 환불은 불가합니다.
					상태 깨끗한 너부리입니다. 마스코트인형이랑 비교하면 크기가 크고 2023년에 구매했으며 정품이에요 환불은 불가합니다.
					</p>
				</div>
				<div class="seller-info-container">
					<p class="info_title">판매자 정보</p>
					<hr>
					<div id="seller_score">
						<img id="sellerimg" src="/heehee/resources/images/11.png">
						<div>
							<img class="star" src="/heehee/resources/images/star0.png">
							<img class="star" src="/heehee/resources/images/star0.png">
							<img class="star" src="/heehee/resources/images/star0.png">
							<img class="star" src="/heehee/resources/images/star0.png">
							<img class="star" src="/heehee/resources/images/star0.png">
						</div>
					</div>

					<div id="seller_info">
						<a class="seller" href="#">곰두리두리</a>
						<p class="seller_comm">ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ</p>
					</div>
				</div>
			</div>
			<p id="recommand_title">제품 추천</p>
			<div id="recommand">
				<img class="reco" src="/heehee/resources/images/11.png">
				<img class="reco" src="/heehee/resources/images/star0.png">
				<img class="reco" src="/heehee/resources/images/star0.png">
				<img class="reco" src="/heehee/resources/images/star0.png">
				<img class="reco" src="/heehee/resources/images/44.png">
			</div>
		</main>
	</div>
	
	<footer>
		<p>&copy; 2024 희희낙찰. All rights reserved.</p>
	</footer>
	
	<script>
		jQuery.noConflict();
	
		$(document).ready(function() {
			$('.product_slider').slick({
				  infinite: true,
				  slidesToShow: 1,
				  slidesToScroll: 1
				});
			
			/* $(".product_slider").slick({
				slidesToShow : 1,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 2000,
			}); */
    	});
	
		/* $('.product_img').slick(); */
	</script>
</body>
</html>