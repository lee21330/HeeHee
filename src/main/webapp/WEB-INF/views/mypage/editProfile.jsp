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
				<form id="profileForm" action="${path}/mypage/profile/updateProfile" method="post"
					enctype="multipart/form-data">
					<div class="profileImg">
						<img id="previewImage" class="photo"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${profile.profileImg}"
							onerror="this.src='https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg'">
					</div>
					<input type="hidden" id="original" name="originalProfileImg"
						value="${profile.profileImg}" required="required"> 
						<input type="file" id="fileInput" name="profileImg" accept="image/*" style="display: none;">
					<div class="btn_profile">
						<input type="button" class="btn_image" id="btn_modify"
							value="사진변경" onclick="chooseFile();"> 
							<!-- <input type="button" class="btn_image" id="btn_delete" value="삭제"> -->
					</div>

					<table class="input_left">
						<tbody>
							<tr>
								<th>닉네임</th>
								<td>
								<input type="text" id="nickName" name="nickName" value="${profile.nickName}" required="required" oninput="changeBtn(${profile.nickName})"> 
							</tr>
							<tr>
								<th>소개글</th>
								<td><input type="text" id="userIntroduce"
									name="userIntroduce" value="${profile.userIntroduce}"></td>
							</tr>
						</tbody>
					</table>

					<div class="btn_profile">
						<input type="submit" id="btn_submit" value="적용">
						<p id="btn_cancel" onclick="location.href='${path}/mypage/profile'">취소</p>
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
						<div class="right-information" id="phoneNum" onclick="showPhone()">
							<p>${profile.phoneNum}</p>
							<img class="photo-arrow"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-arrow.png">
						</div>

						<!-- 전화번호 수정 모달 -->
						<div id="phoneModal" class="profileModal">
							<div class="profileModalBody">
								<img
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
									alt="전화번호 수정 창 닫기 아이콘" class="profileModalClose"
									onclick="hidePhone()"> 
								<input type="text" id="myphone_input" name="phoneNum" placeholder="${profile.phoneNum}" maxlength="13"
									pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}"> 
								<input type="button" id="btn_send_phone" value="인증번호 발송"> 
								<input type="text" id="phone_auth_input" placeholder="인증번호를 입력해주세요.">
								<input type="button" id="btn_auth_phone" value="인증하기">
								<div id="phone_auth_result"></div>
								<button id="btn_update_phone">수정</button>
							</div>
						</div>

					</div>
					<div class="right-info">
						<p>내 계좌</p>
						<div class="right-information" id="btn-account">
							<p>${profile.bank} ${profile.accountNum}</p>
							<img class="photo-arrow"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-arrow.png">
						</div>

						<!-- 계좌 수정 모달 -->
						<%@ include file="/WEB-INF/views/mypage/accountModal.jsp"%>
					</div>
					<div class="right-info">
						<p>주소</p>
						<div class="right-information" id="address"
							onclick="showAddress()">
							<p>${profile.address} ${profile.detailAddress}</p>
							<img class="photo-arrow"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/photo-arrow.png">
						</div>

						<!-- 주소 수정 모달 -->
						<div id="addressModal" class="profileModal">
							<div class="profileModalBody">
								<img
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
									alt="전화번호 수정 창 닫기 아이콘" class="profileModalClose"
									onclick="hideAddress()">
								<p class="profileModal_p">주소</p>
								<div class="address">
									<input type="text" id="mod_addr_input" placeholder="주소를 검색하세요" value="${profile.address}"> 
									<input type="button" value="검색" onclick="openAddressApiModify()"> 
									<input type="text" id="mod_detail_addr_input" placeholder="사용하실 상세 주소" value="${profile.detailAddress}">
								</div>
								<div class="submit">
									<button class="btn" onclick="updateAddress()">저장</button>
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="right-box" id="pw-box">
					<p>비밀번호</p>
					<button id="btn-pw" class="btn" onclick="showPW()">수정</button>

				</div>
				<div id="pwModal" class="profileModal">
					<div class="profileModalBody">
						<img
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
							alt="로그인 창 닫기 아이콘" class="profileModalClose" onclick="hidePW()">
						<form class="modal_form">
							<p class="profileModal_p">비밀번호 변경</p>
							<input type="password" id="currentPassword"
								name="currentPassword" placeholder="현재 비밀번호 확인" /> <input
								type="password" id="password" name="password"
								placeholder="새로운 비밀번호" /> <input type="password"
								id="confirmPassword" name="confirmPassword"
								placeholder="비밀번호 확인" />
							<div id="new_pw_check" class="dup_result"></div>
							<div>
								<button class="btn_submit" onclick="changePW()">수정하기</button>
								<div class="btn_cancel" onclick="hidePW()">취소하기</div>
							</div>
						</form>

					</div>
				</div>

				<div class="right-box">
					<p id="drawal" onclick="showDrawal()">회원 탈퇴</p>
				</div>
				
				<div id="drawalModal" class="profileModal">
					<div class="profileModalBody">
						<img
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
							alt="로그인 창 닫기 아이콘" class="profileModalClose" onclick="hideDrawal()">

						<h1 class="profileModal_h">정말 탈퇴하시겠습니까?</h1>
						<p class="profileModal_p">계정의 모든 정보는 삭제되며 복구되지 않습니다.</p>
						<div class="btn_modal">
							<button class="btn_cancel" onclick="hideDrawal()">아니요</button>
							<button class="btn_submit" onclick="deleteUser()">네</button>
						</div>
					</div>
				</div>
				
				
			</div>
		</div>
	</section>
</body>
</html>