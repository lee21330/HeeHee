<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>FAQ전체조회</title>
<link rel="stylesheet" href="${path}/resources/css/faqBoard.css">
</head>

<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/faqBoard.js"></script>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<div class="main_container">
		<div class="text">
			<h1>FAQ</h1>
			<p>자주 묻는 질문</p>
		</div>
		<div class="faq _header">
			<div class="type_list">
				<div class="radioContainer">
					<input type="radio" id="all" name="typeSelect" checked="checked">
					<label for="all" class="radioLabel" onclick="faqOption(0)">전체</label>
				</div>
				<c:forEach var="op" items="${qnaOption}">

					<div class="radioContainer">
						<input type="radio" id="${op.qnaOption}" name="typeSelect">
						<label for="${op.qnaOption}" class="radioLabel"
							onclick="faqOption(${op.seqQnaOption})">${op.qnaOption}</label>
					</div>
				</c:forEach>
			</div>
			<input type="text" id="searchInput" placeholder="무엇을 도와드릴까요?">
			<button id="submit" class="btn_small">FAQ 검색</button>
		</div>

		<div id="allTable">
			<table>
				<thead>
					<tr id="faqTable">
						<th class="type">구분</th>
						<th class="title">제목</th>
					</tr>
				</thead>
				<tbody id="faqList">

				</tbody>
			</table>
			<div id="message"></div>
		</div>
	</div>
</body>

</html>