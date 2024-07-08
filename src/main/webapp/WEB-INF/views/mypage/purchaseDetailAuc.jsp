<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 경매 상품 페이지</title>
<link rel="stylesheet" href="${path}/resources/css/saleDetail.css">
</head>
<%@ include file="../common/header.jsp"%>
<body>
	<script src="/heehee/resources/js/purchaseDetailAuc.js"></script>

	<div class="purchaseDetail">
		<p id="proStatus">${saleDetail.aucStatus}</p>

		<div id="product">
			<img id="img"
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${saleDetail.imgName}"
				onclick="location.href='/heehee/auc/detail/${saleDetail.productSeq}'">

			<!-- 카테고리, 거래확정일자, 글제목, 시세조회 -->
			<div class="title-container">
				<p id="product_category">${saleDetail.category}>
					${saleDetail.detailCategory}</p>

				<p id="date">${saleDetail.psDate}</p>
				<p id="articleTitle">${saleDetail.auctionTitle}</p>
				<p id="dealTitle">거래방식:</p>
				<p id="deal">택배</p>

				<div id="delivery">
					<!-- Ajax로 동적 업데이트 -->
					<p>택배사: ${saleDetail.DCompany}</p>
					<p id="dNumber">송장번호: ${saleDetail.DNumber}</p>
					<p id="dStatus">${saleDetail.DStatus}</p>
				</div>
			</div>
			<div class="button-container">
				<button loginUserId="${userId}" sellerId="${saleDetail.id}" sellSeq="${saleDetail.productSeq}" id="gochat" class="auc-chat" style="cursor: pointer">판매자와 채팅하기</button>
			</div>
		</div>
		<p id="progress">진행상황</p>
		<c:if
			test="${saleDetail.PCheck != null && saleDetail.aucStatus != '거래완료'}">
			<p>판매자가 아직 거래 완료 버튼을 누르지 않았습니다.</p>
		</c:if>
		<button id="complete" onclick="updatePCheck(${saleDetail.productSeq})">거래완료</button>
		<p id="pCheck">${saleDetail.PCheck}</p>
		<progress id="graph" value="0" max="100"></progress>
		<div id="deliveryText" class="progress_ing">
			<p>결제완료</p>
			<p>발송완료</p>
			<p>배송중</p>
			<p>배송완료</p>
			<p>거래완료</p>
		</div>
		<div id="price_area">
			<div class="order">
				<p id="total">구매가</p>
				<p id="total_price">
					<fmt:formatNumber value="${saleDetail.aucPrice}" pattern="#,###" />
					원
				</p>
			</div>
		</div>

	</div>
	<div id="footerArea">
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>