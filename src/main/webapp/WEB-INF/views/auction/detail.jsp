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
	    	payForPoint("포인트:5000", 5000);
	    });
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