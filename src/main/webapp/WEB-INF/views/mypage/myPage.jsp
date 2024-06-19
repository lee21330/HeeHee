<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 내역</title>
<link rel="stylesheet" href="${path}/resources/css/myPage.css">
<link rel="stylesheet" href="${path}/resources/css/myPage_header.css">
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/myPage.js"></script>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<section>

		<div class="mypage_container">
			<div class="top">
				<div class="profileImg">
					<img
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${sellerInfo.profileImg}">
				</div>
				<div class="profile">
					<div class="profileName">
						<h2>${sellerInfo.nickName}</h2>
						<button onclick="location.href='${path}/profile'" class="btn">개인정보 변경</button>
					</div>
					<div class="intro">
						<textarea class="self-intro">${sellerInfo.userIntroduce}</textarea>
						<button class="btn" id="btn-intro">소개글 수정</button>
					</div>
				</div>
				<div class="rating">
					<p>평점</p>
					<div class="ratingImg">
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
					</div>
				</div>
				<div class="account">
					<p class=>내 계좌</p>
					<p class="bankName">${sellerInfo.bank}</p>
					<p class="accNum">${sellerInfo.accountNum}</p>
					<div>
						<%@ include file="/WEB-INF/views/mypage/accountModal.jsp"%>
						<button class="btn" id="btn-account">계좌 수정</button>
					</div>
				</div>
				<div class="top-right">
					<a href="${path}/qnaBoard" class="qna">1:1문의</a>
					<div class="point">
						<p>포인트</p>
						<br>
						<p id="userPoint">${sellerInfo.userPoint}</p>
						<img onclick="location.href='${path}/pointlist'"
							src="${path}/resources/images/화살표.png" id="pointImg">

						<div>
							<%@ include file="/WEB-INF/views/mypage/pointModal.jsp"%>
							<button id="btn-point">충전</button>
						</div>
					</div>
				</div>
			</div>
			<ul class="menu">
				<li class="select">판매내역</li>
				<li>구매내역</li>
				<li>찜</li>
			</ul>
			<div class="sub_menu">
				<p class="select_sub">전체</p>
				<p>판매중</p>
				<p>예약중</p>
				<p>거래완료</p>

				<!-- 상세필터  -->
				<button id="btn_search">상세 필터</button>
				<div id="search">
					<img
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
						alt="로그인 창 닫기 아이콘" class="mModal_close">
					<p>조회기간</p>
					<div class="type">
						<div class="radioContainer">
							<input type="radio" id="day" name="typeSelect"> <label
								for="day" class="radioLabel">1일</label>
						</div>
						<div class="radioContainer">
							<input type="radio" id="week" name="typeSelect"> <label
								for="week" class="radioLabel">1주일</label>
						</div>
						<div class="radioContainer">
							<input type="radio" id="month" name="typeSelect"> <label
								for="month" class="radioLabel">1개월</label>
						</div>
						<div class="radioContainer">
							<input type="radio" id="halfYear" name="typeSelect"> <label
								for="halfYear" class="radioLabel">6개월</label>
						</div>
						<div class="radioContainer">
							<input type="radio" id="year" name="typeSelect"> <label
								for="year" class="radioLabel">1년</label>
						</div>
						<div class="radioContainer">
							<input type="date">직접입력
						</div>
					</div>
					<button class="btn_submit">조회</button>
				</div>
			</div>

			<!-- 판매내역 -->
			<div id="salelist" class="list">
				<p class="message">최근 판매 내역이 없습니다.</p>

				<c:forEach var="sale" items="${sInfo}">
					<div class="product"
						onclick="location.href='${path}/${sale.proStatus == '판매중' ? 'productdetail' : 'saledetail'}/${sale.productSeq}'">
						<div class="product_slider">
							<img class="product_img"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
						</div>
						<p>${sale.articleTitle}</p>
						<p>${sale.productPrice}</p>
						<p id="s_statusVal">${sale.proStatus}</p>
					</div>
				</c:forEach>
			</div>
			<!-- 구매내역 -->
			<div id="purchaselist" class="list">
				<p class="message">최근 구매 내역이 없습니다.</p>

				<c:forEach var="purchase" items="${pInfo}">
					<div class="product"
						onclick="location.href='${path}/purchasedetail/${purchase.productSeq}'">
						<img class="product_img"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${purchase.imgName}">
						<p>${purchase.articleTitle}</p>
						<p>${purchase.productPrice}</p>
					</div>
				</c:forEach>
			</div>
			<!-- 찜 -->
			<div id="jjimlist" class="list">
				<p class="message">최근 찜이 없습니다.</p>
				<form>
					<div class="checkbox">
						<input type="checkbox" name="checkBno" value=""> <input
							type="submit" value="삭제" class="btn">
					</div>
					<c:forEach var="jjim" items="${jInfo}">
						<div class="product"
							onclick="location.href='${path}/productdetail/${jjim.productSeq}'">
							<img class="product_img"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${jjim.imgName}">
							<p>${jjim.articleTitle}</p>
							<p>${jjim.productPrice}</p>
						</div>
					</c:forEach>

				</form>
			</div>
		</div>
	</section>
<script>
document.addEventListener('DOMContentLoaded', function () {
    var userRating = ${sellerInfo.userRating};
    var stars = document.querySelectorAll('.rating .star');

    for (var i = 0; i < userRating; i++) {
        stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
    }
    
    const userPointElement = document.getElementById('userPoint');
	const userPoint = parseInt(userPointElement.innerText, 10);
	userPointElement.innerText = userPoint.toLocaleString();
});
</script>
</body>
</html>