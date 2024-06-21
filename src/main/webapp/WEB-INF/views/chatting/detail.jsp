<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ㅎㅇ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<%-- slick slider --%>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
</head>

<body>
	<%@ include file="../common/header.jsp" %>
	<div id="mainContainer">
		<input id = "aucPrice" type="number" value="10000" disabled>
		<button id="placeBidBtn">입찰하기</button>
		<div id="response"></div>
	</div>
    <footer>
        <p>&copy; 2024 희희낙찰. All rights reserved.</p>
    </footer>
</body>
<script>
        var stompClient = null;
        
        aucSeq = ${aucSeq};

        function connect() {
            var socket = new SockJS('/heehee/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/auction', function (response) {
                	console.log(response);
                    showResponse(JSON.parse(response.body).bidPrice);
                });
            });
        }

        function sendBid() {
            var username = 'sinsang';
            var amount = parseInt($('#aucPrice').val()) + 1000;
            stompClient.send("/app/bid/"+aucSeq, {}, JSON.stringify({'username': username, 'amount': amount}));
        }

        function showResponse(message) {
            $('#response').append('<p>' + message + '</p>');
        }

        $(document).ready(function() {
            connect();
            $('#placeBidBtn').click(function() {
                sendBid();
            });
        });
    </script>
</html>