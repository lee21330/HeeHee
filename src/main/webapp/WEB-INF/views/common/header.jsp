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
					<div id="signupBtn" class="login_text" onclick="join('signup')">회원가입</div>

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
					<div class="alarm_container">
                            <div>
                                <img src="${path}/resources/images/icon_alarm_X.png" alt="알림 아이콘">
                                <span>알림</span>
                            </div>
                            <div class="alarm_list">
                                <ul>
                                    <li>알림 목록</li>
                                </ul>
                            </div>
                        </div>
				</div>
				<!-- 카테고리 -->
                    <div class="nav_container">
                        <div class="nav_menu">
                            <img src="${path}/resources/images/icon_menu.png" alt="메뉴 아이콘">
                            <span>카테고리</span>
                        </div>
                        <div class="nav_inner">
                            <div class="nav_title">
                                <div class="category_name">
                                    <p>카테고리 이름</p>
                                </div>
                                <!-- 카테고리 대분류 -->
                                <div class="category_content">
                                    <nav>
                                        <ul class="category_list">
                                            <li>여성의류</li>
                                            <li>남성의류</li>
                                            <li>신발</li>
                                            <li>가방/지갑</li>
                                            <li>시계</li>
                                            <li>쥬얼리</li>
                                            <li>패션 액세서리</li>
                                            <li>디지털</li>
                                            <li>가전제품</li>
                                            <li>스포츠/레저</li>
                                            <li>차량/오토바이</li>
                                            <li>스타굿즈</li>
                                            <li>키덜트</li>
                                            <li>예술/희귀/수집품</li>
                                            <li>음반/악기</li>
                                            <li>도서/티켓/문구</li>
                                            <li>뷰티/미용</li>
                                            <li>가구/인테리어</li>
                                            <li>생활/주방용품</li>
                                            <li>공구/산업용품</li>
                                            <li>식품</li>
                                            <li>유아동/출산</li>
                                            <li>반려동물용품</li>
                                            <li>기타</li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <div class="nav_content">
                                <div class="category_name">
                                    <p></p>
                                </div>
                                <!-- 카테고리 소분류 -->
                                <div class="category_content">
                                    <ul class="content_list">
                                        <li>
                                            <a href="#home">아우터</a>
                                        </li>
                                        <li>
                                            <a href="#home">상의</a>
                                        </li>
                                        <li>
                                            <a href="#home">바지</a>
                                        </li>
                                        <li>
                                            <a href="#home">치마</a>
                                        </li>
                                        <li>
                                            <a href="#home">원피스</a>
                                        </li>
                                        <li>
                                            <a href="#home">점프수트</a>
                                        </li>
                                        <li>
                                            <a href="#home">셋업/세트</a>
                                        </li>
                                        <li>
                                            <a href="#home">언더웨어/홈웨어</a>
                                        </li>
                                        <li>
                                            <a href="#home">테마/이벤트</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
			</div>
		</div>
	</header>
	<script>
		// 수정중
		/* var text = document.querySelector(".a_color");
		
		text.onclick = function() {
			text.style.color = "rgb(63,81,161)";
		} */
		
		$(function () {
            // 알림 숨겨놓기
            $(".alarm_list").hide();

            // 숨긴 알림 보여주기
            $(".alarm_container").mouseenter(function () {
                $(".alarm_list").show();
            });

            // 알림 숨기기
            $(".alarm_container").mouseleave(function () {
                $(".alarm_list").hide();
            });

            // 카테고리 메뉴 숨겨놓기
            $(".nav_title").hide();
            $(".nav_content").hide();

            // 숨긴 카테고리 메뉴 보여주기
            $(".nav_container").mouseenter(function () {
                $(".nav_title").show();
            });

            // 카테고리 메뉴 css 추가
            $(".category_list li").mouseenter(function () {
                var categoryName = $(this).text();

                $(".nav_content .category_name p").text(categoryName);
                $(".nav_content").show();
                $(".category_list li").css({
                    "background": "white",
                    "color": "black"
                });

                /* 마우스 올라가면 카테고리에 css 추가 */
                $(this).css({
                    "background-color": "rgb(63, 81, 161)",
                    "color": "white"
                });
            });

            // 카테고리 메뉴 숨기기
            $(".nav_inner").mouseleave(function () {
                $(".category_list").scrollTop(0); /* 스크롤 위치 초기화 */
                $(".nav_title").hide();
                $(".nav_content").hide();
                $(".category_list li").css("background", "white"); /* css 초기화 */
                $(".category_list li").css("color", "black"); /* css 초기화 */
            })
        });
	</script>
</body>
</html>