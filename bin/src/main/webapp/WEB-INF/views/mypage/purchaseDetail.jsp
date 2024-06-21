<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 상품 페이지</title>
<link rel="stylesheet" href="${path}/resources/css/purchaseDetail.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
<div class="purchaseDetail">
	<p id="title">구매 완료</p>
	
	<div id="img_name">
		<img id="img" src="/heehee/resources/images/11.png">
		<div>
			<p id="date">구매확정일 2024.05.28(화)</p>
			<p id="prod_name">보노보노 얼굴 쿠션</p>
			<p id="prod_intro">엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!
			엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!엄청 푹신하고 귀여워요!!!</p>
		</div>
	</div>
	
	<p id="progress">진행상황</p>
	<button id="complete">거래완료</button>
	<img id="graph" src="/heehee/resources/images/graph.png">
	
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
			<p>구매가</p>
			<p class="order_right">100,000원</p>
		</div>
		<div class="order">
			<p>배송비</p>
			<p class="order_right">5,000원</p>
		</div>
		<hr>
		<div class="order">
			<p id="total">총 결제금액</p>
			<p class="order_right" id="total_price">105,000원</p>
		</div>
	</div>
	
</div>
</body>
</html>