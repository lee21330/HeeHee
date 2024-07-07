<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<body>
<link rel="stylesheet" href="${path}/resources/css/main/search.css">
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

		<!-- 판매내역 -->
		<div id="salelist" class="list">
			<!-- Ajax로 동적 업데이트 -->
			<div class="rankProdDiv">
				<div class="imgContainer">
					<img class="product_img"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${rankProd.imgName}"
						onclick="location.href='${path}/sell/productdetail/${rankProd.productSeq}'">
					<div id="overlay1">
						<p id="auc_intro">경매품</p>
					</div>
				</div>
				<div class="rankProdInfo">
					<p class="rankProdTitle"
						onclick="location.href='${path}/sell/productdetail/${rankProd.productSeq}'">${rankProd.articleTitle}</p>
					<p class="rankProdIntro">${rankProd.introduce}</p>
					<span class="rankProdPrice"> <fmt:formatNumber
							value="${rankProd.productPrice}" pattern="#,###" />원
					</span> <span id="date_diff">12일전</span>
				</div>
			</div>


			<div class="rankProdDiv">
				<div>${statusChangeMenu}</div>
				<div class="imgContainer" onclick="location.href='${detailUrl}'">
					<div class="product_img ${imgClass}">
						<img class="product_img"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sale.imgName}">
						${productBanReason}
					</div>
					<div class="rankProdInfo">
						<p class="rankProdTitle">${sale.articleTitle}</p>
						<span class="rankProdPrice"> <fmt:formatNumber
							value="${sale.productPrice}" pattern="#,###" />원</span> 
						<span id="s_statusVal_div">
						<p id="s_statusVal" class="rankProdPrice" style="${productStyle}">${sale.proStatus}</p></span>
			
					</div>
				</div>
			</div>

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