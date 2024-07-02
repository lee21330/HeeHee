<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
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
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${sellerInfo.profileImg}" onerror="this.src='https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg'">
				</div>
				<div class="profile">
					<div class="profileName">
						<h2>${sellerInfo.nickName}</h2>
						<button onclick="location.href='${path}/mypage/profile'"
							class="btn">개인정보 변경</button>
					</div>
					<div class="intro">${sellerInfo.userIntroduce}</div>
				</div>
				<div class="rating">
					<p>평점</p>
					<p id="myRating">${sellerInfo.userRating}</p>
					<div class="ratingImg">
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
				<%-- <div class="account">
					<p class=>내 계좌</p>
					<p class="bankName">${sellerInfo.bank}</p>
					<p class="accNum">${sellerInfo.accountNum}</p>
					<div>
						<%@ include file="/WEB-INF/views/mypage/accountModal.jsp"%>
						<button class="btn" id="btn-account">계좌 수정</button>
					</div>
				</div> --%>
				<div class="top-right">
					<a href="${path}/mypage/qnaBoard" class="qna">1:1문의</a>
					<div class="point">
						<p>포인트</p>
						<br>
						<fmt:formatNumber value="${sellerInfo.userPoint}" pattern="#,###" />
						<img onclick="location.href='${path}/mypage/pointlist'"
							src="${path}/resources/images/화살표.png" id="pointImg">

						<div>
							<%@ include file="/WEB-INF/views/mypage/pointModal.jsp"%>
							<button id="btn-point">충전</button>
						</div>
					</div>
				</div>
			</div>





			<ul id="deal">
				<li onclick="changeDeal('중고물품')">중고물품</li>
				<li onclick="changeDeal('경매물품')">경매물품</li>
			</ul>


			<div id="dealContainer">
				<%@include file="/WEB-INF/views/mypage/myPageSell.jsp"%>
				<%@include file="/WEB-INF/views/mypage/myPageAuc.jsp"%>
			</div>
		</div>
	</section>
</body>
</html>