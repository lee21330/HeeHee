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
	<div id="deal_sell" class="dealList">
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
				<li id="complete" onclick="changeStatus('판매보류')">보관함</li>
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
</body>
</html>