<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" />

        <link rel="stylesheet" href="${path}/resources/css/proStatusModifyModal.css">

        <body>
<script>
    $(function () {
    	$(".btn_submit").on("click", function(){
    		console.log("테스트");
            var selectedRating = $('#ratingInput').val();
            var productSeq = $('#ratingProdSeq').val();
            var sellerId = $('#ratingId').val();

            $.ajax({
                type: "POST", 
                url: "/heehee/mypage/rating",
                data: { "userRating": selectedRating,
                		"productSeq": productSeq,
                		"sellerId": sellerId},
                success: function(response) {
                	console.log(response);
                    if(response.success) {
                    	alert('평점 등록에 성공했습니다.');
                    	location.href = "/heehee/mypage/main";
                    } else {
                    	alert('평점 등록에 실패했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    console.log('평점 전송 실패:', error);
                    alert('평점 등록에 실패했습니다.');
                }
            });
    	});
    	
        $("#accuracy").on("click", show); // 이게 모달 생기는 코드!!!
        $(".rModal_close").on("click", hide);

        function show() {
            $("#aModal").addClass("show");
            $("body").css("overflow", "hidden");
        }

        function hide() {
            $("#aModal").removeClass("show");
            $("body").css("overflow", "scroll");
        }

        var selectedRating = 0;

        $(".stars").on("mousemove", function (e) {
            var $star = $(this);
            var starWidth = $star.width();
            var x = e.pageX - $star.offset().left; // 별의 시작 위치로부터 마우스 포인터까지의 거리

            var starId = parseInt(this.id.replace('rate', ''), 10);
            $(".stars").each(function (index) {
                var currentId = index + 1;
                if (currentId <= starId) {
                    var src = "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png";
                    $(this).attr("src", src);
                } else {
                    $(this).attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png");
                }
            });
        });

        $(".stars").on("mouseleave", function () {
            updateStars();
        });

        $(".stars").on("click", function (e) {
            var $star = $(this);
            var starWidth = $star.width();
            var x = e.pageX - $star.offset().left; // 클릭 지점 계산

            var starId = parseInt(this.id.replace('rate', ''), 10);
            
            selectedRating = starId;

            console.log(selectedRating);
            updateStars(); // 클릭 이벤트 후 모든 별을 업데이트
            $("#ratingInput").val(selectedRating);
        });

        function updateStars() {
            $(".stars").each(function (index) {
                var starNum = index + 1;
                if (starNum <= Math.floor(selectedRating)) {
                    $(this).attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star1.png");
                } else if (starNum - 0.5 === selectedRating) {
                    $(this).attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star05.png");
                } else {
                    $(this).attr("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png");
                }
            });
            
        }
    });
</script>

            <div class="rModal" id="aModal">
                <div class="rModal_body">
                    <img src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
                       alt="로그인 창 닫기 아이콘" class="rModal_close">

                       <p id="rate_title">판매자 평점 주기</p>
                       <div id="star_wrap">
                       		<img class="stars" id="rate1" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
                       		<img class="stars" id="rate2" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
                       		<img class="stars" id="rate3" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
                       		<img class="stars" id="rate4" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
                       		<img class="stars" id="rate5" src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/star0.png">
                       </div>
                       <input type="hidden" id="ratingInput">
                       <input type="hidden" id="ratingProdSeq">
                       <input type="hidden" id="ratingId">
                       <div class="rate_wrap">
                           <button class="btn_submit">등록하기</button>
                       </div>
                </div>
            </div>
        </body>