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
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<section>
		<%@include file="../mypage/myPage_header.jsp"%>
		<div class="mypage_container">
			<ul class="menu">
				<li class="selected">판매내역</li>
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
			
			<p class="message">최근 판매 내역이 없습니다.</p>

			<c:forEach var="pro" items="${info}">
				<div class="product"
					onclick="location.href='${path}/saledetail/${pro.productSeq}'">
					<img class="product_img"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${pro.imgName}">
					<p>${pro.articleTitle}</p>
					<p>${pro.productPrice}</p>
				</div>
			</c:forEach>

			<!-- 구매내역 -->
			<p class="message">최근 구매 내역이 없습니다.</p>

			<c:forEach var="pro" items="${info}">
				<div class="product"
					onclick="location.href='${path}/saledetail/${pro.productSeq}'">
					<img class="product_img"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${pro.imgName}">
					<p>${pro.articleTitle}</p>
					<p>${pro.productPrice}</p>
				</div>
			</c:forEach>


			<!-- 찜 -->
			<p class="message">최근 찜이 없습니다.</p>
			<form>
				<div class="checkbox">
					<input type="checkbox" name="checkBno" value=""> <input
						type="submit" value="삭제" class="btn">
				</div>
				<div class="list">
					<div class="product"
						onclick="location.href='${path}/productdetail'">
						<img src="${path}/resources/images/보노보노1.jpg">
						<p>보노보노 숲 라잉 쿠션</p>
						<p>10,000원</p>
					</div>
					<div class="product">
						<img src="${path}/resources/images/보노보노2.jpg">
						<p>보노보노 얼굴 쿠션</p>
						<p>10,000원</p>
					</div>
					<div class="product">
						<img src="${path}/resources/images/보노보노3.jpg">
						<p>보노보노 듀얼 고속 무선 충전 거치대</p>
						<p>10,000원</p>
					</div>
					<div class="product">
						<img src="${path}/resources/images/보노보노4.jpg">
						<p>보노보노 인스탁스 미니9</p>
						<p>10,000원</p>
					</div>
					<div class="product">
						<img src="${path}/resources/images/보노보노5.jpg">
						<p>보노보노 더블 범퍼 케이스</p>
						<p>10,000원</p>
					</div>
				</div>
			</form>

		</div>
	</section>
	<script>
		$(function() {
			$("#btn_search").on("click", show);
			$(".mModal_close").on("click", hide);
		});
		function show() {
			$("#search").addClass("show");
		}
		function hide() {
			$("#search").removeClass("show");
		}
	</script>
</body>
</html>