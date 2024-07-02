<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 홈페이지</title>
<link rel="icon" href="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/logo_favicon.png">
<link rel="stylesheet" href="${path}/resources/css/main/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<%-- <%@ include file="/WEB-INF/views/common/loginModal.jsp"%> --%>
	<div class="home_container">
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<div></div>
		<div id="main_container">
			<div>
				<div id="prodRankArea">
					<p class="classifyTitle">실시간 인기 상품</p>
					<div id="rankListArea">
					<c:forEach var="rankProd" items="${rankProdList}" varStatus="status">
						<div class="rankProdDiv">
							<p>${status.count}위</p>
							<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${rankProd.imgName}">
							<div class="rankProdInfo">
								<p class="rankProdTitle">${rankProd.articleTitle}</p>
								<p class="rankProdIntro">${rankProd.introduce}</p>
								<p class="rankProdPrice">${rankProd.productPrice}원</p>
							</div>
						</div>
					</c:forEach>
				
					</div>
				</div>
				<div id="prodRecommandArea">
					<p class="classifyTitle">당신을 위한 추천 상품</p>
					<div id="recommandListArea">
					<c:forEach var="recommandprod" items="${recommandList}">
						<div id="classifyPrev" class="prevBtn">&lt;</div>
						<div id="classifyNext" class="nextBtn">&gt;</div>
						<div class="recommandProdDiv">
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${recommandprod.imgName}">
							<div class="recommandProdInfo">
								<p class="recommandTitle">${recommandprod.articleTitle}</p>
								<p class="recommandPrice">${recommandprod.productPrice}원</p>
								<p class="recommandUpTime">${recommandprod.agoTime}</p>
							</div>
						</div>
					</c:forEach>
					</div>
					<div class="swiper-pagination">
						<span class="pagination-bullet pagination-bullet-active"></span>
						<span class="pagination-bullet"></span>
						<span class="pagination-bullet"></span>
						<span class="pagination-bullet"></span>
						<span class="pagination-bullet"></span>
					</div>
				</div>
				<div id="prodNowRegArea">
					<p class="classifyTitle">방금 등록된 상품</p>
					<div id="nowRegListArea">
						<div id="classifyPrev" class="prevBtn">&lt;</div>
						<div id="classifyNext" class="nextBtn">&gt;</div>
						<c:forEach var="recentprod" items="${recentprodList}" varStatus="status">
						<div class="nowRegProdDiv">
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${recentprod.imgName}">
							<div class="nowRegProdInfo">
								<p class="nowRegProdTitle">${recentprod.articleTitle}</p>
								<p class="nowRegProdPrice">${recentprod.productPrice}원</p>
								<p class="nowRegProdUpTime">${recentprod.agoTime}</p>
							</div>
						</div>
						</c:forEach>
					</div>
					<div class="swiper-pagination">
						<span class="pagination-bullet pagination-bullet-active"></span>
						<span class="pagination-bullet"></span>
						<span class="pagination-bullet"></span>
						<span class="pagination-bullet"></span>
						<span class="pagination-bullet"></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>