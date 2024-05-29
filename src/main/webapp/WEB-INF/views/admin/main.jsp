<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 메인</title>
<link rel="stylesheet" href="/heehee/resources/css/main.css">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ include file="../common/header.jsp" %>
	<header id="header">
	헤더 영역
	</header>
	<div id="bodyContainer">
	<div id="sideMenuContainer">
		<ul>
		<li>전체 관리메뉴</li>
		<li><a>관리자 홈</a></li>
		<li><a>회원정보 관리</a></li>
		<li><a>상품 관리</a></li>
		<li><a>고객 지원</a></li>
		</ul>
	</div>
	<div id="mainMenuContainer">
		<div id="totalOrder">전체 주문통계
			<hr>
			<div id="totalOrderCol">
				<table>
					<th>이름</th>
					<th>이름</th>
					<th>이름</th>
					<th>이름</th>
					<th>이름</th>
					
				</table>
				성명
			</div>
			<hr>
			<hr>
		</div>
		<div id="recentOrder">
			<div id="recentOrderTop"><p>최근 주문내역</p><button id="productDetailBtn">상세정보 바로가기</button></div>
			
			<hr>
			<hr>
			<hr>
		</div>
		<div id="recentQuestion">
			<div id="recentQuestionTop"><p>최근 문의내역</p><button id="questionsBtn">1:1문의 바로가기</button></div>
			<hr>
			<hr>
			<hr>
		</div>
	</div>
	</div>
</body>
</html>