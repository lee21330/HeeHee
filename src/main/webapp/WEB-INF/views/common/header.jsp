<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path}/resources/css/header.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/loginModal.jsp"%>
	
	<header>
		<div class="container">
			<div class="login_container">
				<div class="login_menu">
					<%-- 로그인 전 --%>
					<div id="loginBtn">로그인</div>
					<div class="div_line"></div>
					<div id="signupBtn" class="login_text">회원가입</div>

					<%-- 로그인 후 --%>
					<%-- <div>로그아웃</div>
                        <div class="div_line"></div>
                        <div class="login_text">마이페이지</div> --%>
				</div>
			</div>
			<div class="header_container">
				<div class="logo">
					<a href="">
						<img src="${path}/resources/images/logo.png" alt="로고 이미지">
					</a>
				</div>
				<div class="product_container">
					<div>
						<a class="a_color" href="">중고물품</a>
					</div>
					<div class="div_line"></div>
					<div>
						<a class="a_color" href="">경매물품</a>
					</div>
				</div>
				<div class="search_container">
					<div class="search_bar">
						<input placeholder="어떤 상품을 찾으시나요?">
						<a href="">
							<img src="${path}/resources/images/icon_search.png" alt="검색 버튼 아이콘">
						</a>
					</div>
				</div>
				<div class="menu_container">
					<div>
						<a>
							<img src="${path}/resources/images/icon_sale.png" alt="물품등록 아이콘">
							<span>물품등록</span>
						</a>
					</div>
					<div>
						<a>
							<img src="${path}/resources/images/icon_chat.png" alt="채팅 아이콘">
							<span>채팅</span>
						</a>
					</div>
					<div>
						<img src="${path}/resources/images/icon_alarm_X.png" alt="알림 아이콘">
						<span>알림</span>
					</div>
				</div>
				<div class="nav_container">
					<img src="${path}/resources/images/icon_menu.png" alt="메뉴 아이콘">
					<span>카테고리</span>

					<div class="nav_category">
					</div>
				</div>
			</div>
		</div>
	</header>
	<script>
		var text = document.querySelector(".a_color");
		
		text.onclick = function() {
			text.style.color = "rgb(63,81,161)";
		}
	
		 /* if ($("#loginModal").isOpen) {
			// document.querySelector(".home_container").style.overflow = 'hidden';
			// $(".home_container").css("overflow", "hidden");
			
		} else {
			// document.querySelector(".home_container").style.overflow = 'scroll';
			// $(".home_container").css("overflow", "scroll");
			
		} */
		
		// 모달 열 때
		/* $("html, body").css({"overflow": "hidden", "height": "100%"}); // 모달 중 html, body의 scroll을 hidden시킴
		$("#element").on("scroll touchmove mousewheel", function(event) { // 터치무브와 마우스휠 스크롤 방지
			event.preventDefault();
			event.stopPropagation();

			return false;
		}); */

		// 모달 닫을 때
		/* $("html, body").css({"overflow": "scroll", "height": "100%"}); // scroll hidden 해제
		$("#element").off("scroll touchmove mousewheel"); // 터치무브 및 마우스휠 스크롤 가능 */
	</script>
</body>
</html>