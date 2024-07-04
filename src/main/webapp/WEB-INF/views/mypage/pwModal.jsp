<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<link rel="stylesheet" href="${path}/resources/css/myPageModal.css">

<body>
	<script>
		$(function() {
			$("#btn-pw").on("click", show);
			$(".Modal_close").on("click", hide);
			$(".btn_cancel").on("click", hide);
			function show() {
				$("#wModal").addClass("show");
				$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
			}
			function hide() {
				$("#wModal").removeClass("show");
				$(".Modal_bank input").val("");
				$(".Modal_bank select").val("은행");
				$("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
			}
		});

	</script>

	<div class="mModal" id="wModal">
		<div class="mModal_body">
			<img
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
				alt="로그인 창 닫기 아이콘" class="mModal_close">
			<form class="modal_form">
				<p class="modal_info">비밀번호 변경</p>
				<input type="password" id="currentPassword" name="currentPassword"
					placeholder="현재 비밀번호 확인" />
				<div id="my_pw_check" class="dup_result"></div>
				<input type="password" id="password" name="password"
					placeholder="새로운 비밀번호" /> <input type="password"
					id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" />
				<div id="new_pw_check" class="dup_result"></div>
				<div>
					<button class="btn_submit">수정하기</button>
					<div class="btn_cancel">취소하기</div>
				</div>
			</form>

		</div>
	</div>
</body>