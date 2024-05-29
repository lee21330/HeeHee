<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Responsive Web Page</title>
<link rel="stylesheet" href="/heehee/resources/css/productdetail.css">
</head>
<%@ include file="../common/header.jsp" %>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/productdetail.js"></script>

	<main>
		<div class="product-container">
			<img id="product_img" src="/heehee/resources/images/11.png">
			<div class="product-details">
				<div class="title-container">
                	<p>장난감 > 인형</p>
                	<img id="url_copy" src="/heehee/resources/images/1.png" alt="Copy URL">
            	</div>
            	
				<div class="title-container">
                	<p>너부리 인형</p>
                	<a href="#" class="price-check-link">시세조회</a>
                	
            	</div>
				<p>20000원</p>
				<p>2024.05.27 · 조회 1.5만 · 찜 2.1천 ❤️</p>
				<ul>
					<li>제품 상태: 거의신품</li>
					<li>거래 방식: 택배</li>
					<li>배송비: 70000원</li>
				</ul>
				<div class="button-container">
					<button id="gochat">ㅎㅇ</button>
					<button id="gobuy">ㅎㅎㅇ</button>
				</div>
			</div>
			<div id="plusArea">
				<p>최근 본 상품</p>
				<img class="recentimg" src="/heehee/resources/images/11.png">
				<img class="recentimg" src="/heehee/resources/images/11.png">
				<p id="top">TOP</p>
			</div>
		</div>
		
	</main>


	<footer>
		<p>&copy; 2024 희희낙찰. All rights reserved.</p>
	</footer>
</body>
</html>
