<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 메인</title>
<link rel="stylesheet" href="/heehee/resources/css/admin/main.css?after">
<script type="text/javascript" src="${path}/resources/js/admin/user.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<%@ include file="../common/header.jsp" %>
	<div id="bodyContainer">
<%@ include file="../common/admin/sideMenu.jsp" %>
	<div id="mainMenuContainer">
	<p class="mainTitle">이용상태 관리</p>
		<p class="searchTitle">회원 검색</p>
		<div id="searchContainer">
		<div id="midContainer">
		<p class="searchContext">검색어</p>
		<select >
			<option>회원번호</option>
			<option>회원명</option>
			<option>아이디</option>
			<option>이메일</option>
		</select>
		<input type="text" placeholder="입력란 (제목 혹은 내용 입력)">
		<button class="commonSmallBtn">검색</button>
		<button class="commonSmallBtn">초기화</button>
		</div>
		<div id="btmContainer">
		<p class="searchContext">기간검색</p>
		<select>
			<option>가입일</option>
		</select>
		<input type="date" placeholder="입력란 (시작일)"><p> ~ </p><input type="date" placeholder="입력란 (종료일)">
		<input type="radio" id="today" name="dateSelect" onclick=""><p class="radios">오늘</p>
		<input type="radio" id="week" name="dateSelect"><p class="radios">일주일</p>
		<input type="radio" id="lastMonth" name="dateSelect"><p class="radios">지난달</p>
		<input type="radio" id="oneMonth" name="dateSelect"><p class="radios">1개월</p>
		<input type="radio" id="threeMonth" name="dateSelect"><p class="radios">3개월</p>
		</div>
		
		</div>
		<div id="btmContainer">
		<p class="detailTitle">상세 정보</p>
		<button class="commonSmallBtn" id="editButton">수정</button>
		</div>
		<div id="allTable">
			<table>
				<thead>
					<tr>
						<th><input type="checkbox" class ="cb" /></th>
						<th>상태</th>
						<th>회원번호</th>
						<th>회원명</th>
						<th>아이디</th>
						<th>정지내용</th>
						<th>시작일</th>
						<th>종료일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" class ="cb" value="" /></td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
					<tr>
						<td><input type="checkbox" class ="cb" value="" /></td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</body>
</html>