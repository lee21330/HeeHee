<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" />

        <link rel="stylesheet" href="${path}/resources/css/proStatusModifyModal.css">

        <body>
            <script>
                $(function () {
                    $("#gobuy").on("click", show);
                    $(".mModal_close").on("click", hide);
                    function show() {
                        $("#aModal").addClass("show");
                        $("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
                    }
                    function hide() {
                        $("#aModal").removeClass("show");
                        $("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
                    }
                    
                    /* $("#to_reserve_btn").on("click", toReserve);
                    $("#cancel_reserve_btn").on("click", cancelReserve); */
                    
                    $(".btn_putoff").on("click", toPutOff);
                    $(".btn_delete").on("click", toDelete);
                    $(".btn_selling").on("click", cancelReserve);
                });
                
                function toReserve() {
                	var productSeq = ${info.productSeq};
                	$.ajax({
                        url: '/heehee/sell/reserve',
                        method: 'PUT',
                        contentType: 'application/json',
                        data: JSON.stringify({ "productSeq": productSeq }),
                        success: function (data, status, xhr) {
                            console.log(data);
                            if(data.success == true) {
	                            showTost(data.message);
	                            $("#current_status").text("현재 상태: 예약중");
	                            $("#to_reserve_btn").off("click");
	                            $("#to_reserve_btn").text("예약취소");
	                            $("#to_reserve_btn").attr("id", "cancel_reserve_btn");
	                            $("#cancel_reserve_btn").on("click", cancelReserve);
                            } else {
                            	showTost(data.message);
                            }
                        },
                        error: function (data, status, err) {
                            console.log(err);
                        }
                    });
                }
                
                function cancelReserve() {
                	var productSeq = ${info.productSeq};
                	$.ajax({
                        url: '/heehee/sell/cancelreserve',
                        method: 'PUT',
                        contentType: 'application/json',
                        data: JSON.stringify({ "productSeq": productSeq }),
                        success: function (data, status, xhr) {
                            console.log(data);
                            if(data.success == true) {
	                            showTost(data.message);
	                            $("#current_status").text("현재 상태: 판매중");
	                            $("#cancel_reserve_btn").off("click");
	                            $("#cancel_reserve_btn").text("예약하기");
	                            $("#cancel_reserve_btn").attr("id", "to_reserve_btn");
	                            $("#to_reserve_btn").on("click", toReserve);
                            } else {
                            	showTost(data.message);
                            }
                        },
                        error: function (data, status, err) {
                            console.log(err);
                        }
                    });
                }
                
                function toPutOff() {
                	var productSeq = ${info.productSeq};
                	$.ajax({
                        url: '/heehee/sell/putoff',
                        method: 'PUT',
                        contentType: 'application/json',
                        data: JSON.stringify({ "productSeq": productSeq }),
                        success: function (data, status, xhr) {
                            console.log(data);
                            if(data.success == true) {
	                            showTost(data.message);
	                            window.location.href = '/heehee/mypage/main';
                            } else {
                            	showTost(data.message);
                            }
                        },
                        error: function (data, status, err) {
                            console.log(err);
                        }
                    });
                }
                
                function toDelete() {
                	var productSeq = ${info.productSeq};
                	$.ajax({
                        url: '/heehee/sell/delete',
                        method: 'PUT',
                        contentType: 'application/json',
                        data: JSON.stringify({ "productSeq": productSeq }),
                        success: function (data, status, xhr) {
                            console.log(data);
                            if(data.success == true) {
	                            showTost(data.message);
	                            window.location.href = '/heehee/mypage/main';
                            } else {
                            	showTost(data.message);
                            }
                        },
                        error: function (data, status, err) {
                            console.log(err);
                        }
                    });
                }
            </script>

            <div class="mModal" id="aModal">
                <div class="mModal_body">
                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                       alt="로그인 창 닫기 아이콘" class="mModal_close">

                       <p id="current_status" class="modal_info">현재 상태: ${info.proStatus}</p>

					<c:if test="${info.proStatus == '판매중'}">
                       <div class="btn_modal">
                           <!-- <button id="to_reserve_btn" class="btn_selected">예약하기</button> -->
                           <button class="btn_nonsel btn_putoff">판매 보류하기</button>
                           <button class="btn_nonsel btn_delete">물품 삭제하기</button>
                       </div>
                    </c:if>
                    <%-- <c:if test="${info.proStatus == '예약중'}">
                       <div class="btn_modal">
                           <!-- <button id="cancel_reserve_btn" class="btn_selected">예약취소</button> -->
                           <button class="btn_nonsel btn_putoff">판매보류</button>
                           <button class="btn_nonsel btn_delete">삭제하기</button>
                       </div>
                    </c:if> --%>
                    <c:if test="${info.proStatus == '판매보류'}">
                       <div class="btn_modal">
                           <!-- <button id="to_reserve_btn" class="btn_selected">예약하기</button> -->
                           <button class="btn_nonsel btn_selling">판매중으로 변경</button>
                           <button class="btn_nonsel btn_delete">물품 삭제하기</button>
                       </div>
                    </c:if>
                </div>
            </div>
        </body>