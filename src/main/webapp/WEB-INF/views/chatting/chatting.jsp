<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰</title>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/chatting.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<div id="headerArea">
		<!-- headerInclude 해줘야 해요 -->
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>

	<div class="chatting-area">
		<p class="chatroom-text">전체 대화</p>
		<!-- C로 돌리기 -->
		<ul class="chatting-list">
			<li class="chatting-item">
				<div class="item-header">
					<img class="receiver-image" src="${path}/resources/images/pompompurin.png">
				</div>
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname">이두리</p>
						<p class="unread-count">3</p>
					</div>

					<div class="message-container">
						<span class="recent-message">폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고 싶습니다.</span>
						<span class="send-time">5월 28일</span>
					</div>

				</div>
			</li>
			<li class="chatting-item">
				<div class="item-header">
					<img class="receiver-image" src="${path}/resources/images/pompompurin.png">
				</div>
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname">이두리</p>
						<p class="unread-count">3</p>
					</div>

					<div class="message-container">
						<span class="recent-message">폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고 싶습니다.</span>
						<span class="send-time">5월 28일</span>
					</div>

				</div>
			</li>
		</ul>
	</div>

	<div class="chatting">
	<!-- C로 돌리기 -->
	  <div>
	      
	  </div>
	</div>  
	       

	<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
	<!-- https://github.com/sockjs/sockjs-client -->
	<!-- <script
		src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script> -->
	<script>
      // 로그인한 회원 번호 => 추후 수정
      const loginMemberNo = "test";
   </script>
	<script type="text/javascript" src="/resources/js/chatting.js"></script>
</body>
</html>