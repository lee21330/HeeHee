<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<link rel="stylesheet" href="${path}/resources/css/myPageModal.css">

<body>
	<script>
		$(function() {
			$("#enter_invoice").on("click", show);
			$(".mModal_close").on("click", hide);
			function show() {
				$(".mModal").addClass("show");
				$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
			}
			function hide() {
				$(".mModal").removeClass("show");
				$(".Modal_delivery input").val("");
				$(".Modal_delivery select").val("택배사 선택");
				$("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
			}
		});
	</script>

	<div class="mModal">
		<div class="mModal_body">
			<img
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
				alt="로그인 창 닫기 아이콘" class="mModal_close">
			<form class="modal_form" action="${path}/mypage/saledetail/${saleDetail.productSeq}/insertDelivery" method="post">
				<p id="dMdal_p">송장 입력하기</p>
				<div class="input" id="input_d">
					<div class="Modal_delivery">
						<select name="dCompanySeq">
							<option>택배사 선택</option>
							<c:forEach var="dc" items="${dcOption}">
								<option value="${dc.DCompanySeq}">${dc.DCompany}</option>
							</c:forEach>
						</select>
					</div>

					<div class="Modal_delivery">
						<input type="number" placeholder="운송장 번호 -없이 입력" name="dNumber">
					</div>
					<input type="hidden" name ="buyerId" value="${saleDetail.buyerId}">
>				</div>
				<input type="number" name="sSeq" value = "${saleDetail.SSeq}">
				<div class="btn_modal" id="d_btn">
					<button class="btn_submit" id="btn_delivery" >입력</button>
				</div>
			</form>
		</div>
	</div>
</body>