$(document).ready(function() {
	jQuery.noConflict(); // 충돌 방지
	
	$(".seller-chat").on("click", function() {
		if(checkNick == '') {openLogin(); return false;}
		const loginUserId = $(".seller-chat").attr("loginUserId");
		const sellerId = $(".seller-chat").attr("sellerId");
		const sellSeq = $(".seller-chat").attr("sellSeq");
		
		sellerChat(loginUserId, sellerId, sellSeq);
	});
	
	
	$('.product_slider').slick({
		  infinite: true,
		  slidesToShow: 1,
		  slidesToScroll: 1
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
        $('html, body').animate({scrollTop: 0}, 200);
    });
    
     $('#top_img').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({scrollTop: 0}, 200);
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


	
function payForSell(payName, amount, sellSeq, sellerId) {
	console.log("id : " + id);
	if(id == 'admin') {openLogin(); return false;}
	$.ajax({
	    url: '/heehee/pay/before',
	    method: 'POST',
	    data: {
	    	"payName" : payName,
	    	"amount" : amount,
	    	"sellSeq" : sellSeq,
	    	"sellerId" : sellerId
	    	},
	    success: function (data, status, xhr) {
	    	payment(payName, data);
	    },
	    error: function (data, status, err) {
	    	console.log(err);
	    }
	});
}

function payment(payName, payInfo) {
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
			    buyer_addr: payInfo.buyerTel, //buyerAddr
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
			  }, function (rsp) {
			    if(rsp.success) {
			    	completePayment(payInfo);
			    	return true;
			    }
			  }
			);
}

function completePayment(payInfo) {
	$.ajax({
        url: '/heehee/pay/complete',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ "paySeq": payInfo.paySeq }),
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);
            console.log(payInfo.buyerId);
            console.log(payInfo.sellSeq);
            showTost("☑️ 결제가 완료되었습니다!");
            fetch("/heehee/chatting/reserve",{
                method : "POST",
                headers : {"Content-Type": "application/json"},
                body : JSON.stringify({
                "buyerId" : payInfo.buyerId,
                "productSeq" : payInfo.sellSeq
                })
            })
            .then(resp => resp.text())
            .then(result => payAlarm(payInfo.sellerId, payInfo.sellSeq))
            .catch(err => console.log(err));
            setTimeout(() => window.location.reload(), 1000);
            //return true;
        },
        error: function (data, status, err) {
            console.log(err);
        }
    });
    
}


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

// 구매자가 결제 완료 시 판매자에게 알림 보내기 
function payAlarm(userId, prodSeq) {
    stompClient.send("/app/alarm/"+ userId, {}, JSON.stringify({'cateNum': 2, 'reqSeq': prodSeq, 'alContent': "등록하신 중고물품이 판매되었습니다."}));
}


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