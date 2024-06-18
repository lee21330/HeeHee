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
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/myPage.js"></script>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<section>
		<%@include file="../mypage/myPage_header.jsp"%>
		<div class="mypage_container">
			<ul class="menu">
				<li class="select">판매내역</li>
				<li>구매내역</li>
				<li>찜</li>
			</ul>

			<div class="sub_menu">
				<p>전체</p>
				<p>판매중</p>
				<p>판매완료</p>

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
						onclick="location.href='${path}/saledetail/${sale.productSeq}'">
						<img class="product_img"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
						<p>${sale.articleTitle}</p>
						<p>${sale.productPrice}</p>
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
				<!-- <form>
					<div class="checkbox"> <input type="checkbox" name="checkBno" value=""> <input
							type="submit" value="삭제" class="btn">
					</div> -->
					<c:forEach var="jjim" items="${jInfo}">
						<div class="product" onclick="location.href='${path}/productdetail/${jjim.productSeq}'">
							<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${jjim.imgName}">
							<p>${jjim.articleTitle}</p>
							<p>${jjim.productPrice}</p>
						</div>
					</c:forEach>

				<!-- </form> -->
			</div>
		</div>
	</section>
</body>
</html>