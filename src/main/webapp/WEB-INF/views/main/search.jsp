<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
<link rel="icon"
	href="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/logo_favicon.png">
<link rel="stylesheet" href="${path}/resources/css/main/search.css">
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
					<div id="search_menu_wrap">
						<span class="searchTitle">
						<c:if test="${param.cateName != null}">
							<span id="search_result">${param.cateName}</span> 
						</c:if>
						<c:if test="${param.keyword != null}">
							<span id="search_result">${param.keyword}</span> 
						</c:if>
						검색 결과 <fmt:formatNumber value="${resultCount}" pattern="#,###" /> 개</span>
						<div id="search_menu">
							<span id="accuracy" class="search_order">정확도순</span>
							<%@include file="/WEB-INF/views/mypage/ratingModal.jsp" %>
							<span id="recently" class="search_order">최신순</span>
							<span id="lowPrice" class="search_order">저가순</span> 
							<span id="highPrice" class="search_order">고가순</span>
						</div>
					</div>
					<div id="rankListArea">
						<c:forEach var="product" items="${productArr}"
							varStatus="status">
							<c:if test="${product.gubun == 'AUC'}">
								<div class="rankProdDiv">
									<div class="imgContainer">
										<img class="product_img"
											src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${product.imgName}"
											onclick="location.href='${path}/auc/detail/${product.productSeq}'">
										<div id="overlay1">
											<p id="auc_intro">경매품</p>
										</div>
									</div>
									<div class="rankProdInfo">
										<p class="rankProdTitle"
											onclick="location.href='${path}/auc/detail/${product.productSeq}'">${product.title}</p>
										<p class="rankProdIntro">${product.introduce}</p>
										<span class="rankProdPrice">
											<fmt:formatNumber value="${product.price}"
												pattern="#,###" />원</span>
										<span id="date_diff">12일전</span>
									</div>
								</div>
							</c:if>
							<c:if test="${product.gubun == 'SELL'}">
								<div class="rankProdDiv">
									<div class="imgContainer">
										<img class="product_img"
											src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${product.imgName}"
											onclick="location.href='${path}/sell/productdetail/${product.productSeq}'">
									</div>
									<div class="rankProdInfo">
										<p class="rankProdTitle"
											onclick="location.href='${path}/sell/productdetail/${product.productSeq}'">${product.title}</p>
										<p class="rankProdIntro">${product.introduce}</p>
										<span class="rankProdPrice">
											<fmt:formatNumber value="${product.price}"
												pattern="#,###" />원</span>
										<span id="date_diff">12일전</span>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('.search_order').click(function() {
				// 모든 요소의 'active' 클래스를 제거
				$('.search_order').removeClass('active');
				// 클릭된 요소에만 'active' 클래스 추가
				$(this).addClass('active');
			});
		});
	</script>
</body>
</html>