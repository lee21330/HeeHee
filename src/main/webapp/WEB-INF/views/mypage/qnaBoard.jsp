<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.servletContext.contextPath}"/>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>1:1문의</title>
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <link rel="stylesheet" href="${path}/resources/css/qnaBoard.css">
        </head>

        <body>
           
            <header>
                <%@include file="../common/header.jsp" %>
            </header>
            <div class="main_container">
                <div>
                    <h1>1:1 Q&A</h1>
                    <p> 제품 사용, 오염, 전용 박스 손상, 라벨 제거, 사은품 및 부속 사용/분실 시, 교환/환불이 불가능 합니다.</p>
                    <p>고객님의 주문내역을 선택, 질문이 필요한 상품을 선택하시면 1:1상담이 가능합니다.</p>
                    <p>주문취소/교환/환불은 마이페이지>주문내역에서 신청하실 수 있습니다.</p>
                    <p>1:1문의 처리 내역은 마이페이지>1:1문의를 통해 확인하실수 있습니다.</p>
                    <p>상품 정보(사이즈, 실측, 예상 배송일 등) 관련 문의는 해당 상품 문의에 남기셔야 빠른 답변이 가능합니다.</p>
                </div>
                <form>
                    <div class="qnaboard">
                        <div class="qna">
                            <h1>1:1문의하기</h1>
                            
                            <div class="qna_item">
                                <p>문의유형</p>
                                <div class="type">
                                    <div class="radioContainer">
                                        <input type="radio" id="delivery" name="typeSelect">
                                        <label for="delivery" class="radioLabel">배송</label>
                                    </div>
                                    <div class="radioContainer">
                                        <input type="radio" id="order" name="typeSelect">
                                        <label for="order" class="radioLabel">주문/결제</label>
                                    </div>
                                    <div class="radioContainer">
                                        <input type="radio" id="user" name="typeSelect">
                                        <label for="user" class="radioLabel">회원정보</label>
                                    </div>
                                    <div class="radioContainer">
                                        <input type="radio" id="product" name="typeSelect">
                                        <label for="product" class="radioLabel">상품확인</label>
                                    </div>
                                    <div class="radioContainer">
                                        <input type="radio" id="cancle" name="typeSelect">
                                        <label for="cancle" class="radioLabel">취소/교환/환불</label>
                                    </div>
                                    <div class="radioContainer">
                                        <input type="radio" id="service" name="typeSelect">
                                        <label for="service" class="radioLabel">서비스</label>
                                    </div>
                                </div>
                                <input type="text" id="qna_type">
                            </div>
                            <div class="qna_item">
                                <p>문의내용</p>
                                <textarea></textarea>
                            </div>
                            <div class="qna_item">
                                <p>첨부파일</p>
                                <img id="img_preview"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/file.jpg">
                                <input type="file" id="input_file">
                            </div>

                        </div>
                        <div class="faq">
                            <h1><a href="${path}/faqBoard">Best FAQ</a></h1>
                            
                            <div class="faq_header">
                                <img id="img_q"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
                                [배송] 배송조회가 정상적으로 되지 않는다면?
                            </div>
                            <div class="faq_content">저도 방법을 모르겠어요.</div>

                            <div class="faq_header"><img id="img_q"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
                                [주문/결제] 결제방식을 바꾸고 싶어요.
                            </div>
                            <div class="faq_content">저도 방법을 모르겠어요.</div>
                            <div class="faq_header"><img id="img_q"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
                                [취소/교환/환불] 잘못 결제한 상품은 어떻게 취소하나요?
                            </div>
                            <div class="faq_content">저도 방법을 모르겠어요.</div>
                            <div class="faq_header"><img id="img_q"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
                                [회원정보] 개인정보 변경 페이지는 어디에 있나요?
                            </div>
                            <div class="faq_content">저도 방법을 모르겠어요.</div>
                            <div class="faq_header"><img id="img_q"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
                                [상품확인] 원하는 상품을 검색하고 싶어요.
                            </div>
                            <div class="faq_content">저도 방법을 모르겠어요.</div>
                            <div class="faq_header"><img id="img_q"
                                    src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/emoticon_Q.png">
                                [서비스] 적립금은 어떻게 사용하나요?
                            </div>
                            <div class="faq_content">저도 방법을 모르겠어요.</div>
                        </div>
                    </div>
                    <div class="btn">
                        <button class="btn_cancel">취소</button>
                        <button class="btn_submit">작성하기</button>
                    </div>

                </form>
            </div>

            <script>
                $(function () {
                    $("#img_preview").click(function () {
                        $("#input_file").click();
                    });

                    $(".faq_content").hide();
                    $(".faq_header").on("click", show);
                    function show() {
                        $(".faq_content").hide();

                        $(this).next().show();
                    }
                    $(".type li").on("click", select);
                    function select() {
                        var qnaType = $(this).text();
                        $("#qna_type").val(qnaType);
                        $(this).css({
                            "background" : "gray"
                        });
                    }
                });
            </script>
        </body>

        </html>