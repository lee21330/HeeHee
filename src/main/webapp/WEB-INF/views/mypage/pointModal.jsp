<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" />

        <link rel="stylesheet" href="${path}/resources/css/myPageModal.css">

        <body>
            <script>
                $(function () {
                    $("#btn-point").on("click", show);
                    $("#pModal_close").on("click", hide);
                    $("#btn_cancel").on("click", hide);
                    function show() {
                        $("#pModal").addClass("show");
                        $("body").css("overflow", "hidden"); /* 모달 열리면 스크롤 불가능 */
                    }
                    function hide() {
                        $("#pModal").removeClass("show"); /* 로그인 */
                        $("#charge").val("");
                        $("body").css("overflow", "scroll"); /* 모달 닫히면 스크롤 가능 */
                    }
                });
            </script>

            <div id="pModal">
                <div id="pModal_body">
                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                        alt="로그인 창 닫기 아이콘" id="pModal_close">
                    <form class="modal_form">
                        <p class="modal_acc">현재 포인트: </p>
                        <p>1,000,000원</p>
                        <input type="number" class="charge" placeholder="충전할 금액을 입력해 주세요.">
                        <div class="btn_modal">
                            <button type="submit" id="btn_submit">충전하기</button>
                            <button id="btn_cancel">취소하기</button>
                        </div>
                    </form>
                </div>
            </div>
        </body>