<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>1:1문의</title>
<link rel="stylesheet" href="${path}/resources/css/qnaBoard.css">
</head>

<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="/heehee/resources/js/qnaBoard.js"></script>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<div class="main_container">
		<div class="left">
			<div id="note">
				<h1>1:1 Q&amp;A</h1>
				<p>제품 사용, 오염, 전용 박스 손상, 라벨 제거, 사은품 및 부속 사용/분실 시, 교환/환불이 불가능 합니다.</p>
				<p>고객님의 주문내역을 선택, 질문이 필요한 상품을 선택하시면 1:1상담이 가능합니다.</p>
				<p>주문취소/교환/환불은 마이페이지>주문내역에서 신청하실 수 있습니다.</p>
				<p>1:1문의 처리 내역은 마이페이지>1:1문의를 통해 확인하실수 있습니다.</p>
				<p>상품 정보(사이즈, 실측, 예상 배송일 등) 관련 문의는 해당 상품 문의에 남기셔야 빠른 답변이 가능합니다.</p>
			</div>

			<form id="qna" action="${path}/qnaBoard/insertQna" method="post">
				<h1>1:1문의하기</h1>
				<div class="qna_item">
					<p>문의제목</p>
					<input type="text" id="qna_title" name="QNA_TITLE">
				</div>
				<div class="qna_item">
					<p>문의유형</p>
					<div class="type">
						<c:forEach var="op" items="${qnaOption}">
							<div class="radioContainer">
								<input type="radio" id="${op.qnaOption}" name="SEQ_QNA_OPTION" value="${op.seqQnaOption}">
								<label for="${op.qnaOption}" class="radioLabel">${op.qnaOption}</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="qna_item">
					<p>문의내용</p>
					<textarea name="QNA_CONTENT"></textarea>
				</div>
				<div class="qna_item">
					<p>첨부파일</p>
					<img id="img_preview"
						src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/file.jpg">
					<input type="file" id="input_file" name="imgName">
				</div>
				<div class="btn">
					<button class="btn_cancel">취소</button>
					<button class="btn_submit">작성하기</button>
				</div>
			</form>
		</div>
		<div class="right">
			<div id="faq">
				<h1>
					<a href="${path}/faqBoard">Best FAQ</a>
				</h1>
				<c:forEach var="fa" items="${faq}">
					<div class="header">
						<img id="img_q"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
						<p>[${fa.qnaOption}] ${fa.faqContent}</p>
					</div>
					<div class="content">${fa.faqAns}</div>
				</c:forEach>
			</div>
			<div id="myQna">
				<h1>나의 문의</h1>
				<c:forEach var="myq" items="${myQna}">
					<div class="header">
						<img id="img_q"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
						<p class="qnaTitle">[${myq.qnaOption}] ${myq.qnaTitle}</p>
						<div class="ansStatus"></div>
					</div>
					<div class="content">
						<div class="qnaContent">
							<p>${myq.qnaContent}</p>
							<img class="qnaFile"
								src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${myq.qnaFile}">
								<button>수정</button>
								<button>삭제</button>
						</div>
						<div class="qnaAns">${myq.qnaAns}</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>

</html>