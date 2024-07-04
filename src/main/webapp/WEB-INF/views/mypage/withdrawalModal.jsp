<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<link rel="stylesheet" href="${path}/resources/css/myPageModal.css">

<body>
	<script>
		$(function() {
			$("#drawal").on("click", show);
			$("#wModal_close").on("click", hide);

		});
		function show() {
			$("#wModal").addClass("show");
			$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
		}
		function hide() {
			$("#wModal").removeClass("show");
			$("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
		}
		function deleteUser() {
			$.ajax({
				url : "/heehee/mypage/profile/deleteUser",
				method : "DELETE",
				success : function(data) {
					console.log(data);
					logout();
				},
				error : function(err) {
					console.log(err)
				}
			});
		}
	</script>

	<div class="mModal" id="wModal">
		<div class="mModal_body">
			<img
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
				alt="로그인 창 닫기 아이콘" class="mModal_close" id="wModal_close">

			<h1 class="wModal_h">정말 탈퇴하시겠습니까?</h1>
			<p class="wModal_p">계정의 모든 정보는 삭제되며 복구되지 않습니다.</p>
			<div class="btn_modal">
				<button class="btn_cancel" onclick="hide()">아니요</button>
				<button class="btn_submit" onclick="deleteUser()">네</button>
			</div>
		</div>
	</div>
</body>