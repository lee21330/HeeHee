<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 포인트 내역</title>
<link rel="stylesheet" href="${path}/resources/css/pointList.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
<script src="/heehee/resources/js/point.js"></script>

<div class="pointList">
	<div id="point_text">
		<p id="balance">현재 포인트</p>
		<div id="balance_t">
			<p id="balance_int">500</p><p id="balance_text">원</p>
		</div>
		<button id="charge_btn">충전하기</button>
	</div>

	<div id="point_box">
		<div id="point_box_text">
		<div id="month">
			<p id="month">5월</p>
			<img id="month_list" src="${path}/resources/images/month_list.png">
			<div id="month_dropdown">
	        <div class="month_option">1월</div>
	        <div class="month_option">2월</div>
	        <div class="month_option">3월</div>
	        <div class="month_option">4월</div>
	        <div class="month_option">5월</div>
	        <div class="month_option">6월</div>
	        <div class="month_option">7월</div>
	        <div class="month_option">8월</div>
	        <div class="month_option">9월</div>
	        <div class="month_option">10월</div>
	        <div class="month_option">11월</div>
	        <div class="month_option">12월</div>
    </div>
		</div>
			<p id="date">2024.05.01 ~ 05.31</p>
		</div>
		<div id="point_history">
			<div class="detail">
				<p class="detail_date">2024.05.28</p>
				<p class="detail_point">+ 1000원</p>
			</div>
			<div class="detail">
				<p class="detail_date">2024.05.16</p>
				<p class="detail_point">+ 5000원</p>
			</div>
			<div class="detail">
				<p class="detail_date">2024.05.16</p>
				<p class="detail_point">+ 3000원</p>
			</div>
			<div class="detail">
				<p class="detail_date">2024.05.16</p>
				<p class="detail_point">+ 2000원</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>