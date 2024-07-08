<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" />

        <link rel="stylesheet" href="${path}/resources/css/auction/pointModal.css">

        <body>
            <script>
            var point = "${userInfo.userPoint}";
                $(function () {
                    $("#btn-point").on("click", show);
                    $(".mModal_close").on("click", hide);
                    $(".btn_cancel").on("click", hide);
                    function show() {
                    	if("${userId}" == "") {openLogin(); return false;}
                        $("#pModal").addClass("show");
                        $("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
                    }
                    function hide() {
                        $("#pModal").removeClass("show");
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
                				console.log("결제 완료 테스트입니다.");
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
                            .then(result => window.location.reload())
                            .catch(err => console.log(err));
                            return true;
                        },
                        error: function (data, status, err) {
                            console.log(err);
                        }
                    });
                    
                }
            </script>

            <div class="mModal" id="pModal">
                <div class="mModal_body">
                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                        alt="로그인 창 닫기 아이콘" class="mModal_close">
                    <div class="modal_form">
                        <p class="modal_info">현재 포인트: </p>
                        <fmt:formatNumber value="${userInfo.userPoint}" pattern="#,###" var="formattedPoint"/>
                        <p>${formattedPoint}원</p>
                        <input type="number" id="charge" placeholder="충전할 금액을 입력해 주세요.">
                        <div class="btn_modal">
                            <button class="btn_submit" onclick="chargePoint(${userInfo.userPoint})">충전하기</button>
                            <button class="btn_cancel">취소하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </body>