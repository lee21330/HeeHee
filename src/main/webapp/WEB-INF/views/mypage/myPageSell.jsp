<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<body>
		<div id="deal_sell" class="dealList">
			<ul class="menu" id="menu">
				<li class="select" onclick="changeStatus('all')">판매내역</li>
				<li onclick="showPurchaseList()">구매내역</li>
				<li onclick="showJjimList()">찜</li>
			</ul>

			<!-- pro_status로 조회 -->
			<div class="sub_menu" id="sub_menu">
				<ul>
					<li id="all" class="select_sub" onclick="changeStatus('all')">전체</li>
					<li id="sell" onclick="changeStatus('판매중')">판매중</li>
					<li id="reserve" onclick="changeStatus('예약중')">예약중</li>
					<li id="complete" onclick="changeStatus('거래완료')">거래완료</li>
					<li id="complete" onclick="changeStatus('판매중지')">판매중지</li>
					<li id="complete" onclick="changeStatus('판매보류')">보관함</li>
				</ul>
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
