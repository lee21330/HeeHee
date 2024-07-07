<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<body>
	
	
		<div id="deal_auc" class="dealList">
			<ul class ="menu" id="menu_auc">
				<li class="select" onclick="changeStatus_auc('all')">판매내역</li>
				<li onclick="showPurchaseList_auc()">구매내역</li>
			</ul>
			<!-- pro_status로 조회 -->
			<div class="sub_menu" id="sub_menu_auc">
				<ul>
					<li id="all_acu" class="select_sub"
						onclick="changeStatus_auc('all')">전체</li>
					<li id="sell_acu" onclick="changeStatus_auc('입찰')">입찰중</li>
					<li id="reserve_acu" onclick="changeStatus_auc('낙찰')">낙찰</li>
					<li id="reserve_acu" onclick="changeStatus_auc('유찰')">유찰</li>
					<li id="complete_acu" onclick="changeStatus_auc('거래완료')">거래완료</li>
					<li id="complete_acu" onclick="changeStatus_auc('판매중지')">판매중지</li>


				</ul>
			</div>
			<!-- 판매내역 -->
			<div id="salelist_acu" class="list">
				<!-- Ajax로 동적 업데이트 -->

			</div>

			<!-- 구매내역 -->
			<div id="purchaselist_acu" class="list">
				<!-- Ajax로 동적 업데이트 -->

			</div>
		</div>

</body>
