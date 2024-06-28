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
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<%-- slick slider --%>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
</head>

<body>
	<%@ include file="../common/header.jsp" %>
	<div id="mainContainer">
		<div id="top_area">
			<img id="aucProdImg" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/auction/${aucProdInfo.imgName}">
		</div>
		<input id = "aucPrice" type="number" value="${aucProdInfo.aucPrice}" disabled>
		<button id="placeBidBtn">입찰하기</button>
		<button id="paymentBtn">결제하기</button>
		<button id="에러" onclick="error()"></button>
		<div id="response"></div>
	</div>
    <footer>
        <p>&copy; 2024 희희낙찰. All rights reserved.</p>
    </footer>
</body>
<script>
	$(document).ready(function() {
		
	    connect();
	    $('#placeBidBtn').click(function() {
	        sendBid();
	    });
	    
	    $('#paymentBtn').click(function() {
	        payment();
	    });
	});
	
	function payment() {
		IMP.init("imp22447463");
		IMP.request_pay(
				  {
				    pg: "html5_inicis.INIpayTest", //테스트 시 html5_inicis.INIpayTest 기재
				    pay_method: "card",
				    merchant_uid: "order_no_0004", //상점에서 생성한 고유 주문번호
				    name: "포인트:5000",
				    amount: 1,
				    buyer_email: "test@portone.io",
				    buyer_name: "sinsang",
				    buyer_tel: "010-1234-5678", //필수 파라미터 입니다.
				    buyer_addr: "서울특별시 강남구 삼성동",
				    buyer_postcode: "123-456",
				    m_redirect_url: "{모바일에서 결제 완료 후 리디렉션 될 URL}",
				    escrow: true, //에스크로 결제인 경우 설정
				    vbank_due: "20240725",
				    bypass: {
				      // PC 경우
				      acceptmethod: "noeasypay", // 간편결제 버튼을 통합결제창에서 제외(PC)
				      // acceptmethod: "cardpoint", // 카드포인트 사용시 설정(PC)
				      // 모바일 경우
				      P_RESERVED: "noeasypay=Y", // 간편결제 버튼을 통합결제창에서 제외(모바일)
				      // P_RESERVED: "cp_yn=Y", // 카드포인트 사용시 설정(모바일)
				      // P_RESERVED: "twotrs_bank=Y&iosapp=Y&app_scheme=your_app_scheme://", // iOS에서 계좌이체시 결제가 이뤄지던 앱으로 돌아가기
				    },
				    period: {
				      from: "20240101", //YYYYMMDD
				      to: "20241231", //YYYYMMDD
				    },
				  },
				  function (rsp) {
				    console.log(rsp);
				  },
				);
	}
	
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
	
	function remaindTime(seq, expDate, expTime) {
		var expD = expDate;
		var dSplit = expD.split("/");
		
		var expT = expTime;
		var tSplit = expT.split(":");
		
	    var now = new Date();
	    var exp = new Date(dSplit[0],dSplit[1] - 1,dSplit[2],tSplit[0],tSplit[1],tSplit[2]);

      	let gap = Math.max(exp.getTime() - now.getTime(), 0);
      	let day = Math.floor((gap / 3600000)/24);
      	let HH = fGet2char(Math.floor(gap / 3600000) % 24 + (day * 24));
      	let MM = fGet2char(Math.floor((gap % 3600000) / 60000));
      	let SS = fGet2char(Math.floor((gap % 60000) / 1000));
      
	    if((gap/60000) < 30) {
	    	$(".c_" + seq).removeClass("blue");
	    	$(".c_" + seq).addClass("red");
	    }
	    
	    $(".h_" + seq).html(HH);
	    $(".m_" + seq).html(MM);
	    $(".s_" + seq).html(SS);
	}
	
	function error() {
		$.ajax({
		    url: '/heehee/auc/detail/asdfasf',
		    method: 'GET',
		    success: function (data, status, xhr) {
		    	console.log(data);
		    	console.log(status);
		    	console.log(xhr);
		    },
		    error: function (data, status, err) {
		    	console.log(err);
		    }
		});
	}
    </script>
</html>