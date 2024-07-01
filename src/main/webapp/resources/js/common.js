function showTost(txt) {
	let tostMessage = $("#tost_message");
	
	tostMessage.text(txt);
	tostMessage.addClass("active");
	
	setTimeout(function() {
		tostMessage.removeClass("active");
	}, 1000);
	
	// throw new Error("stopExecution");
}

// 나중에 리팩토링 필요. (중복 코드 제거)
function payForSell(payName, amount, sellSeq) {
	$.ajax({
	    url: '/heehee/pay/before',
	    method: 'POST',
	    data: {
	    	"payName" : payName,
	    	"amount" : amount,
	    	"sellSeq" : sellSeq
	    	},
	    success: function (data, status, xhr) {
	    	payment(payName,data);
	    },
	    error: function (data, status, err) {
	    	console.log(err);
	    }
	});
}

function payForAuc(payName, amount, aucSeq) {
	$.ajax({
	    url: '/heehee/pay/before',
	    method: 'POST',
	    data: {
	    	"payName" : payName,
	    	"amount" : amount,
	    	"aucSeq" : aucSeq
	    	},
	    success: function (data, status, xhr) {
	    	payment(payName,data);
	    },
	    error: function (data, status, err) {
	    	console.log(err);
	    }
	});
}

function payForPoint(payName, amount) {
	$.ajax({
	    url: '/heehee/pay/before',
	    method: 'POST',
	    data: {
	    	"payName" : payName,
	    	"amount" : amount
	    	},
	    success: function (data, status, xhr) {
	    	payment(payName,data);
	    	return true;
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
			  }, function (rsp) {
			    if(rsp.success) {
			    	completePayment(payInfo.paySeq);
			    	return true;
			    }
			  }
			);
}

function completePayment(paySeq) {
	$.ajax({
        url: '/heehee/pay/complete',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ "paySeq": paySeq }),
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);
            return true;
        },
        error: function (data, status, err) {
            console.log(err);
        }
    });
}

function inputPhoneNumber( phone ) {
    if( event.keyCode != 8 ) {
        const regExp = new RegExp( /^[0-9]{2,3}-^[0-9]{3,4}-^[0-9]{4}/g );
        if( phone.value.replace( regExp, "").length != 0 ) {                
            if( checkPhoneNumber( phone.value ) == true ) {
                let number = phone.value.replace( /[^0-9]/g, "" );
                let tel = "";
                let seoul = 0;
                if( number.substring( 0, 2 ).indexOf( "02" ) == 0 ) {
                    seoul = 1;
                    phone.setAttribute("maxlength", "12");
                    console.log( phone );
                } else {
                    phone.setAttribute("maxlength", "13");
                }
                if( number.length < ( 4 - seoul) ) {
                    return number;
                } else if( number.length < ( 7 - seoul ) ) {
                    tel += number.substr( 0, (3 - seoul ) );
                    tel += "-";
                    tel += number.substr( 3 - seoul );
                } else if(number.length < ( 11 - seoul ) ) {
                    tel += number.substr( 0, ( 3 - seoul ) );
                    tel += "-";
                    tel += number.substr( ( 3 - seoul ), 3 );
                    tel += "-";
                    tel += number.substr( 6 - seoul );
                } else {
                    tel += number.substr( 0, ( 3 - seoul ) );
                    tel += "-";
                    tel += number.substr( ( 3 - seoul), 4 );
                    tel += "-";
                    tel += number.substr( 7 - seoul );
                }
                return tel;
            } else {
                const regExp = new RegExp( /[^0-9|^-]*$/ );
                return phone.value.replace(regExp, "");
            }
        }
    }
}

function checkPhoneNumber( number ) {
    const regExp = new RegExp( /^[0-9|-]*$/ );
    if( regExp.test( number ) == true ) { return true; }
    else { return false; }
}