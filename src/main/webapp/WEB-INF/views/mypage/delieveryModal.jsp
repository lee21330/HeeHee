<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<link rel="stylesheet" href="${path}/resources/css/myPageModal.css">
<style>
.profileModal {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: 0 auto;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
	z-index: 100;
}

.profileModalBody {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 600px;
	height: 300px;
	background-color: rgb(255, 255, 255);
	border-radius: 10px;
	box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
	transform: translateX(-50%) translateY(-60%);
	padding: 40px;
	z-index: 99;
}

.profileModalClose {
	width: 20px;
	cursor: pointer;
	float: right;
}

.modal_form {
	height: 90%;
	padding: 20px 0px 20px 0px;
}

#dMdal_p {
	display: block;
	width: 100%;
	height: 30px;
	font-size: 30px;
	margin: 0px 0px 70px 0px;
	text-align: center;
	color: #3F51A1;
}

#input_d {
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 15px;
	margin: 0px 0px 70px 0px;
}

.Modal_delivery select {
	color: rgb(63, 81, 161);
	width: 200px;
	height: 50px;
	font-size: 17px;
	border: 1px solid rgb(189, 189, 189);
	border-radius: 10px;
	text-align: center;
}

.Modal_delivery input {
	width: 300px;
	height: 50px;
	padding: 0px;
	font-size: 17px;
	border: 1px solid rgb(189, 189, 189);
	border-radius: 10px;
	padding-left: 10px;
}

#d_btn {
	display: flex;
	align-items: center;
	justify-content: center;
}
.btn_submit {
	cursor: pointer;
	width: 150px;
	height: 50px;
	background: #3F51A1;
	border: none;
	border-radius: 5px;
	color: white;
	margin: 0px 5px 0px 5px;
	font-size: 20px;
} 
</style>
<body>
	<script>
		$(function() {
			$("#deliveryModal").hide();

			$("#enter_invoice").on("click", show);
			$(".profileModalClose").on("click", hide);
			function show() {
				$("#deliveryModal").show();
				$("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
			}
			function hide() {
				$("#deliveryModal").hide();
				$(".Modal_delivery input").val("");
				$(".Modal_delivery select").val("택배사 선택");
				$("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
			}
		});
		function maxLengthCheck(object) {
	   	    if (object.value.length > object.maxLength) {
	   	        object.value = object.value.slice(0, object.maxLength);
	   	    }    
	   	}
	</script>

	<div class="profileModal" id="deliveryModal">
		<div class="profileModalBody">
			<img
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
				alt="로그인 창 닫기 아이콘" class="profileModalClose">
			<form class="modal_form"
				action="${path}/mypage/saledetail/${saleDetail.productSeq}/insertDelivery"
				method="post">
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
						<input type="number" placeholder="운송장 번호 -없이 입력" name="dNumber"maxlength="9" oninput="maxLengthCheck(this)">
					</div>
					<input type="hidden" name="buyerId" value="${saleDetail.buyerId}">
					<input type="hidden" name="sSeq" value="${saleDetail.SSeq}">
				</div>
				<div class="btn_modal" id="d_btn">
					<button class="btn_submit" id="btn_delivery">입력</button>
				</div>
			</form>
		</div>
	</div>
</body>