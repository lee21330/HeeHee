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
                <div>
	                <div class="item-info">
	                	<p class="time-left blue">
	                    	<span class="h"></span>:<span class="m"></span>:<span class="s"></span>
	                    </p>
	                    <p class="auc_category">${aucProdInfo.category} > ${aucProdInfo.detailCategory}</p>
	                    <h2>${aucProdInfo.auctionTitle}</h2>
	                    <div class="info_left">
	                    <p class="price">${aucProdInfo.aucPrice}원</p>
	                    <input id="auc_price" type="hidden" value="${aucProdInfo.aucPrice}">
	                    <p class="bids">입찰자수: <span id="joinCount">${aucProdInfo.joinCount}</span>명</p>
	                    <p class="state">제품 상태: ${aucProdInfo.condition}</p>
	                    </div>
	                    <div class="info_right">
	                    	<p class="increase_price">입찰 시 증가 금액: ${aucProdInfo.increasePrice}원</p>
	                    	<p class="current_user">예상 낙찰자:<span id="current_user_nickname">${aucProdInfo.currentNick}</span></p>
	                    </div>
                    </div>
                    <button id="place_bid_btn">입찰하기</button>
                    <div id="my_point_area">
                    	<p class="my_point_text">나의 보유 포인트</p>
                    	<p class="my_point_amount_area"><span class="my_point_amount">5,000</span>P</p>
                    	<button id="charge_point_btn">포인트 충전</button>
                    </div>
                </div>
            </div>
            <div class="item-details">
                <div class="item-description">
                    <h3>물품 정보</h3>
                    <hr>
                    <p>${aucProdInfo.introduce}</p>
                </div>
                <div class="seller-info">
                    <h3>판매자 정보</h3>
                    <hr>
                    <div id="seller_score">
	                    <img id="sellerimg" onclick="location.href='/heehee/sell/sellerProfile/${sellerInfo.id}'" 
	                    style="cursor: pointer" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${sellerInfo.profileImg}">
	                    <div>
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
							<img class="star" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
						</div>
					</div>
                    <p onclick="location.href='/heehee/sell/sellerProfile/${sellerInfo.id}'">${sellerInfo.nickName}</p>
                    <p>${sellerInfo.userIntroduce}</p>
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
	var expT = "${aucProdInfo.expTime}";
	var expD = "${aucProdInfo.expDate}";
	var dSplit = expD.split("/");
	var tSplit = expT.split(":");
	var exp = new Date(dSplit[0],dSplit[1] - 1,dSplit[2],tSplit[0],tSplit[1],tSplit[2]);
	
	var userRating = "${sellerInfo.userRating}";
	
	$(document).ready(function() {
	    connect();
		
	    $("#charge_point_btn").on("click", function() {
	    	beforeLocationCheck();
	    });
	    
	    var stars = document.querySelectorAll('#seller_score .star');

	    for (var i = 0; i < userRating; i++) {
	        stars[i].src = 'https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png';
	    }

	    $('#place_bid_btn').click(function() {
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
	
	
    var AucStompClient = null;
    
    aucSeq = "${aucProdInfo.productSeq}";

    function connect() {
        var socket = new SockJS('/heehee/auctionws');
        AucStompClient = Stomp.over(socket);
        AucStompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            AucStompClient.subscribe('/topic/auction/' + aucSeq, function (response) {
            	console.log(response);
                showResponse(JSON.parse(response.body));
            });
        });
    }

    function sendBid() {
    	var now = new Date();

      	let gap = Math.max(exp.getTime() - now.getTime(), 0);
    	if(gap == 0) {
    		showTost("종료된 경매입니다.");
    		return false;
    	}
        var userId = "${userId}";
        var bidPrice = parseInt($('#auc_price').val()) + ${aucProdInfo.increasePrice};
        AucStompClient.send("/app/bid/"+aucSeq, {}, JSON.stringify({"aucProdSeq": aucSeq, "userId": userId, "bidPrice": bidPrice,"userNickName": "${userNickName}"}));
    }

    function showResponse(message) {
    	$("#auc_price").val(message.bidPrice);
        $(".price").text(message.bidPrice + "원");
        $("#joinCount").text(message.joinCount);
        $("#current_user_nickname").text(message.userNickName);
    }

   
    
    function fGet2char(num) {
    	return num < 10 ? "0" + num : String(num);
	}
	
	function remaindTime() {
	    var now = new Date();

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