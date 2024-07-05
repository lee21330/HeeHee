$(document).ready(function() {
	jQuery.noConflict();
	

	$(".seller-chat").on("click", function() {
		const loginUserId = $(".seller-chat").attr("loginUserId");
		const sellerId = $(".seller-chat").attr("sellerId");
		const sellSeq = $(".seller-chat").attr("sellSeq");
		
		sellerChat(loginUserId, sellerId, sellSeq);
	});
	
	$(document).ready(function() {
	
		$('.product_slider').slick({
			  infinite: true,
			  slidesToShow: 1,
			  slidesToScroll: 1
			});
	});

    // URL을 클립보드에 복사하는 함수
    function copyToClipboard(text) {
        var $temp = $('<input>');
        $('body').append($temp);
        $temp.val(text).select();
        document.execCommand('copy');
        $temp.remove();
        alert('URL이 복사되었습니다.');
    }

    // URL 복사를 위한 이벤트 리스너
    $('#url_copy').on('click', function(e) {
        e.preventDefault();
        var link = window.location.href; // 현재 페이지의 URL을 가져옵니다
        copyToClipboard(link); // URL을 복사하는 함수를 호출합니다
    });

    // 맨 위로 스크롤하는 이벤트 리스너
    $('#gotop').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({scrollTop: 0}, 500);
    });
    
    
    $('.input_file').on('change', function() {
        var input = this;
        var index = $('.input_file').index(this) + 1;
        var file = input.files[0];
        
        if (file) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#prv_img' + index).attr('src', e.target.result);
            }
            reader.readAsDataURL(file);
        }
    });
    
    $('.input_file').on('change', function() {
        var input = this;
        var index = $('.input_file').index(this) + 1;
        var file = input.files[0];
        
        if (file) {
            var fileType = file.type;
            var validImageTypes = ["image/gif", "image/jpeg", "image/png", "image/jpg"];
            if ($.inArray(fileType, validImageTypes) < 0) {
                // 파일 타입이 이미지가 아닌 경우
                alert('이미지 파일만 선택 가능합니다.');
                resetInput(input, index);
            } else {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#prv_img' + index).attr('src', e.target.result);
                }
                reader.readAsDataURL(file);
            }
        } else {
            // 파일 선택이 취소된 경우
            resetInput(input, index);
        }
    });

    function resetInput(input, index) {
        $(input).val(''); // 선택한 파일 초기화
        $('#prv_img' + index).attr('src', '/heehee/resources/images/picture.png'); // 기본 이미지로 되돌림
    }
    
    
    $("img[id='preview']").click(function () {
        $("#input_file").click();
    });

    // 슬라이더 초기화
    $('.product-images').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 1
    });
});


$(function() {
			$(".category_list li").mouseenter(function() {
			 	var categoryName = $(this).text();
				$(".detail_category .category_name p").text(categoryName); 
				$(".detail_category").show();
				$(".category_list li").css({
					"background" : "white",
					"color" : "black"
				});
				$(this).css({
					"background" : "#3F51A1",
					"color" : "white"
				});
			});
});
		


//판매자와 채팅하기
function sellerChat(loginUserId, sellerId, sellSeq){
     fetch("/heehee/chatting/seller",{
             method : "POST",
             headers : {"Content-Type": "application/json"},
             body : JSON.stringify({"loginUserId" : loginUserId,
                                    "sellerId" : sellerId,
                                    "sellSeq" : sellSeq})
            })
            .then(resp => resp.text())
            .then(result => {
                console.log(result);
                if (result > 0) {
                    window.location.href = "/heehee/chatting";
                }
            })
            .catch(err => console.log(err));
}
