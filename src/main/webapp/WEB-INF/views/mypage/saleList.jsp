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
				<li><a href="${path}/purchaseList">구매내역</a></li>
				<li><a href="${path}/jjimList">찜</a></li>
			</ul>

			<ul class="sub_menu">
				<li>전체</li>
				<li>판매중</li>
				<li>판매완료</li>
			</ul>
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
			<p class="message">최근 판매 내역이 없습니다.</p>

			<div class="product"
				onclick="location.href='${path}/saledetail/${pro.productSeq}'">
				<%-- <c:forEach var="product" items="${prodImg}">
					<img class="product_img"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}">
				</c:forEach> --%>
				<c:forEach var="pro" items="${info}">
					<img class="product_img"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${pro.imgName}">
					<p>${pro.articleTitle}</p>
					<p>${pro.productPrice}</p>

				</c:forEach>
			</div>

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