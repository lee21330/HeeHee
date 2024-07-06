<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/myPageModal.css">
<body>
    <!-- <button id="phoneNum">전화번호 수정하기</button> -->
    <div id="phoneModal" class="myModal hide">
        <div class="myModal_body">
            <img
                src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                alt="전화번호 수정 창 닫기 아이콘" id="phone_close">
            <div class="btn_modal">
                <button type="submit" id="phone_submit">수정하기</button>
                <input type="button" id="phone_cancel" value="취소하기">
            </div>
        </div>
    </div>
    <script>
        $(function() {
            $("#phoneNum").on("click", function() {
                $("#phoneModal").removeClass("hide");
                $("#phoneModal").addClass("show");
                $("body").css("overflow", "hidden"); // 모달이 열렸을 때 스크롤 금지
            });
            $("#phone_close").on("click", function() {
                console.log("타??");
                console.log($("#phoneModal"));
                $("#phoneModal").addClass("hide");
                $("#phoneModal").removeClass("show");
                
                $("body").css("overflow", "auto"); // 모달이 닫혔을 때 스크롤 가능
            });
        });
    </script>
</body>
