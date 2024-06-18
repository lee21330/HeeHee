<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰</title>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/chatting.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function(){
	  $(".chatting-item").on("click", function() {
		  var nickname = $(this).children(".item-body").children(".name-count").children(".receiver-nickname").text();
		  
		  console.log(nickname);
	  });
	});

</script>
</head>
<body>

	<div id="headerArea">
		<!-- headerInclude 해줘야 해요 -->
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<div class="chatting-area">
		<!-- 왼쪽 채팅방 목록 영역 -->
		<ul class="chatting-list">
		<li class="chatroom-text">전체 대화</li>
		<c:forEach var="room" items="${roomList}">
		    <!-- 채팅방 목록 한칸 -->
			<li class="chatting-item" room-id="${room.id}" receiver-id="${room.receiverid}">
			    <!-- 왼쪽 상대방 사진 부분 -->
				<div class="item-header">
					<img class="receiver-image"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${room.receiverimg}">
				</div>
				<!-- 오른쪽 상대방 닉네임, 안 읽은 메세지 수, 최근 메세지 내용, 최근 메시지 보낸 날짜 -->
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname" >${room.receivernickname}</p>
						<p class="unread-count">${room.unreadcount}</p>
					</div>

					<div class="message-container">
						<span class="recent-message">${room.lastcontent}</span> <span class="send-time">${room.sendtime}</span>
					</div>
				</div>
			</li>
			</c:forEach>
			</ul>
			
			<!-- <li class="chatting-item">
				<div class="item-header">
					<img class="receiver-image"
						src="${path}/resources/images/pompompurin.png">
				</div>
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname">이두리</p>
						<p class="unread-count">3</p>
					</div>

					<div class="message-container">
						<span class="recent-message">폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고
							싶습니다. 폼폼푸린 구매하고 싶습니다.</span> <span class="send-time">5월 28일</span>
					</div>
				</div>
			</li>
			<li class="chatting-item">
				<div class="item-header">
					<img class="receiver-image"
						src="${path}/resources/images/pompompurin.png">
				</div>
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname">이두리</p>
						<p class="unread-count">3</p>
					</div>

					<div class="message-container">
						<span class="recent-message">폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고
							싶습니다. 폼폼푸린 구매하고 싶습니다.</span> <span class="send-time">5월 28일</span>
					</div>
				</div>
			</li>
			<li class="chatting-item">
				<div class="item-header">
					<img class="receiver-image"
						src="${path}/resources/images/pompompurin.png">
				</div>
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname">이두리</p>
						<p class="unread-count">3</p>
					</div>

					<div class="message-container">
						<span class="recent-message">폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고
							싶습니다. 폼폼푸린 구매하고 싶습니다.</span> <span class="send-time">5월 28일</span>
					</div>
				</div>
			</li>
			<li class="chatting-item">
				<div class="item-header">
					<img class="receiver-image"
						src="${path}/resources/images/pompompurin.png">
				</div>
				<div class="item-body">
					<div class="name-count">
						<p class="receiver-nickname">이두리</p>
						<p class="unread-count">3</p>
					</div>

					<div class="message-container">
						<span class="recent-message">폼폼푸린 구매하고 싶습니다. 폼폼푸린 구매하고
							싶습니다. 폼폼푸린 구매하고 싶습니다.</span> <span class="send-time">5월 28일</span>
					</div>
				</div>
			</li> -->
			
        <!-- 오른쪽 채팅방 채팅 메세지 내역 -->
		<div class="chatting-content">
		    <!-- 채팅 메세지 위 영역: 상대방 닉네임, 판매 물품 정보(이미지, 가격, 제품명) -->
			<div class="content-header">
				<p class="receiver-nickname">이두리</p>
				<div class="selling-info">
					<img class="selling-image"
						src="${path}/resources/images/pompompurin.png">
					<div class="price-name">
						<p class="selling-price">50000원</p>
						<p class="selling-name">폼폼푸린 아이스크림</p>
					</div>
					<!-- roomId 판매자/구매자별로 버튼 내용 다르게 하기 -->
					<button class="pay">결제하기</button>
				</div>
			</div>
			<!-- 채팅 메시지 내역 -->
			<div class="content-body">
				<div class="message-list">
				    <!-- 내 메세지 -->
					<div class="my-chat">
						<!-- 나중에 읽음/안읽음 처리 해주기 -->
						<span class="chatDate">14:58 읽음</span>
						<p class="chat">폼폼푸린 팔렸나요?</p>
					</div>
                    <!-- 상대 메세지 -->
					<div class="target-chat">
						<p class="chat">아직 안 팔렸어요.</p>
						<span class="chatDate">14:59 읽음</span>
					</div>
					
					<div class="my-chat">
						<!-- 나중에 읽음/안읽음 처리 해주기 -->
						<span class="chatDate">14:58 읽음</span>
						<p class="chat">폼폼푸린 팔렸나요?</p>
					</div>

					<div class="target-chat">
						<p class="chat">아직 안 팔렸어요.</p>
						<span class="chatDate">14:59 읽음</span>
					</div>
					
					<div class="my-chat">
						<!-- 나중에 읽음/안읽음 처리 해주기 -->
						<span class="chatDate">14:58 읽음</span>
						<p class="chat">폼폼푸린 팔렸나요?</p>
					</div>

					<div class="target-chat">
						<p class="chat">아직 안 팔렸어요.</p>
						<span class="chatDate">14:59 읽음</span>
					</div>
					
					<div class="my-chat">
						<!-- 나중에 읽음/안읽음 처리 해주기 -->
						<span class="chatDate">14:58 읽음</span>
						<p class="chat">폼폼푸린 팔렸나요?</p>
					</div>

					<div class="target-chat">
						<p class="chat">아직 안 팔렸어요.</p>
						<span class="chatDate">14:59 읽음</span>
					</div>
					
					<div class="my-chat">
						<!-- 나중에 읽음/안읽음 처리 해주기 -->
						<span class="chatDate">14:58 읽음</span>
						<p class="chat">폼폼푸린 팔렸나요?</p>
					</div>

					<div class="target-chat">
						<p class="chat">아직 안 팔렸어요.</p>
						<span class="chatDate">14:59 읽음</span>
					</div>
					
					<div class="my-chat">
						<!-- 나중에 읽음/안읽음 처리 해주기 -->
						<span class="chatDate">14:58 읽음</span>
						<p class="chat">폼폼푸린 팔렸나요?</p>
					</div>

					<div class="target-chat">
						<p class="chat">아직 안 팔렸어요.</p>
						<span class="chatDate">14:59 읽음</span>
					</div>
				</div>
			</div>
			<div class="chatting-input">
				<img class="input-photo" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/camera.png">
				<div>
					<input class="input-area" type="text">
				</div>
				<img class="input-send" src="${path}/resources/images/send.png">
			</div>
		</div>
	</div>

	<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
	<!-- https://github.com/sockjs/sockjs-client -->
	<!-- <script
		src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script> -->
	<script>
		// 로그인한 회원 번호 => 추후 수정
		loginMemberNo = "a";
	</script>
	<script type="text/javascript" src="/resources/js/chatting.js"></script>
</body>
</html>