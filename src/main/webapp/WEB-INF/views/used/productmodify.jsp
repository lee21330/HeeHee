<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>등록 물품 수정 페이지</title>
<link rel="stylesheet" href="/heehee/resources/css/productregiModi.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/product.js"></script> <!-- 이미지파일 업로드때문에 필요 -->
	<script src="/heehee/resources/js/productregiCategory.js"></script>
	<form action="#" method="GET">
	<div class="productRegistrate">
	<div id="test">
		<main>
			<p id="regi_title">상품 등록</p>
			<div id="regi_info">
				<p>상품 이미지</p>
				<img id="preview" src="/heehee/resources/images/picture.png">
				<div id="regi_img">
					<input type="file" id="input_file">
				</div>
			</div>
				<p id="cate">카테고리</p>
					<div id="elementColumn">
					<div class="nav_inner1">
						<div class="nav_title1">
							<div class="category_name1">
								<p>대분류</p>
							</div>
							<%-- 카테고리 대분류 --%>
							<div class="category_content1">
								<nav>
									<ul class="category_list1">
										<%-- <c:forEach var="bigCate" items="${bigCateList}">
											<li>${bigCate.}</li>
										</c:forEach> --%>
									</ul>
								</nav>
							</div>
						</div>
						<div class="nav_content1">
							<div class="category_name1">
								<p>소분류</p>
							</div>
							<%-- 카테고리 소분류 --%>
							<div class="category_content1">
								<ul class="content_list1">
									<li><p>아우터</p></li>
									<li><p>상의</p></li>
									<li><p>바지</p></li>
									<li><p>치마</p></li>
									<li><p>원피스</p></li>
									<li><p>점프수트</p></li>
									<li><p>셋업/세트</p></li>
									<li><p>언더웨어/홈웨어</p></li>
									<li><p>테마/이벤트</p></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
	<div class="regi_item">
		<p>상품 이름</p>
		<input type="text" class="input_name" placeholder="상품명을 입력해주세요.">
	</div>
	<div class="regi_item">
		<p>상품 상태</p>
		<div class="state_radio">
		    <input type="radio" id="new" name="state" checked="checked">
		    <label for="new">새 상품(미사용)</label>
		
		    <input type="radio" id="slightly_used" name="state" class="radio_order">
		    <label for="slightly_used">사용감 적음</label>
		
		    <input type="radio" id="used" name="state" class="radio_order">
		    <label for="used">사용감 많음</label>
		
		    <input type="radio" id="damaged" name="state" class="radio_order">
		    <label for="damaged">고장/파손</label>
		</div>
	</div>
	<div class="regi_item">
		<p>상품 설명</p>
		<input type="text" class="input_name" placeholder="설명을 입력해주세요.">
	</div>
	<div class="regi_item">
		<p>상품 가격</p>
		<input type="text" class="input_name" placeholder="가격을 입력해주세요.">
	</div>
	<div class="regi_item">
		<p id="d_charge">배송비</p>
		<input type="text" class="input_name" placeholder="배송비를 입력해주세요.">
	</div>
	<div class="regi_item">
		<p>거래 유형</p>
		<div class="state_radio">
		    <input type="radio" id="package" name="deal_type" checked="checked">
		    <label for="package">택배</label>
		
		    <input type="radio" id="direct" name="deal_type" class="radio_order">
		    <label for="direct">직거래</label>
		</div>
	</div>
		</main>
		</div>
	</div>
	<div id="sub_button">
		<input type="submit" value="수정하기">
	</div>
	
	</form>
	<footer>
		&copy; 2024 희희낙찰. All rights reserved.
	</footer>
</body>

</html>