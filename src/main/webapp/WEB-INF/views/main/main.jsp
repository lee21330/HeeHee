<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 홈페이지</title>
<link rel="stylesheet" href="${path}/resources/css/main/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<%-- <%@ include file="/WEB-INF/views/common/loginModal.jsp"%> --%>

	<div class="home_container">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<div id="main_container">
			<div>
				<div id="prodRankArea">
					<p class="classifyTitle">실시간 인기 상품</p>
					<div id="rankListArea">
						<div class="rankProdDiv">
							<p>1위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>2위</p>
							<img
								src="https://media.bunjang.co.kr/product/255141704_1_1709222992_w180.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">언어펙티드 젤카야노</p>
								<p class="rankProdIntro">3회 착용 물품입니다! 네고.....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>3위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>4위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>5위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>6위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>7위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>8위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>9위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>10위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>11위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
						<div class="rankProdDiv">
							<p>12위</p>
							<img src="${path}/resources/images/prod/zel.jpg">
							<div class="rankProdInfo">
								<p class="rankProdTitle">젤카야노 250 사이즈</p>
								<p class="rankProdIntro">거의 새상품입니다 ....</p>
								<p class="rankProdPrice">130,000</p>
							</div>
						</div>
					</div>
				</div>
				<div id="prodRecommandArea">
					<p class="classifyTitle">당신을 위한 추천 상품</p>
					<div id="recommandListArea">
						<div id="classifyPrev" class="prevBtn">&lt;</div>
						<div id="classifyNext" class="nextBtn">&gt;</div>
					</div>
				</div>
				<div id="prodNowRegArea">
					<p class="classifyTitle">방금 등록된 상품</p>
					<div id="nowRegListArea"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>