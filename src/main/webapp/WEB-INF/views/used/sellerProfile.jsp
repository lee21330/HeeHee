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
					<p>평점</p>
					<div class="ratingImg">
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
						<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png"> 
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
						onclick="location.href='${path}/productdetail/${sellerprod.productSeq}'"
						style="cursor: pointer;">
						<p class="sellerprodintro" onclick="location.href='${path}/productdetail/${sellerprod.productSeq}'"
						style="cursor: pointer;">
						${sellerprod.articleTitle}
						</p>
					</div>
				</c:forEach>
				</div>
			</form>
		</div>
	</section>
	
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