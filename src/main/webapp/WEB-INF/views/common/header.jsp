<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<%-- header css --%>
<link rel="stylesheet" href="${path}/resources/css/admin/header.css">
</head>
<body>
	<div class="container">
        <div class="login_container">
            <div class="login_menu">
                <!-- 로그인 전 -->
                <div>로그인</div>
                <div class="menu_line"></div>
                <div class="login_text">로그인</div>
                
                <!-- 로그인 후 -->
                <!-- <div>로그아웃</div>
                <div class="login_line"></div>
                <div class="login_text">마이페이지</div> -->
            </div>
        </div>
        <div class="header_container">
            <div class="logo">
                <img src="images/logo.png">
            </div>
            <div class="product_container">
                <div><a href="https://www.naver.com/">중고물품</a></div>
                <div class="menu_line"></div>
                <div><a>경매물품</a></div>
            </div>
            <div class="menu_container">
                <div>
                	<%-- <img src="../heehee_project/images/icon_sale.png"> --%>
                    <a>물품등록</a>
                </div>
                <div>
                	<%-- <img src="../heehee_project/images/icon_chat.png"> --%>
                    <a>채팅</a>
                </div>
                <div>
                	<%-- <img src="../heehee_project/images/icon_alarm_X.png"> --%>
                    알림
                </div>
            </div>
        </div>
    </div>
</body>
</html>