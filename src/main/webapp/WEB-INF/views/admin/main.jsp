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
		<div id="mainTitleContainerTop"><p class="mainTitle">전체 주문통계</p></div>
		<div class="mainTopTableContainer">
			<div class="totalOrderDiv">
			<p class="tableSubTitle">전체 주문현황(일반/경매)</p>
			<table class="totalOrderTable">
				<thead>
					<tr>
						<th>일반 주문건수</th>
						<th>일반 주문액</th>
						<th>경매 주문건수</th>
						<th>경매 주문액</th>
					</tr>
				</thead>
				<tbody id="totalOrderBody">
				<!-- Ajax로 동적 업데이트 -->
					<tr>
						<td id="sellProCtn"></td>
						<td id="sellProPriceSum"></td>
						<td id="aucProCtn"></td>
						<td id="aucProPriceSum"></td>
					</tr>
				</tbody>
			</table>
			</div>

			<div class="proStatusDiv">
			<p class="tableSubTitle">일반상품 주문현황</p>
			<table class="proStatusTable">
				<thead>
					<tr>
						<th>판매중</th>
						<th>예약중</th>
						<th>거래완료</th>
						<th>판매중지</th>
					</tr>
				</thead>
				<tbody id="proStatusTableBody">
				<!-- Ajax로 동적 업데이트 -->
					<tr>
						<td id="sellIng"></td>
						<td id="sellRes"></td>
						<td id="sellEnd"></td>
						<td id="sellStop"></td>
					</tr>
				</tbody>
			</table>
			</div>

			<div class="aucStatusDiv">
			<p class="tableSubTitle">경매상품 주문현황</p>
			<table class="aucStatusTable">
				<thead>
					<tr>
						<th>입찰</th>
						<th>낙찰</th>
						<th>거래완료</th>
						<th>판매중지</th>
					</tr>
				</thead>
				<tbody id="aucStatusTableBody">
				<!-- Ajax로 동적 업데이트 -->
					<tr>
						<td id="aucIng"></td>
						<td id="aucRes"></td>
						<td id="aucEnd"></td>
						<td id="aucStop"></td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		
		<div id="recentProductContainer">
			<div id="mainTitleContainer"><p class="mainTitle">최근 등록내역</p><button class="mainMenuBtn" onclick="location.href='${path}/admin/product'">상세정보 바로가기</button></div>
			<div class="recentProductDiv">
			<table class="recentProductTable">
				<thead>
					<tr>
						<th>등록번호</th>
						<th>구매자ID</th>
						<th>판매자ID</th>
						<th>품목명</th>
						<th>거래상태</th>
						<th>거래금액</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody id="recentProductTableBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>
		</div>
		
		<div id="recentQuestionContainer">
			<div id="mainTitleContainer"><p class="mainTitle">최근 문의내역</p><button class="mainMenuBtn" onclick="location.href='${path}/admin/qnaManager'">1:1문의 바로가기</button></div>
			<div id="recentQuestionDiv">
			<table class="recentQuestionTable">
				<thead>
					<tr>
						<th>작성자ID</th>
						<th>처리현황</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody id="recentQuestionTableBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>
		</div>
		
		<div id="recentJoinContainer">
		<div id="mainTitleContainer"><p class="mainTitle">최근 회원가입</p><button class="mainMenuBtn" onclick="location.href='${path}/admin/user'">회원정보 관리 바로가기</button></div>
			<div id="recentJoinDiv">
			<table class="recentJoinTable">
				<thead>
					<tr>
						<th>회원ID</th>
						<th>회원명</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody id="recentJoinTableBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="${path}/resources/js/admin/main.js"></script>
</body>
</html>