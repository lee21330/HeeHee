<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>희희낙찰 홈페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<%-- slick slider --%>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<link rel="stylesheet" href="/heehee/resources/css/auction/detail.css">
</head>

<body>
	<%@ include file="../common/header.jsp" %>
	<script src="/heehee/resources/js/auction/auction.js"></script>
	<div class="productDetail">
		<main>
			<div class="auction-container">
            <div class="auction-item">
                <div class="item-image product_slider">
                	<c:forEach var="product" items="${aucImgs}">
						<img class="product_img" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${product.imgName}">
					</c:forEach>
                </div>
                <div class="item-info">
                	<p class="time-left blue">
                    	<span class="h"></span>:<span class="m"></span>:<span class="s"></span>
                    </p>
                    <h2>${aucProdInfo.auctionTitle}</h2>
                    <p class="price">${aucProdInfo.aucPrice}원</p>
                    <p class="bids">입찰자수: 5명</p>
                    <p class="state">제품 상태: 거의 신품</p>
                    
                    <button>입찰하기</button>
                </div>
            </div>
            <div class="item-details">
                <div class="item-description">
                    <h3>물품 정보</h3>
                    <p>상태 깨끗한 품품푸린 인형 이에요...</p>
                </div>
                <div class="seller-info">
                    <h3>판매자 정보</h3>
                    <p>이두리</p>
                    <p>안녕하세요 5조 프론트 총괄 담당 이두리입니다.</p>
                </div>
            </div>
            <!-- <div class="related-items">
                <h3>마감 임박 제품</h3>
                <div class="items">
                    <div class="item"><img src="item1.png" alt="item1"></div>
                    <div class="item"><img src="item2.png" alt="item2"></div>
                    <div class="item"><img src="item3.png" alt="item3"></div>
                    <div class="item"><img src="item4.png" alt="item4"></div>
                    <div class="item"><img src="item5.png" alt="item5"></div>
                </div>
            </div> -->
        </div>
		</main>
	</div>
</body>
<script>
	$(document).ready(function() {
		
		var userRating = "${info.userRating}"; // EL문법때문에 js파일로 따로 못뺌
	    var stars = document.querySelectorAll('#seller_score .star');

	    for (var i = 0; i < userRating; i++) {
	        stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
	    }
		
	    connect();
	    $('#placeBidBtn').click(function() {
	        sendBid();
	    });
	    
	    $('#paymentBtn').click(function() {
	    	payForPoint("포인트:5000", 5000);
	    });
	    
	    remaindTime();
	    
	    setInterval(function() {
	    	remaindTime();
		 },1000);
	});
	
	
    var stompClient = null;
    
    aucSeq = ${aucProdInfo.productSeq};

    function connect() {
        var socket = new SockJS('/heehee/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/auction/' + aucSeq, function (response) {
                showResponse(JSON.parse(response.body).bidPrice);
            });
        });
    }

    function sendBid() {
        var userId = 'sinsang';
        var bidPrice = parseInt($('#aucPrice').val()) + ${aucProdInfo.increasePrice};
        stompClient.send("/app/bid/"+aucSeq, {}, JSON.stringify({'aucProdSeq': aucSeq, 'userId': userId, 'bidPrice': bidPrice}));
    }

    function showResponse(message) {
    	$("#aucPrice").val(message);
        $('#response').append('<p>' + message + '</p>');
    }

   
    
    function fGet2char(num) {
    	return num < 10 ? "0" + num : String(num);
	}
	
	function remaindTime() {
		var expD = "${aucProdInfo.expDate}";
		var dSplit = expD.split("/");
		
		var expT = "${aucProdInfo.expTime}";
		var tSplit = expT.split(":");
		
	    var now = new Date();
	    var exp = new Date(dSplit[0],dSplit[1] - 1,dSplit[2],tSplit[0],tSplit[1],tSplit[2]);

      	let gap = Math.max(exp.getTime() - now.getTime(), 0);
      	let day = Math.floor((gap / 3600000)/24);
      	let HH = fGet2char(Math.floor(gap / 3600000) % 24 + (day * 24));
      	let MM = fGet2char(Math.floor((gap % 3600000) / 60000));
      	let SS = fGet2char(Math.floor((gap % 60000) / 1000));
      
	    if((gap/60000) < 30) {
	    	$(".time-left").removeClass("blue");
	    	$(".time-left").addClass("red");
	    }
	    
	    $(".h").html(HH);
	    $(".m").html(MM);
	    $(".s").html(SS);
	}
	
    </script>
</html>