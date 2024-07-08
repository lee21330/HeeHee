<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<body>
	<div id="deal_sell" class="dealList">
		<ul class="menu" id="menu">
			<li class="select" onclick="changeStatus('all');changeMenu(event);">판매내역</li>
			<li onclick="showPurchaseList();changeMenu(event);">구매내역</li>
			<li onclick="showJjimList();changeMenu(event);">찜</li>
		</ul>
		<!-- pro_status로 조회 -->
		<div class="sub_menu" id="sub_menu">
			<select id="selectMenu" onchange="changeStatus(this.value)">
				<option value="all" selected="selected">전체</option>
				<option value="판매중">판매중</option>
				<option value="예약중">예약중</option>
				<option value="거래완료">거래완료</option>
				<option value="판매중지">판매중지</option>
				<option value="판매보류">보관함</option>
			</select>
		</div>
		
		<div id="deleteJjim"><input type="button" value="삭제" class="btn_jjim" onclick="deleteJjim()"></div>
	

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