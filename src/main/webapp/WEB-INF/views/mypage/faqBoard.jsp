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
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<div class="main_container">
		<div class="text">
			<h1>FAQ</h1>
			<p>자주 묻는 질문</p>
		</div>
		<input type="text" placeholder="무엇을 도와드릴까요?">
		<button class="btn_small">FAQ 검색</button>

		<div class="faq _header">
			<p>문의유형</p>
			<div class="type_list">
				<div class="radioContainer">
					<input type="radio" id="delivery" name="typeSelect"> <label
						for="delivery" class="radioLabel">배송</label>
				</div>
				<div class="radioContainer">
					<input type="radio" id="order" name="typeSelect"> <label
						for="order" class="radioLabel">주문/결제</label>
				</div>
				<div class="radioContainer">
					<input type="radio" id="user" name="typeSelect"> <label
						for="user" class="radioLabel">회원정보</label>
				</div>
				<div class="radioContainer">
					<input type="radio" id="product" name="typeSelect"> <label
						for="product" class="radioLabel">상품확인</label>
				</div>
				<div class="radioContainer">
					<input type="radio" id="cancle" name="typeSelect"> <label
						for="cancle" class="radioLabel">취소/교환/환불</label>
				</div>
				<div class="radioContainer">
					<input type="radio" id="service" name="typeSelect"> <label
						for="service" class="radioLabel">서비스</label>
				</div>
			</div>
		</div>


		<div id="allTable">
			<table>
				<thead>
					<tr>
						<th class="type">구분</th>
						<th class="title">제목</th>
					</tr>
				</thead>
				<tbody>
					<tr class="question">
						<td class="type">배송</td>
						<td class="title">배송조회가 안될때는 어떻게 해야하나요?</td>
					</tr>
					<tr class="answer">
						<td class="type">답변</td>
						<td class="title">저도 몰라요</td>
					</tr>

					<tr class="question">
						<td class="type">주문/결제</td>
						<td class="title">포인트 충전을 다른 수단으로도 하고싶어요.</td>
					</tr>
					<tr class="answer">
						<td class="type">답변</td>
						<td class="title">저도 몰라요</td>
					</tr>
					<tr class="question">
						<td class="type">회원정보</td>
						<td class="title">회원 탈퇴를 취소하고 싶습니다.</td>
					</tr>
					<tr class="answer">
						<td class="type">답변</td>
						<td class="title">저도 몰라요</td>
					</tr>
					<tr class="question">
						<td class="type">상품확인</td>
						<td class="title">판매자가 등록한 상품은 모두 정품인가요</td>
					</tr>
					<tr class="answer">
						<td class="type">답변</td>
						<td class="title">저도 몰라요</td>
					</tr>
					<tr class="question">
						<td class="type">취소/교환/환불</td>
						<td class="title">구매한 상품을 취소하고 싶으면 어떻게 해야하나요?</td>
					</tr>
					<tr class="answer">
						<td class="type">답변</td>
						<td class="title">저도 몰라요</td>
					</tr>
					<tr class="question">
						<td class="type">서비스</td>
						<td class="title">나이키 브랜드 상품은 A/S가 가능한가요?</td>
					</tr>
					<tr class="answer">
						<td class="type">답변</td>
						<td class="title">저도 몰라요</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		$(function() {
			$(".answer").hide();
			$(".question").on("click", show);
		});

		function show() {
			if ($(this).next().css("display") != "none") {
				$(".answer").hide();
			} else {
				$(".answer").hide();
				$(this).next().show();
			}
		}
	</script>
</body>

</html>