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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
							<li id="list1">여성의류</li>
							<li id="list2">남성의류</li>
							<li id="list3">신발</li>
							<li id="list4">가방/지갑</li>
							<li id="list5">시계</li>
							<li id="list6">쥬얼리</li>
							<li id="list7">패션 액세서리</li>
							<li id="list8">디지털</li>
							<li id="list9">가전제품</li>
							<li id="list10">스포츠/레저</li>
							<li id="list11">차량/오토바이</li>
							<li id="list12">스타굿즈</li>
							<li id="list13">키덜트</li>
							<li id="list14">예술/희귀/수집품</li>
							<li id="list15">음반/악기</li>
							<li id="list16">도서/티켓/문구</li>
							<li id="list17">뷰티/미용</li>
							<li id="list18">가구/인테리어</li>
							<li id="list19">생활/주방용품</li>
							<li id="list20">공구/산업용품</li>
							<li id="list21">식품</li>
							<li id="list22">유아동/출산</li>
							<li id="list23">반려동물용품</li>
							<li id="list24">기타</li>
						</ul>
					</div>
				</div>
				<div class="detail_category">
					<div class="category_name">
						<p></p>
						<hr>
					</div>
					<div class="category_content">
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
	/* $(function() {
	    $(".detail_category").hide();
	    
	    $("#list1").mouseenter(show).mouseleave(hide);
	    
	    $(".detail_category").mouseenter(show).mouseleave(hide);
	});

	function show() {
	    $(".detail_category").show();
	    
	}

	function hide() {
	    $(".detail_category").hide();
	} */

	$(function() {
	    // detail_category 숨기기
	    $(".detail_category").hide();

	    // 마우스를 가져다 댔을 때 이벤트 처리
	    $("#list1").mouseenter(function() {
	        var categoryName = $(this).text();
	        $(".detail_category .category_name p").text(categoryName);
	        $(".detail_category").show();
	    });
	    $("#list2").mouseenter(function() {
	        var categoryName = $(this).text();
	        $(".detail_category .category_name p").text(categoryName);
	        $(".detail_category").show();
	    });

	    // detail_category에서 마우스를 떼었을 때 숨기기
	    $(".detail_category").mouseleave(function() {
	        $(this).hide();
	    });
	});
	</script>
	<script>
	$(function() {
		const burger = document.querySelector('.burger');
		const navLinks = document.querySelector('.category');
		
		burger.addEventListener('click', () => {
	    navLinks.classList.toggle('nav-active');
		});
		}
	)
	</script> 
</body>
</html>