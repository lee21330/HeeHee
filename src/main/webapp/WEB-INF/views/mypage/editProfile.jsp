<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<link rel="stylesheet" href="${path}/resources/css/profile.css">
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/profile.js"></script>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<section>
		<div class="profile_container">

			<!-- 왼쪽 영역 -->
			<div class="left">
				<p>내 프로필</p>
				<form action="${path}/mypage/editProfile/updateProfileImg" method="post">
					<div id="profileImg">
						<img class="photo"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${profile.profileImg}">
						<img class="photo-edit"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-edit.png">

						<input type="file" class="file" name="profileImg"
							onchange="readURL(this);">
							
					</div>
					<p id="nickName">${profile.nickName}</p>
				<!-- 	<button id="btn_photo">저장</button> -->
				</form>
			</div>

			<!-- 오른쪽 영역 -->
			<div class="right">

				<div class="right-box">
					<p>아이디</p>
				<p>${profile.id}</p>
				<p>이름</p>
				<p>${profile.name}</p>
				</div>
				<div class="right-box">
					<form action="${path}/mypage/editProfile/updateProfile"
						method="post">
						<div class="item">
							<p>이메일</p>
							<input type="text" placeholder="${profile.email}" name="email">
							<input type="button" value="중복체크" class="btn">
						</div>
						<div class="item">
							<p>전화번호</p>
							<input type="text" placeholder="${profile.phoneNum}"
								name="phoneNum">
						</div>
						<div class="item">
							<p>닉네임</p>
							<input type="text" placeholder="${profile.nickName}"
								name="nickName"> <input type="button" value="중복체크"
								class="btn">
						</div>
						<div class="item">
							<p>주소</p>
							<div class="address">

								<input type="text" placeholder="${profile.address}"
									name="address"> <input type="text" value="101동 101호">
							</div>
						</div>
						<div class="submit">
							<button class="btn">수정</button>
							<button class="btn">저장</button>
						</div>
					</form>
				</div>
				<div class="right-box">
					<div class="item">
						<p>비밀번호</p>
						<button>수정</button>
					</div>
				</div>
				<div class="right-box">
					<%@include file="/WEB-INF/views/mypage/withdrawalModal.jsp"%>
					<p id="drawal">회원 탈퇴</p>
				</div>
			</div>
		</div>
	</section>
</body>
</html>