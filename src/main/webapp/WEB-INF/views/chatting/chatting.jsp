<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

	<%-- modal --%>
	<!--가격 수정하기 모달  -->
	<div class="chattingModal">
		<%--  <div class="modal-content">
	        <div class="current-price">기존 가격 : 50000원</div>
	        <div class="current-input">
	  	  	    <input type="number" class="new-price" placeholder="수정할 가격을 입력해주세요. (원)">
	        </div>
	        <div class="chModalBtn">
	            <button class="submit-price">수정하기</button>
	            <button class="cancel">취소하기</button>
	        </div>
	    </div>
	</div>
	
	<!-- 결제하기 모달(성공) -->
	<div class="chattingModal">
	    <div class="modal-content2">
	        <div>결제에 성공하였습니다!</div>
	    </div>
	</div>
	
	<!-- 결제하기 모달(충전) -->
	<div class="chattingModal">
	    <div class="modal-content">
	        <div class="info">포인트가 부족합니다.</div>
	        <div class="current-account">현재 계좌 : 123 - 456 - 789000 </div>
	        <div class="current-input3">
	  	  	    <input type="number" class="add-point" placeholder="충전할 금액을 입력해주세요. (원)">
	        </div>
	        <div class="chModalBtn3">
	            <button class="submit-price">충전하기</button>
	            <button class="cancel">취소하기</button>
	        </div>
	    </div>
	</div> --%>
	</div>

	<div class="chatting-area">
		<!-- 왼쪽 채팅방 목록 영역 -->
		<ul class="chatting-list">
			<li class="chatroom-text">전체 대화</li>
			<c:if test="${fn:length(roomList) > 0}">
			<c:forEach var="room" items="${roomList}">
				<!-- 채팅방 목록 한칸 -->
				<li class="chatting-item" room-id="${room.id}" receiver-id="${room.receiverid}">
					<!-- 왼쪽 상대방 사진 부분 -->
					<div class="item-header">
					    <c:choose>
                            <c:when test="${not empty room.receiverimg}">
                                <img class="receiver-image"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${room.receiverimg}">
                            </c:when>
                            <c:otherwise>
                                <img class="receiver-image"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg">
                            </c:otherwise>
                            </c:choose>
					</div> <!-- 오른쪽 상대방 닉네임, 안 읽은 메세지 수, 최근 메세지 내용, 최근 메시지 보낸 날짜 -->
					<div class="item-body">
						<div class="name-count">
							<c:choose>
                            <c:when test="${not empty room.receivernickname}">
                                <p class="receiver-nickname">${room.receivernickname}</p>
                            </c:when>
                            <c:otherwise>
                                <p class="receiver-nickname">(알 수 없음)</p>
                            </c:otherwise>
                            </c:choose>
							<c:if test="${room.unreadcount > 0}">
								<p class="unread-count">${room.unreadcount}</p>
							</c:if>
						</div>

						<div class="message-container">
					    	<c:choose>
                         	   <c:when test="${fn:substring(room.lastcontent, 0, 12) == '[img_asdfzv]'}">
                          	      <span class="recent-message">사진</span>
                         	   </c:when>
                         	   <c:otherwise>
                          	      <span class="recent-message">${room.lastcontent}</span>
                        	    </c:otherwise>
                        	    </c:choose>
							<span class="send-time">${room.sendtime}</span>
						</div>
					</div>
				</li>
			</c:forEach>
			</c:if>
			<c:if test="${fn:length(roomList) == 0}">
			<div class = "noneRoom">
                <span>지금 바로 대화를 시작해보세요💭</span>
                <p>내가 올린 상품이나 관심있는 상품에 관한 대화를<br>여기서 볼 수 있어요.</p>
            </div>
            </c:if>
		</ul>

		<%-- <li class="chatting-item">
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
			</li> --%>

		<!-- 오른쪽 채팅방 채팅 메세지 내역 -->
		<div class="chatting-content">
			<!-- 채팅 메세지 위 영역: 상대방 닉네임, 판매 물품 정보(이미지, 가격, 제품명) -->
			<div class="content-header">
				<%--     <p class="receiver-nickname">이두리</p>
				<div class="selling-info">
					<img class="selling-image"
						src="${path}/resources/images/pompompurin.png">
					<div class="price-name">
						<p class="selling-price">50000원</p>
						<p class="selling-name">폼폼푸린 아이스크림</p>
					</div>
					<button class="payEdit">결제하기</button>
				</div>--%>
			</div>
			<!-- 채팅 메시지 내역 -->
			<div class="content-body">
			<c:if test="${fn:length(roomList) == 0}">
		    	<div class = "noneRoomChat">
              	  <span>대화방을 선택해주세요💭</span>
          	  </div>
            </c:if>
				<%-- <div class="message-list">
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

				<!-- 이미지 메세지 -->
				<div class="my-chat">
					<span class="chatDate">14:58 읽음</span>
					<img class="chat-image"
					     src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/purin.png"
						 alt="image">
				</div>
				<div class="target-chat">
					<img class="chat-image"
						 src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/purin.png"
						 alt="image">
				    <span class="chatDate">14:59 읽음</span>
				</div>--%>
			</div>
			
			<div class="chatting-input">
				<%--	<img class="input-photo" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/camera.png">
				<input type="file" class="image-input" multiple style="display: none;">
				<div>
					<input class="input-area" type="text">
				</div>
				<img class="input-send" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/send.png">
				--%>
			</div>
		</div>
	</div>

	<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
	<!-- https://github.com/sockjs/sockjs-client -->
	<script
		src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script>
		// 로그인한 회원 번호 => 추후 수정
		loginMemberNo = "${userId}";
	</script>
	<script type="text/javascript" src="/heehee/resources/js/chatting.js"></script>
</body>
</html>