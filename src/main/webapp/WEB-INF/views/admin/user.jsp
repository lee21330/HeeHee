<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 회원정보 관리</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ include file="../common/header.jsp" %>
	<h1>회원정보 관리</h1>
	<hr>
	<div>회원검색
		<hr>
		<p>검색어 </p>
		<select>
			<option>회원번호</option>
			<option>회원명</option>
			<option>아이디</option>
			<option>이메일</option>
		</select>
		<input value="" placeholder="입력란 (제목 혹은 내용 입력)">
		<hr>
		<p>기간검색</p>
		<select>
			<option>가입일</option>
		</select>
		<input placeholder="입력란 (시작일)">
		<p> ~ </p>
		<input placeholder="입력란 (종료일)">
		<hr>
	</div>
	<div>
		<p>최근 주문내역</p><button id="productDetailBtn">상세정보 바로가기</button>
		<hr>
		<hr>
		<hr>
	</div>
	<div>
		<p>최근 문의내역</p><button id="questionsBtn">1:1문의 바로가기</button>
		<hr>
		<hr>
		<hr>
	</div>
</body>
</html>