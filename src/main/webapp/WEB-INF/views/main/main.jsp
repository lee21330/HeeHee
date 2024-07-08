<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<div class="home_container">
		<div id="main_container">
			<div>
				<div id="prodRankArea">
					<p class="classifyTitle">실시간 인기 상품</p>
					<div id="rankListArea">
					<c:forEach var="rankProd" items="${rankProdList}" varStatus="status">
					    <div class="rankProdDiv">
					        <p>${status.count}위</p>
					        <img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${rankProd.imgName}" 
					            onclick="location.href='${path}/sell/productdetail/${rankProd.productSeq}'">
					        <div class="rankProdInfo">
					            <p class="rankProdTitle" onclick="location.href='${path}/sell/productdetail/${rankProd.productSeq}'">${rankProd.articleTitle}</p>
					            <p class="rankProdIntro">${rankProd.introduce}</p>
					            <p class="rankProdPrice"><fmt:formatNumber value="${rankProd.productPrice}" pattern="#,###" />원</p>
					        </div>
					    </div>
					</c:forEach>
				
					</div>
				</div>
				<div id="prodRecommandArea">
					<p class="classifyTitle">당신을 위한 추천 상품</p>
					<div id="recommandListArea" class="mainSlider">
					<c:forEach var="recommandprod" items="${recommandList}">
						<div class="recommandProdDiv" >
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${recommandprod.imgName}" 
							onclick="location.href='${path}/sell/productdetail/${recommandprod.productSeq}'" style="cursor: pointer">
							<div class="recommandProdInfo">
								<p class="recommandTitle" onclick="location.href='${path}/sell/productdetail/${recommandprod.productSeq}'" style="cursor: pointer">${recommandprod.articleTitle}</p>
								<p class="recommandPrice"><fmt:formatNumber value="${recommandprod.productPrice}" pattern="#,###" />원</p>
								<p class="recommandUpTime">${recommandprod.agoTime}</p>
							</div>
						</div>
					</c:forEach>
					</div>
				</div>
				<div id="prodNowRegArea">
					<p class="classifyTitle">방금 등록된 상품</p>
					<div id="nowRegListArea" class="mainSlider">
						<c:forEach var="recentprod" items="${recentprodList}" varStatus="status">
						<div class="nowRegProdDiv" onclick="location.href='${path}/sell/productdetail/${recentprod.productSeq}'">
							<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${recentprod.imgName}" 
							onclick="location.href='${path}/sell/productdetail/${recentprod.productSeq}'" style="cursor: pointer">
							<div class="nowRegProdInfo">
								<p class="nowRegProdTitle" onclick="location.href='${path}/sell/productdetail/${recentprod.productSeq}'" style="cursor: pointer">${recentprod.articleTitle}</p>
								<p class="nowRegProdPrice"><fmt:formatNumber value="${recentprod.productPrice}" pattern="#,###" />원</p>
								<p class="nowRegProdUpTime">${recentprod.agoTime}</p>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
</body>
</html>