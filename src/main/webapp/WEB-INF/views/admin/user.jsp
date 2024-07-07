<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 회원정보 관리</title>
<link rel="stylesheet" href="/heehee/resources/css/admin/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/admin/header.jsp" %>
	<div id="bodyContainer">
<%@ include file="../common/admin/sideMenu.jsp" %>
	<div id="mainMenuContainer">
	<p class="mainTitle">회원정보 관리</p>
		<p class="searchTitle">회원 검색</p>
		<div id="searchContainer">
		<div id="midContainer">
		<p class="searchContextUser">검색어</p>
		<select id="searchCategory">
			<option value="memberName">회원명</option>
			<option value="userID">아이디</option>
			<option value="email">이메일</option>
		</select>
		<input type="text" id="searchInput" placeholder="입력란 (제목 혹은 내용 입력)">
		<button type="submit" class="commonSmallBtn" id="searchButton">검색</button>
		<button type="submit" class="commonSmallBtn" id="resetButton">초기화</button>
		</div>
		<div id="btmContainer">
		<p class="searchPeriodContext">기간검색</p>
		<select id="dateCategory">
			<option value="joinDate">가입일</option>
		</select>
		<input type="date" id="startDate" placeholder="입력란 (시작일)">
		<p> ~ </p>
		<input type="date" id="endDate" placeholder="입력란 (종료일)">
		<div class="radioContainer">
			<input type="radio" id="today" name="dateSelect">
			<label for="today" class="radioLabel">오늘</label>
		</div>
		<div class="radioContainer">
			<input type="radio" id="week" name="dateSelect">
			<label for="week" class="radioLabel">일주일</label>
		</div>
		<div class="radioContainer">
			<input type="radio" id="lastMonth" name="dateSelect">
			<label for="lastMonth" class="radioLabel">지난달</label>
		</div>
		<div class="radioContainer">
			<input type="radio" id="oneMonth" name="dateSelect">
			<label for="oneMonth" class="radioLabel">1개월</label>
		</div>
		<div class="radioContainer">
			<input type="radio" id="threeMonth" name="dateSelect">
			<label for="threeMonth" class="radioLabel">3개월</label>
		</div>
		</div>
		</div>
		<p class="detailTitleUser">상세 정보</p>
		<div class="userTable">
			<table>
				<thead>
					<tr>
						<th>회원명</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${path}/resources/js/admin/user.js"></script>
</body>
</html>