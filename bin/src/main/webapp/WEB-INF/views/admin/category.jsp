<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 상품 관리</title>
<link rel="stylesheet" href="/heehee/resources/css/admin/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/admin/header.jsp" %>
	<div id="bodyContainer">
<%@ include file="../common/admin/sideMenu.jsp" %>
	<div id="mainMenuContainer">
	<p class="mainTitle">카테고리 관리</p>
		<p class="searchTitle">카테고리 편집</p>
		<div id="searchContainer">
			<div id="btmContainer">
				<div class="containerHeadBlock">
					<p class="searchContext">검색어</p>
				</div>
				<select id="searchCategory">
					<option value="number">번호</option>
					<option value="category">카테고리</option>
					<option value="subCategory">세부 카테고리</option>
				</select>
				<input type="text" id="searchInput" placeholder="입력란 (제목 혹은 내용 입력)">
				<button type="submit" class="commonSmallBtn" id="searchButton">검색</button>
				<button type="submit" class="commonSmallBtn" id="resetButton">초기화</button>
			</div>
		</div>
		<div id="btmContainer">
		<p class="detailTitle">상세 내용</p>
		<button class="commonSmallBtn" id="addButton">신규 등록</button>
		<button class="commonSmallBtn" id="editButton">수정</button>
		<button class="commonSmallBtn" id="deleteButton">삭제</button>
		</div>
		<div class="categoryTable">
			<table>
				<thead>
					<tr>
						<th>선택</th>
						<th>번호</th>
						<th>카테고리</th>
						<th>세부 카테고리</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${path}/resources/js/admin/category.js"></script>
</body>
</html>