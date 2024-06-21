<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
					<%-- <div id="signupBtn" class="login_text" onclick="join('signup')">회원가입</div> --%>

					<%-- 로그인 후 --%>
					<%-- <div>로그아웃</div>
                        <div class="div_line"></div>
                        <div class="login_text">마이페이지</div> --%>
				</div>
			</div>
			<div class="header_container">
				<div class="logo">
					<a href="">
						<img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/logo.png" alt="로고 이미지">
					</a>
				</div>
			</div>
		</div>
	</header>
</body>
</html>