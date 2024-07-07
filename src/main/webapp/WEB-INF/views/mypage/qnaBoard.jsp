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
		<!-- 왼쪽 영역 -->
		<div class="left">
			<div id="note">
				<h1>1:1 Q&amp;A</h1>
				<p>제품 사용, 오염, 전용 박스 손상, 라벨 제거, 사은품 및 부속 사용/분실 시, 교환/환불이 불가능 합니다.</p>
				<p>고객님의 주문내역을 선택, 질문이 필요한 상품을 선택하시면 1:1상담이 가능합니다.</p>
				<p>주문취소/교환/환불은 마이페이지>주문내역에서 신청하실 수 있습니다.</p>
				<p>1:1문의 처리 내역은 마이페이지>1:1문의를 통해 확인하실수 있습니다.</p>
				<p>상품 정보(사이즈, 실측, 예상 배송일 등) 관련 문의는 해당 상품 문의에 남기셔야 빠른 답변이 가능합니다.</p>
			</div>


			<form id="qna" method="post"
				action="${path}/mypage/qnaBoard/insertQna"
				enctype="multipart/form-data">
				<h1>1:1문의하기</h1>
				<div class="qna_item">
					<p>문의제목</p>
					<input type="text" id="qna_title" name="qnaTitle"
						required="required">
				</div>
				<div class="qna_item">
					<p>문의유형</p>
					<div class="type">
						<c:forEach var="op" items="${qnaOption}" varStatus="loop">
							<div class="radioContainer">
								<input type="radio" id="${op.qnaOption}" name="seqQnaOption"
									value="${op.seqQnaOption}"
									onclick="showQnaOptionContent('${op.qnaOptionContent}')"
									required="required" ${loop.first ? 'checked' : ''}> <label
									for="${op.qnaOption}" class="radioLabel">${op.qnaOption}</label>
							</div>
						</c:forEach>
						<p id="qnaOptionContent"></p>
					</div>
				</div>
				<div class="qna_item">
					<p>문의내용</p>
					<input type="text" name="qnaContent" required="required">
				</div>
				<div class="qna_item">
					<p>사진첨부 최대(3개)</p>

					<div>
						<img id="img_preview" class="img_preview"
							src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/file.jpg"
							onclick="selectFileInput()"> 
							
						<!-- 	<input type="file"
							class="input_file" id="input_file" name="uploadImgs"
							accept="image/*" onchange="readURL(this);" /> -->
							 <input type="file" id="input_file" class="input_file" name="uploadImgs" accept="image/*" multiple onchange="previewImages(event);">

						<div id="preview_container">
							<!-- <img id="preview" class="preview"> <button type="button" class="remove_img">x</button>  -->
						</div>
					</div>
				</div>
				<div class="btn">
					<%-- <div id="cancel" class="btn_cancel" onclick="location.href='${path}/mypage/qnaBoard'">취소</div> --%>
					<button class="btn_submit">작성하기</button>
				</div>
			</form>
		</div>

		<!-- 오른쪽 영역 -->
		<div class="right">
			<div id="faq">
				<h1>
					<a href="${path}/mypage/faqBoard">Best FAQ</a>
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
			<h1>나의 문의</h1>
			<div id="myQna">

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
							<c:forEach var="img" items="${myq.imgList}">
								<img class="qnaFile"
									src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/qnaBoard/${img.imgName}">
							</c:forEach>
							<button id="delete"
								onclick="location.href='${path}/mypage/qnaBoard/deleteQna?seqQnaBno=${myq.seqQnaBno}'">삭제</button>
						</div>
						<div class="qnaAns">${myq.qnaAns}</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="footerArea">
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>

</html>