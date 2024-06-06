<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link rel="stylesheet" href="${path}/resources/css/myPage_header.css">
</head>
<body>
	<section>
		<div class="mypage_container">
			<div class="top">
				<div class="profileImg">
					<img src="${path}/resources/images/duck.jpg">
				</div>
				<div class="profile">
					<div class="profileName">
						<h2>손동희</h2>
						<button onclick="location.href='${path}/profile'" class="btn">개인정보
							변경</button>
					</div>
					<div class="self-intro">

						<textarea>보노보노를 원하신다면 저를 찾아주세요!</textarea>
						<button class="btn" id="btn-intro">소개글 수정</button>

					</div>
				</div>
				<div class="rating">
					<p>평점</p>
					<div class="ratingImg">
						<img src="${path}/resources/images/star0.png"> 
						<img src="${path}/resources/images/star0.png"> 
						<img src="${path}/resources/images/star0.png"> 
						<img src="${path}/resources/images/star0.png"> 
						<img src="${path}/resources/images/star0.png">
					</div>
				</div>
				<div class="account">
					<p class=>내 계좌</p>
					<p class="bankName">우리은행</p>
					<p class="accNum">123-123-123456</p>
					<button onclick="location.href='${path}/account'" class="btn"
						id="btn-account">계좌 수정</button>
				</div>
				<div class="top-right">
					<a href="${path}/qnaBoard" class="qna">1:1문의</a>
					<div class="point">
						<p>포인트</p><br>
						<p>1,000,000원</p>
						<img onclick="location.href='${path}/pointlist'" src="${path}/resources/images/화살표.png">
						<button onclick="location.href='${path}/chargepoint'"
							class="btn-point">충전</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>