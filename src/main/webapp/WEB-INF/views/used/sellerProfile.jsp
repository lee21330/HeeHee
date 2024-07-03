<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 프로필</title>
<link rel="stylesheet" href="${path}/resources/css/sellerProfile.css">
</head>
<body>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<main>
	<section>
	<div class="mypage_container">
			<div class="top">
				<div class="profileImg">
					<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${sellerinfo.profileImg}">
				</div>
				<div class="profile">
						<h2>${sellerinfo.nickName}</h2>
					<div class="self-intro">
						<%-- <textarea readonly="readonly">${sellerinfo.userIntroduce}</textarea> --%>
						<p>${sellerinfo.userIntroduce}</p>
					</div>
				</div>
				<div id="rating">
					<p id="rating_title">평점</p>
					<div class="ratingImg">
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
					</div>
					<div id="summing_container">
						<div class="summing">
							<p class="summing_title">가입한지</p>
							<p class="summing_body">${sellerinfo.createDiff}일</p>
						</div>
						<div class="summing">
							<p class="summing_title">판매횟수</p>
							<p class="summing_body">${dealComplete.counting}회</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<section>
		<div class="mypage_container">
			<p id="sellprodtitle">판매중인 제품목록</p>
			<form>
				<div class="list">
				<c:forEach var="sellerprod" items="${sellerprodList}">
					<div class="product">
						<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${sellerprod.imgName}"
						onclick="location.href='${path}/sell/productdetail/${sellerprod.productSeq}'"
						style="cursor: pointer;">
						<p class="sellerprodintro" onclick="location.href='${path}/sell/productdetail/${sellerprod.productSeq}'"
						style="cursor: pointer;">
						${sellerprod.articleTitle}
						</p>
					</div>
				</c:forEach>
				</div>
			</form>
		</div>
	</section>
	</main>

<script>
document.addEventListener('DOMContentLoaded', function () {
    var userRating = ${sellerinfo.userRating};
    var stars = document.querySelectorAll('#rating .star');

    for (var i = 0; i < userRating; i++) {
        stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
    }
});
</script>

</body>
</html>