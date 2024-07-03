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
			<p class="tableSubTitle">전체 주문현황</p>
			<table class="totalOrderTable">
				<thead>
					<tr>
						<th>총 주문건수</th>
						<th>총 주문액</th>
					</tr>
				</thead>
				<tbody id="totalOrderBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>

			<div class="proStatusDiv">
			<p class="tableSubTitle">주문상태 현황</p>
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
				</tbody>
			</table>
			</div>

			<div class="aucStatusDiv">
			<p class="tableSubTitle">구매확정/클래임 현황</p>
			<table class="aucStatusTable">
				<thead>
					<tr>
						<th>입찰</th>
						<th>낙찰</th>
						<th>거래완료<th>
						<th>판매중지</th>
					</tr>
				</thead>
				<tbody id="aucStatusTableBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>
		</div>
		
		<div id="recentOrderContainer">
			<div id="mainTitleContainer"><p class="mainTitle">최근 주문내역</p><button class="productDetailBtn" onclick="location.href='${path}/admin/product'">상세정보 바로가기</button></div>
			<div class="recentOrderDiv">
			<table class="recentOrderTable">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>구매자명</th>
						<th>판매자명</th>
						<th>품목명</th>
						<th>거래방식</th>
						<th>거래금액</th>
						<th>거래일</th>
					</tr>
				</thead>
				<tbody id="recentOrderTableBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>
		</div> <!-- recentOrder -->
		
		<div id="recentQuestionContainer">
			<div id="mainTitleContainer"><p class="mainTitle">최근 문의내역</p><button class="questionsBtn" onclick="location.href='${path}/admin/qnaManager'">1:1문의 바로가기</button></div>
			<div id="recentQuestionDiv">
			<table class="recentQuestionTable">
				<thead>
					<tr>
						<th>작성자ID</th>
						<th>회원명</th>
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
		</div> <!-- recentQuestion -->
		
		<div id="recentJoinContainer">
		<div id="mainTitleContainer"><p class="mainTitle">최근 회원가입</p><button class="questionsBtn" onclick="location.href='${path}/admin/user'">회원정보 관리 바로가기</button></div>
			<div id="recentJoinDiv">
			<table class="recentJoinTable">
				<thead>
					<tr>
						<th>회원명</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody id="recentQuestionTableBody">
				<!-- Ajax로 동적 업데이트 -->
				</tbody>
			</table>
			</div>
		</div> <!-- recentJoinContainer -->
	</div> <!-- mainMenuContainer -->
	</div> <!-- bodyContainer -->
</body>
</html>