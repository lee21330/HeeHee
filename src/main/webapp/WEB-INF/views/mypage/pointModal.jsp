<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
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

.modal_form p {
	margin: 0px 5px 60px 5px !important;
	font-size: 27px;
	display: inline-block;
}

.modal_info {
	color: #3F51A1;
}

.btn_modal {
	width: 100%;
	text-align: center;
	margin: 0px 0px 20px 0px;
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

#charge {
	width: 400px;
	height: 60px;
	border-width: 0 0 2px;
	margin: 0px 0px 60px 0px;
	font-size: 20px;
}

.align_point {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<body>
	<script>
                $(function () {
                	 $("#pModal").hide();
                    $("#btn-point").on("click", show);
                    $(".profileModalClose").on("click", hide);
                    function show() {
                        $("#pModal").show();
                        $("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
                    }
                    function hide() {
                        $("#pModal").hide();
                        $("#charge").val("");
                        $("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
                    }
                });
             // 충전하기 모달
                function chargePoint(point) {
                	var amount = $("#charge").val();
                	payForPoint("포인트 충전", amount, point);
                }

                function payForPoint(payName, amount, point) {
                	$.ajax({
                		url: '/heehee/pay/before',
                		method: 'POST',
                		data: {
                			"payName": payName,
                			"amount": amount
                		},
                		success: function(data, status, xhr) {
                			payment(payName,amount, data, point);
                			return true;
                		},
                		error: function(data, status, err) {
                			console.log(err);
                		}
                	});
                }

                function payment(payName, newPoint, payInfo, point) {
                	IMP.init("imp22447463");
                	IMP.request_pay(
                		{
                			pg: "html5_inicis.INIpayTest", //테스트 시 html5_inicis.INIpayTest 기재
                			pay_method: "card",
                			merchant_uid: payInfo.paySeq, //상점에서 생성한 고유 주문번호
                			name: payName,
                			amount: 1,
                			buyer_email: payInfo.buyerEmail,
                			buyer_name: payInfo.buyerId,
                			buyer_tel: payInfo.buyerTel, //필수 파라미터 입니다.
                			buyer_addr: payInfo.buyerTel,
                			buyer_postcode: "123-456",
                			m_redirect_url: "{모바일에서 결제 완료 후 리디렉션 될 URL}",
                			escrow: true, //에스크로 결제인 경우 설정
                			vbank_due: "20240725",
                			bypass: {
                				acceptmethod: "noeasypay", // 간편결제 버튼을 통합결제창에서 제외(PC)
                				P_RESERVED: "noeasypay=Y", // 간편결제 버튼을 통합결제창에서 제외(모바일)
                			},
                			period: {
                				from: "20240101", //YYYYMMDD
                				to: "20241231", //YYYYMMDD
                			},
                		}, function(rsp) {
                			if (rsp.success) {
                				completePayment(payInfo.paySeq, newPoint, point);
                				return true;
                			}
                		}
                	);
                }

                function completePayment(paySeq,newPoint, point) {
                	$.ajax({
                        url: '/heehee/pay/complete',
                        method: 'PUT',
                        contentType: 'application/json',
                        data: JSON.stringify({ "paySeq": paySeq }),
                        success: function (data, status, xhr) {
                            console.log(data);
                            console.log(status);
                            console.log(xhr);
                            fetch("/heehee/mypage/chargePoint",{
                                method : "PUT",
                                headers : {"Content-Type": "application/json"},
                                body : JSON.stringify({
                                "point" : newPoint,
                                "userPoint" : point
                                })
                            })
                            .then(resp => resp.text())
                            .then(result => console.log(result))
                            .catch(err => console.log(err));
                            return true;
                        },
                        error: function (data, status, err) {
                            console.log(err);
                        }
                    });
                    window.location.reload();
                }
            </script>

	<div class="profileModal" id="pModal">
		<div class="profileModalBody">
			<img
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
				alt="로그인 창 닫기 아이콘" class="profileModalClose">
			<div class="modal_form">
				<div class="align_point">
					<p class="modal_info">현재 포인트:</p>
					<fmt:formatNumber value="${sellerInfo.userPoint}" pattern="#,###"
						var="formattedPoint" />
					<p>${formattedPoint}원</p>
				</div>
				<div class="align_point">
					<input type="number" id="charge" placeholder="충전할 금액을 입력해 주세요.">
				</div>
				<div class="btn_modal">
					<button class="btn_submit"
						onclick="chargePoint(${sellerInfo.userPoint})">충전하기</button>

				</div>
			</div>
		</div>
	</div>
</body>