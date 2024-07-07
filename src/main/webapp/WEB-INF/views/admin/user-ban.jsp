<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 메인</title>
<link rel="stylesheet" href="/heehee/resources/css/admin/main.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/admin/header.jsp" %>
	<div id="bodyContainer">
<%@ include file="../common/admin/sideMenu.jsp" %>
	<div id="mainMenuContainer">
	<p class="mainTitle">이용상태 관리</p>
		<p class="searchTitle">회원 검색</p>
		<div id="searchContainer">
		<div id="midContainer">
		<p class="searchContext">검색어</p>
		<select id="searchCategory">
			<option value="memberName">회원명</option>
			<option value="userID">아이디</option>
		</select>
		<input type="text" id="searchInput" placeholder="입력란 (제목 혹은 내용 입력)">
		<button type="submit" class="commonSmallBtn" id="searchButton">검색</button>
		<button type="submit" class="commonSmallBtn" id="resetButton">초기화</button>
		</div>

		<!-- 향후 개선하여 추가 구현 예정
		<div id="btmContainer">
		<p class="searchContext">기간검색</p>
		<select id="dateCategory">
			<option value="startDate">시작일</option>
			<option value="endDate">종료일</option>
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
 		 -->
 		
		</div>
		<div id="detailContainer">
		<p class="detailTitle">상세 정보</p>
		<button class="commonSmallDetailBtn2" id="addButton">정지 등록</button>
		<button class="commonSmallDetailBtn3" id="deleteButton">삭제</button>
		</div>
		<div class="userBanTable">
			<table>
				<thead>
					<tr>
						<th>선택</th>
						<th>회원명</th>
						<th>아이디</th>
						<th>정지내용</th>
						<th>시작일</th>
						<th>종료일</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${path}/resources/js/admin/user-ban.js"></script>
</body>
</html>