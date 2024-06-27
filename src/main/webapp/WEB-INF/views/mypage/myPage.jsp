<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${sellerInfo.profileImg}">
				</div>
				<div class="profile">
					<div class="profileName">
						<h2>${sellerInfo.nickName}</h2>
						<button onclick="location.href='${path}/mypage/profile'"
							class="btn">개인정보 변경</button>
					</div>
					<div class="intro">
						<form action="${path}/mypage/userIntroUpdate" method="post">
							<textarea class="self-intro" name="intro">${sellerInfo.userIntroduce}</textarea>
							<button class="btn" id="btn-intro" type="submit">소개글 수정</button>
						</form>
					</div>
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
					<a href="${path}/mypage/qnaBoard" class="qna">1:1문의</a>
					<div class="point">
						<p>포인트</p>
						<br>
						<p id="userPoint">${sellerInfo.userPoint}</p>
						<img onclick="location.href='${path}/mypage/pointlist'"
							src="${path}/resources/images/화살표.png" id="pointImg">

						<div>
							<%@ include file="/WEB-INF/views/mypage/pointModal.jsp"%>
							<button id="btn-point">충전</button>
						</div>
					</div>
				</div>
			</div>
			<ul class="menu">
				<li class="select" onclick="changeStatus('all')">판매내역</li>
				<li onclick="showPurchaseList()">구매내역</li>
				<li onclick="showJjimList()">찜</li>
			</ul>

			<!-- pro_status로 조회 -->
			<div class="sub_menu">
				<ul>
					<li id="all" class="select_sub" onclick="changeStatus('all')">전체</li>
					<li id="sell" onclick="changeStatus('판매중')">판매중</li>
					<li id="reserve" onclick="changeStatus('예약중')">예약중</li>
					<li id="complete" onclick="changeStatus('거래완료')">거래완료</li>
					<li id="complete" onclick="changeStatus('판매중지')">판매중지</li>
				</ul>
				<!-- 상세필터  -->
				<button id="btn_search">상세 필터</button>
			</div>
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
				</div>
				<button id="btn_submit">조회</button>
			</div>
	
			<!-- 판매내역 -->
			<div id="salelist" class="list">
				<!-- Ajax로 동적 업데이트 -->

			</div>

			<!-- 구매내역 -->
			<div id="purchaselist" class="list">
				<!-- Ajax로 동적 업데이트 -->

			</div>
			<!-- 찜 -->
			<div id="jjimlist" class="list">
				<!-- Ajax로 동적 업데이트 -->

			</div>
		</div>
	</section>
</body>
</html>