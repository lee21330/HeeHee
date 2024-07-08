<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<body>


	<div id="deal_auc" class="dealList">
		<ul class="menu" id="menu_auc">
			<li class="select"
				onclick="changeStatus_auc('all');changeMenuAuc(event);">판매내역</li>
			<li onclick="showPurchaseList_auc();changeMenuAuc(event);">구매내역</li>
		</ul>

		<div class="sub_menu" id="sub_menu">
			<select id="selectMenuAuc" onchange="changeStatus_auc(this.value)">
				<option value="all" selected="selected">전체</option>
				<option value="입찰">입찰중</option>
				<option value="낙찰">낙찰</option>
				<option value="유찰">유찰</option>
				<option value="거래완료">거래완료</option>
				<option value="판매중지">판매중지</option>
			</select>
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
