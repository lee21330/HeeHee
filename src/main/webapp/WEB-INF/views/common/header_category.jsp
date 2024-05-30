<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Responsive Web Page</title>
<link rel="stylesheet" href="${path}/resources/css/header_category.css">
</head>

<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<div class="header">
		<div class="inner">

			<div class="nav">
				<div class="burger">
					<div class="line1"></div>
					<div class="line2"></div>
					<div class="line3"></div>
				</div>
			</div>

			<div class="product_category">
				<div class="category">
					<div class="category_name">
						<p>전체 카테고리</p>
						<hr>
					</div>
					<div class="category_content">
						<ul class="category_list">
							<li>여성의류</li>
							<li>남성의류</li>
							<li>신발</li>
							<li>가방/지갑</li>
							<li>시계</li>
							<li>쥬얼리</li>
							<li>패션 액세서리</li>
							<li>디지털</li>
							<li>가전제품</li>
							<li>스포츠/레저</li>
							<li>차량/오토바이</li>
							<li>스타굿즈</li>
							<li>키덜트</li>
							<li>예술/희귀/수집품</li>
							<li>음반/악기</li>
							<li>도서/티켓/문구</li>
							<li>뷰티/미용</li>
							<li>가구/인테리어</li>
							<li>생활/주방용품</li>
							<li>공구/산업용품</li>
							<li>식품</li>
							<li>유아동/출산</li>
							<li>반려동물용품</li>
							<li>기타</li>
						</ul>
					</div>
				</div>
				<div class="detail_category">
					<div class="category_name">
						<p></p>
						<hr>
					</div>
					<div class="category_content" id="category_content1">
						<ul class="sub_list" id="sub_list1">
							<li><a href="#home">아우터</a></li>
							<li><a href="#home">상의</a></li>
							<li><a href="#home">바지</a></li>
							<li><a href="#home">치마</a></li>
							<li><a href="#home">원피스</a></li>
							<li><a href="#home">점프수트</a></li>
							<li><a href="#home">셋업/세트</a></li>
							<li><a href="#home">언더웨어/홈웨어</a></li>
							<li><a href="#home">테마/이벤트</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
 	<script>
		$(function() {
			// detail_category 숨기기
			$(".category").hide();
			$(".detail_category").hide();

			$('.burger').mouseenter(function() {
				$(".category").show();
			});

			$(".category_list li").mouseenter(function() {
				var categoryName = $(this).text();
				$(".detail_category .category_name p").text(categoryName);
				$(".detail_category").show();
				$(".category_list li").css({
					"background" : "white",
					"color" : "black"
				});
				$(this).css({
					"background" : "#3F51A1",
					"color" : "white"
				});

			});

			//product_category에서 마우스 떼었을 때
			$(".product_category").mouseleave(function() {
				$('.category').hide();
				$(".detail_category").hide();
			});

		});
	</script> 
</body>
</html>