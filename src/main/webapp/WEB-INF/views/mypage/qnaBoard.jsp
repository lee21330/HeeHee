<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의</title>
<link rel="stylesheet" href="${path}/resources/css/qnaboard.css">
</head>
<body>
    <header>
        <%@include file="../common/header.jsp"%>
    </header>
    <div class="main_container">
	   	<div>
		    <h1>1:1 Q&A</h1>
		    <p> 제품 사용, 오염, 전용 박스 손상, 라벨 제거, 사은품 및 부속 사용/분실 시, 교환/환불이 불가능 합니다.</p>
		    <p>고객님의 주문내역을 선택, 질문이 필요한 상품을 선택하시면 1:1상담이 가능합니다.</p>
		    <p>주문취소/교환/환불은 마이페이지>주문내역에서 신청하실 수 있습니다.</p>
		    <p>1:1문의 처리 내역은 마이페이지>1:1문의를 통해 확인하실수 있습니다.</p>
		    <p>상품 정보(사이즈, 실측, 예상 배송일 등) 관련 문의는 해당 상품 문의에 남기셔야 빠른 답변이 가능합니다.</p>
	    </div>
	    <form>
            <div class="qnaboard">
                <div class="qna">
                    <h1>1:1문의하기</h1>
                    <hr>
                    <div class="qna_item">
                        <p>문의유형</p>
                        <ul>
                            <li>배송</li>
                            <li>주문/결제</li>
                            <li>취소/교환/환불</li>
                            <li>회원정보</li>
                            <li>상품확인</li>
                            <li>서비스</li>
                        </ul>
                    </div>
                    <div class="qna_item">
                        <p>문의내용</p>
                        <textarea></textarea>
                    </div>
                    <div class="qna_item">
                        <p>첨부파일</p>
                        <img id="img_preview" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/%E1%84%8E%E1%85%A5%E1%86%B7%E1%84%87%E1%85%AE%E1%84%91%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AF.jpg">
                        <input type="file" id="input_file">
                    </div>
                    
                </div>
                <div class="faq">
                    <h1>Best FAQ</h1>
                    <hr>
                    <ul>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[탈퇴/기타] 회원 탈퇴는 어떻게 하나요?</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[로그인/정보] 아이디와 비밀번호가 기억나지 않아요.</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[상품 문의] 구매했을 때보다 가격이 떨어졌어요 차액 환불이 되나요?</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[배송 일반] 출고가 지연된다는 알림톡을 받았어요.</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[기타] 배송 완료 상품을 받지 못했어요.</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[기타] 송장 흐름 확인이 안되고 있어요.</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[배송 일반] 배송 조회는 어떻게 하나요?</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[배송 일반] 고객 보상 지원 제도가 무엇인가요?</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[배송 일반] 일반 배송 상품은 언제 배송 되나요?</li>
                        <li><img id="img_q" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/Q%E1%84%8B%E1%85%B5%E1%84%86%E1%85%A9%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png">[취소/반품(환불)] 반송장을 입력하라고 하는데, 반송장 입력 버튼이 보이지 않아요.</li>
                    </ul>
                    
                </div>
            </div>
            <div class="btn">
            	<button class="btn_cancel">취소</button>
            	<button class="btn_submit">작성하기</button>
           	</div>
            
        </form>
    </div>
    <script>
      $(function() {
            $("#img_preview").click(function () {
            $("#input_file").click();
          });
        });
    </script>
</body>
</html>