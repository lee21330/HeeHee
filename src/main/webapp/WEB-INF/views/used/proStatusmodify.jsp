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
                });
            </script>

            <div class="mModal" id="aModal">
                <div class="mModal_body">
                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                       alt="로그인 창 닫기 아이콘" class="mModal_close">

                       <p class="modal_info">현재 상태: ${info.proStatus}</p>

					<c:if test="${info.proStatus == '판매중'}">
                       <div class="btn_modal">
                           <button class="btn_selected">예약중</button>
                           <button class="btn_nonsel">판매보류</button>
                           <button class="btn_nonsel">삭제하기</button>
                       </div>
                    </c:if>
                    <c:if test="${info.proStatus == '예약중'}">
                       <div class="btn_modal">
                           <button class="btn_selected">판매중</button>
                           <button class="btn_nonsel">판매보류</button>
                           <button class="btn_nonsel">삭제하기</button>
                       </div>
                    </c:if>
                </div>
            </div>
        </body>