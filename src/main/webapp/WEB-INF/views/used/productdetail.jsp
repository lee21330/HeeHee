<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>판매 제품 상세페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%-- slick slider --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />

<link rel="stylesheet" href="/heehee/resources/css/productdetail.css">

</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script src="/heehee/resources/js/product.js"></script>
	<div class="productDetail">
		<main>
			<div class="product-container">
				<div class="product_slider">
					<c:forEach var="product" items="${prodImgList}">
						<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
					</c:forEach>
				</div>
				<div class="product-details">
					<div class="title-container">
						<p id="product_category">${info.category} > ${info.detailCategory} (${info.prodName})</p>
						<img id="url_copy" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/linkcopy.png" alt="Copy URL" style="cursor: pointer">
					</div>

					<div class="title-container">

						<p id="product_name">${info.articleTitle}</p>
						<a href="#" class="price-check-link">시세조회</a>

					</div>
					<p id="product_price">${info.productPrice}원</p>
					<p id="product_etc">
					<fmt:formatDate value="${info.createDate}" pattern="yyyy/MM/dd" type="date"/>
					 · 
					 조회 ${info.viewCnt}
					 · 
					 찜 ${info.jjimCnt} ❤️</p>
					<ul id="product_state">
						<li>제품 상태: ${info.condition}</li>
						<li>거래 방식: ${info.deal}</li>
						<li>배송비: ${info.DCharge}원</li>
					</ul>
					<c:if test="${userId == info.id && info.deal == '직거래'}">
						<div class="button-container">
							<button onclick="location.href='${path}/productmodify/${info.id}'" id="gochat" style="cursor: pointer">보류하기</button>
							<button onclick="location.href='#'" id="gobuy" style="cursor: pointer">수정하기</button>
						</div>
					</c:if>
					<c:if test="${userId == info.id && info.deal == '택배'}">
						<div class="button-container">
							<button onclick="location.href='${path}/productmodify/${info.id}'" id="gochat" style="cursor: pointer">보류하기</button>
							<button onclick="location.href='#'" id="gobuy" style="cursor: pointer">수정하기</button>
						</div>
					</c:if>
					<c:if test="${userId != info.id && info.deal == '직거래'}">
						<div class="button-container">
							<button onclick="location.href='${path}/chatting/${info.id}'" id="gochat" style="cursor: pointer">판매자와 채팅</button>
							<button onclick="location.href='#'" id="gobuy" disabled="disabled" style="color: white; background-color: lightgray;">즉시구매</button>
						</div>
					</c:if>
					<c:if test="${userId != info.id && info.deal == '택배'}">
						<div class="button-container"> 
							<button onclick="location.href='${path}/chatting/${info.id}'" id="gochat" style="cursor: pointer">판매자와 채팅</button>
							<button onclick="location.href='#'" id="gobuy" style="cursor: pointer">즉시구매</button>
						</div>
					</c:if>
				</div>
				<div id="plusArea">
					<p>최근 본 상품</p>
					<div id="recentArea">
						<img class="recentimg" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/nuboori.png" style="cursor: pointer">
						<img class="recentimg" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/nuboori.png" style="cursor: pointer">
					</div>
					<p id="gotop" style="cursor: pointer">TOP</p>
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
						<img id="sellerimg" onclick="location.href='${path}/sellerProfile/${info.id}'" style="cursor: pointer"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${info.profileImg}">
						<div>
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
						</div>
					</div>

					<div id="seller_info">
						<a class="seller" href="${path}/sellerProfile/${info.id}">${info.nickName}</a>
						<p class="seller_comm">${info.userIntroduce}</p>
					</div>
				</div>
			</div>
			<p id="recommand_title">제품 추천</p>
			<div id="recommand">
				<c:forEach var="prodReco" items="${prodRecoList}">
					<img class="reco" onclick="location.href='${path}/productdetail/${prodReco.productSeq}'" style="cursor: pointer"
					src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${prodReco.imgName}">
				</c:forEach>
			</div>
		</main>
	</div>
	
	<footer>
		<p>&copy; 2024 희희낙찰. All rights reserved.</p>
	</footer>
	
	<script>
		document.addEventListener('DOMContentLoaded', function () {
	        var userRating = ${info.userRating}; // EL문법때문에 js파일로 따로 못뺌
	        var stars = document.querySelectorAll('#seller_score .star');

	        for (var i = 0; i < userRating; i++) {
	            stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
	        }
	    });
		
	
	</script>
</body>
</html>