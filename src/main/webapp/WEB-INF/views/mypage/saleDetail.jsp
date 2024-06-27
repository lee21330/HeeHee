<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 완료 페이지</title>
<link rel="stylesheet" href="${path}/resources/css/saleDetail.css">
</head>
<%@ include file="../common/header.jsp"%>
<body>
	<script src="/heehee/resources/js/saleDetail.js"></script>
	<div class="purchaseDetail">
		<p id="title">${saleDetail.proStatus}</p>

		<div id="img_name">
			<img id="img"
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${saleDetail.imgName}">
			<div>
				<p id="date">${saleDetail.psDate}</p>
				<p id="prod_name">${saleDetail.articleTitle}</p>
				<%@ include file="/WEB-INF/views/mypage/delieveryModal.jsp"%>
				<button id="enter_invoice">송장 입력하기</button>

			</div>
		</div>

		<p id="progress">진행상황</p>
		<button id="complete" onclick="graphFunc()">거래완료</button>
		<progress id="graph" value="0" max="100"></progress>

		<div id="progress_ing">
			<p>결제대기</p>
			<p>결제완료</p>
			<p>발송완료</p>
			<p>배송 중</p>
			<p>배송완료</p>
			<p>거래완료</p>
		</div>

		<div id="price_area">
			<div class="order">
				<p>판매가</p>
				<p class="order_right">
					<fmt:formatNumber value="${saleDetail.productPrice}"
						pattern="#,###" />
				</p>
			</div>
			<div class="order">
				<p>배송비</p>
				<p class="order_right">
					<fmt:formatNumber value="${saleDetail.DCharge}" pattern="#,###" />
				</p>
			</div>
			<hr>
			<div class="order">
				<p id="total">총 판매금액</p>
				<p class="order_right" id="total_price">
					<fmt:formatNumber
						value="${saleDetail.productPrice + saleDetail.DCharge}"
						pattern="#,###" />
				</p>
			</div>
		</div>

	</div>
</body>
</html>