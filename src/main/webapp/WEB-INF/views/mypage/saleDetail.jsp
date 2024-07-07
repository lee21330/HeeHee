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
		<p id="proStatus">${saleDetail.proStatus}</p>

		<div id="product">
			<img id="img"
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${saleDetail.imgName}">

			<!-- 카테고리, 거래확정일자, 글제목, 시세조회 -->
			<div class="title-container">
				<p id="product_category">${saleDetail.category}>
					${saleDetail.detailCategory} (${saleDetail.prodName})</p>

				<!-- </div>
				<div class="title-container"> -->
				<p id="date">${saleDetail.psDate}</p>
				<p id="articleTitle">${saleDetail.articleTitle}</p>
				<p id="dealTitle">거래방식:</p>
				<p id="deal">${saleDetail.deal}</p>

				<%@ include file="/WEB-INF/views/mypage/delieveryModal.jsp"%>
				<c:if
					test="${saleDetail.deal == '택배' && saleDetail.DNumber == null && saleDetail.proStatus == '예약중'}">
					<button id="enter_invoice">송장 입력하기</button>
				</c:if>
				<c:if test="${saleDetail.deal == '택배'}">
					<div id="delivery">
						<!-- Ajax로 동적 업데이트 -->
						<p>택배사: ${saleDetail.DCompany}</p>
						<p id="dNumber">송장번호: ${saleDetail.DNumber}</p>
						<p id="dStatus">${saleDetail.DStatus}</p>
					</div>
				</c:if>
			</div>
		</div>

		<p id="progress">진행상황</p>
		<c:if
			test="${saleDetail.SCheck == null && saleDetail.proStatus != '판매중지'}">
			<button id="complete"
				onclick="updateSCheck(${saleDetail.productSeq})">거래완료</button>
		</c:if>
		<p id="sCheck">${saleDetail.SCheck}</p>
		<p id="dStatus">${saleDetailDStatus}</p>
		<progress id="graph" value="0" max="100"></progress>

		<c:if test="${saleDetail.deal == '택배'}">
			<div id="deliveryText" class="progress_ing">
				<p>결제완료</p>
				<p>발송완료</p>
				<p>배송중</p>
				<p>배송완료</p>
				<p>거래완료</p>
			</div>
		</c:if>
		<c:if test="${saleDetail.deal == '직거래'}">
			<div id="directText" class="progress_ing">
				<p>예약중</p>
				<p>거래완료</p>
			</div>
		</c:if>
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
	<div id="footerArea">
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>