<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sideMenu</title>
<%-- sideMenu css --%>
<link rel="stylesheet" href="${path}/resources/css/admin/sideMenu.css">
<script type="text/javascript" src="${path}/resources/js/admin/sideMenu.js"></script>
</head>
<body>
	<div id="sideMenuContainer">
		<ul>
			<li>전체 관리메뉴</li>
			<li><a href="${path}/admin/main">관리자 홈</a></li> <!-- 대쉬보드 항목 정리 필요 -->
			<li class="dropdown">
			<a href="javascript:void(0)">회원정보 관리</a>
				<ul class="submenu">
					<li><a href="${path}/admin/user">회원정보 관리</a></li> <!-- 초안 제작 완료 -->
					<li><a href="${path}/admin/ban">이용상태 관리</a></li> <!-- 초안 제작 완료 -->
				</ul>
			</li>
			<li class="dropdown">
			<a href="javascript:void(0)">상품 관리</a>
				<ul class="submenu">
					<li><a class="middleMenu">일반상품 관리</a></li>
					<li><a href="${path}/admin/product">상세조회</a></li> <!-- 초안 제작 완료 -->
					<li><a class="middleMenu">경매상품 관리</a></li>
					<li><a href="${path}/admin/auction">상세조회</a></li> <!-- 초안 제작 완료 -->
					<li><a href="${path}/admin/category">카테고리 관리</a></li> <!-- 제작 필요 -->
				</ul>		
			</li>
			<li class="dropdown">
			<a href="javascript:void(0)">고객 지원</a>
				<ul class="submenu">
					<li><a class="middleMenu">문의사항 관리</a></li>
					<li><a href="${path}/admin/qnaManager">1:1 상담문의</a></li> <!-- 제작 필요 -->
					<li><a class="middleMenu">FAQ 관리</a></li>
					<li><a href="${path}/admin/faqManager">FAQ 내용관리</a></li> <!-- 제작 필요 -->
					<li><a class="middleMenu">문의 유형 관리</a></li>
					<li><a href="${path}/admin/questionManager">문의 유형 관리</a></li> <!-- 제작 필요 -->
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>