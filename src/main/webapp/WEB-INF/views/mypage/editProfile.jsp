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
			<div class="left" id="profile">
				<p>내 프로필</p>

				<div id="profileImg" class="profileImg">
					<img class="photo"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${profile.profileImg}"
						onerror="this.src='https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg'">
					<img class="photo-edit"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-edit.png">
				</div>
				<p class="nickName">${profile.nickName}</p>
				<p class="userIntroduce">${profile.userIntroduce}</>
			</div>
			<!-- 왼쪽 영역 form -->
			<div class="left" id="editProfile">
				<p>프로필 수정</p>
				<form enctype="multipart/form-data">
					<div class="profileImg">
						<img id="previewImage" class="photo"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${profile.profileImg}"
							onerror="this.src='https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg'">
					</div>
					<input type="hidden" id="original" name="originalProfileImg"
						value="${profile.profileImg}"> 
						<input type="file"
						id="fileInput" name="profileImg" accept="image/*"
						style="display: none;">
					<div class="btn_profile">
						<input type="button" class="btn_image" id="btn_modify" value="사진변경"
							onclick="chooseFile();"> <input type="button"
							class="btn_image" id="btn_delete" value="삭제">
					</div>
					<div class="input_left">
						<p>닉네임</p>
						<input type="text" id="nickName" name="nickName" value="${profile.nickName}" required="required"> 
						<input type="button" value="중복체크" class="btn" id="btn-nick" onclick="dupMyNickCheck(${profile.nickName})">
						<div id="my_nick_dup_result" class="dup_result"></div>
						<p>소개글</p>
						<input type="text" id="userIntroduce" name="userIntroduce"
							value="${profile.userIntroduce}">
					</div>
					<div class="btn_profile">
						<input type="button" id="btn_submit" value="적용"
							onclick="updateProfile()">
						<p id="btn_cancel"
							onclick="location.href='${path}/mypage/profile'">취소</p>
					</div>
				</form>

			</div>

			<!-- 오른쪽 영역 -->
			<div class="right">
				<div class="right-box">
					<p>기본 정보</p>
					<div class="right-info">
						<p>아이디</p>
						<p>${profile.id}</p>
					</div>
					<div class="right-info">
						<p>이름</p>
						<p>${profile.name}</p>
					</div>
					<div class="right-info">
						<p>이메일</p>
						<p>${profile.email}</p>
					</div>
				</div>
				<div class="right-box" id="Info">
					<div class="right-info">
						<p>전화번호</p>
						<div class="right-information" id="phoneNum">
							<p>${profile.phoneNum}</p>
							<img class="photo-arrow"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-arrow.png">
							<%@include file="/WEB-INF/views/mypage/phoneModal.jsp"%>
						</div>
					</div>
					<div class="right-info">
						<p>내 계좌</p>
						<div class="right-information" id="btn-account">
							<p>${profile.bank}${profile.accountNum}</p>
							<%@ include file="/WEB-INF/views/mypage/accountModal.jsp"%>
							<img class="photo-arrow"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-arrow.png">
						</div>
					</div>
					<div class="right-info">
						<p>주소</p>
						<div class="right-information" id="address">
							<p>${profile.address}${profile.detailAddress}</p>
							<img class="photo-arrow"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-arrow.png">
						</div>
					</div>



				</div>

				<%-- 				<div class="right-box" id="modifyInfo">
					<form action="${path}/mypage/editProfile/updateProfile" method="post">
						<div class="right-info">
							<p>전화번호</p>
							<input type="text" id="phone_input" name="phoneNum" 
							placeholder="사용하실 전화번호를 입력하세요" maxlength="13" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" value="${profile.phoneNum}"> 
							<input type="button" value="인증번호 발송" class="btn">
						</div>
						<div class="right-info">
							<p>주소</p>
							<div class="address">
								<input type="text" id="mod_addr_input" placeholder="주소를 검색하세요" value="${profile.address}"> 
								<input type="button" value="검색" onclick="openAddressApiModify()"> 
								<input type="text" placeholder="사용하실 상세 주소" value="${profile.detailAddress}">
							</div>
						</div>
						<div class="submit">
							<button class="btn" id="btn_save">저장</button>
						</div>
					</form>
				</div> --%>

				<div class="right-box" id="pw-box">
					<p>비밀번호</p>
					<button id="btn-pw" class="btn">수정</button>
					<%@include file="/WEB-INF/views/mypage/pwModal.jsp"%>
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