<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" />

        <link rel="stylesheet" href="${path}/resources/css/myPageModal.css">

        <body>
            <script>
                $(function () {
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
                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                        alt="로그인 창 닫기 아이콘" class="mModal_close">
                    <form class="modal_form">
                    <p id="dMdal_p">송장 입력하기</p>
                        <div class="input" id="input_d">                       
                            <div class="Modal_delivery">                      	
                                <select>
                                    <option>택배사 선택</option>
                                    <option>CJ대한통운</option>
                                    <option>우체국택배</option>
                                    <option>배송하기좋은날 (SHIPNERGY)</option>
                                </select>
                            </div>

                            <div class="Modal_delivery">
                                <input type="text" placeholder="운송장 번호 -없이 입력">
                            </div>
                        </div>

                        <div class="btn_modal" id="d_btn">
                            <button type="submit" class="btn_submit" id="btn_delivery">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </body>