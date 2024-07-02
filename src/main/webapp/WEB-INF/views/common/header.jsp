<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
 
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/heehee/resources/js/headerCategory.js"></script>
<script src="/heehee/resources/js/alarm.js"></script>
<script src="/heehee/resources/js/common.js"></script>
<link rel="stylesheet" href="${path}/resources/css/header.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/loginModal.jsp"%>

	<header>
		<div class="container">
			<div class="login_container">
				<div class="login_menu">
					<%@ include file="/WEB-INF/views/common/loginCheck.jsp"%>
				</div>
			</div>
			<div class="header_container">
				<div class="logo">
					<a href="${path}/main"> <img
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/logo.png"
						alt="로고 이미지">
					</a>
				</div>
				<div class="product_container">
					<div>
						<a class="a_color" href="/heehee/main">중고물품</a>
					</div>
					<div class="div_line"></div>
					<div>
						<a class="a_color" href="/heehee/auc">경매물품</a>
					</div>
				</div>
				<div class="search_container">
					<div class="search_bar">
						<input placeholder="어떤 상품을 찾으시나요?"> <a href=""> <img
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_search.png"
							alt="검색 버튼 아이콘">
						</a>
					</div>
				</div>
				<div class="menu_container">
					<div class="menu_div">
						<a href="${path}/sell/productregi"> <img
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_sale.png"
							alt="물품등록 아이콘"> <span>물품등록</span>
						</a>
					</div>
					<div class="menu_div">
						<a href="/heehee/chatting"> <img
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_chat.png"
							alt="채팅 아이콘"> <span>채팅</span>
						</a>
					</div>
					<div id="alarmDiv" class="menu_div">
						<div>
							<%-- <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_alarm_X.png" alt="알림 없는 아이콘"> --%>
							<img class="alarmImg"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_alarm_O.png"
								alt="알림 있는 아이콘"> <span>알림</span>
						</div>
						<div class="alarm_container">
							<div>
								<div id="alarmAll" class="alarm_type add">전체 알림</div>
								<div id="alarmUnck" class="alarm_type add">미확인 알림</div>
							</div>

							<%-- 알림 없는 경우 --%>
							<%--
							<div id="none">
								<p>최근 알림이 없습니다.</p>
							</div>
							--%>

							<%-- 알림 있는 경우 --%>
							<div id="here" class="alarm_list"></div>
						</div>
					</div>
				</div>

				<%-- 카테고리 --%>
				<div class="nav_container">
					<div class="nav_menu">
						<img
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_menu.png"
							alt="메뉴 아이콘"> <span>카테고리</span>
					</div>
					<div class="nav_inner">
						<div class="nav_title">
							<div class="category_name">
								<p>카테고리 이름</p>
							</div>
							<%-- 카테고리 대분류 --%>
							<div class="category_content">
								<nav>
									<ul class="category_list">
										<c:forEach var="mainCategory" items="${mainCateList}">
											<li>${mainCategory.category}
												<ul class="sub-category-list">
													<c:forEach var="subCategory"
														items="${mainCategory.subCategory}">
														<li>${subCategory}</li>
													</c:forEach>
												</ul>
										</c:forEach>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div id="tost_message"></div>
</body>
</html>